package com.mall.userservice.process.user.repository;

import com.mall.userservice.process.user.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM user u WHERE u.email = :email")
    Mono<UserEntity> findByEmail(String email);

}
