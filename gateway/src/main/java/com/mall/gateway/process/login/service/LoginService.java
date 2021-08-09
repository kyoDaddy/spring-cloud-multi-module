package com.mall.gateway.process.login.service;

import com.mall.gateway.process.login.model.LoginRequest;
import com.mall.gateway.process.login.model.LoginResponse;
import reactor.core.publisher.Mono;

public interface LoginService {

    Mono<LoginResponse> login(final LoginRequest request);

}
