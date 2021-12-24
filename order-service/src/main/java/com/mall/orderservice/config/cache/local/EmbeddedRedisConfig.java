package com.mall.orderservice.config.cache.local;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Optional;

/**
 * redis inmemory db
 */
@Slf4j
@Profile("local")
@Configuration
@RequiredArgsConstructor
public class EmbeddedRedisConfig implements InitializingBean, DisposableBean {

    private final RedisProperties redisProperties;

    private RedisServer redisServer;

    @Override
    public void destroy() throws Exception {
        Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }


}
