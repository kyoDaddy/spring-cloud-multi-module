package com.mall.orderservice.config.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Local class
 *  - 지역의 [언어][나라] 등의 정보를 담고 있는 클래스
 *
 *
 */
@Slf4j
@Configuration
public class LocaleConfig {

    /**
     * @PostConstruct Bean이 완전히 초기화 된 후, 단 한번만 호출되는 메소드
     */
    @PostConstruct
    public void setDefaultLocale() {
        Locale.setDefault(Locale.forLanguageTag("ko-KR"));
        log.info("set Default Locale => {}", Locale.getDefault());
    }

}
