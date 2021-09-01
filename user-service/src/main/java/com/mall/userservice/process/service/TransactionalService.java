package com.mall.userservice.process.service;

import com.grpc.lib.DeleteUserRequest;
import com.grpc.lib.UpdateUserRequest;
import com.mall.userservice.process.entity.UserEntity;
import com.mall.userservice.process.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransactionalService {

    private final UserRepository userRepository;

    @Transactional
    public Mono<UserEntity> save(UserEntity user) {
        return userRepository.save(user).map(it -> {
            if (it.getEmail().equals("rlarbghrbgh@gmail.com")) {
                throw new IllegalStateException();
            } else {
                return it;
            }
        });
    }

    @Transactional
    public Mono<UserEntity> update(UpdateUserRequest request) {
        return userRepository.findById(request.getId())
                .log()
                .flatMap(dbEntity -> {
                    dbEntity.setFullName(request.getFullName());
                    return userRepository.save(dbEntity);
                });
    }

    @Transactional
    public Mono<UserEntity> deleteById(DeleteUserRequest request) {
        return userRepository.findById(request.getId())
                .log()
                .map(user -> {
                    userRepository.deleteById(request.getId());
                    return user;
                });
    }
}
