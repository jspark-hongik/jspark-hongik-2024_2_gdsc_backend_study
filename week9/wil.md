# <초급 백엔드 스터디 9주차 WIL>  

## 참고할만한 사이트  
[강의록](https://file.notion.so/f/f/81276f83-e80d-4730-b0ae-1b519fac7648/bf093b9a-4ad8-430c-a1d9-c59b69f6ab3e/%E1%84%8E%E1%85%A9%E1%84%80%E1%85%B3%E1%86%B8_%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3_%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5_6%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1.pdf?table=block&id=3cb1385f-53d0-4739-8d63-03655854b791&spaceId=81276f83-e80d-4730-b0ae-1b519fac7648&expirationTimestamp=1731484800000&signature=F3V-IGstRimNXChoBkPMQonHTdXDEKKdmdWKI_-XMms&downloadName=%5B%E1%84%8E%E1%85%A9%E1%84%80%E1%85%B3%E1%86%B8+%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3+%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5%5D+6%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1.pdf)  
[creating bean 오류](https://dololak.tistory.com/674)  
[creating bean 오류2](https://hseungyeon.tistory.com/454)  
[primitive type](https://velog.io/@wkdwoo/Primitive-type%EC%9B%90%EC%8B%9C%ED%83%80%EC%9E%85-vs.-Reference-type%EC%B0%B8%EC%A1%B0%ED%83%80%EC%9E%85)  
[jackson data binding error](https://velog.io/@maketheworldwise/Jackson-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B0%94%EC%9D%B8%EB%94%A9-%EC%97%90%EB%9F%AC-InvalidDefinitionException-No-serializer-found-for-class)  
[jackson 관련2](https://sharonprogress.tistory.com/273)  
[jackson 관련3](https://velog.io/@yunred/disable-SerializationFeature.FAILONEMPTYBEANS)  
[스프링 부트 매개변수 이름 인식 문제](https://velog.io/@ghwns9991/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-3.2-%EB%A7%A4%EA%B0%9C%EB%B3%80%EC%88%98-%EC%9D%B4%EB%A6%84-%EC%9D%B8%EC%8B%9D-%EB%AC%B8%EC%A0%9C)

## Troubleshooting  
    - H2 DB console에서 계속 에러가 뜨길래 원인을 모르겠어서 답답했는데, Varchar의 매개변수를 ' '(작은따옴표)가 아니라 " "(큰따옴표)로 써서 생긴 오류였다. 왜 큰따옴표를 쓰면 에러가 나는지는 모르겠다.  
    - INSERT INTO Member VALUES (1, 'testid1', '홍길동', 'testpass1'); << 작은따옴표 사용  
    - BeanCreationException 에러(TodoApiApplication 실행 시 '종료 코드 1(으)로 완료된 프로세스'라고 뜨며 바로 종료됨) >> 매핑 경로가 같은 여러 @GetMapping 등을 가지면 해당 에러가 발생.  
    - DB 테이블의 속성 중 원시 타입을 가진 속성에 null 값이 들어가 있으면, CRUD의 RUD에서 에러가 발생한다 (코드 : 500)  
    - com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: java.util.ArrayList[0]->com.example.todoapi.todo.Todo["member"]->com.example.todoapi.member.Member$HibernateProxy$oIvyKXA8["hibernateLazyInitializer"])  
    - 위 에러가 발생했다. @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)을 추가해봐도, @JsonProperty을 추가해봐도 에러 해결이 안된다..  
    - application.yml에 spring:jackson:serialization:FAIL_ON_EMPTY_BEANS: false를 추가해서 postman을 돌려봤더니 해결됐다.(완벽한 해결은 아니라 더 알아봐야 할 듯)  
    - 할 일 조회에서 localhost:8080/todo/1을 해도 500 에러가 뜨는 문제가 발생했다. 위 '스프링 부트 매개변수 이름 인식 문제' 사이트를 보고 해결했다.  

## 키워드  
    - 스프링의 **컨트롤러 계층**, Postman을 사용한 **API 테스트** 

### 1. 컨트롤러 계층
    - 컨트롤러  
        - 클라이언트의 요청을 받고, 응답을 보내는 계층  
        - DTO(Data Transfer Object)를 사용하여 서비스 계층과 데이터를 주고받음   

    - 컨트롤러 계층  
        - HTTP 요청(매개변수)이 들어오면 전용 컨트롤러가 이 요청을 처리하고 HTTP 응답(반환값)을 반환한다.  
        - Todo 컨트롤러 클래스 내에는  
            - GET /todo/list 처리 메서드  
            - POST /todo 처리 메서드  
            - DELETE /todo/{todo_id} 처리 메서드  
        등이 있다.  
        - 컨트롤러도 하나의 객체만 생성하면 되므로 빈으로 등록한다.  
        - @Controller 어노테이션을 사용하면 컴포넌트 스캔의 대상이 된다.

    - **복습 : 프로토콜과 HTTP**  
        - 보통 어플리케이션과 관련된 데이터는 body에 담는다.(header에 담는 경우도 있지만, 이번 스터디에서는 다루지 않음)  
        - HTTP 요청으로 보내는 데이터는 Request Body,  
        - HTTP 응답으로 보내는 데이터는 Response Body에 담긴다.  

    - 컨트롤러 계층(이어서)  
        - API 서버는 json 데이터를 응답하는 경우가 많음  
        - @ResponseBody 어노테이션을 사용하면 메서드가 자바 객체를 반환했을 때, 객체를 json 데이터로 변환해서 response body에 담아 응답함  
        - 보통은 편의를 위해 @Controller와 @ResponseBody를 묶은 @RestController 어노테이션을 대신 사용한다.  
        - 컨트롤러 계층은 서비스 계층에 의존함  
        - 생성자 주입 방식으로 서비스 빈을 주입받는다(@RequiredArgsConstructor 및 private final TodoService todoService; 추가)  
        - @RequestMapping을 이용하여 메서드가 처리할 요청 method, url을 지정한다.(예 : @RequestMapping(method = RequestMethod.POST, value = "/todo") -> POST /todo로 요청을 받으면 @RequestMapping 아래의 메서드를 호출함)  
        - TodoController는 모든 todo 관련 요청을 처리하는 클래스  
        - 이때 API 설계에서 todo 관련 요청은 모두 /todo로 시작한다.  
        - URL 앞 공통 URL은 클래스에서 지정하고, URL 뒤 상세 URL은 메서드에서 지정할 수 있다.  
        - 보통 편의를 위해 요청 Method 종류는 어노테이션으로 지정한다.(예 : @PostMapping, @GetMapping 등)  
        - Request body 데이터는 보통 json 형식으로 들어오며, 메서드 파라미터로 받을 수 있다.  
        - @RequestBody를 사용하여 파라미터로 들어오는 json 데이터를 자바 객체로 변환하여 받을 수 있는데, 이 자바 객체를 DTO(Data Transfer Object)라고 함  
        - 예시 : public void createTodo(@RequestBody TodoCreateRequest request) { ... }  
        - 컨트롤러 계층은 클라이언트의 요청을 처리한 뒤, 처리 결과를 클라이언트에게 알려주어야 한다  
        - HTTP 프로토콜에 정의된 상태코드를 사용하여 결과를 알려주자  
        - 스프링이 제공하는 ResponseEntity를 통해 http 응답을 만들어서 반환한다  
        - 예시 : public ResponseEntity<Response Body로 반환할 객체의 타입> createTodo( ... ) { ...; return ResponseEntity.created(URL.create("/todo/")).build(); }  
        - **.created(URL.create("/todo/"))** : 201 created 상태코드를 갖는 응답을 생성함(201 응답은 생성된 데이터의 위치(path)를 함께 응답하는 것이 일반적임)  
        - 따라서 createTodo 메서드의 반환 타입을 Void에서 Long으로 바꾸고 todo.getId()를 return하도록 코드를 수정한다  
        - TodoService에서 생성된 todo 엔티티의 id를 코드를 적절히 수정함으로써 가져오자.(강의록 참고)  


### 2. Postman을 사용한 API 테스트  
    - Postman을 사용한 API 테스트와 관련된 자세한 사용법은 강의록을 참고하자  
    - 할 일을 조회하는 getTodoList 메서드에서 반환 타입을 ResponseEntity<List<Todo>>로 바꾸자. 여러 개의 Todo List가 반환될 것이므로 반환될 객체 타입으로 List<Todo>를 지정한다.  
    - 원래는 Response에도 DTO를 사용하는 것이 일반적이다(List<TodoResponse>와 같이 DTO를 리스트로 반환하면 된다)  
    - 할 일을 삭제하는 deleteTodo 메서드에서는 Path variable을 지정한다. 코드 적용 예시는 다음과 같다.  
    - @DeleteMapping(**"/{todoId}"**)  
    - public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) { todoService.deleteTodo(todoId); ... }  
    - 할 일 수정 메서드는 @RequestBody, @PathVariable을 모두 사용한다.