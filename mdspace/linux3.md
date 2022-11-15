# Linux - Java 버전 관리 (+ alternatives(+ 심볼릭 링크), jenv)

## 1. alternatives

### 1-1. alternatives란?
- Linux 시스템에서 여러가지 버전의 패키지를 설치하여 사용하는 경우, 필요에 따라 유동적으로 원하는 버전을 선택할 수 있음
- alternatives는 심볼릭 링크를 생성, 제거, 관리, 조회할 수 있는 기능을 제공하는 GNU 라이선스의 커맨드라인 툴

### ※ 심볼릭 링크(소프트 링크)와 하드 링크
- inode
  - Linux 파일 시스템에서 사용되는 용어로, 파일이나 디렉토리의 각종 정보를 담고 있음
  - 모든 파일과 디렉토리는 inode를 가지고 있으며, 여기에는 권한 정보나 실제 위치 등의 정보를 가지고 있음
  - 즉, inode + data block이 하나의 파일 또는 디렉토리이며, inode는 파일에 대한 정보, data block은 실 데이터를 가리킴
- 심볼릭 링크(소프트 링크)
  - Windows의 바로가기처럼 단순히 원본 파일을 가리키도록 링크시켜 둔 것으로, 원본 inode가 아닌 복사본 inode를 하나 더 만듬
  - 단순히 원본 파일만 가리키기 때문에 원본 파일의 크기와 무관하며, 원본 파일이 삭제되어 존재하지 않을 경우 링크 파일은 더 이상 사용할 수 없음
  - 심볼릭 링크 설정 : ln -s [원본 파일 이름] [심볼릭 링크 이름]
  - 심볼릭 링크 삭제 : rm [심볼릭 링크 이름]
- 하드 링크
  - 원본 inode를 가리키는 링크 파일을 생성하여 원본 파일(inode + data block)을 가리키는 포인터를 2개로 만듬
  - 원본 파일이나 하드 링크한 파일을 수정하면 둘 다 수정이 되며, 둘 중 하나를 삭제하더라도 원본 파일이 존재하면 데이터가 유지됨

### 1-2. alternatives 사용법
- alternatives config 확인 & java 버전 설정
```linux
alternatives --config java

2 개의 프로그램이 'java_home'를 제공합니다.

  선택    명령
-----------------------------------------------
*+ 1           /usr/local/lib/jdk-14.0.2
   2           /usr/local/lib/jdk-15.0.2

현재 선택[+]을 유지하려면 엔터키를 누르고, 아니면 선택 번호를 입력하십시오:2
```
- alternatives 심볼릭 링크 생성
  - alternatives --install [link:심볼릭 링크 경로] [name:심볼릭 링크 그룹명] [path:패키지 절대 경로] [priority:우선 순위(숫자가 클수록 우선)]
```linux
alternatives --install /usr/bin/java java /usr/local/java/jdk-11.0.9/bin/java 1
```
- 환경변수 설정
```linux
vi /etc/profile
```
```linux
# add line
export JAVA_HOME=$(readlink -f /usr/bin/java) | sed "s:bin/java::")
```
- 환경변수 확인
```linux
cd $JAVA_HOME
```

## 2. jenv

### 2-1. jenv란?
- JAVA_HOME 환경을 관리하는 커맨드라인 툴
- JDK 버전 전환을 쉽게 할 수 있으며, 디렉토리 별로 다른 버전의 JDK를 설정할 수 있도록 해줌

### 2-2. jenv 사용법
- 설치
```linux
git clone https://github.com/gcuisinier/jenv.git ~ / .jenv
```
- .bash_profile 수정
```linux
echo 'export PATH="$HOME/.jenv/bin:$PATH"' >> ~/.bash_profile
echo 'eval "$(jenv init -)"' >> ~/.bash_profile
```
- Java 등록
```linux
jenv add /usr/java/jdk1.7.0_80
jenv add /usr/java/jdk1.8.0_241
```
- jenv에 등록된 Java 버전 확인
```linux
jenv versions
```
- 전역(global) 버전 설정
```linux
jenv global 1.7.0.80
```
- 지역(local) 버전 설정
```linux
# 원하는 폴더로 이동
cd /home/seng/script

jenv local 1.8.0.241
```

---
참고

https://velog.io/@3rd-big/alternatives-%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-jdk-%EB%B2%84%EC%A0%84%EA%B4%80%EB%A6%AC

https://veneas.tistory.com/entry/Linux-Javajdk-%EC%97%AC%EB%9F%AC-%EB%B2%84%EC%A0%84-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-alternatives#2.2_Java%EB%B2%84%EC%A0%84_%EB%B3%80%EA%B2%BD

https://kshman94.tistory.com/7