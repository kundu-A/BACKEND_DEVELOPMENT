package com.arpankundu.journalApp.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.OTP;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPService {
	
	private Map<String,String> otpMap=new HashMap<>();
	private Map<String, Long> otpExpiryMap = new HashMap<>();
	
	private static final String ACCOUNT_SID = "ACc3561647d13069ad4a277435ae5541b6";
    public static final String AUTH_TOKEN = "84a8f3324446dc094c07e544765954a7";
    public static final String fromMobileNo = "+18159380805";

	public void generateOtp(OTP userRequest) {
		try {
		String mobileNo="";
		String temp=userRequest.getMobileNo();
		if(!temp.startsWith("+91"))
			mobileNo=mobileNo+"+91"+temp;
		else
			mobileNo=temp;
		Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
		 Message.creator(new PhoneNumber(mobileNo), new PhoneNumber(fromMobileNo), generateOtpFor(mobileNo))
         .create();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String generateOtpFor(String mobileNo) {
		try {
		 	Random random = new Random();
	        String otp = String.valueOf(random.nextInt(99999)+100000);
	        otpMap.put(mobileNo,otp);
	        otpExpiryMap.put(mobileNo, System.currentTimeMillis() + (1 * 60 * 1000)); //1 min valid.
	        System.out.println(otpMap);
	        return otp;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public boolean verifyOtp(OTP userRequest) {
		try {
	    String mobileNo = userRequest.getMobileNo();
	    if (!otpMap.containsKey(mobileNo))
	    	return false;
	    
	    if (System.currentTimeMillis() > otpExpiryMap.get(mobileNo)) {
	        otpMap.remove(mobileNo);
	        otpExpiryMap.remove(mobileNo);
	        return false;
	    }

	    return userRequest.getOtp().equals(otpMap.get(mobileNo));
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
