package com.bm.concurrency.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisManager {
    @Value("${REDIS_HOST}")
    private String redisHost;
    @Value("${REDIS_PORT}")
    private String redisPort;
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, Integer.parseInt(redisPort));
        return new LettuceConnectionFactory(config);
    }
}
