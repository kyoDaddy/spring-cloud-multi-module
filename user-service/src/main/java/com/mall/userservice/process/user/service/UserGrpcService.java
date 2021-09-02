package com.mall.userservice.process.user.service;

import com.grpc.lib.*;
import com.mall.common.utils.ObjectMapperUtils;
import com.mall.userservice.process.user.entity.UserAuth;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Function;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final TransactionalService transactionalService;
    private final UserRepository userRepository;

    @Override
    public void findById(GetUserByIdRequest request, StreamObserver<UserResponse> responseObserver) {
        userRepository.findById(request.getId())
                .log()
                //.map(UserGrpcService::toUserReply)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();
    }

    @Override
    public void findByEmail(GetUserByEmailRequest request, StreamObserver<UserResponse> responseObserver) {
        userRepository.findByEmail(request.getEmail())
                .log()
                //.map(UserGrpcService::toUserReply)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();
    }

    @Override
    public void create(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        // entity mapper 사용
        UserEntity userEntity = ObjectMapperUtils.map(request, UserEntity.class);
        userEntity.setUserAuth(UserAuth.USER);
        userEntity.setCreatedAt(LocalDateTime.now());

        transactionalService.save(userEntity).map(it -> {
                    if(it == null)  throw new IllegalStateException();
                    else            return it;
                })
                .log()
                //.map(UserGrpcService::toUserReply)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();

        /* entity builder 사용
        userComponent.save(
                UserEntity.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .fullName(request.getFullName())
                    .createdAt(LocalDateTime.now())
                    .typeCode(4)
                    .build()).map(it -> {
                        if(it == null)  throw new IllegalStateException();
                        else            return it;
                    })
                    .log()
                    .map(UserGrpcService::toUserReply)
                    .transform(registerObs(responseObserver))
                    .subscribe();
        */

        /* Dataclient 사용
        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        userResponseOnNext(client.insert()
                .into(UserEntity.class)
                .using(userEntity)
                .then()
                .thenReturn(userEntity), responseObserver);
        */


    }

    @Override
    public void update(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {

        transactionalService.update(request)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();

        /*
        userRepository.findById(request.getId())
                .log()
                .flatMap(dbEntity -> {
                    dbEntity.setFullName(request.getFullName());
                    return transactionalService.save(dbEntity);
                })
                //.map(UserGrpcService::toUserReply)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();
        */

        /*
        Mono<UserEntity> info = userRepository.findById(request.getId());
        // flatMap : 중첩 구조를 한 단계 제거하기 위한 중간 연산 (ex : Mono<UserEntity> -> UserEntity)
        info.flatMap(dbEntity -> {
            dbEntity.setFullName(request.getFullName());
            return userRepository.save(dbEntity);
        });
        userResponseOnNext(info, responseObserver);
        */
    }

    @Override
    public void deleteById(DeleteUserRequest request, StreamObserver<UserResponse> responseObserver) {

        transactionalService.deleteById(request)
                .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
                .transform(registerObs(responseObserver))
                .subscribe();

        /*
        userRepository.findById(request.getId())
            .map(user -> {
                userRepository.deleteById(request.getId());
                return user;
            })
            .log()
            .map((u) -> ObjectMapperUtils.map(u, UserResponse.class))
            .transform(registerObs(responseObserver))
            .subscribe();
        */

        /*
        Mono<UserEntity> info = userRepository.findById(request.getId());
        info.flatMap(dbEntity -> userRepository.deleteById(dbEntity.getId()));
        userResponseOnNext(info, responseObserver);
        */

    }

    private static UserResponse toUserReply(UserEntity u) {
        return UserResponse.newBuilder()
                .setId(u.getId())
                .setFullName(u.getFullName())
                .setEmail(u.getEmail())
                .setPassword(u.getPassword())
                .build();
    }

    private static <T> Function<Mono<T>, Mono<T>> registerObs(StreamObserver<T> responseObserver) {
        return userReplyMono -> userReplyMono
                .doOnSuccess(responseObserver::onNext)      //unary라 onNext 1회만 호출 가능 //데이터 전송
                .doOnError(responseObserver::onError)
                .doAfterTerminate(responseObserver::onCompleted);
    }



}