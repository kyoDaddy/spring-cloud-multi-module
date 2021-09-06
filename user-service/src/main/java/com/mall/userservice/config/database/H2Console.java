package com.mall.userservice.config.database;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@Component
@Profile("local")
public class H2Console implements InitializingBean, DisposableBean {

    private Server webServer;
    private final String webPort = "8082";

    /**
     * Bean 생성 시 동작할 코드 구현
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("starting h2 console at port -> {}", webPort);
        this.webServer = Server.createWebServer("-webPort", webPort, "-tcpAllowOthers").start();
    }

    /**
     * Bean 소멸 시 동작할 코드 구현
     */
    @Override
    public void destroy() throws Exception {
        log.info("stopping h2 console");
        this.webServer.stop();

    }
}
