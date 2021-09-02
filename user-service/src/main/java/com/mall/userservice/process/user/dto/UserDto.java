package com.mall.userservice.process.user.dto;


import com.mall.userservice.process.user.vo.ResponseOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
public class UserDto {

    private String email;
    private String pwd;
    private String name;
    private String userId;
    private LocalDateTime createdAt;

    private String encryptedPwd;


    private List<ResponseOrder> orders;

}
