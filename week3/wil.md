## <초급 백엔드 스터디 3주차 WIL>

### 과제 스크린샷
![WEEK3 SCREENSHOT](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week3/week3HW.png)

### 키워드
스프링 빈, 스프링 컨테이너, 의존성 주입, 컨테이너에 빈 등록(저장)하는 방법;(컴포넌트 스캔, 설정 파일 작성), 의존성 주입 받는 방법;(생성자 주입, 필드 주입), 스프링 계층 구조

**1. 스프링과 스프링 부트, 스프링 어플리케이션 구조**  
    <스프링>  
        - JAVA 진영의 대표적인 백엔드 프레임워크  
        - 객체지향 원칙을 지키며 개발할 수 있도록 도와줌  
    <스프링 부트>  
        - 스프링 프레임워크를 사용하여 개발할 때, 편의를 더해주는 도구  
        - 스프링으로 개발할 때는 스프링 부트를 함께 사용함  
    <스프링 어플리케이션 구조>
    ![스프링 어플리케이션 구조](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week3/springAPKstruct.png)

**2. 스프링 빈(Spring Bean)**  
    - 어플리케이션 전역에서 사용할 공용 **객체**  
    - 스프링 컨테이너라고 하는 공용 창고에 빈(객체)을 저장해두고, 필요한 빈을 컨테이너에서 받아 사용함  
    - 필요한 빈은 스프링 프레임워크가 자동으로 가져다 줌  
    - 이때 빈을 요구하는 객체도 스프링 빈(빈이 아닌 객체가 빈을 요구해도 프레임워크가 자동으로 가져다주지는 못함)  
    - 빈을 사용하는 주체 역시 스프링 빈이므로 서로가 서로를 필요로 하는 구조로 되어 있음

**3. 스프링 컨테이너**  
    - 스프링 빈이 저장되는 공간  
    - **어플리케이션 컨텍스트(Application Context)**라고도 함

**4. 스프링 빈을 스프링 컨테이너에 등록(저장)하는 2가지 방법**  
    4-1. 설정 파일 작성(수동 등록)  
        - 설정 파일은 자바 클래스로 작성  
        - 이때 클래스에 @Configuration 으로 설정 파일임을 명시  
        
        4-1-1. 스프링 빈 클래스 생성  
            - 스프링 빈을 생성할 클래스를 만듦  
            - main 폴더 밑에 bean 패키지(일단은 폴더라고 생각)를 만들고 MyBean 클래스 생성  
        4-1-2. 설정 파일 작성  
            - test 폴더 밑에 bean 패키지 생성, bean 패키지 내에 TestConfig 클래스 생성  
            - 클래스에 @Configuration, 메서드에 @Bean 어노테이션 사용  
            - 테스트용 컨테이너를 만들어 빈을 등록하고 확인해본다  
            - 테스트 확인 과정은 다음 링크 참고  
[강의록](https://file.notion.so/f/f/81276f83-e80d-4730-b0ae-1b519fac7648/84819199-93bd-4f78-94fc-81b7df70d578/%EC%B4%88%EA%B8%89_%EB%B0%B1%EC%97%94%EB%93%9C_%EC%8A%A4%ED%84%B0%EB%94%94_2%EC%A3%BC%EC%B0%A8.pdf?table=block&id=167fcfd7-03e6-400a-9115-0ad9d928338c&spaceId=81276f83-e80d-4730-b0ae-1b519fac7648&expirationTimestamp=1727935200000&signature=nDzlONURR-CdK43TRNYLUOB7KyemHP8TAqgtrvYJHWY&downloadName=%5B%EC%B4%88%EA%B8%89+%EB%B0%B1%EC%97%94%EB%93%9C+%EC%8A%A4%ED%84%B0%EB%94%94%5D+2%EC%A3%BC%EC%B0%A8.pdf)  
    4-2. 컴포넌트 스캔(자동 등록)  
        - 빈을 생성할 클래스에 @Component 어노테이션 사용  
        - 어플리케이션을 시작할 때 @Component 가 붙은 클래스를 찾아서 자동으로 빈 등록  
        - 컴포넌트 스캔은 @ComponentScan 어노테이션 사용  
        - 기존 TestConfig 내용을 모두 지우고, @ComponentScan 추가. 스캔해서 발견한 컴포넌트를 빈으로 등록함  
    4-3. 정리  
        - 설정 파일 작성 : @Configuration, @Bean  
        - 컴포넌트 스캔 : @Component, @ComponentScan  
        - 내가 등록할 빈을 생성하는 클래스에 @Component 를 붙이면 끝!  

**5. 의존성 주입**  
    - 빈을 사용할 때는 컨테이너에 직접 접근해서 빈을 꺼내지 않고, 프레임워크에게 **필요한 빈(의존성)을 요청**하고 받아서 사용함  
    - 자동차가 움직이려면 반드시 바퀴가 필요하다. 즉, 자동차는 바퀴에 **의존**한다.  
    - A의 기능을 실행하는데 B의 기능이 필요하다면, **'A는 B에 의존한다'**고 한다.  
    - **의존성 주입(Dependency Injection, DI)** : 내가 의존하는 객체를 직접 생성하지 않고 밖에서 주입받는 것  
    - 스프링에서는 컨테이너에 저장된 빈(객체)과 빈(객체) 사이의 의존성을 프레임워크가 주입하는 것을 말함  
    - cf. 빈이 아닌 객체에 빈을 자동으로 주입할 수는 없음. 스프링이 빈을 주입하려면, 두 객체 모두 스프링에 의해 관리(=빈으로 등록)되어야 하기 때문  
    **<의존성을 주입받는 이유>**  
        - 객체 지향 원칙 중 하나인 **OCP(Open Close Principle) 원칙**을 준수함(필요한 객체를 내가 하드코딩하지 않기에 유지보수하기 좋아짐)  
        - 매번 필요한 객체를 생성하는 대신, 생성해둔 객체를 사용하므로 메모리를 효율적으로 사용할 수 있음    
    <의존성 클래스 추가>  
        - bean 패키지에 MySubBean 클래스 생성 후 빈으로 등록  
        - Mybean에 MySubBean 의존성과 @Getter 를 추가  

**6. 의존성 주입 방법**  
    - 어떤 객체에 다른 객체를 주입하려면, 주입할 통로가 필요  
    - 우리는 통로를 만들고, 이 통로를 통해 주입해 달라고 표시해두면 프레임워크가 알아서 객체를 주입해줌  
    - 통로는 크게 **생성자, 필드, 메서드**가 존재  
    - 표시를 남길 때는 @Autowired 어노테이션으로 표시를 남김  
    
    6-1. 생성자 주입  
        - 의존성이 바뀔 일이 없는 경우 안전하게 final 로 선언  
        - 이때 final 필드는 생성자를 통해 초기화되어야 함  
        - 생성자에 @Autowired 를 사용하면, 생성자를 통해 빈을 주입함  
        - 만약 생성자가 하나만 있다면, @Autowired 생략 가능  
        - @RequiredArgsConstructor 를 사용하면 모든 final 필드에 대한 생성자를 자동으로 만들어주어 생성자 코드까지 생략 가능  
    6-2. 필드 주입  
        - 필드에 바로 @Autowired 어노테이션을 사용함(final은 사용 불가)  
        - 이 방식은 주로 테스트 코드에서 사용함(운영 코드에서 사용 시 IDE에서 경고 띄움)  
    6-3. 세터 주입(메서드 주입)  
        - 이 강의에서는 다루지 않음(실제로 잘 안쓰임)  

**7. 스프링 계층 구조(스프링 Layered Architecture)**  
    - 컨트롤러 <-> 서비스 <-> 레포지토리 <-> DB  
    <컨트롤러>  
        - 클라이언트의 요청을 받고, 응답을 보내는 계층  
        - DTO(Data Transfer Object)를 사용하여 서비스 계층과 데이터를 주고받음  
    <서비스>  
        - 어플리케이션의 비즈니스 로직이 담기는 계층  
        - 레포지토리 계층과 소통하며 엔티티, 또는 DTO로 소통함  
    <레포지토리>  
        - DB와 소통하며 데이터를 조작하는 계층  
        - 서비스 계층이 결정한 비즈니스 로직을 실제 DB에 적용함    
    - 컨트롤러, 서비스, 레포지토리는 스프링 빈으로 등록함  
    - 매번 새로운 객체를 생성할 필요가 없고, 객체지향 원칙을 준수하며 의존성을 관리할 수 있기 때문