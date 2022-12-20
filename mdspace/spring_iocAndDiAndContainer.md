# Spring - IoC & DI & 컨테이너

## 1. IoC (Inversion Of Control, 제어의 역전)
- 기존에는 개발자가 스스로 제어 흐름을 조종하기 위해 필요한 객체를 생성하고 연결하며 실행하였으나, AppConfig가 등장한 이후 프로그램의 제어 흐름은 AppConfig가 담당하고 구현 객체는 자신의 로직을 실행하는 역할만 담당하게 됨
- 즉, 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있기 때문에, serviceImpl은 필요한 인터페이스를 호출하지만 어떠한 구현 객체들이 실행될지 모르며, 각 구현 객체는 그저 묵묵히 자신의 로직을 실행함
- 이렇게 프로그램의 제어 흐름을 직접 하는 것이 아니라 외부에서 관리하는 것을 IoC(제어의 역전)라 함

## 2. DI (Dependency Injection, 의존 관계 주입)
- serviceImpl은 인터페이스에 의존하며, 실제 어떤 구현 객체가 사용될지는 알 수 없음
- 의존 관계는 정적인 클래스 의존 관계와 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계를 분리하여 생각해야 함

### 2-1. 정적인 클래스 의존 관계
- 정적인 의존 관계는 어플리케이션을 실행하지 않아도 클래스가 사용하는 import 코드만 보고 의존 관계를 쉽게 판단할 수 있음

### 2-2. 동적인 객체 인스턴스 의존 관계
- 어플리케이션 실행 시점(런타임)에 외부에서 실현 구현 객체를 생성하고 클라이언트에 전달하여, 클라이언트와 서버의 실제 의존 관계가 연결되는 것 ⇒ 의존 관계 주입, DI(Dependency Injection)
- 의존 관계 주입을 사용하면 클라이언트 코드를 변경하지 않고 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있음
- 의존 관계 주입을 사용하면 정적인 클래스 의존 관계를 변경하지 않고 동적인 객체 인스턴스 의존 관계를 쉽게 변경할 수 있음

## 3. IoC 컨테이너, DI 컨테이너
- AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너, 혹은 DI 컨테이너라 함
- 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 불림

### 3-1. Spring 컨테이너 적용
- ApplicationContext 를 Spring 컨테이너라 하며, 기존에 AppConfig를 사용하여 직접 객체를 생성하고 의존 관계를 주입했던 역할을 대체함
- Spring 컨테이너는 @Configuration이 붙은 AppConfig를 설정(구성) 정보로 사용하고, @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록하며, 이를 Spring 빈이라 함
- Spring 컨테이너를 통해서 필요한 Spring 빈(객체)를 찾을 수 있으며, applicationContext.getBean() 메서드를 사용해서 찾을 수 있음