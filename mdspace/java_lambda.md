# Java - Lambda

## 1. Lambda
- 익명함수를 지칭하는 용어로, 함수를 보다 단순하게 표현하는 방법
- 익명함수이기 때문에 함수의 이름이 없으며, 다른 객체들에 적용 가능한 모든 연산을 지원하는 일급객체의 특징을 가짐
- 함수를 값으로 사용할 수도 있고, 파라미터로 전달하거나 변수에 대입하는 등의 연산이 가능함

## 2. Lambda의 장단점

### 2-1. 장점
- 코드의 간결성 : 불필요한 반복문의 삭제가 가능하며, 복잡한 식을 단순하게 표현할 수 있음
- 지연연산 수행 : 지연연산을 수행하여 불필요한 연산을 최소화할 수 있음
- 병렬처리 기능 : 멀티쓰레딩을 활용, 병렬처리가 가능함

### 2-2. 단점
- 호출이 까다로움
- Lambda Stream에서 for문과 while문을 사용하면 성능이 떨어질 수 있음
- 불필요하게 너무 사용하면 가독성이 떨어질 수 있음

## 3. Lambda 표현
- 매개변수 화살표(->)를 함수몸체로 사용하여 표현
- 함수몸체가 단일 실행문이라면 괄호({})를 생략할 수 있음
- 함수몸체가 return문으로만 구성되어 있는 경우, 괄호({})를 생략할 수 있음

```java
// 정상적인 유형
() -> {}
() -> 1
() -> { return 1; }

(int x) -> x+1
(x) -> x+1
x -> x+1
(int x) -> { return x+1; }
x -> { return x+1; }

(int x, int y) -> x+y
(x, y) -> x+y
(x, y) -> { return x+y; }

(String lam) -> lam.length()
lam -> lam.length()
(Thread lamT) -> { lamT.start(); }
lamT -> { lamT.start(); }


// 잘못된 유형 선언된 type과 선언되지 않은 type을 같이 사용 할 수 없다.
(x, int y) -> x+y
(x, final y) -> x+y  
```

## 4. Lambda 예제
```java
// 일반 java 문법
new Thread(new Runnable() {
   @Override
   public void run() { 
      System.out.println("Welcome Heejin blog"); 
   }
}).start();

// Lambda식 문법
new Thread(()->{
      System.out.println("Welcome Heejin blog");
}).start();
```

## 5. 함수형 인터페이스
- @FunctionalInterFace : 구현해야할 추상 메소드 하나만 정의된 인터페이스를 가리키며, 함수형 인테페이스에 두 개 이상의 메소드가 선언되면 오류가 발생함

```java
// 일반적인 함수형 인터페이스

// 구현해야 할 메소드가 한개이므로 Functional Interface이다.
@FunctionalInterface
public interface Math {
    public int Calc(int first, int second);
}

// 오류인 경우 => 구현해야 할 메소드가 두개이므로 Functional Interface가 아니다. 
@FunctionalInterface
public interface Math {
    public int Calc(int first, int second);
    public int Calc2(int first, int second);
}
```

```java
// Lambda를 사용한 함수형 인터페이스

@FunctionalInterface
interface Math {
    public int Calc(int first, int second);
}

public static void main(String[] args){
   Math plusLambda = (first, second) -> first + second;
   System.out.println(plusLambda.Calc(4, 2));

   Math minusLambda = (first, second) -> first - second;
   System.out.println(minusLambda.Calc(4, 2));

}
```

## 6. java.util.function 인터페이스

|종류|매개값|리턴값|메서드형태|활용|
|:---:|:---:|:---:|:---:|:---:|
|Consumer|O|X|accept()|소비자, 매개값이 있고, 리턴값이 없음|
|Supplier|X|O|get~~~()|생산자, 매개값이 없고, 리턴값이 있음|
|Function|O|O|apply~~~()|매개값을 매핑(=타입변환)해서 리턴|
|Operator|O|O|apply~~~()|매개값을 연산해서 결과 리턴|
|Predicate|O|O|test()|매개값이 조건에 맞는지 확인해서 boolean 리턴|
### 6-1. IntFunction\<R>
- int형의 인수를 받아들이고 그 결과를 생성함
```java
IntFunction intSum = (x) -> x+1;
System.out.println(intSum.apply(1)); // 2
```

### 6-2. BinaryOperator\<T>
```java
BinaryOperator stringSum=(x, y)->x+" "+y;
System.out.println(stringSum.apply("Hello","World")); // Hello World
```

## 7. Stream API

### 7-1. Stream이란?
- Stream은 다양한 데이터를 표준화된 방법으로 다루기 위한 라이브러리이며, Java8부터 추가되었음
- Stream API는 아래와 같이 구성된다
    ```java
    example.stream().filter(x -> x < 2).count
    ```
    - stream() : Stream 생성
    - filter : 중간 연산(Stream 변환), 연속해서 수행 가능
    - count : 최종 연산(Stream 사용), 마지막에 단 한 번만 수행 가능

### 7-2. Stream의 특징
- 1회용으로, 데이터를 변경하지 않음
- 지연연산을 수행함
- 병렬실행이 가능함

### 7-3. Stream의 종류
|종류|내용|
|---|---|
|Stream \<T>|범용 Stream|
|IntStream|값 타입이 Int인 Stream|
|LongStream|값 타입이 Long인 Stream|
|DoubleStream|값 타입이 Double인 Stream|

### 7-4. Stream의 중간 연산 명령어
|종류|내용|
|---|---|
|Stream \<T> distinct()|Stream의 요소 중복 제거|
|Stream \<T> sorted()|Stream 요소 정렬|
|Stream \<T> filter(Predicate \<T> predicate)|조건에 충족하는 요소를 Stream으로 생성|
|Stream \<T> limit(long maxSize)|maxSize 까지의 요소를 Stream으로 생성|
|Stream \<T> skip(ling n)|처음 n개의 요소를 제외하는 stream 생성|
|Stream \<T> peek(Consumer\<T> action)|T타입 요소에 맞는 작업 수행|
|Stream \<R> flatMap(Function\<T, stream<? extends R>> Tmapper)|T타입 요소를 1:N의 R타입 요소로 변환하여 스트림 생성|
|Stream \<R> map(Function<? super T, ? extends R> mapper)|입력 T타입을 R타입 요소로 변환한 스트림 생성|
|Stream mapToInt(),mapToLong(),mapToDobule()|만약 map Type이 숫자가 아닌 경우 변환하여 사용|

### 7-5. Stream의 최종 연산 명령어
|종류|내용|
|---|---|
|void forEach(Consumer <? super T> action)|Stream 의 각 요소에 지정된 작업 수행|
|long count()|Stream 의 요소 개수|
|Optional \<T> sum (Comparator <? super T> comparator)|Stream 의 요소 합|
|Optional \<T> max (Comparator <? super T> comparator)|Stream 요소의 최대 값|
|Optional \<T> min (Comparator <? super T> comparator)|Stream 요소의 최소 값|
|Optional \<T> findAny()|Stream 요소의 랜덤 요소|
|Optional \<T> findFirst()|Stream 의 첫 번째 요소|
|boolean allMatch(Pradicate \<T> p)|Stream 의 값이 모두 만족하는지 boolean 반환|
|boolean anyMatch(Pradicate \<T> p)|Stream 의 값이 하나라도 만족하는지 boolean 반환|
|boolean noneMatch(Pradicate \<T> p)|Stream 의 값이 하나라도 만족하지않는지 boolean 반환|
|Object[] toArray()|Stream 의 모든 요소를 배열로 반환|

### 7-6. reduce 연산
- Stream의 요소를 하나씩 줄여가며 계산

|종류|내용|
|---|---|
|Optional \<T> reduce(Binary Operator\<T> accumulator)|.reduce((x,y) -> x > y ? x : y );|
|T reduce ( T identity, BinaryOperator\<T> accumulator)|.reduce(1, (x,y) -> x * y);|
|\<U> U reduce (U indentity, BiFunction\<U, ? super T, U> accumulator, BinaryOperator\<U> combiner)|.reduce(0.0, (val1, val2) -> Double.valueOf(val1 + val2 / 10), (val1, val2) -> val1 + val2);|

### 7-7. Stream API 예제

```java
// 간단한 짝수 판별
IntStream.range(1, 11 ).filter(i-> i%2==0)
      .forEach(System.out::println); 
```

```java
// 0~1000까지의 값 중 500이상이며 짝수이면서 5의 배수인 수의 합 구하기
System.out.println(
      IntStream.range(0, 1001)
            .skip(500)
            .filter(i-> i%2==0)
            .filter(i-> i%5==0)
            .sum()
);
```

```java
import java.util.*;
import java.util.stream.Collectors;

// List to Array
int[] array = list.stream().mapToInt(i->i).toArray();

// Array to List
List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
```
 
 
