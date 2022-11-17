# Java - ConcurrentHashMap (+ HashMap, HashTable)

## 1. HashMap

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    public V get(Object key) {}
    public V put(K key, V value) {}
}
```

- HashMap 클래스는 가장 기본적으로 사용되며, synchronized 키워드가 존재하지 않기 때문에 Map 인터페이스를 구현한 클래스 중에서 성능이 가장 좋음
- But, synchronized 키워드가 존재하지 않기 때문에 Multi-Thread 환경에서 사용할 수 없음

## 2. HashTable

```java
public class Hashtable<K,V>
    extends Dictionary<K,V>
    implements Map<K,V>, Cloneable, java.io.Serializable {

    public synchronized int size() { }

    @SuppressWarnings("unchecked")
    public synchronized V get(Object key) { }

    public synchronized V put(K key, V value) { }
}
```

- Hashtable 클래스의 대부분의 메소드에는 synchronized 키워드가 존재하기 때문에 Multi-Thread 환경에서 사용이 가능함 (메소드 전체가 임계구역으로 설정됨)
- But, 동시에 작업을 하려해도 객체마다 Lock을 하나씩 가지고 있기 때문에 동시에 여러 작업을 해야할 때 병목현상이 발생할 수 있음 (메소드에 접근하게 되면 다른 쓰레드는 Lock을 얻을 때까지 기다려야 하기 때문)
- 따라서, Hashtable 클래스는 Thread-safe 하다는 특징이 있긴 하지만, 위와 같은 특징 때문에 Multi-Thread 환경에서 사용하기에 살짝 느리다는 단점이 있어 최근에 잘 사용되지 않음
 
## 3. ConcurrentHashMap 

```java
public class ConcurrentHashMap<K,V> extends AbstractMap<K,V>
    implements ConcurrentMap<K,V>, Serializable {

    public V get(Object key) {}

    public boolean containsKey(Object key) { }

    public V put(K key, V value) {
        return putVal(key, value, false);
    }

    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED)
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
}
```

- Multi-Thread 환경에서 사용할 수 있도록 HashTable 클래스의 단점을 보완하여 나온 클래스
- ConcurrentHashMap 클래스를 보면 DEFAULT_CAPACITY, DEFAULT_CONCURRENCY_LEVEL이 16으로 설정되어 있는데, 여기서 DEFAULT_CAPACITY는 버킷의 수, DEFAULT_CONCURRENCY_LEVEL는 동시에 작업 가능한 쓰레드 수를 의미
- ConcurrentHashMap은 버킷 단위로 Lock을 사용하기 대문에 같은 버킷만 아니라면 Lock을 기다릴 필요가 없다는 특징이 있음
- 즉, 여러 쓰레드에서 ConcurrentHashMap 객체에 동시에 데이터를 삽입하고 참조하더라도 그 데이터가 다른 세그먼트에 위치하면 서로 Lock을 얻기위해 경쟁하지 않음
- 일반적으로 ConcurrentHashMap은 읽기 작업보다는 쓰기 작업에 성능이 중요한 상황에 쓰임
- 추가로, 읽기 작업(get())에는 동기화가 적용되지 않으므로 업데이트 작업(put(), remove())와 겹칠 수 있는데, 이 땐 가장 최근에 완료된 업데이트 작업의 결과가 반영됨

---

참고

https://devlog-wjdrbs96.tistory.com/269