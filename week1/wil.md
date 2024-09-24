## <초급 백엔드 스터디 1주차 WIL>

### 마크다운 작성법 참고
[WIL은 무엇이고 왜 작성해야 하나요?](https://www.gdschongik.com/what-is-wil)
[알아두면 좋은 간단한 md 문법](https://www.gdschongik.com/what-is-wil/md)

마크다운 문법에 따라 wil 작성하여 github에 레포짓하는데, 뭔가 내가 의도한 문법대로 글이 안 나온다..(들여쓰기 등)
여유 있을 때 알아보자

### 강의 내용 중 궁금했던 점
HTTP Method 중 데이터 수정 메소드 PUT, PATCH의 차이점이 잘 와닿지 않았다. 그래서 스터디장님에게 물어봤더니 예시를 들어 설명해주셨다.
> 노트북 자판에서 f 키를 흰색에서 검은색으로 바꾸고 싶다고 할 때, PATCH는 f 키만 떼내어 검은 f 키를 바꿔 끼우는 식이라면 PUT은 기존 노트북을 복제하여 복제한 노트북의 f 키를 검은 키로 만든 후 기존 노트북과 복제한 노트북을 교체하여 기존 노트북은 폐기되는 식이다.(맞게 이해했는지 모르겠다)
>> 종이로도 비유해주셨는데, 어떤 내용이 적힌 종이에 수정할 내용이 중간에 있을 때, 지우개로 그 부분만 지워서 고쳐 쓰는 게 PATCH라면, 종이를 교체해서 새로 쓰면서 그 부분을 고쳐쓰는 게 PUT이라고 하셨다. PUT을 쓰느냐 PATCH를 쓰느냐가 알고리즘에서 시간 복잡도를 따질 때처럼 유의미한 시간 차를 가져오지는 않지만 설계자가 PUT을 쓸지 PATCH를 쓸지 결정함에 있어 설계자의 의도를 반영한다고 볼 수 있다고 한다.(마찬가지로 올바르게 이해하고 글로 옮겨 적은 것인지는 모르겠다)

### 과제 - 스프링 개발 환경 준비 및 자바 어플리케이션 실행
![흰색 에러 화면](https://github.com/jspark-hongik/GDSCstudy/blob/main/week1/week1HW.png)

### 프로젝트 - 과제(API 명세서 작성)
원래 API 명세를 작성할 때는 request body, request header, response body, status code 등도 함께 정의해야 한다.

이번 과제에서는 HTTP Method, URL만 정의한다.  

<유의사항>
- URL을 정의할 때는 공통적인 서버 정보를 빼고, path만 정의
- Path는 대분류 → 소분류 단계로 점점 구체화되도록 작성하기  

<Todo mate의 기능들>  
    <핵심 기능>  
        • 각 유저마다 자신의 todo list를 갖고 있다.  
        • 각 유저는 자신의 todo list를 친구와 공유할 수 있다.  
    <세부 기능>  
        • 유저 회원가입 / 로그인  
        • 로그인한 유저의 할 일 생성 / 조회 / 수정 / 삭제  
        • 로그인한 유저의 할 일 체크 / 체크 해제  
        • 친구 추가 / 친구 조회 / 친구 삭제  
        • 특정 친구의 할 일 조회  

<명세서>  
    할 일 전체 조회 : GET /todo/list  
    할 일 생성 : POST /todo  
    할 일 수정 : PATCH /todo/{todo_id}  
    할 일 삭제 : DELETE /todo/{todo_id}  
    할 일 체크 : POST /todo/{todo_id}/check  
    할 일 체크해제 : POST /todo/{todo_id}/uncheck  
    (여기까지 강의록 예시)  
  
    할 일 전체 공유 : POST /todo/list/share  
    할 일 부분 공유 : POST /todo/{todo_id}/share  
    유저 회원가입 : POST /register  
    유저 로그인 : POST /login  
    유저 로그아웃 : POST /logout  
    친구 추가 : POST /friend/add/{user_id}  
    친구 조회 : GET /friend/list  
    친구 삭제 : DELETE /friend/{user_id}  
    특정 친구의 할 일 조회 : GET /friend/{user_id}/list  
  
### 키워드
HTTP Method와 URL, URL 구조,  HTTP 헤더와 바디, 상태 코드, 프론트엔드와 백엔드, API, 프로젝트 진행 과정, POST

**1. HTTP Method와 URL**  
    HTTP를 통해 요청을 보낼 땐 HTTP Method와 URL이 필요하다  
        - HTTP Method : 데이터를 다루는 방법  
            (cf. 자주 사용하는 HTTP Method : GET, POST, PUT, PATCH, DELETE)  
        - URL : 다룰 데이터의 위치  

**2. URL 구조**  
    - 기본 구조
        http://           (프로토콜(scheme))  
        www.example.com     (서버 주소(domain))  
        /user/1/nickname    (서버 내 데이터 위치(path))  

    - Path Parameter(URL의 일반화된 표현 방법)  
        http://                     (프로토콜(scheme))  
        www.example.com             (서버 주소(domain))  
        /user/{user_id}/nickname    (서버 내 데이터 위치(path))  

    - Query String  
        .com                    (서버 주소(domain))  
        /post/search            (데이터 위치(path))  
        ?page=1&keyword=hello   (Query String)  

**3. HTTP 헤더와 바디**  
        - HTTP 헤더 : 통신에 대한 정보(언제 보냈는지, 누가 보내는지, HTTP method 종류, 요청 경로 등)  
        - HTTP 바디 : 주고 받으려는 데이터(보통 json 형식)

**4. 상태 코드(Status Code)**  
        - 요청에 대한 처리 결과는 HTTP가 정의하는 상태 코드로 나타낸다.  
        - 상태코드는 응답 데이터의 HTTP 헤더에 들어간다.  
        - 대표적인 상태 코드  
            - 200 : 처리 성공(ok)  
            - 201 : 데이터 생성 성공(created)  
            - 400 : 클라이언트 요청 오류(bad request)  
            - 404 : 요청 데이터 없음(not found)  
            - 500 : 서버 에러(internal server error)  

**5. 프론트엔드와 백엔드**  
        - 응답받은 화면에는 여러 컨텐츠가 있고 그 컨텐츠들은 매일 바뀔 수 있다(예 : 네이버의 증시 차트, 날씨 등)  
        - 그렇다고 매일 html 코드를 수정하기는 번거롭다 → 상대적으로 덜 변하는 ***화면 UI(프론트엔드)***와, 자주 변하는 ***컨텐츠(백엔드)***를 분리  
        - 프론트는 화면에 채울 컨텐츠 데이터를 백엔드에 요청하고, 백엔드는 DB에서 가져온 컨텐츠 데이터를 프론트에 응답한다.  

**6. API**  
        - 어플리케이션에서 원하는 기능을 수행하기 위해 어플리케이션과 소통하는 구체적인 방법을 정의한 것  
        - 쉽게 말하면 어플리케이션의 사용 설명서  
        - 백엔드 API : 프론트가 백엔드에 요청을 보낼 때  
            - 어떤 http method, url을 사용해야 하는지 정의한 것  
            - 각 요청에 대해 어떤 응답을 보내는지 정의한 것  

**7. 프로젝트 진행 과정**  
        1. API 설계  
        2. DB, ERD 설계  
        3. API 서버 프로그램 작성  
        4. 테스트  
        5. 배포  

**8. POST**  
        - 로그인, 좋아요와 같은 기능은 URL에 동사를 그대로 쓰고 POST로 호출하기도 함  
            - POST /login  
            - POST /logout  
            - POST /register  
            - POST /like