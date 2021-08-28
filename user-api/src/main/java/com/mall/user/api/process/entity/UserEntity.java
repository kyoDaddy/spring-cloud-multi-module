package com.mall.user.api.process.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    private String email;

    @Column(name = "full_name")
    private String fullName;

    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "type_code")
    private int typeCode;


}
