package com.mall.gateway.process.security.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mall.gateway.process.security.service.AuthTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * JWT 토큰 생성 및 유효성 검증
 */
@Slf4j
@RequiredArgsConstructor    // final이나 @NonNull 인 필드 값만 파라미터로 받는 생성자를 만들어 준다.
public class AuthTokenServiceImpl implements AuthTokenService {

    private final Algorithm algorithm;

    private final int expireMinutes;

    @Override
    public Mono<String> sign(Map<String, Object> claims) {
        return Mono.fromCallable(() -> {
            final Instant issuedAt = Instant.now();
            final Instant expireAt = issuedAt.plus(Duration.ofMinutes(expireMinutes));
            return JWT.create()
                    .withIssuedAt(Date.from(issuedAt))
                    .withExpiresAt(Date.from(expireAt))
                    .withClaim("data", claims)
                    .sign(algorithm);
        });
    }

    @Override
    public Mono<Map<String, Object>> verify(String token) {

        if (!StringUtils.hasText(token)) {
            return Mono.error(new AuthenticationException("Required token"));
        }

        return Mono.fromCallable(() -> {
            try {
                return JWT.require(algorithm)
                        .build()
                        .verify(token)
                        .getClaims()
                        .get("data")
                        .asMap();
            } catch (JWTVerificationException e) {
                log.warn("VerifyToken error => ", e);
                throw new AuthenticationException("Invalid token");
            }
        });
    }
}
