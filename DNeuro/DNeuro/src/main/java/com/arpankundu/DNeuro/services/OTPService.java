package com.arpankundu.DNeuro.services;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.components.OTP;

@Service
public class OTPService {

	@Autowired
	JavaMailSender javaMailSender;
	
	private Map<String,String> otpMap=new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiryMap = new ConcurrentHashMap<>();
	Set<String> verifiedEmails = Collections.newSetFromMap(new ConcurrentHashMap<>());
	private Map<String, Integer> otpAttempts = new ConcurrentHashMap<>();
	
	public String generateOTP(String toEmail) {
		try {
		 	Random random = new Random();
	        String otp = String.valueOf(random.nextInt(99999)+100000);
	        otpMap.put(toEmail,otp);
	        otpExpiryMap.put(toEmail, System.currentTimeMillis() + (5 * 60 * 1000)); //1 min valid.
	        otpAttempts.put(toEmail, 0);
	        return otp;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Failed to generate OTP. Please try again later.");
		}
	}
	
	public boolean verifyOTP(OTP request) {
		try {
		    String email = request.getEmail();
		    if (!otpMap.containsKey(email))
		    	throw new RuntimeException("OTP not generated for this email.");
		    
		    if (System.currentTimeMillis() > otpExpiryMap.get(email)) {
		        otpMap.remove(email);
		        otpExpiryMap.remove(email);
		        throw new RuntimeException("OTP has expired");
		    }
		    
	        int attempts = otpAttempts.getOrDefault(email, 0);
	        if (attempts >= 3) {
	            otpMap.remove(email);
	            otpExpiryMap.remove(email);
	            otpAttempts.remove(email);
	            throw new RuntimeException("Too many failed attempts. Please request a new OTP.");
	        }
	        
		    if(request.getOtp().equals(otpMap.get(email))) {
		    	verifiedEmails.add(email);
		    	otpExpiryMap.remove(email);
		    	otpMap.remove(email);
		    	otpAttempts.remove(email);
		    	return true;
		    }
		    otpAttempts.put(email, attempts + 1);
		    throw new RuntimeException("Invalid OTP entered , Attempts left: "+(3 - (attempts + 1)));
		}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
		}
	}
	
	@Scheduled(fixedRate = 30*60000)
	public void removeExpiredOTPs() {
	    System.out.println("Running OTP cleanup at: " + new java.util.Date());
	    long currentTime = System.currentTimeMillis();
	    otpExpiryMap.entrySet().removeIf(entry -> { 
	        boolean isExpired = currentTime > entry.getValue();
	        if (isExpired) { 
	            String email = entry.getKey();
	            otpMap.remove(email);  
	            verifiedEmails.remove(email);
	            otpAttempts.remove(email);
	            System.out.println("Removed expired OTP for: " + email);
	        }
	        return isExpired; 
	    });
	}
}
