package com.mall.user.api.process.entity;

import lombok.*;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class UserEntity {

    @Id
    private Long id;

    private String email;

    @Column("full_name")
    private String fullName;

    private String password;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("type_code")
    private int typeCode;


}
