# Spring - 의존 관계 주입

## 1. 다양한 의존 관계 주입 방법
- 의존 관계 주입 방법은 아래와 같이 4가지가 있음
    - 생성자 주입
    - 수정자 주입 (Setter 주입)
    - 필드 주입
    - 일반 메소드 주입
- 이 중에서 기본으로 생성자 주입을 사용해야하며, 가끔 옵션이 필요한 경우 수정자 주입을 사용해야 함

### 1-1. 생성자 주입
- 생성자를 통해 의존 관계를 주입 받는 방법으로, 가장 많이 사용되는 방법
- 생성자 호출 시점에 딱 1번만 호출되는 것이 보장되며, 불변/필수 의존 관계에서 사용함

```java
@Component
public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired // Spring 빈의 경우, 생성자가 딱 1개만 있으면 @Autowired는 생략해도 무방함
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
}
```

```java
@Component
public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;
		
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}
}
```

### 1-2. 수정자 주입 (Setter 주입)
- 필드의 값을 변경하는 수정자 메소드인 Setter를 통해 의존 관계를 주입하는 방법
- 선택(required=false)이나 변경 가능성이 있는 의존 관계에서 사용함
- Java 빈 프로퍼티 규약의 수정자 메소드 방식을 사용하는 방법

```java
@Component
public class OrderServiceImpl implements OrderService {
	private MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy;
	}
}
```
- 참고
    - 옵션 처리 : @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생하기 때문에, 주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)로 지정해 주어야 함

## 2. 생성자 주입을 사용해야 하는 이유

### 2-1. 불변
- 대부분의 의존 관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없으며, 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안됨 (불변해야 함)
- 수정자 주입을 사용하면 setXxx 메서드를 public으로 열어두어야 하는데, 누군가 실수로 변경할 수도 있거나 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아님
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없기 때문에 불변하게 설계할 수 있음

### 2-2. 누락
- 생성자 주입을 사용하면 의존 관계 주입이 누락 됐을 때 컴파일 오류(Null Point Exception)가 발생하기 때문에 금방 에러 상황을 파악할 수 있음

### 2-3. final 키워드
- 생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있기 때문에 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아줌
    - java: variable discountPolicy might not have been initialized
- 컴파일 오류는 세상에서 가장 빠르고, 좋은 오류!
- 참고로, 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로 필드에 final 키워드를 사용할 수 없으며, 오직 생성자 주입 방식만 final 키워드를 사용할 수 있음

### 2-4. 정리
- 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 Java 언어의 특징을 잘 살리는 방법
- 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여
- 생성자 주입과 수정자 주입을 동시에 사용할 수 있지만, 항상 생성자 주입을 선택하고, 가끔 옵션이 필요하면 수정자 주입을 선택해야 함 (필드 주입은 사용하지 않는게 좋음)