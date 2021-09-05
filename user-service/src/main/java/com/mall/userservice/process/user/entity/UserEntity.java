package com.mall.userservice.process.user.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "user")
public class UserEntity {

    // JPA만 사용할때는 javax.persistence.Id 만 있으면 되지만, reactive에서 Id를 사용하는 annotation은 org.springframework.data.annotation.Id 이라서 같이 선언해야 id를 사용하는게 정상 동작된다.
    @javax.persistence.Id
    @Id @GeneratedValue
    private Long id;

    private String email;

    @Column("full_name")
    private String fullName;

    private String password;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("user_id")
    private String userId;

    @Column("user_auth")
    @Enumerated(EnumType.STRING)
    private UserAuth userAuth;


}
