package com.mall.user.api;

import com.mall.user.api.process.entity.UserEntity;
import com.mall.user.api.process.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Profile("daemon-local")
@SpringBootTest
class UserApiApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {

        Flux<UserEntity> list = userRepository.findAll();
        list.subscribe(value -> System.out.println(value.getId()));


    }

}
