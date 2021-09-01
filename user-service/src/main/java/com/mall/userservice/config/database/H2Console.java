package com.mall.userservice.config.database;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@Component
@Profile("local")
public class H2Console {

    private Server webServer;
    private Server tcpServer;

    private String webPort = "8078", tcpPort = "9092";

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        log.info("starting h2 console at port -> {}, tcp port -> {}", webPort, tcpPort);
        this.webServer = Server.createWebServer("-webPort", webPort, "-tcpAllowOthers").start();
        this.tcpServer = Server.createTcpServer("-tcpPort", tcpPort, "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port -> {}, tcp port -> {}", webPort, tcpPort);
        this.webServer.stop();
        this.tcpServer.stop();
    }

}
