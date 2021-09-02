package com.mall.userservice.config.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.mall.userservice.config.security.prop.JwtProp;
import com.mall.userservice.process.security.filter.AuthTokenWebFilter;
import com.mall.userservice.process.security.handler.AuthServerAccessDeniedHandler;
import com.mall.userservice.process.security.repository.AuthServerSecurityContextRepository;
import com.mall.userservice.process.security.service.AuthTokenService;
import com.mall.userservice.process.security.service.impl.AuthTokenServiceImpl;
import com.mall.userservice.process.security.service.impl.DefaultUserDetailsJwtClaimsConverterImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Slf4j
@EnableWebFluxSecurity // @EnableWebFluxSecurity는 Spring Security를 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProp jwtProp;

    private final List<String> WHITE_LIST_IP = List.of("192.168.1.21", "127.0.0.1", "0:0:0:0:0:0:0:1");

    private final String[] PUBLIC_ACCESS_PATHS = new String[]{
        "/webjars/springfox-swagger-ui/**",
        "/swagger-ui/**",
        "/v3/api-docs",
        "/v3/api-docs/_",
        "/swagger-resources",
        "/swagger-resources/**",
        "/favicon.ico",
        "/assets/**",
        "/public/**",
        "/actuator/**",
        "/login",
        "/register",
        "/"
    };

    @Bean
    public AuthTokenService authTokenService() {
        final Algorithm algorithm = Algorithm.HMAC256(jwtProp.getSecretKey());
        return new AuthTokenServiceImpl(algorithm, jwtProp.getExpiresMinutes());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 인증
     * @param passwordEncoder
     * @return
     */
    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService(final PasswordEncoder passwordEncoder) {
        return username -> {
            log.info("login with username => {}", username);
            return Mono.just(
                    User.withUsername(username)
                            .password(passwordEncoder.encode("password"))
                            .authorities(Collections.emptyList())
                            .build()
            );
        };
    }

    /**
     * 권한
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        return http
                .securityContextRepository(new AuthServerSecurityContextRepository())           // 서버부담 줄이기, cache 서버를 사용을 위함
                .exceptionHandling().accessDeniedHandler(new AuthServerAccessDeniedHandler())
                .and()
                .logout().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(PUBLIC_ACCESS_PATHS).permitAll()
                .anyExchange()
                .access(this::getWhiteListIp)
                //.authenticated()
                .and()
                .addFilterAt(
                        new AuthTokenWebFilter(
                                authTokenService(),
                                new DefaultUserDetailsJwtClaimsConverterImpl(),
                                new AuthServerSecurityContextRepository()
                        ),
                        SecurityWebFiltersOrder.AUTHENTICATION
                )
                .build();

    }

    private Mono<AuthorizationDecision> getWhiteListIp(Mono<Authentication> authentication, AuthorizationContext context) {
        String ip = context.getExchange().getRequest().getRemoteAddress().getAddress().toString().replace("/", "");
        return authentication.map((a) -> new AuthorizationDecision(a.isAuthenticated()))
                .defaultIfEmpty(new AuthorizationDecision(
                        (WHITE_LIST_IP.contains(ip)) ? true : false
                ));
    }

}
