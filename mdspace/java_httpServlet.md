# Java - HttpServletRequest & HttpServletResponse

## 1. HttpServlet
- HTTP 프로토콜을 사용하는 웹 브라우저에서 Servlet 기능을 수행하고, 개발자는 HttpServlet을 상속 받아 많은 기능을 사용할 수 있음
  - Servlet : WAS에서 동적 웹 페이지 구현을 할 수 있도록 도와주는 Java 클래스의 종류
- WAS가 웹 브라우저로부터 Servlet 요청을 받으면...
  - 요청을 받을 때, 전달 받은 정보를 HttpServletRequest 객체를 생성해서 저장
  - 웹 브라우저에 응답을 반환할 HttpServletResponse 객체 생성(응답을 받기 전의 빈 객체)
  - 생성된 HttpServletRequest 객체, HttpServletResponse 객체를 Servlet에 전달

## 2. HttpServletRequest
- HTTP 요청 정보(Client의 요청, Cookie, Session 등)를 제공하는 인터페이스
- HTTP 프로토콜의 request 정보를 Servlet에게 전달하기 위한 목적으로 사용
- Message Body의 Stream을 읽어들이는 메소드 존재
  - getParameterNames() : 현재 요청에 포함된 매개변수 이름을 열거 형태로 넘겨줌
  - getParameter(name) : 문자열 name과 같은 이름의 매개변수를 가져옴

## 3. HttpServletResponse
- HTTP 응답 정보(요청 처리 결과)를 제공하는 인터페이스
- Servlet은 HttpServletResponse 객체에 content-type, 응답 코드, 응답 메세지 등을 담아서 전송
- Servlet으로 들어온 요청은 텍스트(HTML)로 응답을 보내기 때문에, 출력 스트림을 받기 위해 주로 response로부터 writer 객체를 얻어서 내보냄
- 메소드 예시
  - setContentType() : 요청에 대해 클라이언트에게 돌려줄 content-type 결정
  - setCharacterEncoding()

## 4. Servlet 요청 정보 처리(HttpServletRequest) 주요 메소드
- 참고 : https://kgvovc.tistory.com/31