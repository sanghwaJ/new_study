# Java - final

## 1. final 키워드의 정의
- 변수, 메소드, 클래스에 사용될 수 있는 키워드로 각각 어떤 곳에 사용되냐에 따라 다른 의미를 가짐
- 보통 오직 한 번만 할당할 수 있는 Entity를 정의할 때 사용 
- 만약 final로 선언된 변수가 할당되면 항상 같은 값을 가지며, 만약 final 변수가 객체를 참조하고 있다면 그 객체의 상태가 바뀌어도 final 변수는 매번 동일한 내용을 참조함

## 2. final 키워드의 사용

### 2-1. final 변수
- final 변수는 한 번 값을 할당하면 수정할 수 없기 때문에, 초기화는 한 번만 가능함

```java
public class Sphere {

    // PI 변수는 상수로 선언되어 수정할 수 없음
    public static final double PI = 3.141592653589793;

    public final double radius;
    public final double xPos;
    public final double yPos;
    public final double zPos;

    Sphere(double x, double y, double z, double r) {
         radius = r;
         xPos = x;
         yPos = y;
         zPos = z;
    }

    [...]
}
```

- 아래와 같이 먼저 선언해놓고 각각 다른 값을 갖도록 초기화할 수도 있는데, 이렇게 값을 할당하면 다음부터 수정할 수 없음 (blank final)

```java
public class Test {
  // 선언만 하고 초기화는 각 인스턴스에서 진행
  private final int value;

  public Test(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
```

### 2-2. final 메소드
- 어떠한 클래스를 상속할 때, 그 안에 final 메소드가 있다면 오버라이딩으로 수정할 수 없음

```java
public class Base {
    public void m1() {...}
    public final void m2() {...}    // final 메서드 선언
}

public class Derived extends Base {
    public void m1() {...}  // Base 클래스의 m1() 상속
    public void m2() {...}  // 오버라이딩, 즉 상속받은 메서드 수정 불가!
  }
```

### 2-3. final 클래스
- final이 붙은 클래스는 상속할 수 없으며, 보통 보안이나 효율성 측면에 장점을 가지고 사용됨 (ex. java.lang.system, java.lang.String)

```java
public final class MyFinalClass {...}    // final 클래스 선언

public class ThisIsWrong extends MyFinalClass {...} // 상속 불가!
```

## 3. static final

### 3-1. static
- static은 변수나 함수에 함께 붙는 키워드로, 어디에 선언하는지에 따라 조금씩 다른 의미를 가지지만, 보통은 메모리에 딱 한 번만 할당되어 메모리를 효율적으로 사용할 수 있다는 특징이 있음
- 즉, 같은 주소 값을 공유하기 때문에 변수 하나로 여기저기에 공유할 수 있다는 장점이 있음

### 3-2. static과 final의 사용
- final 키워드를 쓰면 그 값을 변경없이 계속해서 사용하겠다는 의미이기 때문에, static과 함께 사용하면 같은 값을 메모리에 딱 한 번만 할당해서 사용하여 효율성을 더 높일 수 있음
- 다만, 인스턴스마다 다른 값을 가지는 blank final과 같은 경우는 static을 함께 사용하지 않음



참고 : https://makemethink.tistory.com/184