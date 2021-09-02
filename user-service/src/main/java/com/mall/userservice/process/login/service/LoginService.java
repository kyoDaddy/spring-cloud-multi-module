package com.mall.userservice.process.login.service;

import com.grpc.lib.GetUserByEmailRequest;
import com.grpc.lib.UserResponse;
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
import com.mall.userservice.process.user.service.UserService;
import com.mall.userservice.process.user.vo.RequestUser;
import com.mall.userservice.process.user.vo.ResponseUser;
import io.grpc.StatusRuntimeException;
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

    public Mono<ResponseLogin> login(RequestLogin login) {

        return userRepository.findByEmail(login.getEmail())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "미등록 회원입니다.(" + login.getEmail() + ")"))))
                .filter((user) -> !passwordEncoder.matches(login.getPassword(), user.getPassword()))
                .flatMap((user) -> getLoginResponseMono(login.getEmail()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."))));

    }

    public Mono<ResponseLogin> register(RequestUser user) {

        return userRepository.findByEmail(user.getEmail())
                .log()
                .switchIfEmpty(Mono.defer(() -> {
                    UserEntity userEntity = ObjectMapperUtils.map(user, UserEntity.class);
                    userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    userEntity.setUserAuth(UserAuth.USER);
                    userEntity.setCreatedAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                }))
                .flatMap((u) -> getLoginResponseMono(user.getEmail()));
        
        /*
        UserEntity userEntity = userRepository.findByEmail(user.getEmail()).block();
        if (userEntity != null) {
            throw new UnauthorizedException(HttpStatus.UNAUTHORIZED, "이미 등록된 회원입니다.(" + user.getEmail() + ")");
        }
        userEntity = ObjectMapperUtils.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setUserAuth(UserAuth.USER);
        userEntity.setCreatedAt(LocalDateTime.now());

        final UserEntity finalUserEntity = userEntity;
        return userRepository.findByEmail(user.getEmail())
                .switchIfEmpty(Mono.defer(() -> transactionalService.save(finalUserEntity)))
                .map((u) -> ObjectMapperUtils.map(u, ResponseUser.class));

         */

    }



    @NotNull
    private Mono<ResponseLogin> getLoginResponseMono(String email) {
        final DefaultUserDetails userDetails = DefaultUserDetails.builder()
                //.id( UUID.fromString(userResponse.getEmail()) )   //uuid 를 키값으로 사용할때 사용
                .id(email)
                .authorities(List.of(UserAuth.USER.toString()))
                .build();

        final Map<String, Object> claims = defaultUserDetailsJwtClaimsConverter.convert(userDetails);

        return authTokenService.sign(claims).log()
                .map(token -> ResponseLogin.builder()
                        .expiresIn(jwtProp.getExpiresMinutes() * 60L)
                        .tokenType("bearer")
                        .accessToken(token)
                        .build());
    }


}
