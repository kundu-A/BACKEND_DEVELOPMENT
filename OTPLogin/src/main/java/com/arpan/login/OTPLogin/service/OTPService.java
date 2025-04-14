package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.OTP;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OTPService {

    Map<String , String> otpMap=new HashMap<>();
    Set<String> verifiedPhoneNumber=new HashSet<>();
    Map<String , String> otpStatus=new HashMap<>();

    public String generateOTP(OTP otp,String status){
        Random random=new Random();
        String generatedOtp=String.valueOf(random.nextInt(100000,999999));
        otpMap.put(otp.getPhoneNumber(),generatedOtp);
        otpStatus.put(otp.getPhoneNumber(),status);
        System.out.println(otpMap.get(otp.getPhoneNumber()));
        return generatedOtp;
    }

    public boolean verifyOTP(OTP otp,String status){
        if(!status.equals(otpStatus.get(otp.getPhoneNumber()))) {
            System.out.println("Please generate OTP for Registration");
            return false;
        }
        String phoneNumber=otp.getPhoneNumber();
        String retrieveOtp=otp.getOtp();
        if(!otpMap.containsKey(phoneNumber)){
            System.out.println("OTP is not generated for this Phone Number");
            return false;
        }
        if(retrieveOtp.equals(otpMap.get(phoneNumber))){
            System.out.println("OTP verification successful");
            otpMap.remove(phoneNumber);
            verifiedPhoneNumber.add(phoneNumber);
            return true;
        }
        return false;
    }

    public boolean isValid(String phoneNumber , String otp){
        return otp.equals(otpMap.get(phoneNumber));
    }
}
