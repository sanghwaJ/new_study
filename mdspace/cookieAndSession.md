# Cookie & Session (+ Cache)

## 1. HTTP 특징과 Cookie와 Session의 사용 이유
- HTTP의 특징
  - Connectionless (비연결지향) : Client가 요청을 한 후, 응답을 받으면 그 연결을 끊어버리는 특징
  - Stateless (상태정보 유지하지 않음) : 연결을 끊는 순간, Client와 Server의 통신이 끝나면서 상태 정보는 유지하지 않는 특성
- HTTP의 약점
  - 위와 같은 HTTP의 특징으로 인해, 데이터가 유지가 필요한 경우에 문제 발생
  - 예를 들어, 매번 페이지를 이동할 때마다 다시 로그인을 하거나, 상품을 선택했지만 구매 페이지에서 상품 정보가 없는 문제가 발생할 수 있음
  - 이러한 HTTP의 약점을 보완하기위해 Cookie와 Session이 사용됨

## 2. Cookie
- HTTP의 일종으로 해당 웹 페이지의 정보를 Client Local PC에 저장하는 작은 정보 파일
- HTTP에서 Client Local PC에 저장하였다가 필요시 정보를 참조하거나 재사용할 수 있음

### 2-1. Cookie의 특징
- key, value, 만료일(저장기간), 경로 정보로 구성
- Client에 총 300개의 Cookie를 저장할 수 있음
- 하나의 도메인 당 20개의 쿠키를 가질 수 있음
- 하나의 쿠키는 4KB까지 저장 가능

### 2-2. Cookie의 동작 순서
1. Client가 웹 페이지에 접근(요청)
2. Server는 Cookie 생성
3. 생성한 Cookie에 정보를 담아, HTTP 화면을 돌려줄 때 Client에게 돌려줌
4. 넘겨받은 Cookie는 Client가 Local PC에 저장하고 있다가 다시 Server에 요청할 때, 요청과 함께 Cookie를 전송
5. 동일한 사이트를 재방문 시, Client Local PC에 해당 Cookie가 있는 경우, 요청 페이지와 함께 Cookie 전송

### 2-3. Cookie의 사용 예시
- 동일한 페이지 재방문 시, 아이디와 비밀번호 저장
- 팝업창의 "오늘 이 창을 다시 보지 않기"

## 3. Session
- 일정 시간(웹 브라우저를 통해 웹 서버 접근 ~ 웹 브라우저를 종료하여 연결 해제) 동안 같은 Client로부터 들어오는 요구를 하나의 상태로 보고 그 상태를 유지시키는 기술
- 즉, 방문자가 웹 서버에 접속해있는 상태를 하나의 단위로 보는 것이 Session

### 3-1. Session의 특징
- 웹 서버에 웹 컨테이너의 상태를 유지하기 위한 정보를 저장
- 웹 서버에 저장되는 쿠키(= Session Cookie)
- 웹 브라우저를 종료하거나 웹 서버에서 삭제했을 때만 삭제가 되므로, Cookie보다 보안이 비교적 좋음
- 저장 데이터에 제한이 없음
- 각 Client에 고유 Session ID를 부여하고, Session ID 별로 Client를 구분해 각 요구에 맞는 서비스를 제공함

### 3-2. Session의 동작 순서
1. Client가 웹 페이지에 접근(요청)
2. Server는 접근한 Client의 Request-Header 필드인 Cookie를 확인하고, Client가 해당 Session ID를 보냈는지 확인
3. Session ID가 존재하지 않는다면, Server에서 Session ID를 생성하여 Client에게 돌려줌
4. Server에서 Client로 돌려준 Session ID를 Cookie를 사용해 서버에 저장
5. Client는 재접속 시, 이 Cookie를 이용하여 Session ID 값을 Server에 전달

### 3-3. Session의 사용 예시
- 화면을 이동해도 로그아웃을 하기 전까진 로그인 상태가 유지됨

## 4. Cookie와 Session 비교
- Client의 정보가 저장되는 위치
  - Cookie는 서버의 자원을 전혀 사용하지 않으며, Session은 서버의 자원을 사용함
- 보안
  - Cookie는 Client Local PC에 저장되기 때문에 변질될 위험이 있음
  - 반면, Session은 Cookie를 이용해서 Session ID만 저장하고 나머지는 서버에서 처리하기 때문에 비교적 보안에 더 우수함
- Life Cycle
  - Cookie는 만료기간이 있더라도 파일로 저장되기 때문에 웹 브라우저가 종료되어도 정보가 유지되며, 만료기간을 따로 지정하여 Cookie가 삭제될 때까지 유지할 수 있음
  - Session은 웹 브라우저가 종료되면 만료기간에 상관없이 삭제됨
- 속도
  - Cookie는 데이터가 Cookie 안에 있기 때문에 속도가 빠름
  - Session은 데이터가 서버에 있기 때문에 비교적 속도가 느림

## 5. Cookie와 Session의 사용
- Cookie만 사용한다면?
  - 보안에 취약함
- Session만 사용한다면?
  - 서버 자원을 사용하기 때문에 서버 자원에 한계가 오게됨
  - 서버에서 모든 데이터 처리를 하게되면 속도면에서 불리함

## ※ Cache
- 웹 페이지 요소(이미지, 비디오, css, js 등)를 저장하기 위한 임시 저장소로, 웹 페이지를 빠르게 렌더링할 수 있도록 도와줌
- 저장공간이 작고 비용이 비싼 대신, 빠른 성능을 제공
- 같은 웹 페이지를 접속할 때, Client Local에서 로드하므로, Server를 거치치 않아도 됨
- 이전에 사용된 데이터가 다시 사용될 가능성이 높으면, Cache 서버에 있는 데이터를 사용

