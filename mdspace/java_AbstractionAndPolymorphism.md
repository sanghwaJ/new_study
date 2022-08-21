# Java - 추상화(Abstraction)와 다형성(Polymorphism)

## 1. 추상화(Abstraction)

추상화의 사전적 의미는 특정한 개별 사물들의 공통된 속성이나 관계를 뽑아내는 것이다.

이를 컴퓨터의 관점에서 생각하면, 추상화란 데이터나 프로세스 등을 의미가 비슷한 개념이나 표현으로 정의해 나가는 과정이면서, 동시에 각 개별 개체의 구현에 대한 상세함을 감추는 것이다.

```Java
class ClassA() {
    private String name:
    public void setName(String name) {
    this.name = name
}
    public String getName() {
        return this.name;
}

    public void processA() {
    // A 작업
    }
}

class ClassB() {
    private String name:
    public void setName(String name) {
    this.name = name
    }
    public String getName() {
        return this.name:
    }

    public void processB() {
    // B 작업
    }
} 
```

위의 코드에서 ClassA와 ClassB는 name 속성, setName(), getName() 메소드가 중복이 된다.

이렇게 사용해도 문제는 없지만 코드가 비효율적이기 때문에 아래와 같이 코드를 바꿔주는 것이 추상화의 목적이다.

```Java
class CommoncClass {
    private String name:
    public void setName(String name) {
        this.name = name
    }
    public String getName() {
        return this.name:
    }
}

class ClassA() extends CommonClass {
    public void processA() {
    // A작업
    }
}

class ClassB() extends Commonclass {
    public void processB() {
    // B작업
    }
} 
```

위와 같이 코드를 수정하여 더 효율적인 코드가 되었다.

<br/>

## 2. 다형성이란?

다형성은 OOP에서 중요한 개념으로, 하나의 객체가 여러가지 자료형으로 표현될 수 있음을 말한다.

자바에서는 이러한 다형성을 부모 클래스 타입의 참조변수로, 자식 클래스 타입의 인스턴스를 참조할 수 있도록 구현하고 있다.

<br/>

## 3. 참조 변수의 다형성

자바에서는 다형성을 위해 부모 클래스 타입의 참조 변수로 자식 클래스 타입의 인스턴스를 참조할 수 있도록 하고 있다.

이때, 참조 변수가 사용할 수 있는 멤버의 개수가 실제 인스턴스의 멤버 개수보다 같거나 적어야 참조가 가능하다.

```Java
class Parent { ... }
class Child extends Parent { ... }

...

Parent pa = new Parent(); // 허용
Child ch = new Child();   // 허용
Parent pc = new Child();  // 허용
Child cp = new Parent();  // 오류 발생.
```

위의 코드는 참조 변수의 다형성을 보여주는 예이다.

특정 타입의 참조 변수로 당연히 같은 타입의 인스턴스를 참조할 수 있다.

참조변수가 사용할 수 있는 멤버의 개수가 실제 인스턴스의 멤버 개수와 같기 때문이다.

또한, 부모 클래스 타입의 참조 변수로도 자식 클래스 타입의 인스턴스를 참조할 수 있다.

참조 변수가 사용할 수 있는 멤버의 개수가 실제 인스턴스의 멤버 개수보다 적기 때문이다.

하지만, 반대의 경우인 자식 클래스 타입의 참조 변수로는 부모 클래스 타입의 인스턴스를 참조할 수 없다.

그 이유는 참조 변수가 사용할 수 있는 멤버의 개수가 실제 인스턴스의 멤버 개수보다 많기 때문이다.

=> 즉, 클래스는 상속을 통해 확장될 수는 있어도 축소될 수는 없으므로, 자식 클래스에서 사용할 수 있는 멤버의 개수가 언제나 부모 클래스와 같거나 많게 된다.

<br/>

## 4. 참조 변수의 타입 변환

자바에서는 참조 변수도 다음과 같은 조건에 따라 타입 변환을 할 수 있다.

- 서로 상속 관계에 있는 클래스 사이에만 타입 변환을 할 수 있다.

- 자식 클래스 타입에서 부모 클래스 타입으로의 타입 변환은 생략할 수 있다.

- 하지만, 부모 클래스 타입에서 자식 클래스 타입으로의 타입 변환은 반드시 명시해야 한다.

```Java
// 문법
// (변환할 타입의 클래스 이름)변환할 참조 변수

class Parent { ... }
class Child extends Parent { ... }
class Brother extends Parent { ... }

...

Parent pa01 = null;
Child ch = new Child();
Parent pa02 = new Parent();
Brother br = null;

pa01 = ch;          // pa01 = (Parent)ch; 와 같으며, 타입 변환을 생략할 수 있음.
br = (Brother)pa02; // 타입 변환을 생략할 수 없음.
br = (Brother)ch;   // 직접적인 상속 관계가 아니므로, 오류 발생.
```

<br/>

## 5. instance of 연산자

이러한 자바의 다형성으로 인해, 런타임에 참조 변수가 실제로 참조하고 있는 인스턴스의 타입을 확인할 필요성이 생긴다.

자바에서는 instance of 연산자를 제공하여 참조 변수가 참조하고 있는 인스턴스의 실제 타입을 확인할 수 있도록 해준다.

```Java
// 문법
// 참조변수 instanceof 클래스이름

class Parent { }
class Child extends Parent { }
class Brother extends Parent { }

public class Polymorphism01 {
    public static void main(String[] args) {
        Parent p = new Parent();
        System.out.println(p instanceof Object); // true
        System.out.println(p instanceof Parent); // true
        System.out.println(p instanceof Child);  // false
        System.out.println();

        Parent c = new Child();
        System.out.println(c instanceof Object); // true
        System.out.println(c instanceof Parent); // true
        System.out.println(c instanceof Child);  // true
    }
}
```