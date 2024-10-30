# <초급 백엔드 스터디 5주차 WIL>  

## 참고할만한 사이트
[How to specify a JPA named parameter surrounded by wildcards?](https://stackoverflow.com/questions/5983321/how-to-specify-a-jpa-named-parameter-surrounded-by-wildcards)  
[[Spring JPA] JPQL like 사용법](https://ncookie21.tistory.com/17)  
[[JPA] JPQL 기본문법(JPA 기본편 by 김영한)](https://velog.io/@rmswjdtn/JPA-JPQL-%EA%B8%B0%EB%B3%B8%EB%AC%B8%EB%B2%95JPA-%EA%B8%B0%EB%B3%B8%ED%8E%B8-by-%EA%B9%80%EC%98%81%ED%95%9C)  
[4주차 강의록](https://file.notion.so/f/f/81276f83-e80d-4730-b0ae-1b519fac7648/f5cf836e-bc83-43a7-99ae-011986e624d0/%E1%84%8E%E1%85%A9%E1%84%80%E1%85%B3%E1%86%B8_%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3_%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5_4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1.pdf?table=block&id=73ab3320-6635-4a77-b05a-4394aa077b6c&spaceId=81276f83-e80d-4730-b0ae-1b519fac7648&expirationTimestamp=1730340000000&signature=KxM9gASPUGCyXyd-2fXZfxNcZnJolJ5kXkbCSBEpsKk&downloadName=%5B%E1%84%8E%E1%85%A9%E1%84%80%E1%85%B3%E1%86%B8+%E1%84%87%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A6%E1%86%AB%E1%84%83%E1%85%B3+%E1%84%89%E1%85%B3%E1%84%90%E1%85%A5%E1%84%83%E1%85%B5%5D+4%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1.pdf)  
구글 검색 : jpql wildcard, jpql like query  
[[Git] LF will be replaced by CRLF in 에러 해결법](https://velog.io/@realzu/Git-LF-will-be-replaced-by-CRLF-in-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0%EB%B2%95)  
[LF will be replaced by CRLF 에러](https://velog.io/@shrewslampe/LF-will-be-replaced-by-CRLF-%EC%97%90%EB%9F%AC)  

## 키워드  
    - 레포지토리 계층, 엔티티 매니저, 트랜잭션, 영속성 컨텍스트

### 1. 레포지토리 계층  
    - DB와 소통하며 데이터를 조작하는 계층  
    - 서비스 계층이 결정한 비즈니스 로직을 실제 DB에 적용함  
    - 데이터 조작은 크게 4가지 기능을 위주로 구현함(CRUD)  
        - 생성(Create)  
        - 조회(Read)  
        - 수정(Update)  
        - 삭제(Delete)  
    - CRUD 기능은 JPA가 제공하는 기능을 이용하여 구현함  

### 2. 엔티티 매니저(Entity Manager)  
    - JPA는 application.yml 정보를 통해 Entity Manager(이하 em)를 생성함  
    - em은 우리 대신 DB와 직접 소통하는 객체  
    - **em이 하는 일**  
        - 새로 생성한 엔티티 객체를 DB에 추가  
        - DB에서 조회한 데이터로 엔티티 객체 만들기  
        - 엔티티 객체에 대한 수정, 삭제를 DB에 반영하기  

### 3. 트랜잭션  
    - JPA는 DB와 유사하게 트랜잭션 단위로 동작함  
    - 트랜잭션이 끝나면 모든 변경사항을 DB에 반영함  
    - 트랜잭션 중간에 에러가 발생하면 트랜잭션 범위 안의 모든 변경점을 되돌림(롤백)  

### 4. 영속성 컨텍스트  
    - DB에서 조회한 엔티티를 캐싱하는 공간  
    - JPA가 DB에 반영할, 엔티티의 모든 변경 사항을 보관하는 공간(일종의 버퍼)   
    - 엔티티에 대한 변경 사항을 영속성 컨텍스트에 저장해 두었다가, 트랜잭션을 커밋하면 저장된 모든 변경점이 DB에 반영되도록 영속성 컨텍스트를 기반으로 한번에 SQL을 생성함  
    - **정리**  
        - 엔티티 매니저는 변경 사항을 모았다가, 한번에 SQL을 생성   
        - 이때 모든 변경 사항은 영속성 컨텍스트에 저장됨    
    - 엔티티 매니저에게 데이터 변경을 요청하고 싶다면...    
        - 하나의 데이터를 나타내는 '엔티티 객체'를 영속성 컨테이너에 올려두고, 영속성 컨텍스트에 올려둔 엔티티 객체를 변경하면 됨   
---  
## 정리 
    - 트랜잭션 = 데이터를 조작하는 행위들에 대한 쪼갤 수 없는 큰 동작 단위 (@Transactional)  
    - 엔티티 매니저 = 스프링 어플리케이션과 DB의 직접적인 소통을 대신 해주는 객체  
    - 영속성 컨텍스트 = 스프링 어플리케이션과 DB 사이에 존재하는 임시 엔티티 보관 공간  
      
    - 데이터 생성 = em.persist() (= 영속성 컨텍스트에 신규 엔티티 객체 등록)  
    - 데이터 조회 = em.find() (= DB에서 엔티티 객체 조회)  
    - 데이터 수정 = 조회한 엔티티 객체를 수정하면 트랜잭션이 끝날 때 알아서 반영  
    - 데이터 삭제 = em.remove() (= 영속성 컨텍스트에 엔티티 삭제 표시)  