# XML vs JSON vs YAML

## 1. XML
- 데이터를 표현하기 위한 방식으로, HTML과 흡사하게 태그를 사용하며, 트리 계층 구조를 가지고 있음
- XML 선언
  - version: XML 문서 버전을 명시
  - encoding: XML 문서의 문자 셋(Character Set)을 명시 (일반적으로 UTF-8을 사용)
  - standalone: XML 문서 외부 소스 데이터에 의존하는지의 여부를 명시
- XML 문법
  - 태그는 꺽쇠(<>)를 사용하며, 닫는 태그에는 슬래시(/)를 넣어줌
  - 시작 태그와 종료 태그는 대소문자까지 모두 동일해야하며, 속성을 명시할 때는 따옴표("")를 사용함
  - 주석은 \<!-- --> 형태로 사용 가능

```xml
<?xml version="1.0" encoding="UTF-8"?>
<users>  
  <user>    
    <name>홍길동</name>    
    <score>95</score>    
    <hobby>      
      <element>Soccer</element>      
      <element>Ninza</element>    
    </hobby>  
  </user>  
  <user>    
    <name>이순신</name>    
    <score>100</score>    
    <hobby>      
      <element>Sing</element>      
      <element>Dancing</element>    
    </hobby>  
  </user>
</users>
```

## 2. JSON (JavaScript Object Notation)
- XML과 같이 데이터를 처리하기 위한 형식으로, 서버와의 통신 규약인 REST API 등의 API를 개발할 때 가장 많이 사용
- 모든 프로그래밍 언어에서 JSON을 지원하기 때문에, 최근 XML에 비해 채택률이 높아지고 있음
- JSON은 주석을 사용할 수 없는 특징이 있음

```json
{	
  "users": {		
    "1": {			
      "name": "홍길동",			
      "score": 95,			
      "hobby": ["Soccer", "Ninza"]		
    },		
    "2": {			
      "name": "이순신",			
      "score": 100,			
      "hobby": ["Sing", "Dancing"]		
    }
  }
}
```

## 3. YAML
- XML과 문법적으로 유사하며, 주석이 사용 가능함
- 개행과 공백 위주로 블록을 인식하기 때문에 한 줄로 작성할 수 없다는 특징이 있음 (데이터 직렬화 형식)
- JSON과 달리 한글과 같은 유니코드를 그대로 사용할 수 있음
- 상속 등의 기능을 적용할 수 있음
- 주로 Swagger API, Spring Boot, Docker 등에서 환경 설정(config) 파일 작성을 목적으로 사용

```yaml
users:  
  1:    
    name: 홍길동    
    score: 95    
    hobby:      
      - Soccer      
      - Ninza  
  2:    
    name: 이순신    
    score: 100    
    hobby:      
      - Sing      
      - Dancing
```