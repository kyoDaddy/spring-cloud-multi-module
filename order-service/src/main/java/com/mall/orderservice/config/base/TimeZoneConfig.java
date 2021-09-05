package com.mall.orderservice.config.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@Configuration
public class TimeZoneConfig {

    /**
     * @PostConstruct Bean이 완전히 초기화 된 후, 단 한번만 호출되는 메소드
     */
    @PostConstruct
    public void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("set Default Time Zone => {}", TimeZone.getDefault().getID());
    }

}
