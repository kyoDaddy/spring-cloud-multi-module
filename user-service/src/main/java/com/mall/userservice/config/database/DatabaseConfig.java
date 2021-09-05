package com.mall.userservice.config.database;

import com.mall.userservice.process.user.entity.UserAuth;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Profile("local")
@Slf4j
@Configuration
public class DatabaseConfig {

    @Bean
    public ConnectionFactoryInitializer dbInit(ConnectionFactory connectionFactory) {

        // 데이터베이스 초기화를 할수 있음 ConnectionFactoryInitializer
        ConnectionFactoryInitializer init = new ConnectionFactoryInitializer();
        init.setConnectionFactory(connectionFactory);
        init.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));  // 커넥션꺼

        return init;
    }

    @Bean
    public CommandLineRunner dataInit(UserRepository userRepository, PasswordEncoder passwordEncoder) { // ioc에서 해당하는 userRepository 주입이 된다.
        return (args) -> { // run 함수
            // 데이터 초기화 하기
            userRepository.saveAll(Arrays.asList(
                            UserEntity.builder()
                                    .fullName("kyo")
                                    .email("rlarbghrbgh@gmail.com")
                                    .password(passwordEncoder.encode("test!1234"))
                                    .createdAt(LocalDateTime.now())
                                    .userId(UUID.randomUUID().toString())
                                    .userAuth(UserAuth.USER)
                                    .build()
                    )
            ).blockLast(); // 끝인걸 알려줘야함
        };
    }

}

