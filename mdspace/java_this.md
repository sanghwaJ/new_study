# Java - this

## 1. 클래스의 속성과 생성자/메소드의 매개변수의 이름이 같은 경우 사용

> ## this는 객체 자신의 속성을 나타낸다.

```Java
public class Fruit {
  public String name;
  public String color;
  public double weight;
  public int count;

  public Fruit(String name, String color, double weight, int count) {
    name = name;
    color = color;
    weight = weight;
    count = count;
  }
  
  public static void main(String [] args) {
    Fruit banana = new Fruit('banana', 'yellow', 5.0, 10);
    system.out.println("name : " + banana.name);     // name : null
    system.out.println("color : " + banana.color);   // color : null
    system.out.println("weight : " + banana.weight); // weight : 0.0
    system.out.println("count : " + banana.count);   // count : 0
  }
}
```

일반적으로는 생성자나 set/get 매소드의 매개변수의 이름은 클래스의 속성 이름과 동일하게 정의되어 사용된다.

위의 코드에서도 생성자의 매개변수 역시 클래스 속성과 동일한 이름으로 정의되어 있는데, 출력 결과 초기화가 이루어지지 않아 예상하지 않은 결과가 나오게 된다.

그 이유는 생성자의 구현 부분에서 "name = name;" 이렇게 사용하게 되면 "name 매개변수 = name 매개변수"의 형태가 되어 Fruit 객체의 name 속성에는 값이 입력되지 않는다. 즉, java는 좌측의 name이 Fruit 객체의 name 속성을 가리키고 있다는 사실을 인지하지 못하고 있다는 것이다.

```Java
public class Fruit {
  public String name;
  public String color;
  public double weight;
  public int count;

  public Fruit(String name, String color, double weight, int count) {
    this.name = name;
    this.color = color;
    this.weight = weight;
    this.count = count;
  }
  
  public static void main(String [] args) {
    Fruit banana = new Fruit('banana', 'yellow', 5.0, 10);
    system.out.println("name : " + banana.name);     // name : null
    system.out.println("color : " + banana.color);   // color : null
    system.out.println("weight : " + banana.weight); // weight : 0.0
    system.out.println("count : " + banana.count);   // count : 0
  }
}
```

위의 코드처럼 "this.name = name;"으로 코드를 작성하여야 Fruit 객체의 "name 속성 = name 매개변수"의 형태가 되어 Fruit 객체의 속성에 값을 입력하게 된다.

<br/>

## 2. 클래스에 오버로딩된 다른 생성자 호출

> ## this는 같은 클래스에 오버로딩된 다른 생성자를 호출할 때 사용된다.

```Java
public class Fruit {
  public String name;
  public String color;
  public double weight;
  public int count;

  public Fruit(String name, String color) {
    // Fruit(String name, String color, double weight, int count) 호출
    this(name, color, 0.0, 0);
  }
  
  public Fruit(String name, String color, double weight, int count) {
    this.name = name;
    this.color = color;
    this.weight = weight;
    this.count = count;
  }
}
```

하나의 클래스에 여러 개의 생성자가 오버로딩 되어 있을 때, 일부분을 제외하고는 서로 중복된 코드를 가지고 있는 경우가 있다. 이런 경우, 내부에 정의된 다른 생성자를 호출하여 코드의 중복을 피하고 깔끔한 소스를 작성할 수 있다.

생성자를 호출할 때는 원하는 생성자의 매개변수를 확인한 후, 메소드를 호출하는 것처럼 this(매개변수...)의 형태로 이용하면 된다. 위의 코드에서는 2개의 매개변수를 입력받은 생성자(name, color) 구현 부분에서 4개의 매개변수를 입력받는 생성자(name, color, weight, count)를 호출하고 있다.

<br/>

## 3. 객체 자신의 참조 값을 전달하고 싶을 때

> ## this는 객체 자신의 참조 값을 전달할 때 사용된다.

```Java
public class Fruit {
  public String name;
  public String color;
  public double weight;
  public int count;
  
  public Fruit(String name, String color, double weight, int count) {
    this.name = name;
    this.color = color;
    this.weight = weight;
    this.count = count;
  }

  publice Fruit getFruitInstance() {
    return this;
  }
}
```

어떤 메소드에서는 동작을 완료하고 리턴값으로, 또 어떤 메소드에서는 내부에서 호출하고자 하는 메소드의 매개변수로 객체나 자기 자신의 참조값을 전달하고 싶은 경우가 있다. 이런 경우 getFruitInstance() 메소드처럼 this 키워드를 이용하여 구현이 가능하다.