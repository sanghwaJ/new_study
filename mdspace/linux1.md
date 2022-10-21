# Linux - 프로세스 종료

- 프로세스가 중복 실행이 되었거나 프로세스에 문제가 있어 강제로 프로세스를 종료해야되는 경우가 있는데, 이럴 때 아래와 같이 진행하면 됨

### 1-1. PORT 상태 확인
```linux
netstat -nap | grep :9309
```
```linux
netstat -tulpen
```

### 1-2. 실행중인 프로세스 확인
```linux
lsof -i -P -n | grep LISTEN
```

### 2-1. PID를 통해 kill
```linux
kill -9 4044
```

### 2-2. 서버 IP 주소, PORT, 프로세스 상태등을 통해 프로세스를 특정하여 kill
```linux
netstat -nap | grep 100.100.100.100 | grep :9309 | grep CLOSE_WAIT | awk '{print $7}' | cut -d \/ -f1 | grep -oE "[[:digit:]]{1,}" | xargs kill
```