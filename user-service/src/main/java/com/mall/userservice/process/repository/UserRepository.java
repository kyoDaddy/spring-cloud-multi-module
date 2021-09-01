package com.mall.userservice.process.repository;

import com.mall.userservice.process.entity.UserEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    //@Modifying
    //@Query("SELECT * FROM users u WHERE u.email = :#{[0]}")
    @Query("SELECT * FROM users u WHERE u.email = :email")
    Mono<UserEntity> findByEmail(String email);

}
