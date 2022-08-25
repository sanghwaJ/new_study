# Spring - Spring VS Spring Boot

## 1. Spring Framework의 주요 특징

### 1-1. IoC (Invesion of Control)
- Servlet이나 Bean을 개발자가 직접 컨트롤 하는 것이 아닌, Framework가 대신 수행해주는 것

### 1-2. DI (Dependency Injection)
- Spring Framework에 의존성을 주입(XML과 같은 Spring 설정 파일)하여 객체 간 결합을 느슨하게 하고, 코드의 재사용성 및 단위 테스트를 용이하게 하는 것

### 1-3. AOP (Aspect Oriented Programming)
- 핵심 기능을 제외한 부수적인 기능을 Framework가 제공하는 것
- 예를 들어, Security를 적용하거나 Logging 등을 추가하고 싶을 때, 기존 비즈니스 로직을 건들지 않고 AOP로 추가할 수 있음

### 1-4. 중복 코드 제거
- 복잡하고 중복되는 코드를 모두 제거 (ex. JDBC 등)

### 1-5. 다른 Framework와 통합
- JUnit, Mockito와 같은 유닛 테스트 Framework와 통합이 간단하여, 이를 통해 개발하는 프로그램의 품질을 향상시킬 수 있음

## 2. Spring과 Spring Boot의 주요 차이점

### 2-1. Dependency
- Spring은 Spring Boot에 비해 dependency를 설정해줄 때 작성하기가 어렵고, 버전 관리도 더 어려움
- Spring에서의 dependency 추가 예시
```XML
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>5.3.5</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.5</version>
</dependency>
```
- Spring Boot에서의 dependency 추가 예시 (build.gradle 파일)
```XML
implementation 'org.springframework.boot:spring-boot-starter-web'
```
- 또한 Spring은 test Framework를 사용하고자 하면, Spring Test, JUnit 등의 모든 라이브러리를 추가해야 하지만, Spring Boot는 spring-boot-starter-test만 추가하면 됨

### 2-2. Configuration
- Spring의 경우 Configuration 설정을 할 때도 Spring Boot에 비해 작성하기 어렵고, 모든 Annotation 및 Bean도 등록해주어야 함
- 반면, Spring Boot는 application.properties 파일이나 application.yml 파일에 설정하면 됨
- Spring과 Spring Boot에서의 Thymeleaf 템플릿 사용 코드 예시

```java
// Spring
@Configuration
@EnableWebMvc
public class MvcWebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = 
          new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
```

```java
// Spring Boot
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf
```

### 2-3. AutoConfiguration
- Spring Boot는 Spring과 달리 @SpringBootApplication이라는 Annotation을 사용할 수 있는데, 해당 Annotation을 사용하면 외부 라이브러리, 내장 톰캣 서버 실행 등을 할 수 있음 (Annotation을 사용하지 않으면 일반 Java 프로그램과 동일하게 실행)
- @ComponentScan : @Component, @Controller, @Repository, @Service라는 Annotation이 붙어있는 객체들을 스캔해 자동으로 Bean에 등록
- @EnableAutoConfiguration : @ComponentScan 이후 사전에 정의한 라이브러리들을 Bean에 등록

### 2-4. 편리한 배포
- Spring의 경우 WAR 파일을 WAS에 담아 배포하지만, Spring Boot의 경우 Tomcat과 같은 내장 WAS를 가지고 있기 때문에 jar 파일로 간편하게 배포할 수 있음

## 3. 결론
- Spring은 기존 EJB를 대신하여 Java 어플리케이션을 더 쉽게 만들 수 있게 해줌
- Spring Boot는 Spring보다 개발자가 개발 그 자체에만 집중할 수 있게 해줌