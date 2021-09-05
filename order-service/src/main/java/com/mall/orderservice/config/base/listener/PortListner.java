package com.mall.orderservice.config.base.listener;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 서블릿 컨테이너 = 웹컨테이너 = 웹 어플리케이션 서버(WAS) ex) tomcat, jetty ..
 *     => 서블릿의 생명 중기 관리(생성, 초기화, 전달)
 *     => 클라이언트의 request를 받아주고, response를 보내주며, 정적인 웹 페이지 생성을 위해 존재함
 * 서블릿 :
 *     => 클라이언트의 요청을 처리하고 그 결과를 다시 클라이언트에게 응답하는 Servlet class 의 구현 규칙을 지킨 자바 프로그램
 *      init() - 초기화, service() - 요청, destory() - 파괴
 */
@Component
public class PortListner implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        ServletWebServerApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("on port ==> " + applicationContext.getWebServer().getPort());
    }
}
