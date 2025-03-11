package com.arpankundu.journalApp.models;

import org.springframework.stereotype.Component;

@Component
public class ChangePassword {

	private String olderPassword;
	private String newPassword;
	private String confirmPassword;
	public String getOlderPassword() {
		return olderPassword;
	}
	public void setOlderPassword(String olderPassword) {
		this.olderPassword = olderPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
