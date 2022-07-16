# Java - String & StringBuffer & StringBuilder

<p align="center"><img src="../imagespace/javatips3.jpg"></p>

- String VS StringBuffer VS StringBuilder
    - String : 문자열 연산이 적고 조회가 많은 멀티스레드 환경인 경우 사용
    - StringBuffer : 문자열 연산이 많고 멀티스레드 환경인 경우 사용 (Thread-Safe 함)
    - StringBuilder : 문자열 연산이 많고 단일스레드이거나 동기화를 고려하지 않아도 되는 경우 사용 (Thread-Safe 하지 않음)

```java
// StringBuffer
String example1 = "abcdefg";
StringBuffer sbf = new StringBuffer(example1); // String -> StringBuffer
         
System.out.println(sbf); //처음상태
System.out.println(sbf.toString()); //String 변환
System.out.println(sbf.substring(2,4)); //문자열 추출
System.out.println(sbf.insert(2,"추가")); //문자열 추가
System.out.println(sbf.delete(2,4)); //문자열 삭제
System.out.println(sbf.append("hijk")); //문자열 붙이기
System.out.println(sbf.length()); //문자열 길이
System.out.println(sbf.capacity()); //용량 크기
System.out.println(sbf.reverse()); //문자열 뒤집기
System.out.println(sbf); //마지막상태
sbf.setLength(0); // 초기화

// StringBuilder
String example2 = "abcdefg";
StringBuilder sbb = new StringBuilder(example2); // String -> StringBuilder
		
System.out.println(sbb); //처음상태
System.out.println(sbb.toString()); //String 변환
System.out.println(sbb.substring(2,4)); //문자열 추출
System.out.println(sbb.insert(2,"추가")); //문자열 추가
System.out.println(sbb.delete(2,4)); //문자열 삭제
System.out.println(sbb.append("hijk")); //문자열 붙이기
System.out.println(sbb.length()); //문자열 길이
System.out.println(sbb.capacity()); //용량 크기
System.out.println(sbb.reverse()); //문자열 뒤집기
System.out.println(sbb); //마지막상태
sbb.setLength(0); // 초기화
```