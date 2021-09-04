# mall

![kyo-mall-architecture drawio](https://user-images.githubusercontent.com/25473606/132099298-5a25c47e-a831-4288-8452-b64f06dd10b5.png)


[목표 : 쿠폰 교환 쇼핑몰 구현]
1. msa 구성
   - spring-cloud discovery & gateway & config 사용
   - webflux + r2dbc : user-service 를 비동기로 구현
   - rest api (hateoas) + jpa : item-service, order-service 구현
   - rabbitMQ : spring-cloud-config 사용 후 설정값 동기화 시 경량 메시지 브로커 사용
   - micro service 간 kafka 발행/구독 으로 데이터 동기화 사용
   - 사용자 및 관리자 접근할 gateway api를 물리적으로 분리
    
2. db 관련 
   - rest api (hateoas) + jpa (hibernate + queryDsl) 사용 
   - webflux + r2dbc 사용
      - user-service 구현해보니 R2DBC 를 사용해서 API 에 대한 요청은 처리할 수 있지만, JPA 의 기능은 지원하지 않는다....
      - 단순 Entity 를 조작하는 용도로 사용하겠는데, 그 이상의 용도로는 아직은 그닥..
   - local profile 사용 db 는 h2 사용
   
3. domain 기반 architecture 구성 ? cqrs 패턴을 사용?
   - Command 도메인 DB와 Query 도메인의 DB를 분리하여, 각각의 도메인 목적에 맞게 집중하여 개발해볼까? 
      - 실제 주문이외에 사용자가 커맨드가 많지 않다고 판단
      - 관리자에서의 Command가 다량이고, 이걸 Query 도메인에 어떻게 싱크를 맞출지를 고민
      - ....근데 이건 다 대용량일때의 고민인듯.... 아무도 안들어오면 실제 그에 해당하는 소규모 구성이 관리 및 유지보수 용이...

4. 화면
   - 사용자/관리자 화면 react.js
      - 여기까지 가려면 갈길이 구만리네..??ㅎㅎ

5. network 구성 
   - aws-lightsail 사용하여 인스턴스 구성 후 빌드/배포 적용까지 해보고 싶음.



