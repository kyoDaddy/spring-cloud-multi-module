package com.mall.itemservice.config.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 전역적으로 cors (Cross-Origin Resource Sharing) 설정
    // Single-Origin Policy를 우회하기 위한 표준 기술
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 패턴
                .allowedOrigins("http://localhost:8000");  // 8000 포트 허용

    }
}
