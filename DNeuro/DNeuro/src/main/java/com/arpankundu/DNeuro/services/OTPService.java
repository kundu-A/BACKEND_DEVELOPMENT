package com.arpankundu.DNeuro.services;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.components.OTP;

@Service
public class OTPService {

	@Autowired
	JavaMailSender javaMailSender;
	
	/*private Map<String,String> otpMap=new ConcurrentHashMap<>();
	private Map<String, Long> otpExpiryMap = new ConcurrentHashMap<>();*/
	Set<String> verifiedEmails = Collections.newSetFromMap(new ConcurrentHashMap<>());
	/*private Map<String, Integer> otpAttempts = new ConcurrentHashMap<>();*/

	@CachePut(value = "otpCache", key = "#toEmail")
	public String generateOTP(String toEmail) {
		try {
		 	Random random = new Random();
	        String otp = String.valueOf(random.nextInt(99999)+100000);
	        /*otpMap.put(toEmail,otp);
	        otpExpiryMap.put(toEmail, System.currentTimeMillis() + (5 * 60 * 1000)); //1 min valid.
	        otpAttempts.put(toEmail, 0);*/
	        return otp;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Failed to generate OTP. Please try again later.");
		}
	}

	@Cacheable(value = "otpCache", key = "#request.email")
	public String getOtp(OTP request){
		return  null;
	}

	@CacheEvict(value = "otpCache", key = "#request.email")
	public boolean verifyOTP(OTP request) {
		try {
		    String email = request.getEmail();
			String cachedOtp=getOtp(request);

			if (cachedOtp == null) {
				throw new RuntimeException("OTP not generated or expired.");
			}
			if(request.getOtp().equals(cachedOtp))
				return true;
			throw new RuntimeException("Invalid OTP");

		}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
		}
	}
	
	@Scheduled(fixedRate = 30*60000)
	@CacheEvict(value = "otpCache", allEntries = true)
	public void removeExpiredOTPs() {
		System.out.println("Running OTP cleanup at: " + new java.util.Date());
	}
}
