package com.mall.gateway.process.login.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Required")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Required")
    @Length(min = 8, max = 50, message = "Must between {min} to {max} characters")
    private String password;



}
