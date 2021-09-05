package com.mall.orderservice.config.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    protected final Logger log = LoggerFactory.getLogger(AppStartedListener.class);
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n=============\r\n");
        sb.append("Started Event :)\r\n");
        sb.append("=============\r\n");
        log.info(sb.toString());
    }
}
