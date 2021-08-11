package com.mall.gateway.process.login.dto;

import com.mall.gateway.process.login.validator.AtLeastPassword;
import com.mall.gateway.process.login.validator.PasswordEqualsConfirmPassword;
import com.mall.gateway.process.login.validator.PasswordNotEqualsEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@PasswordEqualsConfirmPassword
@PasswordNotEqualsEmail
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser implements PasswordEqualsConfirmPassword.Model, PasswordNotEqualsEmail.Model {

    @NotBlank(message = "Required")
    @Email(message = "Invalid format")
    private String email;

    @NotBlank(message = "Required")
    @Length(min = 1, max = 50, message = "At least {min} characters")
    private String fullName;

    @NotBlank(message = "Required")
    @AtLeastPassword
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String password;

    @NotBlank(message = "Required")
    @Length(min = 8, max = 50, message = "At least {min} characters")
    private String confirmPassword;



}
