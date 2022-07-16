# Java - 메소드의 구분

## ※ 메소드의 형식

```Java
public static void main(String[] args){
    메소드_내용...
}
/*
=> 접근제어 형식 : public
=> 부가적 형식 : static
=> 자료형 : void (return을 하지 않는 자료형)
=> 메소드 이름 : main
=> 매개변수 : string 배열 형식의 args
*/
```

<br/>

## 1. 전달인자(매개변수)가 있고, 반환 값이 없는 경우

```Java
package testCom;

public class TestJava013 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		one(1);
	}
	public static void one(int a){
		System.out.println("a는 " + a);
	}
}
```

여기서 매개변수는 "int a"이다. int 형 값을 매개변수로 받겠다는 것

그래서 메인 메소드에서 one에 1이라는 값을 넘겨주면서 메소드를 호출한다.

그래서 one 메소드에 있는 내용에 a가 1로 되어 출력이된다.

<br/>

## 2. 전달인자(매개변수)가 있고, 반환 값이 있는 경우

```Java
package testCom;

public class TestJava014 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = one(4); 
		System.out.println(num1); 
	}
	public static int one(int a) {
		return a + a + a;
	}
}
```

여기서 1번 경우와 같이 매개변수는 "int a"이지만, one 메소드의 자료형이 int라는 것이 다르다.

그래서 one 메소드를 보면 "return"이 있다.

따라서 one 메소드는 뭔가를 반환하는 메소드이기 때문에, 같은 자료형의 변수에 그 값을 저장할 수 있다.

즉, int num1에 one(4)를 저장하게 되며, 결과는 4를 3번 더한 12가 출력이 된다.

<br/>

## 3. 전달인자(매개변수)가 없고, 반환 값이 없는 경우

```Java
package testCom;

public class TestJava015 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("메인 메소드");
		one();
	}
	public static void one() {
		System.out.println("one 메소드");
	}
}
```

매개변수도 없고, 자료형도 void로 반환하는 것이 없는 메소드.

<br/>

## 4. 전달인자(매개변수)가 없고, 반환 값이 있는 경우

```Java
package testCom;

public class TestJava016 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = one();
		System.out.println(num1);
	}
	public static int one() {
		return 1;
	}
}
```

전달인자(매개변수)가 있고 반환 값이 있는 경우인 2번의 예시와 비슷해보이지만, 매개변수가 없기 때문에 무언가를 받을 수가 없다.

그래서 값만 반환하는 메소드가 되어버렸다.

​

ex 1) 원의 반지름을 변수로 저장하고, 그 값에 맞는 원의 넓이를 출력하기

```Java
package testCom;

import java.util.Scanner;

public class TestJava017 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("반지름을 입력하시오");
		double num = scan.nextDouble();

		System.out.println("넓이: " + circle(num));
	}
	public static double circle(double r) {
		return r * r * 3.14;
	}
}
```

ex 2) 변수 num1과 num2의 값을 이용하여, num1과 num2의 승을 구하기

```Java
package testCom;

import java.util.Scanner;

public class TestJava018 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		
		System.out.println("첫번쨰, 두번째 숫자를 입력하시오(공백 구분)");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		System.out.println(num1 + "의 " + num2 + "승은 " + doubleint(num1, num2));
	}
	public static int doubleint(int num1, int num2) {
		if (num2 == 0) {
			return 1;
		}else{
			return num1 * doubleint(num1, num2-1);
		}	
	}
}
​```