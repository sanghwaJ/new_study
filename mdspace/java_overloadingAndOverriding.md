# Java - Overloading & Overriding

## 1. 오버로딩(Overloading)

C언어와 같은 경우는 함수명이 고유하게 존재하며, 하나의 함수가 하나의 기능만을 구현해야한다.

하지만, 자바와 같은 경우는 하나의 메소드 이름으로 여러 기능을 구현하는 특징이 있는데, 이러한 특징을 오버로딩(Overloading)이라 한다.

즉, 자바의 한 클래스 내에 이미 사용하려는 이름과 같은 이름을 가진 메소드가 있더라도, 매개변수의 개수나 타입이 다르면 같은 이름을 사용해서 메소드를 정의할 수 있다. (대표적으로 pritln 메소드!)

<br/>

## 2. 오버로딩의 조건

메소드의 이름이 같고, 매개변수의 개수나 타입이 달라야 한다. 

여기서 주의할 점은 리턴 값만 다른 것은 오버로딩 할 수 없다. 

오버로딩의 예시는 아래와 같다.

```Java
class OverloadingTest {

	public static void main(String[] args) {
		OverloadingMethods om = new OverloadingMethods();

		om.print();
		System.out.println(om.print(3));
		om.print("Hello!");
		System.out.println(om.print(4, 5));
	}
}

class OverloadingMethods {
	public void print() {
		System.out.println("오버로딩1");
	}

	String print(Integer a) {
		System.out.println("오버로딩2");
		return a.toString();
	}

	void print(String a) {
		System.out.println("오버로딩3");
		System.out.println(a);
	}

	String print(Integer a, Integer b) {
		System.out.println("오버로딩4");
		return a.toString() + b.toString();
	}
}

/* 결과
오버로딩1
오버로딩2
3
오버로딩3
Hello!
오버로딩4
45
*/
```

print라는 같은 이름을 가진 4개의 메소드가 매개변수의 개수와 타입을 다르게 지정하여 문제없이 실행되고 있다.

또한, 접근 제어자를 public, default, protected, private로 자유롭게 지정할 수 있으며, 단, 접근 제어자만 다르게 한다고 오버로딩이 가능한 것은 아니다.

결국, 오버로딩은 매개변수의 차이로만 구현할 수 있다.

<br/>

## 3. 오버로딩을 사용하는 이유

- 같은 기능을 하는 메소드를 하나의 이름으로 사용할 수 있다.

- 메소드의 이름을 절약할 수 있다.

<br/>

## 4. 오버라이딩(Overriding)

부모 클래스로부터 상속받은 메소드를 자식 클래스에서 재정의 하는 것으로, 상속받은 메소드를 그대로 사용할 수 도 있지만, 경우에 따라 자식 클래스에서 상황에 맞게 메소드를 변경해야하는 경우 오버라이딩을 한다.

<br/>

## 5. 오버라이딩의 조건

오버라이딩은 부모 클래스의 메소드를 재정의하는 것이므로, 자식 클래스에서는 오버라이딩 할 메소드 이름, 매개변수, 리턴 값이 모두 같아야한다.

```Java
public class OverridingTest {

	public static void main(String[] args) {
		Person person = new Person();
		Child child = new Child();
		Senior senior = new Senior();
		
		person.cry();
		child.cry();
		senior.cry();
	}
}

class Person {
	void cry() {
		System.out.println("흑흑");
	}
}

class Child extends Person {
	@Override
	protected void cry() {
		System.out.println("잉잉");
	}
}

class Senior extends Person {
	@Override
	public void cry() {
		System.out.println("훌쩍훌쩍");
	}
}

/* 결과
흑흑
잉잉
훌쩍훌쩍
*/
```

<br/>

## 6. 오버로딩 vs 오버라이딩

이 둘은 사실 이름만 비슷하지 명백히 다른 개념이다.

오버로딩 - 기존에 없는 새로운 메소드를 추가하는 것

오버라이딩 - 상속받은 메소드를 재정의하는 것

<p align="center"><img src="../imagespace/java_overloadingAndOverriding.jpg"></p>
