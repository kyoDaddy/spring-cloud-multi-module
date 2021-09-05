package com.mall.userservice.process.user.controller;

import com.mall.userservice.process.user.service.UserService;
import com.mall.userservice.process.user.vo.RequestUser;
import com.mall.userservice.process.user.vo.ResponseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"회원 관련"})
public class UserController {

    private final Environment env;

    private final UserService userService;


    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", with token secret=" + env.getProperty("jwt.secret-key")
                + ", with token time=" + env.getProperty("jwt.expires-minutes");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("server.application.name");
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 목록", notes = "<big>회원 전체 목록</big>을 반환한다.")
    public Flux<ResponseUser> getUser() {
        return userService.getUserByAll();
    }


    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "회원 정보", notes = "회원 한 명에 대한 정보이다.")
    public Mono<ResponseUser> getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }


    @PutMapping("/users/{userId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 한 명에 대한 수정이다.")
    public Mono<ResponseUser> modifyUser(@RequestBody final RequestUser user, @PathVariable("userId") String userId) {
        user.setUserId(userId);
        return userService.modifyUser(user);
    }


    @DeleteMapping("/users/{userId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @ApiOperation(value = "회원 정보 삭제", notes = "회원 한 명에 대한 삭제이다.")
    public Mono<Void> deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUserByUserId(userId);
    }



}
