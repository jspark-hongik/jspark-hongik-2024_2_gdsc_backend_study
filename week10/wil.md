# <초급 백엔드 스터디 10주차 WIL>  

## git에 add, commit, push할 때 주의할 것  
    VSCODE에서 왼쪽 상단 탐색기 -> 폴더 열기 버튼 눌러서 폴더 여는 것 빼먹지 말자... 'git이 왜 안되지?' 하면서 1시간 날렸네...

## 키워드  
    - 유효성 검증, 커스텀 예외 만들고 예외 종류에 맞는 응답 전송, 예외 메세지 클래스 리팩토링  

### 1. 유효성 검증
    - 유효성 검사  
        - 지금까지 만든 API 서버는 의도한 요청에 대해서만 처리한다.  
        - 하지만 실제로는 의도하지 않은 요청이 들어올 때도 많다.  
        - 예 : 테이블 제약에는 content의 길이가 최대 200으로 명시되어 있지만, 200보다 긴 문자열이 요청으로 들어온 상황  
        - DB에 데이터를 넣을 때 에러가 발생하지만, 결과적으로 길이가 200이 넘는 데이터는 저장이 되지 않으므로 정책을 위반하지는 않음  
        - 하지만 클라이언트는 무엇이 문제인지 알 수 없다. 단지 500 Internal Server Error 를 받을 뿐이다.  
        - cf; HTTP 에러 메세지에서 4xx 에러 : client 측 문제, 5xx 에러 : server 측 문제  
        - 또한, 정책을 위반한 데이터라는 것은 어플리케이션에서도 알 수 있음에도 DB까지 요청을 보내서 예외를 확인하므로 시간, 자원의 낭비가 발생함.  
        - **유효성 검사** : 요청으로 들어오는 데이터가 올바른 형식인지 검사하는 것  
        - 스프링에서는 데이터를 받아들이는 DTO에서 유효성을 검사한다.  
        - 유효성 검사는 '형식'만 검사한다. 존재하지 않는 멤버 아이디와 같은 경우는 유효성 검사로 체크할 수 없다.  
        - build.gradle의 dependencies에 implementation 'org.springframework.boot:spring-boot-starter-validation' 외부 의존성을 추가한다.(build.gradle이 바뀐 뒤에는 꼭 gradle을 다시 로드해야 함)  
        - DTO 클래스에 제약 사항과 에러 메시지를 명시한다.  
        - @Valid를 사용하여 명시된 제약 조건에 맞는지 검사한다.  

### 2. 예외 처리  
    - 예외 처리  
        - 현재 서버에서 에러가 발생했을 때 response body 내용만으로는 에러가 발생한 원인을 알 수 없다.  
        - 에러가 발생했을 때, 원인을 알려주는 에러 메시지를 담도록 직접 응답 객체를 만들어서 전송하자  
    - Global Exception Handler  
        - 스프링은 예외 종류에 따라 응답할 response를 설정할 수 있는 Global Exception Handler를 제공한다.  
        - 이름 그대로, 스프링 어플리케이션 전역에서 발생하는 모든 에러에 대해 어떻게 처리할지 결정한다.  
        - Global Exception Handler는 공통으로 사용하므로 common 패키지 밑에, GlobalExceptionHandler(자바 클래스)를 생성한다.  
        - 에러 정보를 반환할 DTO도 response 패키지 밑에 생성한다.  
        - ErrorResponse 클래스는 아래와 같이 작성한다.  
        `@Getter
        @AllArgsConstructor
        public class ErrorResponse {
            private String message;
        }`  
        - Message 이외에 추가로 보내고 싶은 정보가 있다면 추가하자.  
        - GlobalExceptionHandler 클래스 작성 요령은 강의록 참고  
        - 스프링 어플리케이션에서 에러가 발생하면, 해당 에러 타입에 대한 핸들러가 기존 컨트롤러 대신 response body를 생성해 응답한다.  
        - 에러 클래스를 매칭할 때는, 상속관계를 따라 올라가며 매칭된다.  
        - Exception 클래스는 모든 에러 클래스의 공통 부모  
            -> Exception 클래스에 대한 핸들러를 작성하면 특정 핸들러로 처리하지 못한 에러는 이 핸들러가 처리해준다.  
        - @ControllerAdvice는 무슨 어노테이션일까?  
        - AOP : Aspect-Oriented Programming (관점 지향 프로그래밍)  
        - AOP는 객체지향 프로그래밍을 보완하는 개념  
        - Aspect : 여러 클래스에서 공통적으로 갖는 관심사  
        - Joint Point : 프로그램의 실행 중 발생하는 교차점  
        - Advice : 특정 joint point 에서 실행하는 action  
        - @ControllerAdvice는 모든 컨트롤러의 공통 관심사(에러처리)를 별도의 클래스로 분리하여 구현한 것  
        - @Transactional도 모든 서비스 메서드의 공통 관심사(트랜잭션)를 미리 구현된 별도의 클래스가 대신 처리하도록 구현한 것  
    - **커스텀 예외 처리**  
        - 어플리케이션이 공통으로 사용할 예외이므로 common 패키지 밑에 exception 패키지를 만든다.  
        - 그 패키지 밑에 커스텀 예외 클래스를 구현한다(예 : BadRequestException 클래스)  
        - 실행 중 발생하는 예외이므로 RuntimeException을 구현한다.  
        `public class BadRequestException extends RuntimeException { ... }`  
        - 구체적인 구현 내용은 강의록 참고  
    - 에러 메세지 클래스
        - 예외 메세지 문자열이 여러 곳에 중복적으로 사용되는 상황  
        - 기존 예외 메시지를 추가하거나, 수정하기가 불편하다.  
        - 예외 메세지를 상수로 정의한 클래스를 만들자.(/common/message/ErrorMessage)  
        `public class ErrorMessage {
            public static final String MEMBER_NOT_EXISTS = "존재하지 않는 멤버입니다.";
        }`  
        - 서비스에 작성한 에러 메세지를 상수로 변경한다.  
        - DTO에 작성한 에러 메세지도 상수로 적용할 수 있다.  
