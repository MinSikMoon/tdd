# TDD 
> Trying to make git-log-parsing java module in TDD way.
- TDD... 많이 들어봤을 것이다. 절판된 채수원 아저씨 책도 사보고 유투브도 봤는데, 바쁜 업무를 핑계로 적용시켜보기가 쉽지 않았다. 또 교육용 예제들은 뭔가 강의를 위한 예제같아서 크게 와닿지 않는 느낌도 들었다.
- 이번에 개발계 CI/CD 만들때 마침 윈도우 CLI에서 호출할, GIT 로그 파싱모듈이 필요했다.
- 사이즈도 작고 TDD해보기 좋겠다고 생각했다.
- 아직 미숙하지만, TDD 방식 적용해서 모듈 개발해보고, 개인적인 생각과 기록을 남겨본다.

# 준비물
- 객체지향
- junit, mockito 써보기
- 이클립스

# junit 왜 필요해?
- 써보니까 테스트를 위한 main 메소드가진 클래스를 따로 안만들어서 편했다.
- junit 안썼다면 아마도 개발할때 main메소드 안에서 이것저것 콘솔로그 찍어가면서 테스트했을 것이다. 이 과정을 편하게 만들어주는 프레임워크가 junit 
- 확실히 직접 써봐야 '아~' 이러면서 왜 쓰는지 느낀다. 써보길 추천.
# TDD?
- 내가 원하는 메소드를 가진 모듈이 있다고 가정을 하고 코드를 짜보는것.
- 당연히 이클립스 에디터 창에서는 없는 객체와 메소드를 적고 있으니, 빨간색 경고등을 띄워줄것이다.
- 그러면 실제 존재하지 않는것들을 만들어나가면 된다. -> 실패부터 먼저 해라.
- 많은 자기계발 서적에서 나오는 것과 비슷한듯. 커다란 준비를 하면서 한번에 뭔갈 이루려고 하지말고, 일단 작은 행동먼저하고 실패 한다음에 보완해나가는 방식. 요런게 쌓이면서 커다란게 완성된다는 요지다.
- 커다란 로직을 다 생각하고 개발하는게 아닌, 작은 단위(메소드)부터 만들고 수정해나가는 작업이 반복된다. 이러면서 **패턴이 발견되고, 패턴들을 다시 정리(리팩토링)해나가다 보면 어느새 커다란 로직이 이쁘게 만들어져있는걸 발견하게 될것이다.** 
# TDD의 장점. 산출물
- 단위 테스트코드, 객체설계, 인터페이스가 자동적으로 만들어진다.
- 테스트를 자주 돌릴수 있어, 내가 미처 생각치 못한 경우들 발견할 수 있었다. 이런것들을 자주 보완해나가 주므로 로직이 아주 콤팩트해짐. 야무져진달까.

# GIT-LOG-PARSER 만들어보기
- ## 0. 개요 
    > git log가 정리된 파일을 읽어서, commit message, changed file list들을 쉽게 추출해주는 모듈.  
    
    git 원격 레포지토리 커밋들을 svn 원격 레포지토리에다가 체크인해주는, 다소 희안한 필요때문에 만들어놓고 싶었다.
    ````txt
    ->abcd1234|커밋메시지1입니다.
    
    test1.java
    test2.java

    ->efgh5678|이건 커밋메시지2야.
    readme.md
    test1.java
    test3.sql
    ````
    위와 같은 텍스트 파일이 있다고 치자.  
    '커밋메시지1입니다.'  
    '이건 커밋메시지2야.'   
    처럼 커밋메시지를 쉽게 뽑아주고,  
    test1.java  
    test2.java  
    처럼 커밋당 변화된 파일목록도 string으로 이쁘게 뽑아주는 그런 모듈을 만들고 싶다는 생각을 한다.
- ## 1. 모듈의 이름, 인터페이스 생각해보기 
    git log를 파싱해주는 거니까 gitLogParser 정도가 적당한듯.
    그리고 
    ````javascript
    String commitMessage = gitLogParser.getCommitMessage(oneCommitLog);
    ````
    뭐 이런 모습을 상상해볼 수 있겠지. 요 정도만 생각해놓고 출발해보자.
    