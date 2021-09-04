package com.mall.itemservice.config.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppEndListener implements ApplicationListener<ContextClosedEvent> {

    protected final Logger log = LoggerFactory.getLogger(AppEndListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n=============\r\n");
        sb.append("ContextClosedEvent embedded tomcat shut down bye :)\r\n");
        sb.append("=============\r\n");
        log.info(sb.toString());
    }
}
