package com.mall.gateway.config.security.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProp {

    private String secretKey;

    private int expiresMinutes;

    @PostConstruct
    public void showProperties() {
        log.info("spring.jwt.secretKey => {}", secretKey);
        log.info("spring.jwt.expiresMinutes => {}", expiresMinutes);
    }


}
