package com.mall.userservice.config.base.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ConfigurationProperties
 *      - 자동 설정 클래스 내의 스프링 빈을 property 파일을 통해 재정의 하고자 할 때는 이 어노테이션을 사용한다.
 *      - build.gradle 내 spring-boot-configuration-processor dependency 추가 필요
 *      - Configuration bean 등록 후 사용
 *      - change active spring profile 설정 필요
*/
@Slf4j
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "daemon")
public class DaemonProperties {

    private String nickName;

    @PostConstruct
    public void showProperties() {
        log.info("daemon.nick-name => {}", nickName);
    }

}
