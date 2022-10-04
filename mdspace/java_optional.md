# Java - Optional

## 1. Optional 개요

### 1-1. NPE(NullPointerException)
- 개발을 진행하며 가장 많이 발생하는 예외 상황 중 하나로, null 여부를 검사하거나, null 대신 초기값을 사용하여 NPE를 회피해야함

```java
List<String> names = getNames();
names.sort(); // names가 null이라면 NPE가 발생함

List<String> names = getNames();
// NPE를 방지하기 위해 null 검사를 해야함
if(names != null){
    names.sort();
}
```

### 1-2. Optional
- Java8 부터 추가된 기능으로, Optional\<T> 클래스를 사용하여 NPE를 방지할 수 있음
- null이 올 수도 있는 값을 감싸는 wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와줌
- Optional은 value에 값을 저장하기 때문에 null이라도 NPE가 발생하지 않으며, 클래스이기 때문에 활용할 수 있는 메소드들을 제공해 줌

## 2. 올바르지 않은 Optional의 사용

### 2-1. Optional이 만들어진 이유와 의도
- null을 반환하면 오류가 발생할 가능성이 높은 경우, '결과 없음'을 명확하게 드러내기 위해 매우 제한적으로 사용됨
- Optional을 사용하면 코드가 Null-Safe해지고, 가독성이 좋아지며, 어플리케이션을 안정적으로 운영할 수 있지만, 올바르게 사용하지 못한다면 문제가 발생할 수 있음

### 2-2. NullPointerException 대신 NoSuchElementException 발생
- 값의 존재 여부를 판단하지 않고 접근하는 경우 NoSuchElementException 발생
  
```java
Optional<User> optionalUser = ... ;

// optional이 갖는 value가 없으면 NoSuchElementException 발생
User user = optionalUser.get();
```

### 2-3. 이전에 없었던 새로운 문제들이 발생할 수 있음
- Optional을 사용하며 발생하는 대표적인 문제 중 하나는 직렬화(Serialize)로, Optional은 기본적으로 직렬화를 지원하지 않아 캐시나 메시지큐 등과 연동할 때 문제가 발생할 수 있음
- Jackson처럼 라이브러리에 Optional이 있을 경우 값을 wrap, unwrap 하도록 지원해주긴 하지만, 해당 라이브러리 스펙을 파악하고 사용해야함

### 2-4. 코드의 가독성이 떨어질 수도 있음
- 아래 코드의 경우, optionalUser의 값이 비어있으면 NoSuchElementException이 발생할 수 있으므로 값의 유무를 검사하도록 작성되었음

```java
public void temp(Optional<User> optionalUser) {
    User user = optionalUser.orElseThrow(IllegalStateException::new);
    
    // 이후의 후처리 작업 진행...
}
```

- 하지만, optionalUser 객체 자체가 null일 수도 있기 때문에 아래와 같이 코드를 수정해야함

```java
public void temp(Optional<User> optionalUser) {
    if (optionalUser != null && optionalUser.isPresent()) {
        // 이후의 후처리 작업 진행...
    }
    
    throw new IllegalStateException();
}
```

- 위의 코드는 값의 유무를 2번 검사하게 되므로, 단순히 null을 사용했을 때보다 코드가 복잡해졌음
- 따라서, Optional을 올바르게 사용하지 못 할 경우 가독성이 떨어질 수 있음

### 2-5. 시간적, 공간적 비용(오버헤드) 증가
- 시간적 비용
  - Optional 안에 있는 객체를 얻기 위해서 Optional 객체를 통해 접근해야 하기 때문에 접근 비용이 증가함
- 공간적 비용
  - Optional은 객체를 감싸는 컨테이너이기 때문에 Optional 객체를 저장하기 위한 메모리가 추가로 필요함

## 3. 올바른 Optional의 사용

### 3-1. Optional 변수에 null 할당하지 않기
- Optional 변수에 null을 할당하는 것은 Optional 변수가 null인지 또 검사해야 하므로, 값이 없는 경우라면 Optional.empty()로 초기화하는 것이 좋음

### 3-2. 값이 없을 때 Optional.orElseXXX()로 기본 값 반환하기
- 가급적이면 isPresent()로 값을 검사하고, 값을 꺼낼 땐 get()보다는 orElseGet()을 활용하자
- orElseGet() : 값이 준비되어 있지 않은 경우 사용
- orElse() : 값이 준비되어 있는 경우 사용
- orElse(null) : null 값을 반환해야 하는 경우 사용
- orElseThrow() : 값이 없어 throw 해야 하는 경우 사용

```java
private String findDefaultName() {
    return ...;
}

// AVOID
public String findUserName(long id) {

    Optional<String> optionalName = ... ;

    if (optionalName.isPresent()) {
        return optionalName.get();
    } else {
        return findDefaultName();
    }
}

// PREFER
public String findUserName(long id) {

    Optional<String> optionalName = ... ;
    return status.orElseGet(this::findDefaultName);
}
```

### 3-3. 단순히 값을 얻으려는 목적으로만 Optional을 사용하지 말기
- 단순히 값을 얻으려고 Optional을 사용하는 것은 Optional을 남용하는 대표적인 예시이며, 이러한 경우 직접 값을 다루는 것이 더 효율적일 수 있음

```java
// AVOID
public String findUserName(long id) {
    String name = ... ;
    
    return Optional.ofNullable(name).orElse("Default");
}

// PREFER
public String findUserName(long id) {
    String name = ... ;
    
    return name == null ? "Default" : name;
}
```

### 3-4. 생성자, 수정자, 메소드 파라미터 등으로 Optional 넘기지 말기
- Optional을 파라미터로 넘기는 것은 넘겨온 파라미터를 위해 null 체크를 추가하고, 코드도 복잡해지는 등의 문제가 있어 효율적이지 않음
- Optional은 반환 타입으로 대체 동작을 사용하기 위해 고안된 것이기 때문에, Serializable을 구현하지 않으므로 필드 값으로 사용하지 않아야 함
- Optional을 geeter에 사용하는 것 또한 남용하는 것의 예시

```java
// AVOID
public class User {

    private final String name;
    private final Optional<String> postcode;

    public Customer(String name, Optional<String> postcode) {
        this.name = Objects.requireNonNull(name, () -> "Cannot be null");
        this.postcode = postcode;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
    
    public Optional<String> getPostcode() {
        return postcode;
    }
}
```

### 3-5. Collection의 경우 Optional이 아닌 빈 Collection 사용하기
- Optional은 시간적, 공간적 비용이 꽤 들기 때문에 최대한 사용을 지양하고, 정확히 써야할 때만 써야함

```java
// AVOID
public Optional<List<User>> getUserList() {
    List<User> userList = ...; // null이 올 수 있음

    return Optional.ofNullable(items);
}

// PREFER
public List<User> getUserList() {
    List<User> userList = ...; // null이 올 수 있음

    return items == null ? Collections.emptyList() : userList;
}
```

```java
// AVOID
public Map<String, Optional<String>> getUserNameMap() {
    Map<String, Optional<String>> items = new HashMap<>();
    items.put("I1", Optional.ofNullable(...));
    items.put("I2", Optional.ofNullable(...));
    
    Optional<String> item = items.get("I1");
    
    if (item == null) {
        return "Default Name"
    } else {
        return item.orElse("Default Name");
    }
}

// PREFER
public Map<String, String> getUserNameMap() {
    Map<String, String> items = new HashMap<>();
    items.put("I1", ...);
    items.put("I2", ...);
    
    return items.getOrDefault("I1", "Default Name");
}
```

### 3-6. Optional은 반환 타입으로만 사용하기
- Optional은 반환 타입에서 에러가 발생할 수 있는 경우 결과가 없음을 명확하게 나타내기 위해 만들어졌으며, Stream API와 결합되어 유연한 체이닝 API를 만들기 위해 탄생한 것
- 즉, 값을 반환할 때 null을 반환하는 것보다 값의 유무를 나타내는 객체를 반환하는 것이 더 효율적인 경우 사용

## 4. Optional의 주요 메소드

### 4-1. Optional.empty()
- Optional은 wrapper 클래스이기 때문에 값이 없을 수도 있는데, 이때 Optional.empty()로 생성할 수 있음

```java
Optional<String> optional = Optional.empty();

System.out.println(optional); // Optional.empty
System.out.println(optional.isPresent()); // false
```

- Optional 클래스는 내부에서 static 변수로 EMPTY 객체를 미리 생성해서 가지고 있기 때문에, 빈 객체를 여러 번 생성하는 경우에도 1개의 EMPTY 객체를 공유함으로 메모리를 절약하고 있음

```java
public final class Optional<T> {

    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;
    
    private Optional() {
        this.value = null;
    }

    ...

}
```

### 4-2. Optional.of()
- 값이 절대 null이 아닌 경우 사용하며, 만약 Optional.of()로 null을 저장하려 하면 NPE가 발생함

```java
// Optional의 value는 절대 null이 아님
Optional<String> optional = Optional.of("MyName");
```

### 4-3. Optional.ofNullable()
- 값이 null일 수도, 아닐 수도 있는 경우 사용하며, 이후 orElse()나 orElseGet() 메소드를 통해 값이 없는 경우라도 안전하게 값을 가져올 수 있음

```java
// Optional의 value는 값이 있을 수도 있고 null 일 수도 있음
Optional<String> optional = Optional.ofNullable(getName());
String name = optional.orElse("anonymous"); // 값이 없다면 "anonymous" 를 리턴
```


### 4-4. orElse() & orElseGet()
- orElse()
  - 파라미터로 값을 받음
  - 값이 존재하는 경우에 사용
  - 값이 null이든 아니든 실행

```java
public void findUserEmailOrElse() {
    String userEmail = "Empty";
    String result = Optional.ofNullable(userEmail)
    	.orElse(getUserEmail());
        
    System.out.println(result);
}

private String getUserEmail() {
    System.out.println("getUserEmail() Called");
    return "eminem@hiphop.com";
}

/* 처리 과정
1. Optional.ofNullable로 "EMPTY"를 갖는 Optional 객체 생성
2. getUserEmail()이 실행되어 반환값을 orElse 파라미터로 전달
3. orElse가 호출됨, "EMPTY"가 Null이 아니므로 "EMPTY"를 그대로 가짐
*/

/* 출력 결과
getUserEmail() Called
Empty
*/
```

- orElseGet()
  - 파라미터로 함수형 인터페이스를 받음
  - 값이 존재하지 않는 거의 대부분의 경우에 사용
  - 값이 null일때만 실행
 
```java
public void findUserEmailOrElseGet() {
    String userEmail = "Empty";
    String result = Optional.ofNullable(userEmail)
    	.orElseGet(this::getUserEmail);
        
    System.out.println(result);
}

private String getUserEmail() {
    System.out.println("getUserEmail() Called");
    return "eminem@hiphop.com";
}

/* 처리 과정
1. Optional.ofNullable로 "EMPTY"를 갖는 Optional 객체 생성
2. getUserEmail() 함수 자체를 orElseGet 파라미터로 전달
3. orElseGet이 호출됨, "EMPTY"가 Null이 아니므로 "EMPTY"를 그대로 가지며 getUserEmail()이 호출되지 않음
*/

/* 출력 결과
Empty
*/
```

- orElseGet에서는 파라미터로 넘어간 값인 getUserEmail 함수가 null이 아니므로 get()에 의해 함수가 호출되지 않지만, Optional의 값으로 null이 있다면 orElseGet의 파라미터로 넘어온 getUserEmail이 실행됨

```java
public void findUserEmailOrElseGet() {
    String result = Optional.ofNullable(userEmail)
    	.orElseGet(this::getUserEmail);
        
    System.out.println(result);
}

private String getUserEmail() {
    System.out.println("getUserEmail() Called");
    return "eminem@hiphop.com";
}

/* 처리 과정
1. Optional.ofNullable로 null를 갖는 Optional 객체 생성
2. getUserEmail() 자체를 orElseGet 파라미터로 전달
3. orElseGet이 호출됨, 값이 Null이므로 other.get()이 호출되어 getUserEmail()가 호출됨
*/
```

- orElse()에 의한 발생가능한 장애 예시

```java
public void findByUserEmail(String userEmail) {
    // userEmail이 Unique한 경우 에러 발생
    return userRepository.findByUserEmail(userEmail)
            .orElse(createUserWithEmail(userEmail));
}

// 조회 결과와 무관하게 아래의 함수가 반드시 실행됨
private String createUserWithEmail(String userEmail) {
    User newUser = new User(userEmail);
    return userRepository.save(newUser);
}
```

```java
// 파라미터로 createUserWithEmail 함수 자체가 넘어가게 됨
// 조회 결과가 없을 경우에만 사용자를 생성하는 로직 호출
public void findByUserEmail(String userEmail) {
    // orElseGet에 의해 파라미터로 함수를 넘겨주므로 Null이 아니면 유저 생성 함수가 호출되지 않음
    return userRepository.findByUserEmail(userEmail)
           .orElseGet(createUserWithEmail(userEmail));
}

private String createUserWithEmail(String userEmail) {
    User newUser = new User(userEmail);
    return userRepository.save(newUser);
}
```

### 4-5. isPresent() & ifPresent()
- isPresent()
  - Boolean 타입으로, Optional 객체가 값을 가지고 있으면 true, 없으면 false 반환
```java
@Test
void optionalTest() {
    String name = "eminem";

    Optional<User> user = userRepository.findByName(name);
    if (user.isPresent()) {
        System.out.println("이미 존재하는 이름");
    } else {
        System.out.println("사용가능한 이름");
    }
}
```
- ifPresent()
  - void 타입으로, Optional 객체가 값을 가지고 있으면 실행, 없으면 예외처리
```java
@Test
void optionalTest() {
    Long id = 4L;

    userRepository.findById(id).ifPresent(a -> {
        throw new BadRequestException("이미 존재하는 이름");
    })
    
    System.out.println("사용가능한 이름");
}
```

## 5. Optional의 활용 예시

### 5-1. 예시 1
- 기존에는 null 검사를 한 뒤, null인 경우 새로운 객체를 생성해야 했지만, Optional과 lambda를 사용하여 아래와 같이 간단하게 표현할 수 있음

```java
// Java8 이전
List<String> names = getNames();
List<String> tempNames = list != null ? list : new ArrayList<>();

// Java8 이후
List<String> nameList = Optional.ofNullable(getNames())
    .orElseGet(() -> new ArrayList<>());
```

### 5-2. 예시 2
- 복잡한 null 검사 코드를 아래와 같이 간결하게 작성할 수 있음

```java
// Java8 이전
public String findPostCode() {
    UserVO userVO = getUser();
    if (userVO != null) {
        Address address = user.getAddress();
        if (address != null) {
            String postCode = address.getPostCode();
            if (postCode != null) {
                return postCode;
            }
        }
    }
    return "우편번호 없음";
}

// Java8 이후
public String findPostCode() {
    // 위의 코드를 Optional로 펼쳐놓으면 아래와 같다.
    Optional<UserVO> userVO = Optional.ofNullable(getUser());
    Optional<Address> address = userVO.map(UserVO::getAddress);
    Optional<String> postCode = address.map(Address::getPostCode);
    String result = postCode.orElse("우편번호 없음");

    // 그리고 위의 코드를 다음과 같이 축약해서 쓸 수 있다.
    String result = user.map(UserVO::getAddress)
        .map(Address::getPostCode)
        .orElse("우편번호 없음");
}
```

### 5-3. 예시 3
- 대문자로 값을 변경하는 코드에서 NPE 처리를 하는 경우, Optional을 활용하여 가독성이 높게 작성할 수 있음

```java
// Java8 이전
String name = getName();
String result = "";

try {
    result = name.toUpperCase();
} catch (NullPointerException e) {
    throw new CustomUpperCaseException();
}

// Java8 이후
Optional<String> nameOpt = Optional.ofNullable(getName());
String result = nameOpt.orElseThrow(CustomUpperCaseExcpetion::new)
                  .toUpperCase();
```

---

참고 1 : https://mangkyu.tistory.com/70

참고 2 : https://mangkyu.tistory.com/203

참고 3 : https://cfdf.tistory.com/34