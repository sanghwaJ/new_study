# Java - Generic

## 1. Generic
- 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있도록 하는 방법
- 아래와 같이 ArrayList나 LinkedList 등을 생성할 때 쓰는 것이 Generic

```java
ArrayList<Integer> list1 = new ArrayList<Integer>();
ArrayList<String> list2 = new ArrayList<Integer>();
 
LinkedList<Double> list3 = new LinkedList<Double>():
LinkedList<Character> list4 = new LinkedList<Character>();
```

### 1-1. Generic의 장점
  - 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지해줌
  - 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환해 줄 필요가 없어 관리하기가 편함
  - 비슷한 기능을 지원하는 경우 코드의 재사용성이 높아짐

### 1-2. Generic 사용 방법
- \<T> : Type
- \<E> : Element
- \<K> : Key
- \<V> : Value
- \<N> : Number
- 반드시 위와 같이 사용되는 것은 아니지만, 통용되는 작성 방법이기 때문에 규칙을 지키도록 함

## 2. Generic의 사용

### 2-1. Generic 선언

```java
package Generic1;

public class man <T> {
	//이름 필드
	private T name;
	
	//혈액형 필드
	private T bloodtype;
	
	public T getName() {
		return name;
	}
	public void setName(T name) {
		this.name = name;
	}
	public T getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(T bloodtype) {
		this.bloodtype = bloodtype;
	}
}
```

### 2-2. Generic 실행

```java
package Generic1;

public class mainGe {

	public static void main(String[] args) {
		man<String> man1 = new man<>();
		//새로운 객체 생성시 원하는 타입을 부여
		
		man1.setName("King");
		man1.setBloodtype("A");
		//선언시 스트링 타입으로 선언하여 스트링 데이터를 입력
		
		System.out.println(man1.getName());
		System.out.println(man1.getBloodtype());
	}
}
```

### 2-3. 멀티 Generic 선언

```java
package Generic1;

public class man <T, T2> {
	//이름 필드
	private T name;
	//혈액형 필드
	private T bloodtype;
	
	private T2 age;
	
	public T getName() {
		return name;
	}
	public void setName(T name) {
    	this.name = name;
	}
	public T getBloodtype() {
    	return bloodtype;
	}
	public void setBloodtype(T bloodtype) {
    	this.bloodtype = bloodtype;
	}
	public T2 getAge() {
    	return age;
	}
	public void setAge(T2 age) {
    	this.age = age;
	}
}
```

### 2-4. 멀티 Generic 실행

```java
package Generic1;

public class mainGe {

	public static void main(String[] args) {
		//새로운 객체 생성시 원하는 타입을 부여
		man<String, Integer> man1 = new man<>();
		
		man1.setName("King");
		man1.setBloodtype("A");
		man1.setAge(25);
		
		System.out.println(man1.getName());
		System.out.println(man1.getBloodtype());
		System.out.println(man1.getAge());
	}
}
```

### 2-5. Generic 메소드
- 매개 변수의 타입과 리턴 타입을 Generic으로 받는 메소드

```java
package Generic1;

public class Info {
	public static <T, T2> void info(T t, T2 t2){
		// 타입 파라미터  / 리턴타입 파라미터 or void / 메소드명 매개변수 타입
		man<T, T2> man2 = new man<>();
		man2.setName(t);
		man2.setAge(t2);
		
		System.out.println("나의 이름은 " + man2.getName() + " 입니다.");
		System.out.println("나이는 " + man2.getAge() + " 입니다.");
	}
}
```
```java
package Generic1;

public class mainGeneric {

	public static void main(String[] args) {
		
		Info.info("King", 25);
	}
}
```

## 3. 제한된 Generic과 와일드 카드
- extends(상한 경계)와 super(하한 경계), ?(와일드 카드)를 통해 Generic을 특정 범위 내로 좁혀 제한하여 사용할 수 있음
```java
// T와 T의 자손 타입만 가능 (K는 들어오는 타입으로 지정)
<K extends T>
// T와 T의 부모(조상) 타입만 가능 (K는 들어오는 타입으로 지정)
<K super T>

// T와 T의 자손 타입만 가능
<? extends T>
// T와 T의 부모(조상) 타입만 가능
<? super T>
// 모든 타입 가능 
<?> // = <? extends Object>
```

### 3-1. \<K extends T>, \<? extends T>
- T 타입을 포함한 자손 타입만 가능
```java
<T extends B>	// B와 C타입만 올 수 있음
<T extends E>	// E타입만 올 수 있음
<T extends A>	// A, B, C, D, E 타입이 올 수 있음
 
<? extends B>	// B와 C타입만 올 수 있음
<? extends E>	// E타입만 올 수 있음
<? extends A>	// A, B, C, D, E 타입이 올 수 있음
```

### 3-2. \<K super T>, \<? super T>
- T 타입의 부모(조상) 타입만 가능
```java
<K super B>	// B와 A타입만 올 수 있음
<K super E>	// E, D, A타입만 올 수 있음
<K super A>	// A타입만 올 수 있음
 
<? super B>	// B와 A타입만 올 수 있음
<? super E>	// E, D, A타입만 올 수 있음
<? super A>	// A타입만 올 수 있음
```

### 3-3. \<?> 
- 와일드 카드 <?> 은 <? extends Object> 와 같은 의미
- 보통 데이터가 아닌 '기능'의 사용에만 관심 있는 경우 사용

---
참고

https://st-lab.tistory.com/153

https://seeminglyjs.tistory.com/184
