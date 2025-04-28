package com.arpan.login.OTPLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    public void redisTest(){
        redisTemplate.opsForValue().set("phoneNumber","123456");
        String phoneNumber=redisTemplate.opsForValue().get("salary");
        System.out.println(phoneNumber);
    }
}
