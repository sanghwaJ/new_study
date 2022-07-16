# Java - 정규표현식 (Pattern & Matcher)

## 1. String.matches() & String.contains()

### 1-1. String.matches()
- matches()는 인자로 정규표현식을 받으며, 문자열과 패턴이 일치하는지를 true나 false로 반환한다.

```Java
String str = "HelloWorld";

System.out.println(str.matches("^[A-Za-z]*$")); // true
```

### 1-2. String.contains()
- contains()는 인자로 문자열을 받으며, 해당 문자열이 비교 문자열에 존재하는지를 true나 false로 반환한다.

```Java
String str = "HelloWorld";

System.out.println(str.contains("World")); // true
```

## 2. Pattern 클래스
- java.util.regex.pattern 클래스의 matches() 활용
- 검증 후 대상 문자열이 정규표현식과 일치하면 true, 그렇지 않다면 false 리턴
- 문자열로 정의된 정규표현식은 사용되기 전에 Pattern 클래스의 인스턴스로 컴파일 되어야 함(Pattern.compile())
- 컴파일된 패턴은 Matcher 객체를 만드는데 사용되며, Matcher 객체는 임의의 입력 문자열이 패턴에 부합되는지 여부를 체크함

```Java
import java.util.regex.Pattern

public class RegexEx {
    public static void main(String[] args) {
        String pattern = "^[0-9]*$"; // 숫자만
        String value = "123456789";

        boolean regex = Pattern.matches(pattern, value);
        
        System.out.println(regex); // true
    }
}
```

<br/>

## 3. Pattern 클래스의 주요 메소드
- compile(String regex) : 주어진 정규표현식으로부터 패턴을 만듬
- matcher(CharSequence input) : 대상 문자열이 패턴과 일치할 경우 true 반환
- asPredicate() : 문자열을 일치시키는 데 사용할 수 있는 술어 작성
- pattern() : 컴파일된 정규표현식을 String 형태로 반환
- split(CharSequence input) : 문자열을 주어진 인자값 CharSequence 패턴에 따라 분리

<br/>

## 4. Matcher 클래스
- 대상 문자열의 패턴을 해석하고, 주어진 패턴과 일치하는지 판별할 때 사용
- Matcher 객체는 Pattern 객체의 matcher() 메소드를 호출 받아 올 수 있음

```Java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
	public static void main(String[] args)  {
            Pattern pattern = Pattern.compile("^[a-zA-Z]*$"); //영문자만
            String val = "abcdef"; //대상문자열
	
            Matcher matcher = pattern.matcher(val);
            System.out.println(matcher.find());
	}
}
```

<br/>

## 5. Matcher 클래스의 주요 메소드
- matches() : 대상 문자열과 패턴이 일치할 경우 true 반환
- find() : 대상 문자열과 패턴이 일치하는 경우 true를 반환하고, 그 위치로 이동
- find(int start) : start위치 이후부터 매칭 검색을 수행
- start() : 매칭되는 문자열의 시작위치 반환
- start(int group) : 지정된 그룹이 매칭되는 시작위치 반환
- end() : 매칭되는  문자열 끝 다음 문자위치 반환
- end(int group) : 지정된 그룹이 매칭되는 끝 다음 문자위치 반환합
- group() : 매칭된 부분을 반환
- group(int group) : 매칭된 부분 중 group번의 그룹핑 매칭부분 반환
- groupCount() : 패턴내 그룹핑한(괄호지정) 전체 갯수를 반환

<br/>

## 6. 정규표현식 문법
|정규표현식|설명|
|:---:|---|
|^|문자열 시작|
|$|문자열 종료|
|.|임의의 한 문자(단 \은 넣을 수 없음)|
|*|앞 문자가 없을 수도 무한정 많을 수도 있음|
|+|앞 문자가 하나 이상|
|?|앞 문자가 없거나 하나 있음|
|[ ]|문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로 범위를 나타내며, [] 내에서 ^ 가 선행하여 존재하면 not을 나타냄|
|{ }|횟수 또는 범위를 나타냅니다.|
|( )|소괄호 안의 문자를 하나의 문자로 인식|
|\||패턴 안에서 or 연산을 수행할 때 사용|
|`\`|정규 표현식 역슬래시(`\`)는 확장문자 (역슬래시 다음에 일반 문자가 오면 특수문자로 취급하고 역슬래시 다음에 특수문자가 오면 그 문자 자체를 의미)|
|\b|단어의 경계|
|\B|단어가 아닌것에 대한 경계|
|\A|입력의 시작 부분|
|\G|이전 매치의 끝|
|\Z|입력의 끝이지만 종결자가 있는 경우|
|\z|입력의 끝|
|\s|공백 문자|
|\S|공백 문자가 아닌 나머지 문자|
|\w|알파벳이나 숫자|
|\W|알파벳이나 숫자를 제외한 문자|
|\d|숫자 [0-9]와 동일|
|\D|숫자를 제외한 모든 문자|
|(?!)|앞 부분에 (?!)라는 옵션을 넣어주게 되면 대소문자는 구분하지 않음|

<br/>

## 7. 자주 사용하는 정규표현식

|정규표현식|설명|
|---|---|
|^[0-9]*$|숫자|
|^[a-zA-Z]*$|영문자|
|^[가-힣]*$|한글|
|`\\`w+@`\\`w+`\\`.`\\`w+(`\\`.`\\`w+)?|E-Mail|
|^\d{2,3}-\d{3,4}-\d{4}$|전화번호|
|^01(?:0\|1\|[6-9])-(?:\d{3}\|\d{4})-\d{4}$|휴대전화번호|
|\d{6} \- [1-4]\d{6}|주민등록번호|
|^\d{3}-\d{2}$|우편번호|