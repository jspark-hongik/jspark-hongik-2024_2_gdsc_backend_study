# <초급 백엔드 스터디 4주차 WIL>  

## 과제 스크린샷  
![ERD](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week4/week4ERD.png)  
![h2friend](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week4/h2friend.png)  
![h2member](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week4/h2member.png)  
![h2todo](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week4/h2todo.png)  

## 키워드  
    - 어플리케이션이 사용할 **DB 설계**, **JPA**를 이용한 **DB 구현**

### 1. DB 설계  
    - 개체(Entity) : 문제 상황을 구성하는 요소  
    - 관계(Relationship) : 개체와 개체 사이의 관계  
    - ER Model : 문제 상황을 개체와 관계로 표현하는 방법  
    - ERD : ER Model을 다이어그램으로 표현한 것  
    - 속성 : 개체와 관계가 가지는 세부적인 특징  
    - 속성의 예 : '사람' 개체는 '이름', '나이' 등의 속성을 가질 수 있고, '친구' 관계는 시작된 '연도' 속성을 가질 수 있음  
    - Primary Key(PK, 기본키) : 하나의 개체를 유일하게 식별할 수 있는 속성  
    - ER Model의 DB로의 구현 : 개체 → 테이블, 관계 → 테이블 or 외래키, 속성 → 테이블 컬럼  
    - 개체 간 관계의 종류 : 다대일(N:1), 일대다(1:N), 일대일(1:1), 다대다(N:M)  
    - 1:N 관계는 외래키로 구현  
    - 식별 관계 : 관계 대상의 PK를 자신의 PK로도 사용하는 것  
    - 비식별 관계 : 관계 대상의 PK를 자신의 FK로만 사용하는 것  
    - N:M 관계는 테이블로 구현(이때 관계 테이블은 자신만의 PK를 따로 가지는 것이 좋다)


### 2. JPA  
    - JPA : Java Persistence API  
    - DB에서 읽어온 데이터를 자바 객체로 매핑하는 자바의 표준 기술(ORM)  
    - 엔티티(Entity)는 자바와 DB가 소통하는 단위  
    - 테이블의 데이터 하나(레코드)는 엔티티 객체 하나로 매핑됨  
    - 엔티티 클래스를 정의하면, JPA가 엔티티 클래스 정의를 보고 테이블을 생성하는 SQL문을 알아서 작성하고 실행한다.  

#### 2-1. JPA - 의존성 추가  
    - build.gradle에 JPA와 H2 데이터베이스 의존성을 추가함
    - 의존성 정보가 바뀌면 반드시 gradle을 다시 로드해야 함
#### 2-2. JPA - DB 연결 정보 추가  
    - resources - application.properties 파일에 DB 접속 정보를 작성함
    - 편의를 위해 이 파일의 확장자를 yml로 바꿈
    - yml 파일은 콜론(:)을 사용하여 구분  
#### 2-3. JPA - 설정 파일 작성  
    - 다음과 같이 DB 정보 작성
![DB정보](https://github.com/jspark-hongik/jspark-hongik-2024_2_gdsc_backend_study/blob/main/week4/DBinfo.png)  
    - 스프링 부트는 기본적으로 H2 in-memory DB를 사용  
    - DB에 저장된 데이터를 볼 수 있도록 관리자 콘솔을 활성화(기본값은 false)하고, 관리자 콘솔에 접속할 url을 명시(설정하지 않으면 매번 랜덤 url 생성)  
    - 그 외 JPA 관련 설정은 강의록 참고  
#### 2-4. JPA - 관리자 콘솔 접속  
    - 어플리케이션 실행 후, localhost:8080/h2-console 에 접속  
    - JDBC URL에 application.yml 파일에 작성한 URL 입력 후 Connect 클릭  

### 3. 엔티티 클래스  
    - main - java - com.example.todoapi 안에 todo 패키지를 만들고, 그 안에 Todo 클래스 생성  
    - 엔티티 클래스는 테이블을, 클래스 필드는 컬럼을 나타냄  
    - @Entity 어노테이션으로 이 클래스가 엔티티라는 것을 명시  
    - @Id 어노테이션으로 PK 필드에 이 필드가 PK라는 것을 명시  
    - id 값은 보통 데이터를 생성할 때마다 자동으로 1씩 늘어남  
    - @GeneratedValue를 사용하면 id 값을 자동으로 생성함  
    - 이때 strategy는 IDENTITY로 설정함(키 값 결정을 DB에 위임)  
    - 어플리케이션을 실행하면 쉘에서 테이블 생성 SQL이 실행된 것을 볼 수 있고, 콘솔(localhost:8080/h2-console)에서도 Todo 테이블이 생성된 것을 볼 수 있다.  
    - ERD에서 설계했던 Column 이름과 타입을 맞추기 위해, 필드에 @Column 으로 이름과 타입을 명시함  

### 4. 엔티티 연관관계  
    - 외래키 컬럼을 나타낼 때는 Long 타입의 외래키 필드 대신, 해당 엔티티 타입의 엔티티 객체를 필드로 가지도록 설계함(해당 엔티티의 PK를 자신의 FK로)  
    - 외래키 필드에는 2가지 어노테이션을 지정해주어야 함  
        + FK 컬럼 정보를 명시하는 어노테이션(컬럼 이름 등); @JoinColumn  
        + 해당 외래키로 생기는 연관관계 종류를 나타내는 어노테이션; @ManyToOne(N:1), @OneToOne(1:1) 등  
    - @JoinColumn은 외래키 컬럼 정보를 나타냄  
    - @ManyToMany 는 N:M 관계를 나타냄. N:M 관계는 외래키 대신 테이블로 구현하므로 사용하지 않음  
    - @OneToMany 는 1:N 관계를 나타냄. 1에 해당하는 엔티티에 N에 대한 연관관계를 명시하는 양방향 매핑에 사용됨  
    - 연관관계 종류를 나타내는 어노테이션에넌 fetch 속성이 있음. 이 속성으로, 연결된 엔티티를 언제 가져올지 명시할 수 있음  
    - fetch type에는 EAGER, LAZY 2가지가 있는데, LAZY 사용 권장(디폴트는 EAGER)  
    - EAGER : 즉시 로딩, 객체 정보를 가져올 때 연결된 객체의 모든 정보를 함께 한번에 가져온다.  
    - LAZY : 지연 로딩, 객체 정보를 가져올 때 연결된 객체의 정보는 필요할 때 가져온다.  

### 5. 엔티티 생성자  
    - 엔티티 객체를 생성할 생성자를 만듦  
    - art+insert 키를 눌러 id(=PK)를 제외한 필드에 대한 생성자를 추가(id 값은 자동으로 생성됨)  
    - 필요에 따라 @Builder 와 같은 어노테이션을 사용하기도 함(스스로 찾아보기)  
    - JPA는 엔티티 객체를 다룰 때 public 또는 protected의 인자 없는 생성자가 필요  
    - @NoArgsConstructor를 사용하여 인자 없는 생성자를 만듦  
    - 이때 access 속성을 통해 접근 제한자를 protected로 설정(기본값은 public)  
    - 추가로 엔티티 객체에 @Getter를 추가해 모든 필드에 getter를 만듦(getter는 스스로 알아보기)  

### 6. 정리
    - ERD를 그려서 DB를 설계할 수 있다.  
    - JPA는 DB와 자바 객체를 매핑하는 도구이다. 이때 DB와 매핑되는 자바 객체를 엔티티라고 한다.  
    - JPA를 사용해서 엔티티 클래스로 DB를 정의할 수 있다.  
    - 엔티티 클래스에는 @Entity, @Id 어노테이션이 필요하다.  
    - Id값이 자동 생성될 땐 @GeneratedValue 를 사용한다.  
    - 일반 컬럼에는 @Column 으로 컬럼 명, 컬럼 타입 등을 지정한다.  
    - 외래키를 나타낼 땐 엔티티 객체를 필드로 넣고 @JoinColumn, @ManyToOne 을 사용한다.  
    - @ManyToOne 에서 fetch 속성은 LAZY로 지정한다.  
    - 엔티티 생성자는 보통 id 필드를 제외하고 만든다.  
    - JPA가 엔티티를 사용하려면 인자 없는 생성자가 필요하며, @NoArgsConstructor 어노테이션으로 간편하게 만들 수 있다.  