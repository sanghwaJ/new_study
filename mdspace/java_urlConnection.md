# Java - URL Connection & Http URL Connection

## 1. URL Connection과 Http URL Connection
- URL Connection
  - Java 어플리케이션과 URL 간 연결과 관련된 모든 클래스의 Super 클래스
  - 일반적인 URL에 대한 API 제공
- Http URL Connection
  - URL Connection의 Sub 클래스로, HTTP 고유 기능에 대한 추가 지원 제공
- 두 클래스 모두 추상 클래스이기 때문에 새 인스턴스를 직접 만들 필요는 없고, 대신 URL 객체에서 연결을 통해 URL Connection 인스턴스를 얻음

## 2. URL 통신 과정

### 2-1. URL 객체 생성
- 주어진 URL 주소에 대한 새 URL 객체 생성
- 이때, URL 형식이 잘못된 경우 IOException throw
```java
URL url = new URL("http://www.google.com");
```

### 2-2. URL에서 Connection 객체 얻기
- URL 객체의 openConnection() 메소드 호출
- openConnection() 메소드는 실제 네트워크 연결을 설정하지 않고, 단지 URLConnection 클래스의 인스턴스를 반환함
- 실제 네트워크 연결은 connect() 메소드가 호출될 때 명시적으로 이뤄지거나, 헤더 필드를 읽을 때, 혹은 I/O 스트림을 가져올 때 암시적으로 이뤄짐
```java
URLConnection urlCon = url.openConnection();
```
- 프로토콜이 http://인 경우 HttpURLConnection 객체로 캐스팅할 수 있음
```java
HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
```

### 2-3. URLConnection 구성 (메소드)
- setConnectTimeout (int timeout) 
  - 연결 타임아웃 값 설정(단위 : 밀리초)
  - java.net.SocketTimeoutException는 연결이 설정되기 전에 제한 시간이 만료되면 발생
  - 시간 초과가 0이면, 무한대 타임아웃(기본값)을 의미
- setReadTimeout (int timeout)
  - 읽기 타임아웃 값 설정(단위 : 밀리초)
  - 제한 시간이 만료되고 연결의 입력 스트림에서 읽을 수 있는 데이터가 없으면 SocketTimeoutException이 발생
  - 시간 초과가 0이면, 무한대 타임아웃(기본값)을 의미
- setDefaultUseCaches (boolean default)
  - URLConnection이 기본적으로 캐시를 사용하는지 여부를 설정(기본값 true)
  - URLConnection 클래스의 다음 인스턴스에 영향을 주는 메소드
- setUseCaches (boolean useCaches)
  - 연결이 캐시를 사용하는지 여부를 설정(기본값 true)
- setDoInput (boolean doInput)
  - URLConnection을 서버에서 콘텐츠를 읽는 데 사용할 수있는지 여부를 설정(기본값 true)
- setDoOutput (boolean doOutput)
  - URLConnection이 서버에 데이터를 보내는 데 사용할 수있는지 여부를 설정(기본값 false)
- setIfModifiedSince (long time)
  - 주로 HTTP 프로토콜에 대해 클라이언트가 검색한 콘텐츠의 마지막 수정 시간을 새로 설정
  - 예를 들어, 서버가 지정된 시간 이후에 정적콘텐츠(이미지, HTML 등)가 변경되지 않았으면 콘텐츠를 가져오지 않고 상태 코드 304(수정되지 않음)를 반환
  - 클라이언트는 지정된 시간보다 최근에 수정된 경우, 새로운 콘텐츠를 받게됨
- setAllowUserInteraction (boolean allow)
  - 사용자 상호 작용을 활성화 또는 비활성화 함(기본값은 false)
  - 예를 들어 필요한 경우 인증 대화 상자를 표시
- setDefaultAllowUserInteraction (boolean default)
  - 모든 URLConnection객체에 대한 사용자 상호 작용의 기본값을 설정
- setRequestProperty (String key, String value)
  - key=value 쌍으로 지정된 일반 요청 속성을 설정
  - 키가 있는 속성이 이미 있는 경우 이전 값을 새 값으로 적용
  - 연결을 설정하기 전에 호출해야 하며, 일부 메소드는 연결이 이미 설정된 경우 IllegalStateException이 발생 
  - 하위 클래스 HttpURLConnection은 HTTP 관련 기능을 사용하여 연결을 구성하기 위한 다음 메서드를 제공
- setRequestMethod (String  method)
  - HTTP 메소드 GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE 중 하나인 URL 요청에 대한 메소드를 설정(기본값은 GET)
- setChunkedStreamingMode (int chunkLength)
  - 콘텐츠 길이를 미리 알 수 없는 경우 내부 버퍼링 없이 HTTP 요청 본문을 스트리밍할 수 있음
- setFixedLengthStreamingMode (long contentLength)
  - 콘텐츠 길이를 미리 알고 있는 경우 내부 버퍼링 없이 HTTP 요청 본문을 스트리밍할 수 있음
- setFollowRedirects (boolean follow)
  - HTTP 리다이렉션 뒤에 클래스의 미래 객체가 자동으로 따라야 하는지 여부를 설정한다 (기본값 true)
- setInstanceFollowRedirects (boolean follow)
  - HTTP 리다이렉션 뒤에 이 HttpURLConnection 클래스의 인스턴스가 자동으로 따라와야 하는지 여부를 설정 (기본값 true)

### 2-4. 헤더 필드 읽기
- 연결이 이루어지면 서버는 URL 요청을 처리하고 메타데이터와 실제 콘텐츠로 구성된 응답을 다시 보내는데, 이때 메타데이터는 헤더 필드라고 하는 key=value의 모음
- 헤더 필드는 서버에 대한 정보, 상태 코드, 프로토콜 정보를 나타내며, 실제 내용은 텍스트, HTML, 이미지 등이 될 수 있음
- getHeaderFields ()
  - 모든 헤더 필드를 포함하는 맵을 반환
  - key는 필드 이름이고 value는 해당 필드 값을 나타내는 문자열 목록
- getHeaderField (int n)
  - n 번째 헤더 필드의 값을 읽음
- getHeaderField (String name)
  - 명명된 헤더 필드의 value를 읽음
- getHeaderFieldKey (int n)
  - n 번째 헤더 필드의 key를 읽음
- getHeaderFieldDate (String name, long default)
  - Date로 구문 분석된 명명된 필드의 값을 읽음
  - 필드가 없거나 값 형식이 잘못된 경우 기본값이 대신 반환
- getHeaderFieldInt (String name, int default)
  - 정수로 구문 분석된 명명된 필드의 값을 읽음 
  - 필드가 없거나 값 형식이 잘못된 경우 기본값이 대신 반환
- getHeaderFieldLong (String name, long default)
  - 긴 숫자로 구문 분석된 명명된 필드의 값을 읽음 
  - 필드가 없거나 값 형식이 잘못된 경우 기본값이 대신 반환
- getContentEncoding ()
  - 콘텐츠의 인코딩 유형을 나타내는 콘텐츠 인코딩 헤더 필드의 값을 읽음
- getContentLength () 
  - 콘텐츠의 크기(바이트)를 나타내는 콘텐츠 길이 헤더 필드의 값을 읽음
- getContentType ()
  - 컨텐츠의 유형을 나타내는 컨텐츠 유형 헤더 필드의 값을 읽음
- getDate ()
  - 서버의 날짜 시간을 나타내는 날짜 헤더 필드의 값을 읽음
- getExpiration ()
  - 만료 헤더 필드의 값을 읽고 응답이 오래된 것으로 간주되는 시간을 나타냄(캐시 제어 목적)
- getLastModified ()
  - 컨텐츠의 마지막 수정 시간을 나타내는 last-modified 헤더 필드의 값을 읽음
- getResponseCode ()
  - 서버에서 보낸 HTTP 상태 코드를 반환

### 2-5. 입력 스트림 가져오기 및 데이터 읽기
- 실제 내용을 읽으려면 연결에서 InputStream 인스턴스를 얻은 다음, InputStream의 read() 메소드를 통해 데이터를 읽어야 함
```java
InputStream inputStream = urlCon.getInputStream();
byte[] data = new byte[1024];
inputStream.read(data);
```
- read()는 데이터를 byte의 배열로 읽으며, 문자 데이터를 읽기 위해서는 InputStream을 InputStreamReader를 사용해야 함
```java
InputStream inputStream = urlCon.getInputStream();
InputStreamReader reader = new InputStreamReader(inputStream);


int character = reader.read();  // reads a single character
char[] buffer = new char[4096];
reader.read(buffer);    // reads to an array of characters
```
- 혹은, BufferedReader를 사용해도 됨
```java
BufferedReader reader = new BufferedReader(new InputStreamReader(input));
String line = reader.readLine(); // reads a line
```

### 2-6. 출력 스트림 가져오기 및 데이터 쓰기
- 서버에 데이터를 보내려면, 먼저 연결에서 출력을 활성화해야 함
```java
urlCon.setDoOutput(true);
```
- 그 후, 연결과 관련된 OutputStream 객체를 가져온 다음, write() 메소드를 사용하여 데이터 쓰면 됨
```java
OutputStream outputStream = urlCon.getOutputStream();
byte[] data = new byte[1024];
outputStream.write(data);
```
- write() 메소드는 byte의 배열만 쓸 수 있기 때문에 문자 데이터를 쓰기 위해서 OutputStreamWriter를 사용
```java
OutputStreamWriter writer = new OutputStreamWriter(outputStream);
int character = 'a';
writer.write(character); // writes a single character

char[] buffer = new char[4096];
writer.write(buffer); // writes an array of characters
```
- 혹은, PrintWriter를 사용하면 됨
```java
PrintWriter writer = new PrintWriter(outputStream);
String line = "This is String";
writer.print(line);
```

### 2-7. 연결 종료
- close() 메소드를 호출하고, URLConnection 인스턴스와 연결된 네트워크 리소스를 해제
