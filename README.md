# TDD 
> Trying to make Message-maker-module in TDD way.
- TDD... 많이 들어봤을 것이다. 절판된 채수원 아저씨 책도 사보고 유투브도 봤는데, 바쁜 업무를 핑계로 적용시켜보기가 쉽지 않았다. 
- 또한 교육용 예제들은 디테일한 절차들까지, 차근차근보여주다보니, 오히려 보는 사람으로 하여금, 너무 too much한 방식, 귀찮은 절차가 많은 방식이라는 느낌을 줄 수도 있을 것 같았다. 특히 책으로 보면 속도감이 느껴지지 않아 더 그럴수도.
- 나도 그 전에는 TDD하면 뭐가 좋은지 감이 오질 않다가, 최근에 작은 것 개발단위부터 적용시키는 연습을 해보니, 이걸 하면 뭐가 좋은지, 몸으로 느끼고 있다. 이제 내 개발습관으로 고정될듯하다. 
- 업무 하다가 TDD의 묘미를 느낄 수 있는 심플한 모듈 개발이 있어서, 각색해서 기록해보려고 한다. (원래 텍스트 파일 파싱하는 모듈 예제로 하다가, 그거보다 이게 더 단순하면서 효과적인것 같아서 바꿈.)
- 나도 아직 미숙하지만, 나처럼 TDD 왜 하는지 궁금했던 사람들에게 조금이나마 도움이 될 수 있음 좋겠다.

# 뭐가 좋은 거지?
- 리팩토링을 빨리, 많이 할 수 있다. 
- (테스트 - 리팩토링) 사이클을 반복 수행하다 보면 어느새 야무진 코드가 완성되어져 있을 것이다.
# 어떻게?
- 테스트 코드를 작성해놓고 시작하기때문에, 리팩토링 전과 테스트 결과가 동일하다면, 내가 한 리팩토링이 유효하다고 판단할 수 있기 때문이지.
# 결과물 비교
### 이랬던 로직이
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
### 요렇게 컴팩트해 지더라.
![image](https://user-images.githubusercontent.com/21155325/58746154-e5171700-8495-11e9-954d-86b29ddd19be.png) 

### 장점  
1. 반복되는 리팩토링에만 집중했을 뿐인데, 끝나고 보면 밀도 높은 코드가 완성되어 있다. 나조차도 이런 코드를 짜리라고는 예상못한 코드가 어느새 완성되어져있음.  
2. 작은 단위의 문제 해결을 빠르게 반복하다보니, 일단 재미가 있고, 몰입이 되었다. 리듬을 타게 되는 느낌이 들었다.
3. 테스트 케이스들, 객체 설계가 자동적으로 같이 생산됨.
4. 내가 미처 생각지 못했던 로직상 구멍들을 발견하고 보완할 수 있는 기회가 많이 포착됨.

### 단점  
1.  단점이 있을까 했는데, 나의 경우는 리팩토링에 몰두하다 보니, 초면에 바로 해석이 되지 않는, 현학적인 코드가 완성되어져 있는 경우가 많은 것 같다.
즉 자기 만족으로는 좋지만, 다른 유지보수 개발자가 코드를 처음보고 해석에 시간을 들여야하는 코드라면, 오히려 과도한 리팩토링이 유지보수성을 떨어뜨릴 수 있겠다는 생각이 들엇다. 읽기 좋은 코드를 염두에 두고, 상식적인 리팩토링을 해야할듯.

# 준비물
- 객체지향
- junit, mockito 써보기
- 이클립스

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
- 메시지3 출력 기능까지 작동 및 테스트를 통과한 GradeManager와 해당 메소드를 구현해 보았다.  
![image](https://user-images.githubusercontent.com/21155325/58635181-2db0c200-8328-11e9-8cd2-5d56a11ad5aa.png)
- 이대로 두어도 문제 없지만, 리팩토링을 해서 동일로직 반복을 줄여보자.
- 그냥 슥 보면, position을 체크해주고 point 범위를 분기해주는게 반복되는 게 보인다.
### 리팩토링 1. point 범위 숫자를 배열의 요소 바꿔보자.
![image](https://user-images.githubusercontent.com/21155325/58745283-fd813480-8489-11e9-9912-4dfed61e6c65.png)
- 요런식으로 말이다.
- employeePoints라는 배열이 없으니 빨간줄 뜬다.  
- employeePoints 배열을 만들어주고, 테스트 돌려보자.  
![image](https://user-images.githubusercontent.com/21155325/58745311-5c46ae00-848a-11e9-9f03-4a206c42730e.png)  
![image](https://user-images.githubusercontent.com/21155325/58745318-77b1b900-848a-11e9-8461-e835b1e0e35b.png)

- 테스트 잘 통과한다. 
- #### 위 케이스를 통해서 TDD 장점을 하나 더 알 수 있다.
- #### 그게 뭐냐면 리팩토링을 안심하고 할 수 있다는 점이다.
- 예를 들어, 메소드의 동작은 그대로지만, 코드만 다른 모양으로 수정했다고 할때, 과연 코드를 고치기 전과 코드를 고친 후 메소드가 똑같이 동작하고 있다는 걸 어떻게 보장할 수 있을까?
- 위의 예제에서는 이를 보장할 수 있다. 왜냐면 테스트 코드를 만들어놓았기 때문이다. 리팩토링 전 후 모두 테스트를 통과했기에 우리는 GradeManager의 getGradeMessage() 메소드가 리팩토링 전과 똑같이 작동하고 있다고 말할 수 있다. 
- #### 테스트 코드를 만들어놓았기에, 우리는 안심하고 코드를 리팩토링 할 수 있다. 
### 계속 리팩토링 해보자.  
![image](https://user-images.githubusercontent.com/21155325/58745440-14c12180-848c-11e9-92ff-a3a08f7755fc.png)
- managerPoints 배열도 만들어주고, 위 코드의 테스트 코드 통과 확인했다.
### 리팩토링 2. isBetween 메소드 만들어주기
- point가 기준이 되는 숫자의 사이에 있는 지를 체크하는 구문이 길어보인다. 
```` javascript
boolean isBetween (double point, double sameAndBiggerThan, double belowThan)
````
- point가 특정수 이상, 특정수 미만인지 boolean으로 알려주는 함수를 만들어서 간결하게 만들고 싶다. 
![image](https://user-images.githubusercontent.com/21155325/58745519-f871b480-848c-11e9-849e-46b539a014b1.png)
- 빨간 불 뜨니까 만들러 가보자.
![image](https://user-images.githubusercontent.com/21155325/58745584-ad0bd600-848d-11e9-9196-4d4c54ae7da0.png)
- 요렇게 만들어 보고 테스트 돌리면 통과한다.
- 나머지들도 다 isBetween 함수로 교체 ㄱㄱ !!  
![image](https://user-images.githubusercontent.com/21155325/58745628-05db6e80-848e-11e9-9408-f0aab3614168.png)

- isBetween으로 모두 교체하고 테스트 돌려보면 잘 통과된다. 

### 리팩토링 3. message 들도 배열 요소로 만들기
- return 해주는 메시지들도 그냥 배열의 요소로 만들면 되겠다는 생각이 든다.
- 그 전에 employeePoints와 managerPoints 첫 요소에 0을 추가해주자. 0 이상에 대한 로직을 명시적으로 만들어주기 위해서다. 
![image](https://user-images.githubusercontent.com/21155325/58745695-19d3a000-848f-11e9-97ab-213e59540d98.png)
- 위 사진처럼 고치고 테스트 돌리면 실패난다. 
![image](https://user-images.githubusercontent.com/21155325/58745708-4f788900-848f-11e9-8045-8237bf3b50d6.png)
- 이제 points 배열의 0 인덱스에 위치한 수가 0이 되었기 때문이다. 
- 메소드 로직을 수정해줘서 다시 초록색으로 맞춰주겠다.
![image](https://user-images.githubusercontent.com/21155325/58745766-fc530600-848f-11e9-8c46-64c340d3c988.png)

- 테스트 돌려보면 통과한다.
- 그리고 뭔가 숫자끼리 규칙성이 보이는것 같다. 

### 리팩토링 4. if-else 없애기
- 직무를 체크해서 분기시켜주는 if - else를 봐보자.
- 뭔가 동일 구문이 반복되는 것 같아 찝찝하다.
- 뭐 때문에 분기시켜 주는 것일까?
- 잘보면 로직을 타는 배열이 employeePoints냐, managerPoints냐의 차이밖에 없다.
- 오키 그러면 배열 생산해주는 메소드 하나 만들어주면 if-else 없어지겠군.
![image](https://user-images.githubusercontent.com/21155325/58745841-88b1f880-8491-11e9-8d2c-170232f29fe9.png)
- getPointsArrayByPosition 이라는 메소드를 만들면 요렇게 바꿀 수 있다는 거지.
- 그리고 이제는 로직이 완벽하게 동일하므로 if-else를 없앨 수 있겠지.
![image](https://user-images.githubusercontent.com/21155325/58745856-c9117680-8491-11e9-9b77-88288cef671e.png)
- 확 짧아졌다.
- 이제 getPointsArrayByPosition() 메소드 만들러 가보자구.
![image](https://user-images.githubusercontent.com/21155325/58745922-b2b7ea80-8492-11e9-8822-8da7dad5eb10.png)
- 요렇게 만들어주시고. 테스트 케이스 돌려보면 
![image](https://user-images.githubusercontent.com/21155325/58745919-a03db100-8492-11e9-852b-3ab689aa8297.png)
- 잘 통과됩니다. 
### 리팩토링 5. 반복 isBetween 구문 없애기
- 보면 isBetween 메소드가 반복된다. 안에 인자랑 return 메시지만 바뀐다.
- 뭔가 루프 쓰면 될것 같은 느낌이 든다. 
![image](https://user-images.githubusercontent.com/21155325/58745934-f90d4980-8492-11e9-9888-6959c49b1eba.png)
- 먼저 메시지 들을 배열에 넣어 규칙성 발견 쉽게 만들어주자.
![image](https://user-images.githubusercontent.com/21155325/58746000-0840c700-8494-11e9-8f62-afb0886a0787.png)
- 오 뭔가 더 for 루프 쉽게끔 바뀐것 같다. 자신감 뿜뿜
- 그리고 저렇게 조금씩 바뀔때마다 테스트 돌려보는게 좋다. 이제 일일이 다 스샷 찍으려니 너무 낭비되는 페이지가 많은것 같아. 이런건 스킵한다. 
![image](https://user-images.githubusercontent.com/21155325/58746042-8735ff80-8494-11e9-9105-3711637a902f.png)
- 요렇게 바꿔주고 테스트 돌려보았다.
![image](https://user-images.githubusercontent.com/21155325/58746049-a2087400-8494-11e9-8ffa-969bfd466be4.png)
- !!! fail 뜬다. 왜? isBetween 비교문보면 i+1 인덱스를 넣어주는게 있는데, 이게 인덱스 최대값보다 넘어가니까 outOfIndex 되는거임.
- 그럼 for 구문 속의 최대값에 -1해주면 통과될까? 시도해보자
![image](https://user-images.githubusercontent.com/21155325/58746070-f3b0fe80-8494-11e9-8496-1b6c60787453.png)
- 요렇게 바꿔주고 테스트 돌려보면...
![image](https://user-images.githubusercontent.com/21155325/58746078-04617480-8495-11e9-88c6-bd87d8b27bd7.png)
- 오오오 잘돌아간다 ㅠㅜ

## 테스트3 + 리팩토링 결과를 돌아보며
- 일단 리팩토링까지한 코드가 테스트 1+2+3 통과를 했다. 
- 중간에 스샷이 많아서 길어보이지만, 일단 리팩토링, 전/후만 가져다가 비교해보자. 
- #### 리팩토링 전 : AS-WAS
![image](https://user-images.githubusercontent.com/21155325/58745311-5c46ae00-848a-11e9-9f03-4a206c42730e.png)  
- 리팩토링 후 : TO-BE
![image](https://user-images.githubusercontent.com/21155325/58746120-85207080-8495-11e9-9197-4ffe4bd9042a.png)
- getGradeMessage만 놓고 보면
- 요랬던게.  
![image](https://user-images.githubusercontent.com/21155325/58746160-f829e700-8495-11e9-80de-37de4a4069a9.png)  
- 요렇게 다듬어졌다.  
![image](https://user-images.githubusercontent.com/21155325/58746154-e5171700-8495-11e9-954d-86b29ddd19be.png)  
### 작은걸 고치는데 집중하다보니 어느새 코드가 다듬어져 있더라.
- TO-BE 코드의 모습을 갖추기 까지, 우리는 그저 AS-WAS 코드의 매우작은 부분을 고쳐보고, 테스트 돌려보고, 다시 고쳐보고를 반복했을 뿐이다.
- TO-BE 코드의 모습을 미리 설계하지 않았다. 그저 눈 앞에 닥친 작은 리팩토링만을 반복했을 뿐이다. 그런데 어느새 컴팩트하게 다듬어진 로직이 만들어져있다.
- 요게 기존에 로직을 만들던 방식과 TDD의 차이점이라고 느껴졌다.  
- 다시 한번 더, 우리가 리팩토링을 자신감 있게 할 수 있었던 이유는? = 테스트 코드가 갖춰져 있었기 때문에.
- 테스트 4 만드는 것은 다음 시간에 써야겠다. 
- 사실 요까지만 해도 TDD가 뭔지, 왜 하는건지, 이걸로 얻는 결과가 뭔지 거의 다 느낌이 왔을 거라 본다. 앞으로는 지금까지 했던 행위들의 반복이다.