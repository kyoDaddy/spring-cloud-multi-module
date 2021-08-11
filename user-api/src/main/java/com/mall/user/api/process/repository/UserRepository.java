package com.mall.user.api.process.repository;

import com.mall.user.api.process.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    //@Modifying
    //@Query("SELECT * FROM users u WHERE u.email = :#{[0]}")
    @Query("SELECT * FROM users u WHERE u.email = :email")
    Mono<UserEntity> findByEmail(String email);

}
