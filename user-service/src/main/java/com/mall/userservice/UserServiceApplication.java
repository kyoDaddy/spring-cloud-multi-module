package com.mall.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * R2DBC 를 사용해서 API 에 대한 요청은 처리할 수 있지만, JPA 의 기능은 지원하지 않는다.
 * 아니면 JPA Repository Wrapping 하는 구조의 형태로 구현할 수 있다.
 * 이에 대한 예제는 ReactiveCrudRepositoryAdapter.java 이와 같다.
 * 상용환경에서 간단한 단일 Entity 를 조작하는 용도로 사용하겠는데, 그 이상의 용도로는 손이 많이갈 것으로 예상된다.
 * webflux 를 사용했을 때 효과가 좋은 상황은 API 에 대한 트래픽이 많은 경우일텐데 트래픽이 적은 상황에서는 굳이 reactive 하게 개발할 필요가 없지 않나라는 생각이 들었다.
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


}
