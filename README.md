# mall


목표 : 가벼운 쇼핑몰 구현
1. msa 구성
   - webflux, grpc, r2dbc 등을 사용하여 Non-Blocking 한바퀴 돌려보기 
      - RxJava 공부 필요
      - 함수형 프로그래밍 공부 필요
      - 비동기를 잘못쓰면 장애파티...시행 착오 존재할듯 
   - rabbitMQ로 생성/소비자 
    
2. db 관련 
   - JPA 공부 후 queryDsl-JPA 사용해보기 
   - r2dbc 사용해보기 (실제 사용하면 어떨지?)
   - mariadb, postgresql 사용
   
3. domain 기반 architecture 구성 ? cqrs 패턴을 사용?
   - Command 도메인 DB와 Query 도메인의 DB를 분리하여, 각각의 도메인 목적에 맞게 집중하여 개발해볼까? 
   - 실제 주문이외에 사용자가 커맨드가 많지 않다고 판단
   - 관리자에서의 Command가 다량이고, 이걸 Query 도메인에 어떻게 싱크를 맞출지를 고민
   - ....근데 이건 다 대용량일때의 고민인듯.... 아무도 안들어오면 실제 그에 해당하는 소규모 구성이 관리 및 유지보수 용이...

4. 화면
   - 사용자/관리자 화면 react.js ?

5. network 구성 
   - aws-litesail 사용하여 인스턴스 구성 후 빌드/배포 적용까지 해보고 싶음.



![image](https://user-images.githubusercontent.com/25473606/129032065-a19edbcb-5ffe-47a2-a60a-be4242983961.png)



