# Spring - Spring Container & Spring Bean

## 1. Spring Container란?
- 자바 객체의 생명 주기를 관리하며, 생성된 자바 객체(Bean)들에게 추가적인 기능을 제공하는 역할
- 개발자는 new 연산자, 인터페이스 호출 등으로 객체를 생성하거나 소멸시킬 수 있는데, 이러학 역할을 Spring Container가 대신 해줌
- 객체들 간의 의존 관계를 Spring Container가 런타임 과정에서 만들어 주기도 함

## 2. Spring Container의 종류

### 2-1. Bean Factory
- Bean을 등록하고, 생성하고, 조회하는 등 Bean을 관리하는 역할
- @Bean이라는 annotation을 통해 Bean 등록을 함

### 2-2. Application Context
- Bean Factory의 기능을 상속받아 Bean Factory와 같이 Bean을 관리해주는 역할
- Bean Factory와 달라 Bean을 지연없이 얻을 수 있다는 장점으로, 실제 개발에서 주로 사용됨

## 3. Spring Bean 이란?
- Spring Container가 관리하는 자바 객체
- Class를 생성하고, new 연산자를 통해 객체를 생성하는 일반 Java 프로그래밍과 달리, Spring에 의해 생성된 자바 객체를 Bean이라 함

### 3-1. Spring Bean을 Spring Container에 등록하는 방법
1. Annotation 사용
   - Annotation : Java 소스 코드에 추가하여 상숑할 수 있는 메타 데이터
   - @ComponentScan : 어떠한 지점부터 Component를 찾으라고 알려주는 역할
   - @Component : 실제로 찾아 Bean으로 등록할 Class를 의미
   - 예시로 Spring 프로젝트에서 Controller를 등록할 때는 @Controller Annotation을 사용하는데, @Controller Annotation 내부적으로는 @Component를 사용한다
    ```java
    @Controller
    public class HelloController {
        @GetMapping("hello")
        public String hello(Model model){
            model.addAttribute("data", "This is data!!");
            return "hello";
        }
    }
    ```
    ```java
    // @Controller 내부
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Component
    public @interface Controller {
            /**
            * The value may indicate a suggestion for a logical component name,
            * to be turned into a Spring bean in case of an autodetected component.
            * @return the suggested component name, if any (or empty String otherwise)
            */
            @AliasFor(annotation = Component.class)
            String value() default "";
        }
    ```
    
2. Bean Config File에 직접 등록
    - @Configuration과 @Bean Annotation을 이용하여 직접 Bean 동록
    - File 하위에서 Bean으로 등록하고자 하는 Class에 @Bean Annotation을 사용하면 된다

    ```java
    // Hello.java
    @Configuration
    public class HelloConfiguration {
        @Bean
        public HelloController sampleController() {
            return new SampleController;
        }
    }
    ```