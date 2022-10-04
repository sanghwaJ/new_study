# Java - for-loop vs Stream (+ forEach)

## 1. Stream
- Java8부터 지원된 기능으로, Collection에 저장되어 있는 Element들을 하나씩 순회하며 처리할 수 있는 코드 패턴으로, for-loop와 유사한 기능
- 주로 Lambda식과 함께 사용되며, Collection에 들어있는 데이터에 대한 처리를 간결하게 표현할 수 있으며, 주로 객체에 Method Chaining을 하며 사용
- 내부 반복자를 사용하여 병렬 처리가 쉬운 장점이 있음

## 2. for-loop와 Stream의 성능
- 결론부터 말하면, for-loop가 Stream보다 훨씬 빠름
- for-loop가 Stream보다 더 빠른 이유
  - for-loop는 단순 인덱스 기반으로, Stream에 비해 빠르고 오버헤드도 없음
  - Stream은 Java8부터 지원했기 때문에, for-loop에 비해 상대적으로 최적화가 덜 되어 있음
- 특히, 원시 데이터(int, String 등)를 반복문으로 처리할 때, for-loop가 Stream에 비해 성능적으로 훨씬 뛰어남

## 3. Stream을 사용하는 이유

### 3-1. 가독성
- Stream API에 포함되어 있는 여러 메소드를 이용하여 코드를 더욱 간결하게 작성할 수 있음

### 3-2. 코드로 작성해야하는 로직을 Stream에서 제공하는 메소드로 간단하게 해결 가능
- Stream은 단순 forEach를 사용할 때보다, filter나 reduce와 같은 메소드를 함께 사용하면 더욱 효과적임

### 3-3. 유지보수의 용이성
- Stream의 사용이 for-loop보다 성능적으로 의미가 있으려면 loop를 도는 대상 Collection의 크기가 매우 크거나, loop를 돌면서 진행하는 연산이 복잡하거나(비용이 비싸거나), 병렬 처리를 통한 이점이 있어야 함
- 그럼에도 Stream은 유지보수의 용이성 측면과 개발자들의 매끄러운 협업을 이유로 많이 사용되고 있음

## 4. for-loop와 forEach

```java
List<String> list = Arrays.asList("A","B","C","D","E");

// 1. 기존 for-loop
for (int i = 0; i < list.size(); ++i) {
	String var = list.get(i);
}

// 2. 향상된 for-loop (forEach)
for (String var : list) {
	// var는 list.get(i)와 같은 역할
}

/* 2번의 실제 동작
Iterator<String> it = list.iterator();

// 다음 객체가 존재하면
while (it.hasNext()) {
	//다음 객체를 반환
  String var = (String) it.next(); 
}
*/
```

### 4-1. 성능에 따라 사용
- ArrayList나 Array를 loop 도는 경우엔 for-loop 성능이 더 우수하나, LinkedList를 loop 도는 경우 forEach의 성능이 더 우수함

### 4-2. 용도에 따라 사용
- for-loop는 index를 사용하며, forEach는 내부적으로 iterator를 사용함 
- 따라서, index를 사용하지 않고 단순히 자료구조를 순회하는 용도라면 순회하는 도중 데이터 변조의 확률이 낮은 forEach를 사용하는 것이 좋음

---

참고 1 : https://jypthemiracle.medium.com/java-stream-api%EB%8A%94-%EC%99%9C-for-loop%EB%B3%B4%EB%8B%A4-%EB%8A%90%EB%A6%B4%EA%B9%8C-50dec4b9974b

참고 2 : https://pamyferret.tistory.com/49

참고 3 : https://chs96.github.io/java/Java-%ED%96%A5%EC%83%81%EB%90%9C-for-%EB%AC%B8-for-each/

참고 4 : https://siyoon210.tistory.com/99