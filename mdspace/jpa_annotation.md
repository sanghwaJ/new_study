# JPA Annotation 정리

## 1. 객체와 테이블 매핑

### 1-1. @Entity
- DB 테이블과 1:1로 매칭되는 객체 단위
- Entity 객체의 인스턴스 하나는 테이블에서 하나의 레코드 값을 의미하며, 테이블과 매핑할 클래스에 필수로 붙여줘야 함
- 객체의 인스턴스를 구분하기 위한 유일한 키 값은 테이블 상의 PK와 같은 의미를 가지며, @Id를 통해 표기됨

```java
@Entity
@Table(name = "Member")
public class Member {
     ...
}


@Entity
public class MemberA { 
    ...
}
```

### ※ @Entity 적용 시 주의사항
- 기본 생성자는 필수 (파리미터가 없는 public or protected 생성자)
- final 클래스나 enum, interface, inner 클래스에서는 사용 불가
- 저장할 필드에 final은 사용할 수 없음

### 1-2. @Table
- Entity와 매핑할 테이블을 지정하는 Annotation으로, 이를 생략하면 Entity이름을 테이블 이름으로 사용함

```java
@Entity
@Table(name = "Member")
public class Member {
     ...
}


@Entity
public class MemberA { 
    ...
}
```

## 2. 기본키 매핑

### 2-1. @Id
- DB에서 유일한 값을 가지는 것을 PK라고 하는데, DB는 이러한 유일한 키 값을 통해 질의한 데이터를 추출, 반환하게 됨
- JPA에서도 Entity 클래스 상에 PK를 명시적으로 표시하기위해 @Id Annotation을 사용하여 PK임을 지정함


### 2-2. @GeneratedValue

- @GeneratedValue(strategy=GenerationType.IDENTITY) 
  - 기본키 생성을 DB에 위임하는 방식으로, id 값을 따로 할당하지 않아도 DB가 AUTO_INCREMENT를 하여 기본키를 생성해줌
  - IDENTITY 전략은 Data를 DB에 Insert한 후 기본 키 값을 조회할 수 있으며, 식별자 값을 할당하기 위해 Statement.getGeneratedKeys()를 사용하여 DB와 통신함
  - Entity가 영속 상태가 되려면 식별자가 반드시 필요한데, IDENTITY 전략은 DB에 저장해야만 식별자를 구할 수 있는 방식이므로, Transaction을 지원하는 쓰기 지연 방식이 동작하지 않음 (em.persist() 호출 즉시 Insert Query를 DB에 전달하며 식별자를 가져오기 때문)

```java
@Entity
public class PkEx() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    private String name;            
}
```

- @GeneratedValue(strategy=GenerationType.SEQUENCE)
  - 데이터베이스의 Sequence Object를 사용하여 DB가 자동으로 기본키를 생성해주며, @SequenceGenerator 어노테이션이 필요함
  - SEQUENCE 전략은 DB Sequence를 사용해 기본키를 할당하며, allocationSize를 통해 성능 최적화를 할 수 있음
  - allocationSize를 50으로 지정한 경우, 50만큼 한 번에 sequence를 증가시키고 그만큼 메모리에 sequence 값을 할당하여 메모리를 활용해 JVM 안에서 sequence를 할당함

```java
@Entity
@SequenceGenerator(
	name = "USER_PK_GENERATOR",
    sequenceName = "USER_PK_SEQ",
    initailValue = 1,
    allocationSize = 50
)

public class PkEx() {		
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_PK_GENERATOR")
    private Long id;
        
    private String name;          
}
```

- IDENTITY vs SEQUENCE
  - IDENTITY ⇒ 먼저 Entity를 DB에 저장한 후, 식별자를 조회하고 Entity에 식별자를 할당
  - SEQUENCE ⇒ em.persist(0 호출 전에 먼저 DB Sequence를 조회하고, 조회한 식별자를 Entity에 할당한 후, Entity를 영속상태로 저장, 마지막으로 Transaction을 commit하여 flush가 발생할 때 해당 Entity DB에 저장

- @GeneratedValue(strategy=GenerationType.TABLE)
```sql
CREATE TABLE CUSTOM_SEQUENCE {
	sequence_name varchar(255) not null
    , next_val bigint
    , primary key (sequence_name)
}
```
```java
@Entity
@TableGenerator(
	name = "USER_SEQ_GENERATOR"
  , table = "CUSTOM_SEQUENCE"
  , pkColumnValue = "USER_SEQ"
  , allocationSize = 1
)

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_SEQ_GENERATOR")
  private long id;
}
```

### 2-3. @Embeddable & @EmbeddedId
- 복합키로서 테이블의 PK를 정의하는 경우 사용하는 Annotation으로 직접 Entity에 정의하는 것이 아니라 별도의 Value를 사용해 정의함
- 먼저 Value 를 생성한 다음, @Embeddable를 이용해 Value 가 Entity 에 삽입이 가능함을 명시 한 뒤, Entity 에서는 @EmbeddedId를 이용해 이 Entity에 해당 Value 를 PK로 사용한다고 지정함

```java
@Embeddable
public class CompanyOrganizationKey implements Serializable {
	@Column(name = "company_code")
    private String companyCode;
    
    @Column(name = "organization_code")
    private String organizationCode;
}

@Entity(name = "company_organization")
public class CompanyOrganization {
	@EmbeddedId
    protected CompanyOrganizationKey companyOrganizationKey;
}
```

### 2-4. @NoArgsConstructor & @AllArgsConstructor & @RequiredArgsConstructor

- @NoArgsConstructor ⇒ 파라미터 없는 기본 생성자를 만들어줌 (final이 붙은 필드는 에러 발생)
- @AllArgsConstructor ⇒ 필드에 쓴 모든 생성자를 만들어줌
- @RequiredArgsConstructor ⇒ final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듬

```java
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
  
    @NonNull
    private String name;
  
    @NonNull
    private String pw;
  
    private int age;
}

User user1 = new User(); // @NoArgsConstructor
User user2 = new User(1L, "user2", "1234", null); // @AllArgsConstructor
User user3 = new User("user3", "1234"); // @RequiredArgsConstructor
```

## 3. 필드와 컬럼 매핑

### 3-1. @Column
- @Column Annotation을 사용하면 DB 테이블에 있는 컬럼과 동일하게 매칭되기 때문에 Entity 클래스 안에 내부 변수로 정의됨
  - 예시) 만약 테이블에 a, b, c 컬럼이 있다면 각각 3개의 @Column Annotation을 작성 하게 되며, 이때 의도적으로 필요없는 컬럼들은 작성하지 않아도 됨(실제 a, b, c, d 총 4개의 컬럼이 있더라도 a, b, c 컬럼만 Entity 클래스에 작성해도 무방)
- @Column Annotation은 별다른 옵션을 설정하지 않는다면 생략이 가능 (즉 Entity 클래스에 정의된 모든 내부변수는 기본적으로 @Column Annotation을 포함하고 있음)

```java
@Column
private String code;

@Column(length = 100)
private String name;

// @Column 은 생략 가능
private String desctiption;

@Column(precision = 11, scale = 2)
private BigDecimal amount;

@Column
private Integer size;

@Column(name = "register_date")
private LocalDateTime registerDate;
```

## 4. 연관 관계 매핑

### 4-1. @ManyToOne : 다대일

- 다대일 단방향 예시
  - Member는 Member.team으로 Team Entity를 참조할 수 있지만, 반대로 Team에는 Member를 참조하는 필드가 없음
```java
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    private String username;
    
    @ManyToOne // 다대일
    @JoinColumn(name = "TEAM_ID") // Member.team 필드를 TEAM_ID FK와 매핑
    private Team team;
    
    ...
}
```
```java
@Entity
public class Team {
	
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    
    private String name;
    
    ...
}
```

- 다대일 양방향 예시
  - @OneToMany(mappedBy = "team")를 통해 연관관계의 주인(외래키를 관리하는 필드인 Member의 team 필드)을 명시함
```java
@Entity
public class Team {
	
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    
    ...
}
```

### 4-2. @OneToMany : 일대다

- 일대다 단방향 예시
  - 일대다 단방향은 Team Entity의 Team.member로 Member 테이블의 TEAM_ID 외래키를 관리함 (외래키가 항상 "다" 쪽의 테이블에 존재하기 때문에 반대편 테이블의 외래키를 관리함)
  - 이 때, @ManyToOne과 다른 점은 mappedBy 속성이 없다는 것인데, Team이 연관관계의 주인이기 때문
  - 만약 @JoinColumn을 사용하지 않으면 JPA는 연결 테이블을 중간에 두고 연관관계를 관리하는 Join Table 전략을 기본으로 사용하여 매핑하기 때문에 꼭 아래의 경우 @JoinColumn을 사용해주어야 함
```java
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    private String username;
    
    ...
}
```
```java
@Entity
public class Team {
	
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    
    private String name;
    
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();
    
    ...
}
```

- 일대다 양방향은 존재하지 않음 (다대일 양방향과 같은 말)

### ※ 일대다 단방향의 문제점
- 일대다 단방향 매핑을 사용하면 자신이 매핑된 테이블이 아닌 다른 테이블의 외래키를 관리하게 되어 유지보수 측면이나 성능적으로 문제가 발생할 수 있음
- 따라서 일대다 단방향 매핑보다는 다대일 양방향을 사용하는 것이 더 권장됨


### 4-3. @OneToOne : 일대일
- 양쪽이 서로 하나의 관계만을 가지는 경우

- 일대일 단방향
  - DB에서 LOCKER_ID(FK)에 유니크 제약 조건을 추가해야 함
```java
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    
    private String username;
    
    
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
```
```java
@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;
}
```

- 일대일 양방향
```java
@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;
    
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member memeber;
}
```
```java
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;
    
    @OneToOne(mappedBy = "member")
    private Locker locker;
}
```

### ※ 외래키로 매핑할 컬럼 지정하기

- @JoinColumn에서 referencedColumnName를 통해 대상 테이블의 어떠한 컬럼을 FK로 사용할지 지정할 수 있음

```java
@Entity
public class Order {

    @Id @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    @ManyToOne 
    // referencedColumnName = "name"이며, name = "fk_name"인 경우
    // 대상 테이블의 name 컬럼의 값을 FK로 사용하며, fk_name 컬럼에 저장
    @JoinColumn(name = "member_id", referencedColumnName="name") 
    private Member member;
    
    ...
}
```

## 5. 기타

### 5-1 @ToString
```java
@ToString(exclude = "password")
public class User {
    private Long id;
    private String username;
    private String password;
    private int[] scores;
}

User user = new User();
user.setId(1L);
user.setUsername("dale");
user.setUsername("1234");
user.setScores(new int[]{80, 70, 100});
System.out.println(user); // User(id=1, username=1234, scores=[80, 70, 100])
```


---
### 참고
https://gwonbookcase.tistory.com/37

https://velog.io/@gillog/JPA-%EA%B8%B0%EB%B3%B8-%ED%82%A4-%EC%83%9D%EC%84%B1-%EC%A0%84%EB%9E%B5IDENTITY-SEQUENCE-TABLE

https://velog.io/@gudnr1451/GeneratedValue-%EC%A0%95%EB%A6%AC

https://ddol.tistory.com/42

https://ttl-blog.tistory.com/129