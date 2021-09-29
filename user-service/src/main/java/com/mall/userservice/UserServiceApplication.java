package com.mall.userservice;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.mall.userservice.process.user.client"}) // feign client 사용 명시 (@FeignCluent를 찾아 구현체를 만들어 줌)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @Bean
    // order-service-url : http://ORDER-SERVICE/order-service/%s/orders (ip, domain 대신 microservice name 사용 가능)
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10*1000); // 10초
        factory.setReadTimeout(10*1000); // 10초

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

    /**
     * feign-client logging
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }



}
