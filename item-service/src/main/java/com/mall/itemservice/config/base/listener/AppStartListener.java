package com.mall.itemservice.config.base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * ApplicationEvent
 *      -Spring Boot에서는 기본적으로 제공해주는 다양한 시점의 이벤트가 있다. ex) Application이 잘 구동이 되었을 때 or 준비가 됐을 때
 *      -ApplicationListener 인터페이스를 상속받는 클래스를 만들 때 주의할 점은 어떤 이벤트의 리스너를 만드는지에 대한 타입을 줘야한다.
 *      -Event 발생 기점 : Application Context 생성 (이전에 발생하는 이벤트는 Bean으로 등록해도 Listner가 동작을 안함)
 *      -직접 등록(Application Context 생성 이전의 이벤트 리스너 등록)한 경우에는 @Component 어노테이션 빼기
 */
public class AppStartListener implements ApplicationListener<ApplicationStartingEvent> {
    protected final Logger log = LoggerFactory.getLogger(AppStartListener.class);
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n=============\r\n");
        sb.append("Application is Starting :)\r\n");
        sb.append("=============\r\n");
        log.info(sb.toString());
        System.out.println(sb.toString());
    }
}