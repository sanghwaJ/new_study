# Git Branch 전략

## 1. Branch란?
- 독립적으로 어떠한 작업을 진행하기 위한 개념으로, 필요에 의해 만들어지는 각각의 Branch는 다른 Branch의 영향을 받지 않기 때문에, 여러 작업을 동시에 진행할 수 있다.
- 개발자들이 협업을 진행할 때, 기능 개발이나 버그 수정과 같은 서로 다른 작업을 하면 동일한 코드여도 서로 다른 버전의 코드가 만들어진다. 이 때, 동시에 여러 작업을 진행할 수 있도록 Branch를 사용하고, 이 Branch를 사용하는데 전략이 필요하다.

## 2. Git에 대한 사전 지식

### 2-1. PR(Pull Request) 과정
1. git clone : github의 저장소 복제
2. git add : 작업 디렉토리 영역의 변경 내용을 스테이징 영역에 넘기는데, 이 때 디렉토리 명을 입력해 일부만 넘길 수도 있고, *을 사용해 변경된 모든 내용을 넘길 수도 있다.
3. git commit : 소스코드의 변경을 저장하고, -m 옵션을 사용하여 메시지를 남길 수 있다.
4. git push : 저장된 소스코드를 github에 올려서 다른 사람들과 코드를 공유한다. (git push [리모트명] [브랜치명])
5. PR : Pull Request로 fork한 기존의 저장소에 소스코드를 보내고, 해당 과정을 통해 변경된 내용을 기존 소스코드 저장소에 반영한다.

### 2-2. add & commit
1. add : git에 현재 commit된 버전과 다르게 변경된 파일이 있음을 알려주는 동작
2. commit : git에 변경된 파일이 있음을 명시하는 동작

## 3. Git Branch 전략 1 : Git-Flow

### 3-1. Git-Flow 전략에 존재하는 5가지 Branch
1. Master : 기준이 되는 Branch로, 제품을 배포하는 Branch
2. Develop : 개발 Branch로, 이 Branch를 기준으로하여 각자 작업한 기능들을 Merge
3. Feature : 단위 기능을 개발하는 Branch로, 기능 개발이 완료되면 develop Branch에 Merge
4. Release : 배포를 위해 Master Branch로 보내기 전, QA(품질검사)를 하기위한 Branch
5. Hotfix : Master Branch로 배포 후, 버그가 생겼을 때 긴급 수정하는 Branch

- Master와 Develop이 중요한 Main Branch이고, 나머지는 필요에 의해 운영하는 Branch이다.
- Branch를 Merge할 때, 항상 -no-ff 옵션을 붙여 Branch에 대한 기록이 사라지는 것을 방지하는 것을 원칙으로 한다.

### 3-2. Git-Flow 과정
1. Master에서 Develop Branch 분기
2. 개발 작업을 하며 개발자들은 Develop에 자유롭게 commit
3. 기능 구현이 있는 경우, Develop에서 Feature-* Branch를 분기
4. 배포를 준비하는 경우, Develop에서 Release-* Branch를 분기
5. 테스트를 진행하며 발생하는 버그 수정은 Release-* Branch에 직접 반영
6. 테스트가 완료되면, Release를 Master와 Develop에 Merge

## 4. Git Branch 전략 2 : Github-Flow
- Git-Flow가 Github에서 사용하기에 복잡하다고 나오게된 Branch 전략
- Hotfix와 Feature를 구분하지 않음(다만, 우선순위만 다름)
- 수시로 배포가 일어나며, CI와 배포가 자동화되어 있는 프로젝트에 유용

### 4-1. Github-Flow Rules
1. Master는 어떤 때든 배포가 가능하다.
    - Master는 항상 최신 상태며, stable 상태로 배포되는 Branch
    - Master는 엄격한 rule과 함께 사용하며, merge하기 전에 충분히 테스트가 되어야 함(테스트는 local에서 하지 않고, Branch를 push한 뒤, Jenkins로 테스트)
2. Master에서 새로운 일을 시작하기 위해 Branch를 새로 만든다면, 이름을 명확히 작성한다.
   - 새 Branch는 항상 Master에서 만듬
   - Git-Flow와 달리, Feature나 Develop이 존재하지 않음
   - 새로운 기능 추가나, 버그 해결을 위한 Branch 이름은 자세하게 작성하고, commit message 또한 명확하게 작성
3. 원격 Branch에 수시로 push한다.
   - 항상 원격 Branch에 자신이 하고 있는 일을 올려, 다른 사람들도 확인 할 수 있고, local 하드웨어 문제가 발생하더라도 원격 Branch의 소스를 받아 작업할 수 있게 해줌
4. 피드백이나 도움이 필요하거나, Merge 준비가 완료되었을 때는 Pull Request를 생성한다.
   - Pull Request(PR)는 코드 리뷰를 도와주는 시스템으로, 이것을 통해 코드를 공유하고 리뷰를 받을 수 있음
   - Master 준비가 완료되었다면, Master Branch 반영을 요구
5. 기능에 대한 리뷰와 논의가 끝났다면, Master로 Merge한다.
   - 곧바로 product에 반영되므로, 충분히 논의하고 CI도 통과된 후, 반영한다.
6. Master로 Merge되고 Push되었을 때는 즉시 배포한다.
   - Github-Flow의 핵심으로, Master로 Merge가 일어나면 자동으로 배포가 되도록 설정

## 5. Git Branch 전략 3 : Gitlab-Flow
- Production : Git-Flow의 Master Branch와 역할이 같으며, 오직 배포만을 담당
- Master : 기준이 되는 Branch로, 배포 전, Production Branch로 Merge하여 배포
- Pre-Production : Production으로 넘기기 전, 테스트를 수행하는 Branch
- Production Branch에서 배포된 코드가 항상 최신 버전 상태를 유지할 필요가 없다는 장점이 있음
- 복잡한 Git-Flow와 너무 간단한 Github-Flow의 절충안

### 5-1. Gitlab-Flow Rules
- Master Branch의 역할은 Production
- Production은 Master 이상의 권한만 push 가능
- Develop 권한 사용자는 Master에서 신규 Branch를 추가하고, 해당 Branch에서 commit & push. 그 후, Merge Request를 생성하여 Master에 Merge 요청
- Master 권한 사용자는 Develop 권한 사용자와 충분한 리뷰를 진행한 뒤, Master에 Merge, 그 후 배포 시에 Production과 Merge
- 테스트가 필요하면, Production으로 Merge하기 전, Pre-Production에서 테스트

## 6. Fork & Pull Request
- 규모가 있는 경우, Branch보단 Fork와 Pull Request를 활용하여 구현
- Fork : Branch와 비슷하지만, 프로젝트를 통째로 외부에 복제하여 개발하는 방식
- Pull Request : 개발이 완료된 뒤, Branch처럼 바로 Merge를 하는 것이 아니라 git 관리자에게 Merge 요청을 보내고, 관리자가 요청과 해당 코드를 보고 적절하다 싶으면 Merge하는 방식