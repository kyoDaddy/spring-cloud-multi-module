package com.mall.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    /* spring actuator httpTrace
          - http를 trace한 정보를 노출(기본적으로 최신 100건의 reqest-response를 보여줌)

          - actuator는 상당히 민감한 정보를 많이 볼 수 있어서, 운영시 보안에 신경 써여함.. 실사용시에 Role을 설정하여 특정 사용자만 접근하도록 변경 필요
          - 정보가 메모리에 저장되어, 운영시에는 영구저장소에 저장되도록 변경 필요
          
     */
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

}
