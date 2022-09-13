# Spring - Annotation (+ Lombok)

## 1. Annotation
- Java에서 Annotation은 코드 사이에 주석처럼 쓰이며, 특별한 의미와 기능을 수행하도록 하는 기술
- 즉, 프로그램에게 추가적인 정보를 제공해주는 메타데이터
- Annotation의 주요 용도
  - 컴파일러에게 코드 작성 문법 에러를 체크하도록 정보를 제공함
  - 빌드나 배치시 코드를 자동으로 생성할 수 있도록 정보를 제공함
  - 실행(런타임) 시 기능을 실행하도록 정보를 제공함
- Reflection(구체적인 Class 타입을 알지 못해도 그 Class의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 Java API)을 이용하여 Annotation 지정만으로도 원하는 Class를 주입할 수 있음

## 2. 주요 Spring Annotation

### 2-1. @Component
- 개발자가 직접 생성한 Class를 Bean으로 등록할 때 사용
```java
@Component(value="myman")
public class Man {
    public Man() {
        System.out.println("hello");
    }
}
```

### 2-2. @ComponentScan
- @Component, @Controller, @Service, @Repository, @Configuration 중 하나라도 등록된 Class를 찾으면 Context에 Bean으로 등록하는 Annotation
- @ComponentScan이 있는 Class의 하위 Bean들을 찾아서 Context에 Bean으로 등록
- ApplicationContext.xml에 \<bean id="anno" class="anno"/> 과 같이 XML에 Bean을 직접등록하는 방법도 있지만 @ComponentScan Annotation을 이용할 수도 있음

### 2-3. @Bean
- 개발자가 제어 불가능한 외부 라이브러리 같은 것들을 Bean으로 만들 때 사용

### 2-4. @Controller
- Spring에 해당 Class가 Controller의 역할을 한다고 명시하기 위해 사용

```java
@Controller  
@RequestMapping("/user") // /user로 들어오는 요청을 모두 처리
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(Model model) {
        //  GET method, /user 요청을 처리
    }
}
```

### 2-5. @RequestHeader
- Request의 header값을 가져올 수 있으며, 해당 Annotation을 쓴 메소드의 파라미터에 사용

```java
@Controller
@RequestMapping("/user")// 이 Class는 /user로 들어오는 요청을 모두 처리
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(@RequestHeader(value="Accept-Language") String acceptLanguage) {
        //  GET method, /user 요청을 처리
    }
}
```

### 2-6. @RequestMapping
- @RequestMapping(value=”“)와 같은 형태로 작성하며, 요청 들어온 URI의 요청과 Annotation value 값이 일치하면 해당 Class나 메소드를 실행 
- Controller 객체 안의 메서드와 Class에 적용 가능 (Class 단위에 사용하면 하위 메소드에 모두 적용)
- 메소드에 적용되면 해당 메소드에서 지정한 방식으로 URI를 처리
  
```java
@Controller
@RequestMapping("/user") // /user로 들어오는 요청을 모두 처리합니다.
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(Model model) {
        //  GET method, /user 요청을 처리
    }
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(Model model) {
        //  POST method, /user 요청을 처리
    }
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String addUser(Model model) {
        //  GET method, /user/info 요청을 처리
    }
}
```

### 2-7. @RequestParam
- URL에 전달되는 파라미터를 메소드의 인자와 매칭시켜, 파라미터를 받아서 처리할 수 있는 Annotation
- Json 형식의 Body를 MessageConverter를 통해 Java 객체로 변환시킴

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String getUser(@RequestParam String nickname, 
                                @RequestParam(name="old") String age {
        // GET method, /user 요청을 처리
        // https://naver.com?nickname=dog&old=10
        String sub = nickname + "_" + age;
        ...
    }
}
```

### 2-8. @RequestBody
- Body에 전달되는 데이터를 메소드의 인자와 매칭시켜, 데이터를 받아서 처리할 수 있는 Annotation 
- 클라이언트가 보내는 HTTP 요청 본문(JSON 및 XML 등)을 Java 오브젝트로 변환

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        //  POST method, /user 요청을 처리
        String sub_name = user.name;
        String sub_old = user.old;
    }
}
```

### 2-9. @ModelAttribute
- 클라이언트가 전송하는 HTTP parameter, Body 내용을 Setter 함수를 통해 1:1로 객체에 데이터를 연결(바인딩)
- @RequestBody와 다르게 HTTP Body 내용은 multipart/form-data 형태를 요구하며, @RequestBody가 json을 받는 것과 달리 @ModenAttribute 의 경우에는 json을 받아 처리할 수 없음

### 2-10. @ResponseBody
- @ResponseBody은 메소드에서 리턴되는 값이 View 로 출력되지 않고 HTTP Response Body에 직접 쓰여지게 되며, return 시에 json, xml과 같은 데이터를 return
  
```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@RequestParam String nickname, 
                                @RequestParam(name="old") String age {
        // GET method, /user 요청을 처리
        // https://naver.com?nickname=dog&old=10
        User user = new User();
        user.setName(nickname);
        user.setAge(age);
        return user;
    }
}
```

### 2-11. @Autowired
 - @Autowired 를 사용하여 Spring Framework가 Class를 보고 Type에 맞게(Type을 먼저 확인 후, 없으면 Name 확인) Bean을 주입
- Spring에서 Bean 객체를 주입하는 방법
  - @Autowired
  - 생성자 (@AllArgsConstructor 사용)
  - setter
  
### 2-12. @GetMapping
- RequestMapping(Method=RequestMethod.GET)과 똑같은 역할을 함

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String getUser(Model model) {
        //  GET method, /user 요청을 처리
    }
    
    ////////////////////////////////////
    // 위와 아래 메소드는 동일하게 동작 //
    ////////////////////////////////////

    @RequestMapping(method = RequestMethod.GET)
    public String getUser(Model model) {
        //  GET method, /user 요청을 처리
    }
}
```

### 2-13. @PostMapping
- RequestMapping(Method=RequestMethod.POST)과 똑같은 역할을 함

```java
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(Model model) {
        //  POST method, /user 요청을 처리
    }

    ////////////////////////////////////
    // 위와 아래 메소드는 동일하게 동작 //
    ////////////////////////////////////

    @PostMapping('/')
    public String addUser(Model model) {
        //  POST method, /user 요청을 처리
    }
}
```

### 2-14. @SpringBootTest
- Spring Boot Test에 필요한 의존성을 제공해줌

```java
// DemoApplicationTests.java
package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {

	}

}
```

### 2-15. @Test
- JUnit에서 테스트 할 대상을 표시해줌

### 2-16. @Resource
- 표준 Java에서 지원하는 Dependency 정의 용도의 Annotation (의존성 주입을 위해 사용)
- Annotation 사용으로 인해 특정 Framework에 종속적인 어플리케이션을 구성하지 않기 위해서는 @Resource를 사용할 것을 권장

```java
package expert006;
import javax.annotation.Resource;

public class Car {
    @Resource
    Tire tire;

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
```

### 2-17. @Autowired
- Spring Framework에서 지원하는 Dependency 정의 용도의 Annotation (의존성 주입을 위해 사용)
- Spring Framework에 종속적이긴 하지만 정밀한 Dependency Injection이 필요한 경우에 유용

```java
package expert006;
import org.springframework.beans.factory.annotation.Autowired;

public class Car {
    @Autowired
    Tire tire;

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
```

## 3. Lombok
- Lombok은 코드를 크게 줄여주여 가독성을 크게 높일 수 있는 라이브러리로, 대표적인 Annotation은 아래와 같음

### 3-1. @Setter
- Class 모든 필드의 Setter method를 생성

### 3-2. @Getter
- Class 모든 필드의 Getter method를 생성

### 3-3. @AllArgsConstructor
- Class 모든 필드 값을 파라미터로 받는 생성자를 추가

### 3-4. @NoArgsConstructor
- Class 기본 생성자를 자동으로 추가

### 3-5. @ToString
- Class 모든 필드의 toString method를 생성