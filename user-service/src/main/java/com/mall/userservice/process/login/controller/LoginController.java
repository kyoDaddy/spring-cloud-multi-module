package com.mall.userservice.process.login.controller;

import com.mall.userservice.process.login.service.LoginService;
import com.mall.userservice.process.login.vo.RequestLogin;
import com.mall.userservice.process.login.vo.ResponseLogin;
import com.mall.userservice.process.user.vo.RequestUser;
import com.mall.userservice.process.user.vo.ResponseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@Api(tags = {"인증 관련"})
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "사용자 로그인(토큰생성)")
    public Mono<ResponseLogin> login(@RequestBody final RequestLogin login) {
        return loginService.login(login);
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "사용자 정보 생성(토큰생성)")
    public Mono<ResponseLogin> register(@RequestBody final RequestUser user) {
        return loginService.register(user);
    }


}
