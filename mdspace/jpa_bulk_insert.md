# JPA Bulk Insert

## 1. Bulk Insert란?

- 단일 쿼리
    
    ```sql
    INSERT INTO table1 (col1, col2) VALUES (val11, val12);
    INSERT INTO table1 (col1, col2) VALUES (val21, val22);
    INSERT INTO table1 (col1, col2) VALUES (val31, val32);
    ```
    
- Bulk Insert
    
    ```sql
    INSERT INTO table1 (col1, col2) VALUES
    (val11, val12),
    (val21, val22),
    (val31, val32);
    ```
    
- Bulk Insert는 위의 예시 처럼 여러 쿼리를 하나로 묶어서 처리하는 방식으로 잘 사용한다면 많은 성능을 올릴 수 있음

### 1-1. Bulk Insert & Spring Data JPA & AUTO_INCREMENT의 관계

- Entity의 id 생성 전략이 AUTO_INCREMENT인 경우, Hibernate가 JDBC 수준에서 Batch Insert를 비활성화 함
- 즉, Spring Data JPA 환경에서 id가 AUTO_INCREMENT인 경우 Batch Insert를 사용할 수 없음
- 또한, MySQL SEQUENCE를 제공하지 않음
- 즉, Bulk Insert를 구현하기 위해서는 JPA를 사용하지 않고, JDBC의 AUTO_INCREMENT 방식이 아닌 채번 전략으로 Bulk Insert를 구현해야 함

## 2. Batch 채번 전략

- 채번 자체를 Batch로 처리하면 Bulk Insert가 가능
    
    ```java
    @Id
    @GenericGenerator(
    		name = "SequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
    		    @Parameter(name = "sequence_name", value = "hibernate_sequence"),
            @Parameter(name = "optimizer", value = "pooled"),
            @Parameter(name = "initial_value", value = "1"),
            @Parameter(name = "increment_size", value = "500")
       }
    )
    
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
        generator = "SequenceGenerator"
    )
    private Long id;
    ```
    
- 하지만, 위의 예시는 saveAll과 같은 함수를 JPA Repository를 extends한 Repository에서는 사용이 불가하고 JDBC를 사용해야 함

## 3. Spring Data JDBC

- JdbcTemplate에는 Batch를 지원하는 batchUpdate() 메서드가 존재 (JdbcTemplate.batchUpdate())
- Repository 구성
    - batchSize 변수로 배치 크기 지정
    - 전체 데이터를 배치 크기만큼 나눠서 Batch Insert를 실행
    - 남은 데이터를 Batch Insert로 저장
    
    ```java
    @Repository
    @RequiredArgsConstructor
    public class ItemJdbcRepositoryImpl implements ItemJdbcRepository {
    
        private final JdbcTemplate jdbcTemplate;
    
        @Value("${batchSize}")
        private int batchSize;
    
        public void saveAll(List<ItemJdbc> items) {
            int batchCount = 0;
            List<ItemJdbc> subItems = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                subItems.add(items.get(i));
                if ((i + 1) % batchSize == 0) {
                    batchCount = batchInsert(batchSize, batchCount, subItems);
                }
            }
            if (!subItems.isEmpty()) {
                batchCount = batchInsert(batchSize, batchCount, subItems);
            }
            System.out.println("batchCount: " + batchCount);
        }
    
        private int batchInsert(int batchSize, int batchCount, List<ItemJdbc> subItems) {
            jdbcTemplate.batchUpdate("INSERT INTO ITEM_JDBC (`NAME`, `DESCRIPTION`) VALUES (?, ?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, subItems.get(i).getName());
                            ps.setString(2, subItems.get(i).getDescription());
                        }
                        @Override
                        public int getBatchSize() {
                            return subItems.size();
                        }
                    });
            subItems.clear();
            batchCount++;
            return batchCount;
        }
    }
    ```
    

## 결론

- 1만 건 이상의 데이터를 Insert할 땐 Bulk Insert를 사용해야 하는데, 이 때에는 Spring Data JDBC의 batchUpdate를 사용하자
    - Spring Data JPA와 혼용해서 Bulk Insert만 JDBC를 활용하는 전략
    - Transactional로 관리 될 수 있음
- 참고로 하루 100만 건 이하인 경우 JPA를 써도 무방함