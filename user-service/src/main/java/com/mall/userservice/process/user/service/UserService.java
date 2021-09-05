package com.mall.userservice.process.user.service;

import com.mall.common.utils.ObjectMapperUtils;
import com.mall.userservice.config.exception.UnauthorizedException;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
import com.mall.userservice.process.user.vo.RequestUser;
import com.mall.userservice.process.user.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final TransactionalService transactionalService;
    private final UserRepository userRepository;


    public Flux<ResponseUser> getUserByAll() {
        return userRepository.findAll()
                .map((user) -> ObjectMapperUtils.map(user, ResponseUser.class))
                .switchIfEmpty(Flux.defer(() -> Flux.empty()));
    }

    public Mono<ResponseUser> getUser(String userId) {
        return userRepository.findByUserId(userId)
                .map((user) -> ObjectMapperUtils.map(user, ResponseUser.class))
                .switchIfEmpty(Mono.defer(() -> Mono.empty()));
    }

    public Mono<Void> deleteUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "미등록 회원입니다.(" + userId + ")"))))
                .flatMap((user) -> userRepository.deleteById(user.getId()));
    }

    public Mono<ResponseUser> modifyUser(RequestUser user) {

        UserEntity userEntity = userRepository.findByEmail(user.getEmail()).block();
        if (userEntity == null) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "미등록 회원입니다.(" + user.getEmail() + ")");
        }
        userEntity = ObjectMapperUtils.map(user, UserEntity.class);

        final UserEntity finalUserEntity = userEntity;
        return userRepository.findByUserId(user.getUserId())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "미등록 회원입니다.(" + user.getUserId() + ")"))))
                .map((u) -> ObjectMapperUtils.map(userRepository.save(finalUserEntity), ResponseUser.class));

    }
}
