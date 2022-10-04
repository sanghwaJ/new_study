# Java - JUnit

## 1. 유닛 테스트
- 프로그래밍에서 모든 함수와 메소드에 대한 테스트 케이스를 작성하여, 의도한대로 잘 동작하는지 검증하는 절차
- 프로그램을 작은 단위로 쪼개어 각 단위가 정확하게 동작하는지 검사하여 프로그램의 안정성을 높임
- System.out.println()으로 하는 번거로운 디버깅이 필요없으며, 개발기간 중 대부분을 차지하는 디버깅 시간을 단축

## 2. JUnit
- Java 프로그래밍 언어용 유닛 테스트 프레임워크
- 테스트 결과는 Test 클래스로 개발자에게 테스트 방법과 클래스의 History를 공유 가능
- 단정(assert) 메소드로 테스트 케이스의 수행 결과를 판별
- 어노테이션으로 간결하게 지원(JUnit4부터)

## 3. 주요 단정 메소드
- assertArrayEquals(a, b) : 배열 A와 B가 일치함을 확인
- assertEquals(a, b) : 객체 A와 B가 같은 값을 가지는지 확인
- assertEquals(a, b, c) : 객체 A와 B가 값이 일치함을 확인 (a : 예상값, b : 결과값, c : 오차범위)
- assertSame(a, b) : 객체 A와 B가 같은 객체임을 확인
- assertTrue(a) : 조건 A가 참인지 확인
- assertNotNull(a) : 객체 A가 null이 아님을 확인

### ※ 참고
- 최근 트랜드는 JUnit의 assertXXX보다 AssertJ의 assertThat(actual).isEqualTo(expected)이 가독성이 좋아 더 많이 사용하는 추세
```java
// 1. jUnit assertEquals
assertEquals(expected, actual); 

// 2. asserJ assertThat
// 실제값이 기대값과 같은지 검증
assertThat(actual).isEqualTo(expected);
// 실제값이 기대값과 다른지 검증
assertThat(actual).isNotEqualTo(expected);
```

## 4. 기본 Annotation
- @Test : 테스트를 만드는 모듈 역할
- @DisplayName : 테스트 클래스 또는 테스트 메소드의 사용자 정의 표시 이름을 정의
- @ExtendWith : 사용자 정의 확장명을 등록하는데 사용
- @BeforeEach : 각 테스트 메소드 전에 실행됨을 나타냄
- @AfterEach : 각 테스트 메소드 후에 실행됨을 나타냄
- @BeforeAll : 현재 클래스의 모든 테스트 메소드 전에 실행됨을 나타냄
- @AfterAll : 현재 클래스의 모든 테스트 메소드 후에 실행됨을 나타냄
- @Disable : 테스트 클래스 또는 메소드를 비활성화

참고 : https://velog.io/@ehddek/JUnit-%EC%9D%B4%EB%9E%80