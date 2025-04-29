package com.arpan.login.OTPLogin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedisTemplate<String ,String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String , String > redisTemplate=new RedisTemplate<>();

        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
        configuration.setHostName(hostName);
        configuration.setPort(Integer.parseInt(port));
        configuration.setPassword(password);
        return new LettuceConnectionFactory(configuration);
    }
}
