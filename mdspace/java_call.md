# Java - Call by Value & Call by Reference

## 1. Call by Value
- 함수 호출 시, 전달되는 변수 값을 복사해서 함수 인자로 전달하는 것
- 복사된 인자는 함수 안에서 지역적으로 사용되기 때문에 local value의 속성을 가지기 때문에 함수 안에서 인자 값이 변경되더라도 외부 변수 값은 변경되지 않음

```c++
void func(int n) {
    n = 20;
}

void main() {
    int n = 10;
    func(n);
    printf("%d", n); // 그대로 10 출력
}
```

## 2. Call by Reference

- 함수 호출 시, 인자로 전달되는 변수의 레퍼런스를 전달하여 함수 안에서 인자 값이 변경되면 아규먼트로 전달된 객체의 값도 변경되는 것

```c++
void func(int *n) {
    *n = 20;
}

void main() {
    int n = 10;
    func(&n);
    printf("%d", n); // 20 출력
}
```

## 3. Java 함수 호출 방식
- Java는 항상 Call by Value로 값을 넘기고, Reference Type(참조 자료형)을 넘길 시에는 해당 객체의 주소값을 복사하여 사용하기 때문에, 원본 객체의 프로퍼티까지는 접근이 가능하나 원본 객체 자체를 변경할 수 없다.

```Java
User a = new User("Hello");   // 1

foo(a);

public void foo(User b){        // 2
    b = new User("World");    // 3
}

/*
==========================================

// 1 : a에 User 객체 생성 및 할당(새로 생성된 객체의 주소값을 가지고 있음)
 
 a   -----> User Object [name = "Hello"]
 
==========================================

// 2 : b라는 파라미터에 a가 가진 주소값을 복사하여 가짐

 a   -----> User Object [name = "Hello"]
               ↑     
 b   -----------
 
==========================================

// 3 : 새로운 객체를 생성하고 새로 생성된 주소값을 b가 가지며 a는 그대로 원본 객체를 가리킴
 
 a   -----> User Object [name = "Hello"]
                   
 b   -----> User Object [name = "World"]
 
*/
```

## 4. 정리

- Call by Value : 데이터 값을 복사하여 함수로 전달하기 때문에 원본 데이터가 변경될 가능성이 없지만 인자를 넘길 때마다 메모리 공간을 할당해야해서 메모리 공간을 더 잡아먹음
- Call by Reference : 메모리 공간 할당 문제는 없지만, 원본 값이 변경될 수 있다는 문제가 존재함