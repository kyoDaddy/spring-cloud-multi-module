package com.mall.userservice.process.user.service;

import com.grpc.lib.DeleteUserRequest;
import com.grpc.lib.UpdateUserRequest;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
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
        return userRepository.save(user)
                .log()
                .switchIfEmpty(Mono.defer(() -> Mono.empty()));
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
