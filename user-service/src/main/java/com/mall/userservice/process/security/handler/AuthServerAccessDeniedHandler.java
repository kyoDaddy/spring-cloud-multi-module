package com.mall.userservice.process.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@Slf4j
public class AuthServerAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        if (isAuthenticated(exchange)) {
            return Mono.error(new AccessDeniedException("Access Denied"));
        }
        return Mono.error(new AuthenticationException("Please login"));
    }

    private boolean isAuthenticated(final ServerWebExchange exchange) {
        return exchange.getAttribute("SPRING_SECURITY_CONTEXT") != null;
    }

}
