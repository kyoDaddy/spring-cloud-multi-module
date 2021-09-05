package com.mall.userservice.process.login.service;

import com.mall.common.utils.ObjectMapperUtils;
import com.mall.userservice.config.base.prop.DaemonProp;
import com.mall.userservice.config.exception.UnauthorizedException;
import com.mall.userservice.config.security.prop.JwtProp;
import com.mall.userservice.process.login.vo.RequestLogin;
import com.mall.userservice.process.login.vo.ResponseLogin;
import com.mall.userservice.process.security.service.AuthTokenService;
import com.mall.userservice.process.security.service.DefaultUserDetailsJwtClaimsConverter;
import com.mall.userservice.process.security.service.impl.DefaultUserDetails;
import com.mall.userservice.process.user.entity.UserAuth;
import com.mall.userservice.process.user.entity.UserEntity;
import com.mall.userservice.process.user.repository.UserRepository;
import com.mall.userservice.process.user.vo.RequestUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final JwtProp jwtProp;

    private final AuthTokenService authTokenService;

    private final DefaultUserDetailsJwtClaimsConverter defaultUserDetailsJwtClaimsConverter;

    private final PasswordEncoder passwordEncoder;

    private final DaemonProp daemonProp;

    private final UserRepository userRepository;

    public Mono<ResponseLogin> login(RequestLogin requestLogin) {

        return userRepository.findByEmail(requestLogin.getEmail())
                .log()
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "미등록 회원입니다.(" + requestLogin.getEmail() + ")"))))
                .filter((user) -> passwordEncoder.matches(requestLogin.getPassword(), user.getPassword()))
                //.flatMap((user) -> getLoginResponseMono(requestLogin.getEmail()))
                .flatMap((user) -> getLoginResponseMono(user.getUserId()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."))));

    }

    public Mono<ResponseLogin> register(RequestUser requestUser) {

        return userRepository.findByEmail(requestUser.getEmail())
                .log()
                .filter(Objects::nonNull)
                .map((user) -> {
                    throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "이미 등록된 회원입니다.(" + requestUser.getEmail() + ")");
                })
                .switchIfEmpty(Mono.defer(() -> {
                    UserEntity userEntity = ObjectMapperUtils.map(requestUser, UserEntity.class);
                    userEntity.setUserId(UUID.randomUUID().toString());
                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    userEntity.setUserAuth(UserAuth.USER);
                    userEntity.setCreatedAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                }))
                //.flatMap((u) -> getLoginResponseMono(requestUser.getEmail()));
                .flatMap(o -> getLoginResponseMono(((UserEntity) o).getUserId()));

    }

    @NotNull
    private Mono<ResponseLogin> getLoginResponseMono(String userId) {
        final DefaultUserDetails userDetails = DefaultUserDetails.builder()
                .id(userId)
                .authorities(List.of(UserAuth.USER.toString()))
                .build();

        final Map<String, Object> claims = defaultUserDetailsJwtClaimsConverter.convert(userDetails);

        return authTokenService.sign(claims).log()
                .map(token -> ResponseLogin.builder()
                        .userId(userId)
                        .expiresIn(jwtProp.getExpiresMinutes() * 60L)
                        .tokenType("bearer")
                        .accessToken(token)
                        .build());
    }

}
