package com.mall.gatewayservice.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    Environment env;

    Algorithm algorithm;

    public AuthorizationHeaderFilter(Environment env) {
        super(Config.class);
        this.env = env;
        this.algorithm = Algorithm.HMAC256(env.getProperty("jwt.secret-key"));
    }

    public static class Config {
        // Put configuration properties here
    }

    // login -> token -> users (with token) -> header(include token)
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            final ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            // 조건에 맞는 요청에 대해 token 기본 유효성 확인
            final String token = parseToken(authorization);
            if (Objects.isNull(token)) {
                return onError(exchange, "input invalid token", HttpStatus.UNAUTHORIZED);
            }
            // 유효하지 않으면 true
            if (isJwtValid(token)) {
                return onError(exchange, "check invalid token", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    // Mono, Flux -> Spring WebFlux
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error(err);
        return response.setComplete();
    }


    private String parseToken(final String authorization) {
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        final String[] arr = authorization.replaceAll("\\s+", " ").trim().split("\\s");
        if (!(arr != null && arr.length == 2)) {
            return null;
        }
        if (!"bearer".equalsIgnoreCase(arr[0])) {
            return null;
        }
        return arr[1];
    }

    /**
     * 유효하지 않으면 true
     */
    private boolean isJwtValid(String jwt) {
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(jwt)
                    .getClaims()
                    .isEmpty();

        } catch (JWTVerificationException e) {
            //log.warn("VerifyToken error => ", e);
            return true;
        }
    }


}
