package com.arpankundu.journalApp.models;

import org.springframework.stereotype.Component;

@Component
public class MailOTP {

	private String email;
	private String otp;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
