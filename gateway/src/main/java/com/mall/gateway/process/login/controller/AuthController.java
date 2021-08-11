package com.mall.gateway.process.login.controller;

import com.mall.gateway.process.login.dto.CreateUser;
import com.mall.gateway.process.login.dto.LoginRequest;
import com.mall.gateway.process.login.dto.LoginResponse;
import com.mall.gateway.process.login.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = {"Authentication"})
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody final LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/authenticate")
    public Mono<LoginResponse> authenticate(@RequestBody final LoginRequest request) {
        return loginService.authenticate(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "사용자 정보 생성")
    public Mono<LoginResponse> register(@RequestBody final CreateUser request) {
        return loginService.register(request);
    }

}
