package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
public class OTPService {

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    public final String field_otp="otp";
    public final String field_verification="isVerified";
    public final String field_status="status";

    public String generateOTP(OTP otp,String status){
        Random random=new Random();
        String generatedOtp=String.valueOf(random.nextInt(100000,999999));
        redisTemplate.opsForHash().put(otp.getPhoneNumber(),field_otp,generatedOtp);
        redisTemplate.opsForHash().put(otp.getPhoneNumber(),field_status,status);
        redisTemplate.expire(otp.getPhoneNumber(), Duration.ofMinutes(1));
        return generatedOtp;
    }

    public boolean verifyOTP(OTP otp,String status){
        String phoneNumber=otp.getPhoneNumber();
        String retrieveOtp=otp.getOtp();

        if(!isOtpPresent(phoneNumber)){
            System.out.println("OTP is not generated for this Phone Number");
            return false;
        }

        if(!checkingWithStatus(otp.getPhoneNumber(),status)) {
            System.out.println("Please generate OTP for Registration");
            return false;
        }

        if(retrieveOtp.equals(redisTemplate.opsForHash().get(phoneNumber,field_otp))){
            System.out.println("OTP verification successful");
            redisTemplate.opsForHash().put(otp.getPhoneNumber(),field_verification,"true");
            deleteOtp(phoneNumber);
            deleteStatus(phoneNumber);
            return true;
        }
        return false;
    }

    public boolean isValid(String phoneNumber , String otp){
        return otp.equals(redisTemplate.opsForHash().get(phoneNumber,field_otp));
    }

    public boolean isPhoneNumberVerified(String phoneNumber){
        Object isVerified=redisTemplate.opsForHash().get(phoneNumber,field_verification);
        return "true".equalsIgnoreCase(String.valueOf(isVerified));
    }

    public boolean checkingWithStatus(String phoneNumber,String status){
        Object getStatus=redisTemplate.opsForHash().get(phoneNumber,field_status);
        return status.equalsIgnoreCase(String.valueOf(getStatus));
    }

    public void deleteVerificationStatus(String phoneNumber){
        redisTemplate.opsForHash().delete(phoneNumber,field_verification);
    }

    public void deleteOtp(String phoneNumber){
        redisTemplate.opsForHash().delete(phoneNumber,field_otp);
    }

    public void deleteStatus(String phoneNumber){
        redisTemplate.opsForHash().delete(phoneNumber,field_status);
    }

    public boolean isOtpPresent(String phoneNumber){
        Object isPresent=redisTemplate.opsForHash().get(phoneNumber,field_otp);
        if(isPresent==null)
            return false;
        return true;
    }

}
