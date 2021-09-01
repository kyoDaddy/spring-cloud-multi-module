package com.mall.userservice.repository;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("local")
@SpringBootTest
public class UserRepositoryTests {

    /*
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

    }*/

}
