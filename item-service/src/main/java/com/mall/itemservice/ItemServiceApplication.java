package com.mall.itemservice;

import com.mall.itemservice.config.base.listener.AppStartListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class ItemServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder()
                .sources(ItemServiceApplication.class)
                .listeners(new ApplicationPidFileWriter(), new AppStartListener())
                .run(args);
    }

}
