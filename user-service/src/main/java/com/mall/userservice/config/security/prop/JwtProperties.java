package com.mall.userservice.config.security.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secretKey;
    private int expiresMinutes;

    @PostConstruct
    public void showProperties() {
        log.info("jwt.secretKey => {}, jwt.expiresMinutes => {}", secretKey, expiresMinutes);
    }


}
