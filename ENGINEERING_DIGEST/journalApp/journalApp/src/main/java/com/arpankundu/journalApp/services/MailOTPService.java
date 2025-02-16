package com.arpankundu.journalApp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.MailOTP;

@Service
public class MailOTPService {

	@Autowired
	JavaMailSender javaMailSender;
	
	private Map<String,String> otpMap=new HashMap<>();
	private Map<String, Long> otpExpiryMap = new HashMap<>();
	List<String> verifiedEmails=new ArrayList<>();
	
	private static final String fromMail = "kunduarpan43@gmail.com";
	
	public void sendOTP(MailOTP mailRequest) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getEmail());
        message.setSubject("One-Time-Password to validate your mail");
        message.setText("Your OTP is: "+generateOTP(mailRequest.getEmail()));
        message.setFrom(fromMail);
        javaMailSender.send(message);
		
	}

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
			return null;
		}
	}

	public boolean verifyOTP(MailOTP mailRequest) {
		try {
		    String email = mailRequest.getEmail();
		    if (!otpMap.containsKey(email))
		    	return false;
		    
		    if (System.currentTimeMillis() > otpExpiryMap.get(email)) {
		        otpMap.remove(email);
		        otpExpiryMap.remove(email);
		        return false;
		    }
		    verifiedEmails.add(email);
		    return mailRequest.getOtp().equals(otpMap.get(email));
		}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
		}
	}
	
	public void welcomeEmail(String mailTo) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailTo);
        message.setSubject("Registration Successfull!!");
        message.setText("Hey, Welcome to the Journal Application.\nNow you can upload your first Journal.....\nBest of luck!!!");
        message.setFrom(fromMail);
        javaMailSender.send(message);
	}

}
