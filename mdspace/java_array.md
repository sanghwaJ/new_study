# Java - Array

## 1. 배열의 존재 이유
- 같은 종류의 데이터를 효율적으로 관리하기 위해
- 같은 종류의 데이터를 순차적으로 저장하기 위해
- 장점
  - 빠른 접근 가능 (첫 데이터의 위치에서 상대적인 위치로 데이터 접근(인덱스 번호로 접근))
- 단점
  - 데이터 추가/삭제가 어려움
  - 미리 최대 길이를 지정해야 함

### ※ Primitive 자료형과 Wrapper 클래스
- Java에는 int와 Integer와 같이 Primitive 자료형과 Wrapper 클래스가 있음
- Wrapper 클래스는 Primitive 자료형과 달리 null 처리가 용이하며, ArrayList 등 객체만을 핸들링하는 기능을 사용할 수 있음

<br/>

## 2. Java와 배열
- 1차원 배열은 [ ]를 통해 선언할 수 있음
- 각 아이템은 { } 내에 콤마로 작성

```java
// new 키워드를 사용, 배열을 미리 선언하고 데이터를 넣을 수 있음
Integer[] data_list = new Interger[10];

data_list[0] = 1;

// 직접 배열 데이터를 선언하여 넣을 수도 있음
Integer data_list1[] = {1, 2, 3, 4, 5};
Integer[] data_list2 = {1, 2, 3, 4, 5};

System.out.println(data_list2[0]); // 1

// Arrays.toString : 배열의 내용 출력하기
System.out.println(Arrays.toString(data_list1)); 
```

<br/>

## 3. ArrayList
- 가변 길이의 배열 자료구조를 다룰 수 있는 클래스
  
```java
import java.util.ArrayList;

// int형 데이터를 담을 수 있는 가변 길이의 배열 선언
ArrayList<Integer> list = new ArrayList<Integer>();

// 아이템 추가
list.add(1);

// 배열의 특정 인덱스의 아이템 read
list.get(0);

// 특정 인덱스에 해당하는 아이템 변경
list.set(0, 10);

// 특정 인덱스에 해당하는 아이템 삭제
list.remove(0);
```

### 참고 : List와 ArrayList
```java
List<Integer> list1 = new ArrayList<Integer>();
ArrayList<Integer> list2 = new ArrayList<Interger>();
```
- List는 인터페이스, ArrayList는 클래스!
- 클래스는 "일반 클래스"와 클래스 내에 추상 메소드가 하나 이상 있거나 abstract로 정의된 "추상 클래스"로 나뉨
- 인터페이스는 모든 메소드가 추상 메소드인 경우를 의미하며, 인터페이스를 상속받는 클래스는 인터페이스에서 정의된 추상 메소드를 모두 구현해야한다. (따라서, 다양한 클래스를 상속받는 특정 인터페이스는 결국 동일한 메소드를 제공한다.)
```java
List<Integer> list = new ArrayList<Integer>();
list = new LinkedList<Integer>();
```
- 따라서, 위와 같이 ArrayList가 아니라 List로 선언된 변수는 다음과 같이 필요에 따라 다른 리스트 클래스를 쓸 수 있는 구현상의 유연성을 제공한다.

<br/>

## 4. 다차원 배열
### 4-1. 2차원 배열
```java
Integer list[][] = {{1, 2, 3}, {4, 5, 6}};

System.out.println(list[0][1]); // 2
System.out.println(list[1][1]); // 5
```
### 4-2. 3차원 배열
```java
Integer list[][][] = {
    {
        {1, 2, 3},
        {4, 5, 6}
    },
    {
        {7, 8, 9},
        {10, 11, 12}
    }
};

System.out.println(list[0][1][1]); // 5
System.out.println(list[1][1][1]); // 11
```
### 연습문제
- 해당 데이터 셋에서 M 문자를 가지고 있는 아이템 수 출력
```java
String dataset[] = {
    "Braund, Mr. Owen Harris",
    "Cumings, Mrs. John Bradley (Florence Briggs Thayer)",
    "Heikkinen, Miss. Laina",
    "Futrelle, Mrs. Jacques Heath (Lily May Peel)",
    "Allen, Mr. William Henry",
    "Moran, Mr. James",
    "McCarthy, Mr. Timothy J",
    "Palsson, Master. Gosta Leonard",
    "Johnson, Mrs. Oscar W (Elisabeth Vilhelmina Berg)",
    "Nasser, Mrs. Nicholas (Adele Achem)",
    "Sandstrom, Miss. Marguerite Rut",
    "Bonnell, Miss. Elizabeth",
    "Saundercock, Mr. William Henry",
    "Andersson, Mr. Anders Johan",
};
Integer cnt = 0;

for(int i =0; i < dataset.length; i++){
    // .indexOf() : 해당 값이 존재하면 true, 아니면 false
    if(dataset[i].contains("M")){
        cnt += 1;
    }
}

System.out.println(cnt);
```