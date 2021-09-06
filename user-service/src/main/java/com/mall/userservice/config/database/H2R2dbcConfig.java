package com.mall.userservice.config.database;

import com.mall.userservice.process.user.entity.UserAuth;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Configuration
@Profile("local")
public class H2R2dbcConfig extends AbstractR2dbcConfiguration {

    @Override
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb") // 데이터베이스 이름
                .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1") // DB연결이 닫혀도 유지되도록 설정
                .username("sa")
                .build());
    }

    @Bean
    public ConnectionFactoryInitializer h2DbInitializer() {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("schema-users-h2.sql"));

        initializer.setConnectionFactory(connectionFactory());
        initializer.setDatabasePopulator(resourceDatabasePopulator);
        return initializer;
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


    /*
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
    */
}

