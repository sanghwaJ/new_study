# Java - split() & StringTokenizer

## 1. split() & StringTokenizer 비교
- split(), StringTokenizer 모두 문자열을 원하는 구분자를 사용하여 분리하고 싶을 때 사용
- split()은 정규표현식으로 구분, StringTokenizer는 문자나 문자열로 구분 
- split()은 정규표현식을 사용하기 때문에 속도적인 측면에서 StringTokenizer가 더 좋지만, 가변적이 요소가 많은 문자열이나 정확한 분리가 필요할 땐 split()을 사용하는 것이 더 유리
- split()은 결과 값이 문자열 배열, StringTokenizer는 결과 값이 문자열 (따라서, StringTokenizer를 이용할 경우 전체 토큰을 보려면 반복문을 사용해야 함)

## 2. split() & StringTokenizer 사용 예시
```java
public class StringDivideTest {
    public static void splitTest(String str) {
        System.out.println("====== splitTest() result ======");
        String split[] = str.split(",");
        for (int i = 0; i < split.lenght; i++) {
            System.out.println(i + "번째 : " + split[i]);
        }
    }
    
    public static void stringTokenizerTest(String str) {
        System.out.println("====== stringTokenizerTest() result ======");
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        for (int i = 0; tokenizer.hasMoreTokens(); i++) {
            System.out.println(i + "번째 : " + tokenizer.nextToken());
        }
    }
 
    public static void main(String[] args) {
        String str = "apple,banana,kiwi";
        splitTest(str);
        System.out.println();
        stringTokenizerTest(str);
    }
}
```

### 2-1. 일반적인 상황 (데이터 + 구분자 + 데이터)
- String str = "apple,banana,kiwi";
- 둘 다 동일하게 동작함

    <결과><br/>
    ======= splitTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi <br/>
    ======= stringTokenizerTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi<br/>

    

### 2-2. 구분자 사이에 데이터가 없는 경우 (데이터 + 구분자 + 구분자 + 데이터)
- String str = "apple,banana,,kiwi";
- split()만 중간의 공백 데이터를 반환함
    
    <결과><br/>
    ======= splitTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : <br/>
    3번째  : kiwi<br/>
    ======= stringTokenizerTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi<br/>

### 2-3. 문자열이 구분자로 끝나는 경우 (데이터 + 구분자)
- String str = "apple,banana,kiwi,";
- 둘 다 마지막 데이터를 무시함
    
    <결과><br/>
    ======= splitTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi<br/>
    ======= stringTokenizerTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi<br/>

### 2-4. 문자열이 구분자로 끝나지만 (데이터 + 구분자), 마지막 데이터를 표시하고 싶은 경우
- String str = "apple,banana,kiwi,";
- String split[] = str.split(",", -1);
   
    <결과><br/>
    ======= splitTest() result =======<br/>
    0번째  : apple<br/>
    1번째  : banana<br/>
    2번째  : kiwi<br/>
    3번째  : <br/>
    ======= stringTokenizerTest() result =======<br/>
    0번째 : apple<br/>
    1번째 : banana<br/>
    2번째 : kiwi<br/>

 



 
    