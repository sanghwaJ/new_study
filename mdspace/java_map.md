# Java - Map (HashMap, TreeMap, LinkedHashMap)

## 1. Map

```Java
Map<String, String> map1 = new HashMap<>();

HashMap<String, Object> map2 = new HashMap<>();
```

Map 선언은 위 두가지 경우 모두 가능한데, 객체 생성은 꼭 HashMap으로 해줘야한다.

그 이유는 Map은 인터페이스이기 때문에 선언만 가능하며, Child인 HashMap으로 객체를 생성하고, 부모인 Map의 메소드를 상속받게 된다.

또한, 데이터를 모아서 관리할 수 있는 Class를 Collection이라 하는데, 자바에서 제공하는 Collection의 예시는 List, Map, Set 등이 있다.

그 중에서 Map은 key와 value로 구성되는 Collection이며, 내부 구현 방식에 따라 HashMap, TreeMap, LinkedHashMap 등으로 나뉘게 된다.

<br/>

## 2. HashMap

- 내부적으로 Entry 배열을 만들어 관리하며, Key 값으로 넘겨준 객체의 HashCode를 계산하여 Entry 배열의 접근 인덱스로 사용<br/>
  ex) "key1"이라는 문자열의 HashCode 계산 결과가 10이면, 배열의 10번째 항목으로 value 저장
- HashCode를 계산하여 사용되기 때문에 순서가 없음
- 다른 Map에 비해 가장 빠른 탐색시간 (시간복잡도 : O(1))

<br/>

## 3. TreeMap

- key와 value를 RedBlack Tree로 저장하여 관리하기 때문에, 정렬된 상태로 유지할 수 있음
- 정렬된 순서는 Comparator를 통해 조정 가능
- 탐색시간 및 추가 삭제의 시간복잡도는 O(log N)

<br/>

## 4. LinkedHashMap

- key와 value를 Linked List로 관리하기 때문에 "입력된 순서"를 기억할 수 있음
- 탐색시간 및 추가 삭제의 시간복잡도는 O(1)

<br/>

## 5. 요약
- HashMap은 순서를 보장하지 않으나, 가장 빠르다.
- TreeMap은 순서를 보장하고 순서를 조정할 수 있으나, 가장 느리다.
- LinkedHashMap은 "입력된 순서"를 보장한다.

<br/>

## 6. Map Collection 주요 메소드
```Java
// Map 선언
HashMap<String, Object> hashmap = new HashMap<>();

// Map 복사
HashMap<String, Object> copiedHashmap = new HashMap<>(hashmap);

// key, value 추가
hashmap.put("Java", 1);

// key 해당하는 value 제거
hashmap.remove("Java"); 

// 모든 값 제거
hashmap.clear();

// key에 해당하는 value 변경
hashmap.replace("Java", 10);

// key에 해당하는 value 리턴
hashmap.get("Java");

// 모든 key 리턴
hashmap.keySet(); 

// 모든 value 리턴
hashmap.values();

// key 존재 여부 확인 (있으면 true, 없으면 false)
hashmap.containsKey("Java");

// value 존재 여부 확인 (있으면, true, 없으면 false)
hashmap.containsValue(1);

// hashmap empty 여부 확인 (empty면 true, 아니면 false)
hashmap.isEmpty();

// key에 해당하는 값("Java")이 없으면 defaultValue(-1) 반환
hashmap.getOrDefault("Java", -1); 
```