# Java - Stream

## 1. Stream
- Java 8부터 지원되기 시작한 기능으로, Collection에 저장되어 있는 엘리먼트들을 하나씩 순회하며 처리할 수 있는 코드 패턴
- 주로 Lambda식과 함께 사용되며, Collection에 들어있는 데이터에 대한 처리를 간결하게 표현할 수 있음
- 내부 반복자를 사용하기 때문에 병렬 처리가 쉽다는 장점이 있음

```java
// iterator 객체를 이용하여 Collection 순회
ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));

Iterator<String> iterator = list.iterator();

while(iterator.hasNext()) {
    String value = iterator.next();

    if (StringUtils.equals(value, "b") {
      System.out.println("값 : " + value);
    }
}
```

```java
// forEach를 이용하여 Collection 순회
ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));

for (String value : list) {
    if (StringUtils.equals(value, "b") {
      System.out.println("값 : " + value);
  }
}
```

```java
// Java 8부터 추가된 stream을 이용하여 Collection 순회
ArrayList<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
list.stream()
    .filter("b"::equals)    
    .forEach(System.out::println);
```

## 2. Stream 사용법

### 2-1. Stream 생성 - Collection
- Stream을 사용하려면 우선 Stream 객체를 생성해야 함

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream(); // Collection 객체에서 stream() 메소드 호출
```

### 2-2. Stream 생성 - Array
- 정적 메소드인 Arrays.stream()에 인자로 배열을 넣어주면 배열을 순회하는 Stream 객체를 만들 수 있음
- Arrays.stream() 메소드에 배열, 시작 인덱스, 종료 인덱스를 인자로 주면 배열의 일부를 순회할 수도 있음

```java
String[] array = new String[]{"a", "b", "c"};
Stream<String> stream1 = Arrays.stream(array);
Stream<String> stream2 = Arrays.stream(array, 1, 3); // 인덱스 1포함, 3제외 ("b", "c")
```

### 2-3. Stream 생성 - Builder
- Lambda식을 이용하여 데이터를 생성하는 Stream을 만들 수 있음

```java
String<String> stream = Stream<String>builder()
                          .add("Apple")
                          .add("Banana")
                          .add("Melon")
                          .build();
```

### 2-4. Stream 생성 - Generator
- Collection이나 Array를 통해서 생성하는게 아닌, 직접 값을 입력하여 Stream 객체를 생성할 수도 있음

```java
public static<T> Stream<T> generate(Supplier<T> s) { ... }
// generate() 메소드에 인자로 "Hello"를 찍어주는 Lambda식
// limit()를 통해 해당 Stream이 "Hello"를 5번만 찍도록 제한 (limit()이 없으면 무한대로 찍음)
Stream<String> stream = Stream.generate(() -> "Hello").limit(5);
```

### 2-5. Stream 생성 - Iterator
- iterate() 메소드를 이용해서 수열 형태의 데이터를 생성할 수 있음

```java
// 초기 값 100부터 10씩 증가하는 숫자를 생성하는 stream
Stream<String> stream = Stream.iterate(100, n -> n + 10).limit(5); 
// (100, 110, 120, 130, 140)
```

### 2-6. Stream 생성 - Empty
- 특수하게 Empty Stream도 사용할 수 있으며, Stream 객체를 참조하는 변수가 null이라면 NullPointException이 발생할 수도 있음

```java
Stream<String> stream = Stream.empty();
```

### 2-7. Stream 생성 - 기본 타입
- Java에서는 기본 타입(원시 타입, Primitive Type)에 대해서 오토박싱과 언박싱이 발생하는데, 예를들어 int 변수를 다룰 때 Integer 클래스로 오토박싱해서 처리하는 경우에는 오버헤드가 발생해서 성능저하가 있을 수 있음 
- Stream 객체의 생성에서도 마찬가지인데, 오토박싱을 하지 않으려면 다음과 같이 사용하면 됨

```java
IntStream intStream = IntStream.range(1, 10); // 1 ~ 9
LongStream longStream = LngStream.range(1, 10000); // 1 ~ 9999
```

- 제네릭을 이용한 클래스로 사용하려면 아래와 같이 박싱해서 사용해야 함

```java
Stream<Integer> stream = IntStream.range(1, 10).boxed();
```

- 정해진 값이 아니라 랜덤 값을 Stream으로 뽑아내려면 Random() 클래스를 사용하면 됨

```java
DoubleStream stream = new Random().double(3); // double 형 랜덤 숫자 3개 생성
```

### 2-8. Stream 생성 - 문자열 Stream
```java
// 문자열의 각 char들의 ASCII 코드 값을 표현해주는 Stream
IntStream stream = "Hello,World".chars(); 
//(72, 101, 108, 108, 111, 44, 87, 111, 114, 108, 100)
```

- 특정 구분자를 통해 문자열을 Split하는 Stream

```java
Stream<String> stream = Pattern.compile(",").splitAsStream("Apple,Banana,Melon");
```

### 2-9. Stream 생성 - 파일
- 텍스트 파일을 읽어서 라인 단위로 처리

```java
// 'test.txt' 파일의 데이터를 라인단위로 읽어서 뽑아주는 Stream 객체
Stream<String> stream = 
        Files.lines(Paths.get("test.txt"), Charset.forName("UTF-8"));
```

### 2-10. Stream 생성 - Stream 연결
- 두 개의 Stream을 연결, 하나의 새로운 Stream으로 만들기

```java
Stream<String> stream1 = Stream.of("Apple", "Banana", "Melon");
Stream<String> stream2 = Stream.of("Kim", "Lee", "Park");

Stream<String> stream3 = Stream.concat(stream1, stream2);
// "Apple", "Banana", "Melon", "Kim", "Lee", "Park"
```

## 3. Stream 데이터 가공
- Stream 객체가 뽑아내는 데이터들에 대해 뭔가 작업을 해야하는 경우, 특정 데이터들만 걸러내거나 데이터에 대해서 가공을 할 수 있음

### 3-1. Filter
- Stream에서 뽑아져 나오는 데이터에서 특정 데이터들만 골라내는 역할
- filter() 메소드에는 boolean 값을 리턴하는 Lambda식을 넘겨주게되고, 데이터들에 대해 Lambda식을 적용해서 true가 리턴되는 데이터만 선별

```java
Stream<T> filter(Predicate<? super T> predicate);
```

```java
// 1부터 9까지의 Stream 데이터
Stream<Integer> stream = IntStream.range(1, 10).boxed();
// 그 중, 짝수만 return
stream.filter(v -> ((v % 2) == 0))
            .forEach(System.out::println);
// 2, 4, 6, 8
```

### 3-2. Map

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

- map() 메소드는 값을 변환해주는 Lambda식을 인자로 받고, Stream에서 생성된 데이터에 map() 메소드의 인자로 받은 Lambda식을 적용해 새로운 데이터를 생성

```java
Stream<Integer> stream = IntStream.range(1, 10).boxed();
// 짝수에다 10을 곱해서 return
stream.filter(v -> ((v % 2) == 0))
            .map(v -> v * 10)
            .forEach(System.out::println);
// 20, 40, 60, 80
```

### 3-3. flatMap

```java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```

- flatMap() 메소드의 인자로 받는 Lambda는 리턴 타입이 Stream인데, 즉 새로운 Stream을 생성해서 리턴하는 Lambda를 인자로 받음 
- flatMap()은 중첩된 Stream 구조를 한단계 적은 단일 Collection에 대한 Stream으로 만들어주는 역할을 하며, 이런 작업을 '플랫트닝(Flattening)'이라고 함

```java
List<List<String>> list = Arrays.asLists(Arrays.asList("A", "B", "C"),
                                          Arrays.asList("a", "b", "c"));

// 2중 구조 stream Flattening
List<String> flatList = list.stream()
                          // Lambda식 (e) → Collection.stream(e) 축약
                          .flatMap(Collection::stream) 
                          .collect(Collectors.toList());
// ["A", "B", "C", "a", "b", "c"]
```
### 3-4. Sorted

```java
// default는 오름차순
Stream<T> sorted(); 
// 정렬할 때 두 값을 비교하는 로직이 있다면, 아래의 comparator를 sorted() 메소드의 인자로 넘길 수 있음
Stream<T> sorted(Comparator<? super T> comparator); 
```

### 3-5. Peek
- Stream 내 엘리먼트들을 대상으로 map() 메소드처럼 연산을 수행하지만, 새로운 Stream을 생성하지는 않고 인자로 받은 Lambda식을 적용만 함 (데이터에 변형을 가하지 않음)
- 따라서 중간에 로깅을 하는 경우 Peek을 사용함

```java
Stream<T> peek(Consumer<? super T> action);

int sum = IntStream.range(1, 10)
            .peek(System.out::println)
            .sum();
```

## 4. Stream 결과 생성
- 앞선 Stream 데이터 가공은 결과를 또 다른 Stream 객체를 생성하여 단순히 return하는 방식이기 때문에, 그 값을 출력하거나 또 다른 Collection으로 모아두는 작업이 필요함

### 4-1. 통계 값
- Stream의 마무리는 '총합'을 구하거나 '최대값', '최소값', '숫자의 개수', '평균 값' 등에 대한 계산

```java
// 비어있는 Stream이라면 sum과 count는 0 return
int sum = IntStream.range(1, 10).sum();
int count = IntStream.range(1, 10).count();

// max와 min은 Optional을 이용해 return
int max = IntStream.range(1, 10).max();
int min = IntStream.range(1, 10).min();
int avg = IntStream.range(1, 10).average();

// 짝수 숫자의 총합
int evenSum = IntStream.range(1, 10)
                .filter(v -> ((v % 2) == 0))
                .sum();
```

### 4-2. Reduce
- 중간 연산을 거친 값들은 reduce 라는 메소드를 이용해 결과 값을 만들어내며, reduce() 메소드는 파라미터에 따라 3가지 종류가 있음

```java
// Stream에서 나오는 값들을 accumulator 함수로 누적
Optional<T> reduce(BinaryOperator<T> accumulator);

// 동일하게 accumulator 함수로 누적하지만 초기값(identity)이 있음
T reduce(T identity, BinaryOperator<T> accumulator);
```
- Stream에서 뽑아져 나오는 값들을 누적시키는 accumulator 함수는 2개의 파라미터를 인자로 받아 하나의 값을 리턴하는 함수형 인터페이스이기 때문에 아래와 같은 함수를 accumulator로 사용할 수 있음

```java
(a, b) -> Integer.sum(a, b);
```

- Stream은 때에 따라서 뽑아져 나오는 값이 없을 수 있기 때문에, reduce() 메소드의 리턴값은 Optional임 - 만약 초기값을 주는 reduce() 메소드를 사용한다면, 초기값이 있기 때문에 Optional이 아님

### 4-3. Collect
- Stream을 이용하는 가장 많은 패턴 중 하나는 Collection의 엘리먼트 중 일부를 필터링하고, 값을 변형해서 또 다른 Collection으로 만드는 것
- collect() 메소드를 이용해 뽑아져 나오는 데이터들을 Collection으로 모아 둘 수 있음

```java
// 1부터 999까지의 숫자 중 짝수만 Set Collection에 모아둠
Set<Integer> evenNumber = IntStream.range(1, 1000).boxed()
                                    .filter(n -> (n%2 == 0))
                                    .collect(Collectors.toSet());
```

- collect() 메소드는 Collector 클래스에 있는 정적 메소드를 이용해서 뽑아져나오는 객체들을 원하는 Collection으로 만들 수 있음 
- Collector.toList()를 호출하면 리스트로 만들고, Collector.toSet()을 호출하면 Set으로 만들어 줌
- Collector.joining()을 사용하면 작업한 결과를 하나의 문자열로 이어 붙일 수 있음

```java
List<String> fruit = Arrays.asList("Banana", "Apple", "Melon");
String returnValue = fruit.stream()
            .collect(Collectors.joining());

System.out.println(returnValue); // BananaAppleMelon

// Collector.joining() 메소드에 추가로 인자를 주는 경우
List<String> fruit = Arrays.asList("Banana", "Apple", "Melon");
String returnValue = fruit.stream()
            .collect(Collectors.joining(",", "<", ">")); // 구분자, prefix, suffix

System.out.println(returnValue); // <Banana,Apple,Melon>
```

### 4-4. foreach
- Stream에서 뽑아져 나오는 값에 대해서 어떤 작업을 하고 싶을 때 forEach 메소드를 사용하며, 앞에서 본 메소드들과 다르게 어떤 값을 리턴하지는 않음

```java
// 1부터 999까지의 숫자 중 짝수만 뽑아내서 출력
Set<Integer> evenNumber = IntStream.range(1, 1000).boxed()
                            .filter(n -> (n%2 == 0))
                            .forEach(System.out::println);
```