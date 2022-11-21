# Java - Set (HashSet, TreeSet, LinkedHashSet)

## 1. Set
- Set은 List와 다르게 데이터를 중복해서 저장할 수 없음
- 기본적으로 데이터를 인덱스로 관리하지 않기 때문에 저장 순서가 보장되지 않으며, 데이터를 접근하기 위해서는 iterator() 메소드로 반복자를 생성하고 데이터를 가져오거나, 향상된 for문(ex. for (int val : set))으로 데이터를 가져와야 함
- 주요 메소드
  - add()
  - size()
  - remove()
  - clear()
  - iterator()

## 2. HashSet

- Set Collection을 구현하는 대표적인 클래스로, 데이터를 중복 저장할 수 없으며, 순서를 보장하지 않음

```java
public class HashSetEx01 {
  public static void main(String[] args) {
    Set<String> set = new HashSet<String>();
    set.add("one");
    set.add("two");
    set.add("three");
    set.add("one");
    set.add("two");
    set.add("4");
    set.add("5");
    set.add("six");
    
    System.out.println("저장된 데이터 수 : " + set.size()); // 6

    Iterator<String> it = set.iterator(); 
    
    // hasNext() : 데이터가 있으면 true 없으면 false
    while (it.hasNext()) { 
      // next() : 다음 데이터 리턴
      System.out.println(it.next()); 
    }

    set.remove("three"); 
    System.out.println("저장된 데이터 수 : " + set.size()); // 5

    // iterator 재생성
    it = set.iterator(); 
    
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
```
## 3. TreeSet

- HashSet과 같이 중복된 데이터를 저장할 수 없으며, 입력된 순서대로 값을 저장하지 않음
- 다만, 기본적으로 데이터를 오름차순으로 정렬함

```java
public class TreeSetEx01 {
  public static void main(String[] args) {
    Set<Integer> set = new TreeSet<Integer>();

    set.add(4); 
    set.add(2);
    set.add(1);
    set.add(3);
    set.add(1);
    set.add(3);

    Iterator<Integer> it = set.iterator(); 

    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("----------");

    Set<String> ts = new TreeSet<String>();
    ts.add("a");
    ts.add("c");
    ts.add("d");
    ts.add("b");

    Iterator<String> itr = ts.iterator();
    while (itr.hasNext()) {
      System.out.println(itr.next());
    }
  }
}
```

## 4. LinkedHashSet

- HashSet, TreeSet과 같이 중복된 데이터를 저장할 수 없고, 다만 입력된 순서대로 데이터를 관리함

```java
public class LinkedHashSetEx01 {
  public static void main(String[] args) {
    Set<String> set = new LinkedHashSet<String>();
    set.add("1");
    set.add("1");
    set.add("two");
    set.add("3");
    set.add("4");
    set.add("five");

    Iterator<String> it = set.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("--------------------------");

    LinkedHashSet<Integer> lh = new LinkedHashSet<Integer>();
    lh.add(1);
    lh.add(1);
    lh.add(4);
    lh.add(2);
    lh.add(3);
    Iterator<Integer> it2 = lh.iterator();

    while (it2.hasNext()) {
      System.out.println(it2.next());
    }
  }
}
```

## 5. 요약
- HashMap은 순서를 보장하지 않으나, 가장 빠르다.
- TreeSet은 데이터를 오름차순으로 정렬한다.
- LinkedHashSet은 "입력된 순서"를 보장한다.
  
---

참고 

https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=heartflow89&logNo=220994601249