# TDD 
> Trying to make git-log-parsing java module in TDD way.
- TDD... 많이 들어봤을 것이다. 절판된 채수원 아저씨 책도 사보고 유투브도 봤는데, 바쁜 업무를 핑계로 적용시켜보기가 쉽지 않았다. 
- 또한 교육용 예제들은 detail한 tdd절차들까지 fm으로 가르쳐주니, 오히려 보는 사람으로 하여금, 너무 too much한 방식, 귀찮은 절차가 많은 방식이라는 느낌을 줄 수도 있을 것 같았다. 나도 몇번 해보니까 이게 왜 좋은지 이제 좀 알것 같은데, 그 전에는 TDD하면 뭐가 좋은지 감도 안잡혔다.
- 업무 하다가 쉽고 단순하지만 TDD의 묘미를 느낄 수 있는 모듈 개발이 있어서, 약간 각색해서 기록해보려고 한다. (원래 텍스트 파일 파싱하는 모듈 예제로 하다가, 그거보다 이게 더 단순하면서 효과적인것 같아서 바꿈.)
- 나도 아직 미숙하지만, 나처럼 TDD 왜 하는지 궁금했던 사람들에게 조금이나마 도움이 될 수 있음 좋겠다.

# TDD로 모듈 개발해보고 느낀점
- 장점  
1. 다 짜놓고 보면 밀도가 높은 코드가 완성되어 있다. 나조차도 이런 코드를 짜리라고는 예상못한 코드가 어느새 완성되어져있음.  
2. 작은 단위의 문제 해결을 빠르게 반복하다보니, 일단 재미가 있고, 몰입이 되었다. 리듬을 타게 되는 느낌이 들었다.
3. 테스트 케이스들, 객체 설계가 자동적으로 같이 생산됨.
4. 내가 미처 생각지 못했던 로직상 구멍들을 발견하고 보완할 수 있는 기회가 많이 포착됨.

- 단점  
1.  단점이 있을까 했는데, 있더라.  
 일단 내경우는 그게 뭐냐면, 코드를 줄이는 것에 몰두하다 보니, 너무 압축되어 해석하기 힘든 코드가 완성될때도 있었다.
즉 자기 만족으로는 좋지만, 다른 유지보수 개발자가 봤을때, 해석하는데 오래 걸리는 코드라면, 오히려 과도한 리팩토링이 유지보수성을 떨어뜨릴 수도 있겠다라고 느꼈다. 읽기 좋은 코드를 염두에 두고, 상식적인 리팩토링을 해야할듯.
# 준비물
- 객체지향
- junit, mockito 써보기
- 이클립스

# junit, mockito 왜 필요해?
- 써보니까 테스트를 위한 main 메소드가진 클래스를 따로 안만들어서 편했다.
- junit 안썼다면 아마도 개발할때 main메소드 안에서 이것저것 콘솔로그 찍어가면서 테스트했을 것이다. 이 과정을 편하게 만들어주는 프레임워크가 junit 
- 이걸로 끊임없이 test run 수행시키면서 개발해가는 거임. 실패뜨면 빨간불, 성공하면 녹색불 뜨는데, 빨간불을 어떻게든 녹색불로 바꾸는 사이클을 반복하는 것임.
- 확실히 직접 써봐야 '아~' 이러면서 이걸 언제, 왜 쓰는지, 왜 필요한지 느낀다. 글로 쓰려니 너무 귀찮다. 써보길 추천한다.
# TDD : 내가 생각하는

- 커다란 로직을 다 생각하고 개발하는게 아닌, 작은 단위(메소드)부터 만들고 수정해나가는 작업이 반복된다. 이러면서 **패턴이 발견되고, 패턴들을 다시 정리(리팩토링)해나가다 보면 어느새 커다란 로직이 이쁘게 만들어져있는걸 발견하게 될것이다.** 

# 실습 : GradeManager 만들기
>직무가 사원일때,  
![image](https://user-images.githubusercontent.com/21155325/58558006-ab0e0100-825a-11e9-8b36-cbc844b9cb68.png)

> 직무가 관리자일때  
![image](https://user-images.githubusercontent.com/21155325/58558829-c24dee00-825c-11e9-9a7a-377222e0a3dc.png)

- 업무중에 어떤 사람의 영업실적 point에 따라서 각각 다른 메시지를 만들어줘야하는 로직이 있었다. (원래 인자들이 몇개 더 있는데, 예제의 간결성을 위해 간단하게 각색함.)
- 기존 리거시에서는 
````java
if( 0 <= point && point < 500){
    grade = 1;
}else if ( 500 <= point && point < 1000){
    grade = 2;
}else if ( 1000 <= point && point < 1500){
    grade = 3;
    if(!prize.equals(""))
        grade = 4;
} .....
.
.
 else if ( 3500 <= point && point < 4000){
    grade = 13;
    if(!prize.equals(""))
        grade = 14;
}else if ( 4000 <= point){
    grade = 15;
    if(!prize.equals(""))
        grade = 16;
   
} 
.
.
생략
.
.
switch (grade) {
    case 1:
    case 9:
        message = "메시지1";    
        break;
    case 2:
    case 10:
        message = "메시지2";    
        break;
    .
    .
    생략
    .
    .
    default:
        break;
}
````

- 이런 스타일로 코드가 짜여져 있었다. 
### 위와 같이 짰을때 장점은?
- 그냥 봐도 코드가 요구사항 표와 1대1로 매칭 되면서 이게 무슨 일을 하는 로직인지 이해할 수 있다.
- 그래서 다음 유지보수 개발자가 빠르게 팔로우업 할 수 있다.
### 단점은?
- 일단 코드 길이가 길고, 코드패턴이 반복되면서 중복이 많다.
- 그래서 뭔가 grade 측정 기준이 바껴서 수정을 해야될때 여러 부분을 반복적으로 수정해줘야한다.

### 내가 원하는 리팩토링 후의 모습
```` java
message1 = gradeManager.getGradeMessage("사원", 1555, "");
message2 = gradeManager.getGradeMessage("사원", 1555, "bronze"); //bronze는 prize의 이름
message3 = gradeManager.getGradeMessage("관리자", 3055, "bronze"); 
````

- 요런 식으로 gradeManager라는 객체의 getGradeMessage(직무, point, prize) 함수를 쓰면 필요한 message를 간편히 받아볼 수 있게끔 만들고 싶다. 

## project 생성
![image](https://user-images.githubusercontent.com/21155325/58563007-53c15e00-8265-11e9-8203-70feae3233cd.png)

- 요렇게 프로젝트 하나 생성해준다. 여기서부터 만들어나갈 것이다.
- 사실 GradeManager도 존재하지 않는 상태에서 테스트 케이스에서 실패 후 만들러 가야되는데, 먼저 만들어 놓았다. 다음번에 수정해보겠음.

## 1. 테스트1 : 메시지 1 출력 
![image](https://user-images.githubusercontent.com/21155325/58563585-612b1800-8266-11e9-8f23-add1e8155060.png)
- 위와 같이 직무가 사원일때 point가 0~499, prize 유무에 상관없이 "메시지1"이라는 메시지를 받을 수 있어야 한다.
### 테스트 케이스 작성만으로도 우리가 얻은것.
- 일단 위와 같은 테스트 케이스를 작성만 했는데도, 설계가 몇개 나왔다.
  1. GradeManager에 메시지들이 MESSAGE_1, MESSAGE_2.. 와 같이 enum으로 관리
  2. 함수가 static
  3. prize가 null로 들어가는 경우도 생각해봐야함. 
  4. prize의 유무는 빈문자를 넣어주는지, 아닌지로 판별.
### 없는 변수, 함수 만들러가기
- 현재 GradeManager에 MESSAGE_1이라는 변수랑, getGradeMessage()함수가 없으니 빨간 줄뜬다.
- 이클립스에서 <kbd>ctrl</kbd> + <kbd>1</kbd> 누르던가 빨간색 클릭해서 만들러간다.
![image](https://user-images.githubusercontent.com/21155325/58564261-96843580-8267-11e9-8b83-68882d5b9fd1.png)  

#### 첫번째 테스트 케이스 통과
- 일단 요렇게 만들어주고, (데이터타입은 수정해주어야한다.)
- return 에 MESSAGE_1을 리턴하게 해주었다. 
![image](https://user-images.githubusercontent.com/21155325/58564598-30e47900-8268-11e9-9f4a-e8287b641b92.png)  
- 그리고 이제 테스트 돌리면 초록불뜨면서 통과된다. 
![image](https://user-images.githubusercontent.com/21155325/58564673-596c7300-8268-11e9-8f9b-a2a563a89267.png)

#### 위의 초록불이 뜻하는 것은?
- 해당 모듈이 위 6개 케이스에 대해 "메시지1"이라는 문자열을 뱉어주는 게 요구사항의 끝이라면, GradeManager은 완성되었다고 보면 된다.
- 하지만 우리의 요구사항은 저것보다는 좀더 많은 케이스를 충족시켜줘야하지.
- 케이스 하나씩 추가해봄면 이제 테스트 실패 뜰것이다. 그러면 초록불로 만들어주기 위해 또 수정, 리팩토링을 해나가는거다.
- 직무에 관리자를 넣고, 조건 point들을 넣어주니 로직 수정없이 테스트가 통과했다.
- 이로써 gradeManager가 MESSAGE_1을 뽑아주는 것에 대해서는 검증 끝.
![image](https://user-images.githubusercontent.com/21155325/58565252-5c1b9800-8269-11e9-94cc-a8f6158dda97.png)

## 테스트 2 : 메시지 2 출력
- 메시지2_테스트 를 만들어보자. 메시지1_테스트 복붙해서 이름만 고쳐보자.
![image](https://user-images.githubusercontent.com/21155325/58632990-28e90f80-8322-11e9-9b9d-566c0d7e1f63.png)

- 메시지2가 없으니 빨간줄 뜬다. 메시지2만들어주시고.
![image](https://user-images.githubusercontent.com/21155325/58633068-5e8df880-8322-11e9-8b7d-bb0b63083178.png)
- 테스트 돌려보면...(조건값 수정)
![image](https://user-images.githubusercontent.com/21155325/58633927-e07f2100-8324-11e9-9b58-9b4142c00142.png)


- 당연히 fail이 떠주신다... 메시지2가 아니라 메시지1이라고 리턴되서 fail났다고 한다. 
- 이제 이 빨간불을 초록불로 바꿔주는 거에만 집중해서 로직 수정하러 가보자.
![image](https://user-images.githubusercontent.com/21155325/58633952-f260c400-8324-11e9-84c1-5ed9081c1c71.png)
- 요렇게 고쳐주고.. 테스트 돌려보면
![image](https://user-images.githubusercontent.com/21155325/58634013-250abc80-8325-11e9-8551-3d3dd425235b.png)

- 초록불 잘뜬다.
- 오키, 2번째 테스트 케이스도 통과했다. 
- 슬슬 뭔가 코드의 중복이 보이고, 패턴같은게 보인다. 몸이 근질 거리지만 좀만 참고 테스트 3만들러 가보자.

## 테스트 3 : 메시지3 출력
![image](https://user-images.githubusercontent.com/21155325/58634779-2210cb80-8327-11e9-843a-9eb6352e8816.png)
- 테스트2랑 똑같다. 메시지3 만들어주고, 테스트 통과되도록 만들어보자.
![image](https://user-images.githubusercontent.com/21155325/58635181-2db0c200-8328-11e9-8cd2-5d56a11ad5aa.png)
- 요렇게 만들면 이제 초록불띄워주신다. 후후
![image](https://user-images.githubusercontent.com/21155325/58635223-49b46380-8328-11e9-8cec-30c2333fc96c.png)

## 리팩토링 타임 : 테스트4를 만들러가기전에 