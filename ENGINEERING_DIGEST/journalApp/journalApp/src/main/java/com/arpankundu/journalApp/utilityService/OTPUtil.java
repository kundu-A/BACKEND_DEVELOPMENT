package com.arpankundu.journalApp.utilityService;

import java.security.MessageDigest;
import java.util.Base64;

public class OTPUtil {
    public static String hashOTP(String otp) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(otp.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        }catch (Exception e) {
            throw new RuntimeException("Error while hashing OTP: "+e);
        }
    }
}
