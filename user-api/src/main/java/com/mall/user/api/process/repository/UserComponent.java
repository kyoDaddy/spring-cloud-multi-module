package com.mall.user.api.process.repository;

import com.mall.user.api.process.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class UserComponent {

    private final UserRepository userRepository;

    @Autowired
    public UserComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserEntity> save(UserEntity user) {
        return userRepository.save(user);
    }

    public Flux<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Mono<Void> deleteById(long id) {
        return userRepository.deleteById(id);
    }



}
