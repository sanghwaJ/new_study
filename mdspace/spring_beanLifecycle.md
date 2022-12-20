# Spring - 빈 생명주기 콜백

## 1. 빈 생명주기 콜백
- 데이터베이스 커넥션 풀, 네트워크 소켓과 같이 어플리케이션 시작 시점에 필요한 연결을 미리 해두고, 어플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면 객체의 초기화와 종료 작업이 필요함
- 스프링 빈은 간단하게 [객체 생성 => 의존 관계 주입]의 라이프 사이클을 가지는데, 이는 객체를 생성하고 의존 관계 주입이 다 끝난 후에 객체 초기화 작업이 호출 되어야 한다는 말
- 스프링은 의존 관계 주입이 완료되면 스프링 빈에 콜백 메소드를 통해 초기화 시점을 알려주는 다양한 기능을 제공하며, 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 주기 때문에 안전하게 종료 작업을 진행할 수 있음

### 1-1. 스프링 빈의 이벤트 라이프 사이클 (싱글톤 방식)
- [스프링 컨테이너 생성 => 스프링 빈 생성 => 의존 관계 주입 => 초기화 콜백 => 사용 => 소멸전 콜백 => 스프링 종료]
	- 초기화 콜백 : 빈이 생성되고 빈의 의존 관계 주입이 완료된 후 호출되는 콜백 메소드
	- 소멸전 콜백 : 빈이 소멸되기 전에 호출되는 콜백 메소드
- 객체의 생성과 초기화 분리
	- 생성자는 필수 정보(파라미터)를 받고 메모리를 할당하여 객체를 생성하는 책임을 가짐
	- 초기화는 생성자를 통해 생성된 값을 활용하여 외부 커넥션을 연결하는 등의 무거운 동작을 수행함
	- 따라서 생성자 안에서 무거운 초기화 작업을 하는 것 보다는 생성과 초기화를 분리하는 것이 유지보수 측면에서 더 유리 (But, 초기화 작업이 매우 단순한 경우 생성자에서 한 번에 처리하는 것이 더 나을 수 있음)

### 1-2. 스프링과 빈 생명주기 콜백
- 싱글톤 빈들은 스프링 컨테이너가 종료될 때 함께 종료되기 때문에, 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어남
- 반면, 생명주기가 짧은 빈들은 스프링 컨테이너 종료와는 무관하게 해당 빈이 종료되기 직전에 소멸전 콜백이 일어남
- 스프링은 3가지 방법으로 빈 생명주기 콜백을 지원하는데 그 방법은 아래와 같으며, 이중 인터페이스 방법은 최근 거의 사용하지 않음
	- 인터페이스 (InitializingBean, DisposableBean)
	- 설정 정보에 초기화 메서드, 종료 메서드 지정
	- @PostConstruct, @PreDestroy 애노테이션 지원

## 2. 스프링의 빈 생명주기 콜백 지원 방법

### 2-1. 빈 등록 초기화, 종료 메서드 지정
- 설정 정보에 @Bean(initMethod = "init", destroyMethod = "close")와 같이 초기화, 소멸 메서드를 지정하여 사용할 수 있음
- 설정 정보 사용 특징
    - 메서드 이름을 자유롭게 줄 수 있음
    - 스프링 빈이 스프링 코드에 의존하지 않음
    - 코드가 아니라 설정 정보를 사용하기 때문에, 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있음
- 종료 메서드 추론 - @Bean의 destroyMethod 속성
    - 라이브러리는 대부분 close, shutdown 이라는 이름의 종료 메서드를 사용하는데, @Bean의 destroyMethod는 기본값이 inferred(추론)으로 등록되어 있어 close, shutdown라는 종료 메서드를 추론해서 호출해주어 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작함
    - 추론 기능을 사용하기 싫으면 destroyMethod="" 처럼 빈 공백을 지정하면 됨

### 2-2. @PostConstruct, @PreDestroy
- 최신 스프링에서 가장 권장하는 방법으로, 애노테이션 하나만 붙이면 되기 때문에 사용하기 편리함
- javax.annotation.PostConstruct 패키지를 사용하여 스프링에 종속적인 기술이 아니라 JSR-250라는 자바 표준이기 때문에 스프링이 아닌 다른 컨테이너에서도 동작하며, 컴포넌트 스캔과 잘 어울린다.
- 단점은 외부 라이브러리에는 적용하지 못한다는 것으로, 외부 라이브러리를 초기화하거나 종료 해야하면 @Bean의 기능을 사용해야 함

### 3-3. 정리
- @PostConstruct, @PreDestroy 애노테이션을 사용이 권장됨
- 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean의 initMethod, destroyMethod를 사용