package com.arpankundu.journalApp.models;

import org.springframework.stereotype.Component;

@Component
public class OTP {

	private String email;
	private String mobileNo;
	private String otp;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
