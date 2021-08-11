package com.mall.gateway.process.login.service;

import com.mall.gateway.process.login.dto.CreateUser;
import com.mall.gateway.process.login.dto.LoginRequest;
import com.mall.gateway.process.login.dto.LoginResponse;
import reactor.core.publisher.Mono;

public interface LoginService {

    Mono<LoginResponse> login(final LoginRequest request);

    Mono<LoginResponse> authenticate(final LoginRequest user);

    Mono<LoginResponse> register(final CreateUser user);

}
