package com.arpankundu.journalApp.services;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.InvalidOTPException;
import com.arpankundu.journalApp.exceptionHandler.OTPExpiredException;
import com.arpankundu.journalApp.exceptionHandler.OTPGenerationException;
import com.arpankundu.journalApp.models.MailOTP;

@Service
public class MailOTPService {

	@Autowired
	JavaMailSender javaMailSender;
	
	private Map<String,String> otpMap=new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiryMap = new ConcurrentHashMap<>();
	Set<String> verifiedEmails = Collections.newSetFromMap(new ConcurrentHashMap<>());


	public String generateOTP(String toEmail) {
		try {
		 	Random random = new Random();
	        String otp = String.valueOf(random.nextInt(99999)+100000);
	        otpMap.put(toEmail,otp);
	        otpExpiryMap.put(toEmail, System.currentTimeMillis() + (1 * 60 * 1000)); //1 min valid.
	        System.out.println(otpMap);
	        return otp;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new OTPGenerationException("Failed to generate OTP. Please try again later.");
		}
	}

	public boolean verifyOTP(MailOTP mailRequest) {
		try {
		    String email = mailRequest.getEmail();
		    if (!otpMap.containsKey(email))
		    	throw new InvalidOTPException("OTP not generated for this email.");
		    
		    if (System.currentTimeMillis() > otpExpiryMap.get(email)) {
		        otpMap.remove(email);
		        otpExpiryMap.remove(email);
		        throw new OTPExpiredException("OTP has expired");
		    }
		    if(mailRequest.getOtp().equals(otpMap.get(email))) {
		    	verifiedEmails.add(email);
		    	otpExpiryMap.remove(email);
		    	otpMap.remove(email);
		    	return true;
		    }
		    throw new InvalidOTPException("Invalid OTP entered");
		}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
		}
	}
}
