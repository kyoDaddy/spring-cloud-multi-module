package com.mall.user.api.repository;


import com.mall.user.api.process.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Profile("daemon-local")
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;


    @Test
    public void testUserRepositoryExisted() {
        assertNotNull(userRepository);
    }

    @Test
    void findAllTest() {

        this.userRepository.findAll().log()
                .take(1)
                .as(StepVerifier::create)
                .consumeNextWith(p -> assertEquals("rlarbghrbgh@gmail.com", p.getEmail()))
                .verifyComplete();

    }

}
