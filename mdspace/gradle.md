# Gradle

## 1. Gradle?
- Groovy 기반의 빌드 자동화 도구로, Java에서 Maven과 더불어 가장 많이 사용되는 Build Tool
- Gradle은 XML 기반인 Maven에 비해 동적으로 빌드를 유연하게 표현할 수 있으며, 가독성과 성능이 더 뛰어나 최근 더 많이 사용되는 추세

## 2. Gradle 주요 명령어
- gradle -version
  - 설치된 Gradle의 버전 확인
- gradle init
  - 프로젝트에 필요한 Gradle 초기 환경 구성 (프로젝트 디렉토리 안에서 실행해야 함)
- gradle init --type \[타입명]
  - Gradle 초기 환경 구성 시, 타입을 주어 Gradle 환경 구성
  - ex 1) gradle init --type java-application
  - ex 2) gradle init --type pom (Maven => Gradle 변경 시, pom.xml을 build.gradle로 변환)
- gradle tasks
  - Gradle이 제공하는 Task의 목록 확인
- gradle build
  - 프로젝트를 컴파일(빌드)하는 명령어로, build.gradle 파일에서 plugins에 'java'를 추가하는 경우 jar 파일로 패키징 됨
  - 컴파일된 파일들은 app > build 폴더에 생성되며, jar 파일은 build > libs에 생성됨
  - 명령어를 입력하지 않고 IntelliJ의 Gradle 탭에서도 빌드 수행 가능
- gradle jar
  - jar파일로 프로젝트 패키징 (plugins에 'java'가 추가된 경우 gradle build 명령어로도 가능)
  - 생성한 jar 파일은 java -jar 파일명.jar로 실행 가능
- gradle run
  - 컴파일 후 메인 클래스 실행
- gradle bootRun
  - Spring Boot 실행
- gradle clean
  - build 폴더를 제거하여 빌드 이전 상태로 되돌림

### ※ gradle-wrapper
- gradle 명령어 말고도 gradle-wrapper의 실행 명령으로도 Task를 실행할 수 있는데, 이러한 경우 새로운 환경에서 Gradle을 설치하지 않고도 빌드가 가능함
- gradlew build 
  - 사용자가 프로젝트를 만든 환경과 동일한 버전으로 빌드를 할 수 있으며, Gradle이 설치되지 않아도 빌드가 가능함
  - Linux, macOS : ./gradlew Task명
  - Windows : gradlew Task명

## 3. Gradle 주요 파일
- .gradle 디렉토리 : 작업(Task) 파일이 위치함
- gradle 디렉토리 : gradle-wrapper 관련 디렉토리
- gradlew(Linux, macOS), gradlew.bat(Windows) : 각각 리눅스와 맥, 윈도우의 실행 명령
- build.gradle : 프로젝트에 필요한 의존성과 빌드 처리 내용을 작성하는 파일로, 가장 중요한 파일
- settings.gradle : 프로젝트에 대한 설정 정보를 작성하는 파일

### ※ build.gradle의 구성
```gradle
buildscript {
    ext {
        springBootVersion = '2.3.7.RELEASE'
        lombokVersion = '1.18.10'
    }
    repositories {
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group 'gradle.test.javaapp'
version '1.0-SNAPSHOT'
sourceCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-web'
    // api '...'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    compileOnly "org.projectlombok:lombok:$lombokVersion"
    // runtimeOnly '...'

    annotationProcessor "org.projectlombok:lombok:$lombokVersion"

}
```
- buildscript
    - gradle로 task 실행 시 사용되는 설정으로, 어플리케이션 빌드와는 별개의 설정 (위의 예시처럼 repositories, dependencies를 따로 구현)
- ext
  - 전역변수 블록으로, 전역변수는 $전역변수명으로 사용할 수 있음
- classpath
  - 라이브러리를 클래스 경로에 추가하며, 빌드에서 실행까지 의존하는 라이브러리를 지정한다.
- plugin
  - 프로젝트에서 사용하는 Gradle 플러그인 추가 (위의 예시에서 플러그인들은 부트 환경구성에 필요한 플러그인)
- eclipse : eclipse IDE 에서도 해당 Gradle project를 개발할 수 있도록 플러그인 설치
- group / version / sourceCompatibility
  - 프로젝트 생성 시의 groupId, 어플리케이션 버젼, 자바버젼
- repositories
  - 필요한 라이브러리를 다운로드할 저장소를 지정
  - 공개저장소(jcenter)와 maven저장소(mavenCentral, mavenLocal를 사용할 수 있으며, 상호보완 되도록 둘 다 사용하는 것이 권장됨
- dependencies (compile, api, implementation)
  - 라이브러리 추가
  - compile, api
    - 모듈 수정 시, 해당 모듈을 의존하고있는 모듈을 모두 빌드 
    - A(api) <- B <- C 로 의존하는 구조라면, A 수정 시 B,C 모두 빌드되기 때문에, compile의 경우 Gradle 3.0부터는 사용안하는 것을 권장 (api로 대체)
- implementation
  - 모듈 수정 시, 해당 모듈을 직접 의존하는 모듈만 빌드하기 때문에 비교적 빠름
  - A(implementation) <- B <- C 로 의존하는 구조라면, A 수정 시 B 만 빌드
- testImplementation
  - 테스트에 사용하는 라이브러리 추가
- annotationProcessor
  - 어노테이션 기반 라이브러리를 컴파일러가 인식하도록 함 (ex. lombok, queryDSL 등)
- compileOnly
  - compile에만 필요하고, runtime에는 필요없는 라이브러리 추가

## 4. Gradle Task의 이해
- Gradle의 Task는 Gradle 프로젝트의 작업 단위로, gradle task명의 명령어로 Task를 실행할 수 있음
- Task는 Gradle 내부에 미리 만들어진 Task들과 build.gradle 파일에 사용자가 정의해 사용하는 Task들 두 종류가 있음
- 앞서 설명했던 gradle init, gradle clean과 같은 명령어는 Gradle에 내장된 Task들 

### ※ 사용자 정의 Task 만들기
- build.gradle 파일에 아래와 같이 추가하여 사용할 수 있으며 콘솔 상에서 실행됨
```gradle
// gradle hello 명령어로 실행
task hello {
  println 'Hello Gradle' 
}
```
- doFirst & doLast : Task 내부에서 블록에 우선순위를 정할 때 사용
```gradle
task hello {
  doFirst {
    // task 내에서 doLast보다 먼저 처리될 내용
    println 'Hello'
  }
  doLast {
    // task 내에서 doFirst보다 나중에 처리될 내용
    println 'Gradle'
  }
}
```
- 콘솔로부터 입력 파라미터 받기
  - gradle 테스크명 –P변수명1=변수값1 –P변수명2=변수값2 … -P변수명n=변수값n
```gradle
task calc {
   def s= s
   def x = x.toInteger()
   def y = y.toInteger()
 
   if (s =='+') {
      println x + '+' + y + '=' + (x+y)
   } else if (s == '-') {
      println x + '-' + y + '=' + (x-y)
   } else if (s == 'x') {
      println x + 'x' + y + '=' + (x * y)
   } else {
      println x + '/' + y + '=' + (x/y)
   }
}
```
- 다른 Task 호출
```gradle
task hello {
    println 'Hello gradle'
   
    doLast {
        println 'Good bye~'
        // tasks.다른태스크명.execute()
        tasks.otherTask.execute()
    }
    doFirst {
        println 'Nice meet you.'
    }
}
 
task otherTask {
    println 'do other task'
}
```

- 종속성 지정

```java
// 방법 1
task 태스크명(dependsOn : ‘다른태스크명’) {
    // 내용
}
task 다른태스크명 {
    // 내용
}

// 방법 2
task 태스크명 {
dependsOn : ‘다른태스크명’
    // 내용
}
task 다른태스크명 {
    // 내용
}

// 예시
task hello(dependsOn:'otherTask') {
    println 'Hello gradle'
   
    doLast {
        println 'Good bye~'
        tasks.otherTask.execute()
    }
    doFirst {
        println 'Nice meet you.'
    }
}
 
task otherTask {
    doLast{
       println 'do other task'
   }
}
```
## 5. Gradle 멀티 모듈 구성

### 5-1. 멀티 모듈을 사용하는 이유
- 하나의 공통 프로젝트를 두고, 다른 여러 프로젝트에서 공통 프로젝트의 기능을 가져가서 사용하여 프로젝트가 커지면서 발생하는 이슈인 유지보수의 어려움을 해결하고, 코드의 중복을 줄이기 위해 사용
- 모듈을 기능별로 분리하기 때문에 각 모듈의 기능을 파악하기 쉬우며, 별도로 빌드를 진행하거나 루트 프로젝트에서 각각의 모듈을 빌드할 수 있어 빌드가 쉽고 간편함

### 5-2. 멀티 모듈 생성 및 셋팅 방법
- IntelliJ 기준으로, 멀티 모듈을 생성할 프로젝트 디렉토리에서 File > Project Structure 탭에서 모듈 추가
- 루트 프로젝트의 setting.gradle에 설정 추가
```gradle
// mulit-module 모듈 예시
rootProject.name = 'multi-module'

include 'library'
include 'application'
```
- 하위 모듈의 build.gradle에 의존성 추가
  - Local 라이브러리 모듈 의존성 설정 : Local에 정의된 모듈의 의존을 정의하는 방법으로, 루트 프로젝트의 setting.gradle에서 "include:"로 정의한 모듈의 이름과 같아야 함
  - Local 바이너리 라이브러리 의존성 설정 : 패키지화되어 있는 jar 파일의 의존을 정의하는 방법
  - Remote 바이너리 라이브러리 의존성 설정 : 가장 많이 사용되는 방법으로 외부에 정의되어 있는 바이너리 라이브러리 의존성을 설정하는 방법
```gradle
dependencies {
    // Local 라이브러리 모듈 or 프로젝트에 대한 종속성을 정의
    implementation project(":mylibrary")

    // Local 바이너리 라이브러리에 대한 종속성을 정의
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    // Remote 바이너리 라이브러리에 대한 종속성을 정의
    implementation 'org.springframework.boot:spring-boot-starter'
}
```


```gradle
// library 모듈 예시
plugins {
	id 'org.springframework.boot' version '2.5.2'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
	mavenCentral()
}

// 설정 부분
bootJar {
	enabled = false
}

jar {
	enabled = true
}
//

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

```gradle
// application 모듈 예시
plugins {
	id 'org.springframework.boot' version '2.5.2'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-actuator'
	implementation 'org.springframework.boot:spring-boot-starter-web'
  
  // library 의존성 추가
	implementation project(':library')

	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

참고 1 : https://velog.io/@franc/Gradle-%EA%B8%B0%EB%B3%B8%EC%82%AC%EC%9A%A9%EB%B2%95

참고 2 : https://blog.naver.com/sharplee7/221413629068

참고 3 : https://kimpaper.github.io/2016/07/14/gradle/

참고 4 : https://tecoble.techcourse.co.kr/post/2021-09-06-multi-module/

참고 5 : https://7942yongdae.tistory.com/133