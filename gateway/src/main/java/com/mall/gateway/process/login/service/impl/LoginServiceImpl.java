package com.mall.gateway.process.login.service.impl;

import com.grpc.lib.GetUserByEmailRequest;
import com.grpc.lib.UserResponse;
import com.grpc.lib.UserServiceGrpc;
import com.mall.gateway.config.exception.UnauthorizedException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.mall.gateway.config.base.prop.DaemonProp;
import com.mall.gateway.config.security.prop.JwtProp;
import com.mall.gateway.process.login.model.LoginRequest;
import com.mall.gateway.process.login.model.LoginResponse;
import com.mall.gateway.process.login.service.LoginService;
import com.mall.gateway.process.security.service.AuthTokenService;
import com.mall.gateway.process.security.service.DefaultUserDetailsJwtClaimsConverter;
import com.mall.gateway.process.security.service.impl.DefaultUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtProp jwtProp;

    private final AuthTokenService authTokenService;

    private final DefaultUserDetailsJwtClaimsConverter defaultUserDetailsJwtClaimsConverter;

    private final PasswordEncoder passwordEncoder;

    private final DaemonProp daemonProp;

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {

        UserResponse userResponse = null;
        try {
            ManagedChannel channel = ManagedChannelBuilder.forAddress(daemonProp.getGrpcIp(), daemonProp.getGrpcPort()).usePlaintext().build();
            UserServiceGrpc.UserServiceBlockingStub stub = UserServiceGrpc.newBlockingStub(channel);
            userResponse = stub.findByEmail(GetUserByEmailRequest.newBuilder()
                    .setEmail(request.getEmail())
                    .build()
            );
            channel.shutdown();
        }
        catch (Exception e) {
            log.info("error => {}", e);
        }

        if(userResponse == null) {
            return Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "등록된 회원이 없습니다."));
        }
        else if(!passwordEncoder.matches(request.getPassword(), userResponse.getPassword())) {
            return Mono.error(new UnauthorizedException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."));
        }

        final DefaultUserDetails userDetails = DefaultUserDetails.builder()
                .id(UUID.fromString(userResponse.getId()))
                .authorities(Arrays.asList("ADMIN"))
                .build();

        final Map<String, Object> claims = defaultUserDetailsJwtClaimsConverter.convert(userDetails);

        return authTokenService.sign(claims)
                .map(token -> {
                    return LoginResponse.builder()
                            .expiresIn(jwtProp.getExpiresMinutes() * 60L)
                            .tokenType("bearer")
                            .accessToken(token)
                            .build();
                });

    }
}
