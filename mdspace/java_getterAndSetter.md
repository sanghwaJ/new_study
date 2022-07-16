# Java - Getter & Setter

## 1. Getter와 Setter를 사용하는 이유

1) 자바와 같은 객체 지향 프로그램에서는 데이터 자체는 외부에서 접근을 할 수 없도록 하고, 메소드만 공개해서 유효한 값들만 데이터로 저장한다. 즉, 객체들이 데이터(필드)를 외부에서 직접적으로 접근하는 것을 막아놓기 위함이다.

2) 필드들은 private 접근 제한자로 막아두고, 각 필드의 Getter & Setter로 접근하는 방식을 취하는데, 이는 클래스의 특성 중 하나인 정보 은닉을 잘 보여준다.

3) Getter와 Setter를 사용하는 가장 큰 이유는 "객체의 무결성"을 보장하기 위함이다.

​

## 2. 예시

예를들어 Man이라는 클래스에서 weight이라는 필드가 존재할 때, weight은 0보다 작을 수 없으나, 외부에서 직접적으로 접근할 경우 weight에 -100이라는 값을 줌으로써 객체의 무결성이 깨지거나 객체가 가지고 있는 로직을 무시하게 될 수 있다.

이를 방지하기 위하여 필드는 private로 만들어 외부의 접근을 제한한 후, Setter를 사용하여 전달 받은 값을 내부에서 가공해 필드에 넣어주는 방식을 사용한다.

마찬가지로 필드 값을 가져올 때도 Getter를 사용해 본 필드의 값을 숨긴채 내부에서 가공된 값을 사용할 수 있다.

​

## 3. Setter

또 하나의 예를 들어보자. 만일 입력될 점수의 유효범위가 필요하다면 어떻게 해야할까?

```Java
if(0 <= inputScore && inputScore <= 100){
    // 값을 바꾸기 전 조건을 준다
    student[i].score = inputScore;
}
```

물론, 위와 같은 케이스로 해결이 가능하지만, 이처럼 객체가 직접 쓰이는 곳에서 검증을 하게 된다면 코드의 분량이 많아지며, 나중에 기준을 바꿔야 할 때 모든 코드들을 기준에 맞게 수정을 해야하는 불편함이 발생하게 된다.

이러한 경우 Setter를 사용하게 되면 아래와 같이 표현이 가능하다.

```Java
public boolean setScore(int score){
    // 값을 바꾸기 전 조건을 준다
    if(0 <= score && score <= 100){
        this.score = score;
        return true; // 값을 바꿀 수 있었습니다
    } else {
        return false; // 값을 바꾸지 못했습니다
    }
}
```

위와 같이 Setter를 사용한다면 클래스 내에서 값 설정에 조건을 걸 수 있으며 return으로 반환 값을 주면 멤버 변수에 값을 제대로 주었는지 확인할 수도 있다.

​

## 4. Getter

위에서 설명했듯이 다른 클래스에서 값을 마음대로 바꾸지 못하게 하려면 public 대신 private로 선언하면 된다.

하지만 이 값을 다름 클래스에서 값을 읽어들일 경우 private으로 접근을 막아놓았기 때문에 다른 클래스에서는 이 값을 사용할 수 없다. 이럴 때 사용하는 것이 Getter이다.

```Java
public int getScore(){
    // 학생의 점수를 반환
    return this.score;
}
```

이렇게 Getter 메소드를 생성하여 값을 가져올 수 있고, 직접 접근을 하지 않기 때문에 값의 의도치 않은 변형 없이 읽어들일 수 있다. 아래는 getter를 사용하여 만든 switch 문이다.

```Java
switch(student[i].getScore()/10){
    case 10:
    case 9:
        // 100점~90점대 A
        System.out.println("A");
        break;
    case 8:
        // 80점대 B
        System.out.println("B");
        break;
    case 7:
        // 70점대 C
        System.out.println("C");
        break;
    case 6:
        // 60점대 D
        System.out.println("D");
        break;
    default:
        // 그 밖의 값은 F
        System.out.println("F");
        break;
}
```