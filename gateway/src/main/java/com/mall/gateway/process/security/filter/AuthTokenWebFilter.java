package com.mall.gateway.process.security.filter;

import com.mall.gateway.config.exception.UnauthorizedException;
import com.mall.gateway.process.security.service.AuthTokenService;
import com.mall.gateway.process.security.service.DefaultUserDetailsJwtClaimsConverter;
import com.mall.gateway.process.security.service.impl.DefaultUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthTokenWebFilter implements WebFilter {

    private final AuthTokenService authTokenService;

    private final DefaultUserDetailsJwtClaimsConverter defaultUserDetailJwtClaimsConverter;

    private final ServerSecurityContextRepository securityContextRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String authorization = request.getHeaders().getFirst("Authorization");
        // 조건에 맞는 요청에 대해 token 기본 유효성 확인
        final String token = parseToken(authorization);
        // token 검증 후 저장
        if(StringUtils.hasText(token)) {
            return authTokenService.verify(token)
                    .flatMap(claims -> securityContextRepository.save(exchange, toSecurityContext(claims)))
                    .then(chain.filter(exchange));
        }
        return chain.filter(exchange);
    }

    private SecurityContext toSecurityContext(Map<String, Object> claims) {
        // 인증된 사용자의 부가 정보
        final DefaultUserDetails userDetails = defaultUserDetailJwtClaimsConverter.convert(claims);
        final SecurityContext securityContext = new SecurityContextImpl();
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authentication.setDetails(userDetails);
        securityContext.setAuthentication(authentication);
        return securityContext;
    }

    private String parseToken(final String authorization) {
        if (!StringUtils.hasText(authorization)) {
            return null;
        }
        final String[] arr = authorization.replaceAll("\\s+", " ").trim().split("\\s");
        if (!(arr != null && arr.length == 2)) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        if (!"bearer".equalsIgnoreCase(arr[0])) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "Invalid bearer token");
        }
        return arr[1];
    }
}
