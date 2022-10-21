# Linux - SSH 접속 시 시간 지연 문제 (+ ntp 시간 동기화)

- SSH로 원격 서버에 연결할 때, 특정 서버의 로그인 프롬프트가 뜨는데 오래 걸리는 경우, DNS 설정을 의심해야 한다.
- SSH 서버에서 연결하려는 클라이언트의 IP를 기반으로 DNS 조회를 하기 때문이며, 해결 방법은 아래와 같다.

## 1. DNS 조회 끄기

- /etc/ssh/sshd_config 파일을 연다.
- UseDNS no 를 추가해준다. (UseDNS yes가 없거나 주석 처리 되어 있어도 꼭 명시해주어야 한다.)
- sshd를 재기동한다. (systemctl restart sshd)
- ssh로 다시 연결해서 증상이 사라졌는지 확인한다.

## 2. DNS 자체의 문제 해결

- VPN 방화벽 설정 상 DNS 서버의 접근을 막고 있어 DNS 서버가 정상적으로 동작하고 있지 않았다.
- 이러한 문제로 SSH 접속 시 시간 지연 문제와 함께, ntp 서버(time.bora.net) 시간 동기화 또한 진행되고 있지 않아, 서버 시간에서도 문제가 되었다.

    => 방법 1. DNS 서버와 연동할 수 있게 해당 서버 IP와 포트에 대한 방화벽을 오픈한다. (UDP 53, TCP 53) 
    
    => 방법 2. DNS 서버를 사용하지 않아도 괜찮다면, 해당 설정 
    파일에서 DNS 서버 부분을 주석처리 하거나, 삭제해준다. (설정파일 1: /etc/sysconfig/network-scripts/ifcfg-eth0) (설정파일 2 : /etc/resolv.conf)

    => service network restart 설정 후 재기동 (= systemctl restart network = /etc/init.d/network restart)

3. 해당 문제를 해결하면서 사용했던 Linux 명령어 모음

- rpm -qa ntp : ntp 설치 여부 확인
- ntpq -p : ntp 상태 확인
- grep . /etc/*-release : 리눅스 버전 확인
- find / -name ntp : ntp가 설치된 파일 경로 확인
- netstat -tnlp : 열려있는 포트 확인
- firewall-cmd --state : 방화벽 작동 여부 확인 1
- systemctl status firewalId : 방화벽 작동 여부 확인 2
- \- firewall-cmd --permanent --zone=public --add-port=[포트번호]/tcp : 방화벽 정책 추가
- systemctl start ntpd : ntp start
- systemctl stop ntpd : ntp stop
- systemctl restart ntpd : ntp restart
- systemctl status ntpd : ntp 상태 확인
- ps -ef|grep [프로세스이름] : 해당 프로세스 이름의 PID 확인
- netstat -anp|grep [포트번호] : 해당 포트번호의 PID 확인