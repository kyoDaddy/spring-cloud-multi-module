package com.mall.userservice.process.user.controller;

import com.mall.userservice.process.user.service.UserService;
import com.mall.userservice.process.user.vo.RequestUser;
import com.mall.userservice.process.user.vo.ResponseUser;
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
public class UserController {

    private final Environment env;

    private final UserService userService;


    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", with token secret=" + env.getProperty("token.secret")
                + ", with token time=" + env.getProperty("token.expiration_time");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("server.application.name");
    }

    /*@PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    //@ApiOperation(value = "사용자 정보 생성")
    public Mono<ResponseUser> createUser(@RequestBody final RequestUser user) {
        return userService.createUser(user);
    }*/

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ResponseUser> getUser() {
        return userService.getUserByAll();
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseUser> getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/users/{userId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public Mono<ResponseUser> modifyUser(@RequestBody final RequestUser user, @PathVariable("userId") Long userId) {
        user.setUserId(userId);
        return userService.modifyUser(user);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public Mono<Void> deleteUser(@PathVariable("userId") Long userId) {
        return userService.deleteUserByUserId(userId);
    }



}
