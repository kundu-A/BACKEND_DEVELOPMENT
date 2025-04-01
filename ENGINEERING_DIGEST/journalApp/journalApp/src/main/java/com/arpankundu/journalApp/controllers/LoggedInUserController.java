package com.arpankundu.journalApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.ChangePassword;
import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.EmailService;
import com.arpankundu.journalApp.services.MailOTPService;
import com.arpankundu.journalApp.services.UserService;

@RestController
@RequestMapping("/logged-user")
public class LoggedInUserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MailOTPService mailOTPService;

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword request , HttpServletRequest req, HttpServletResponse res){
		try {
			if(userService.changePassword(request,req,res)) {
				emailService.successfullPasswordChangingMail(getEmailId());
				return new ResponseEntity<>("Password changed successfully - Login again please , your token has expired!!",HttpStatus.OK);
			}
			return new ResponseEntity<>("Please enter valid credentials!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some internal problem!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/password-change-otp")
	public ResponseEntity<?>changePasswordViaOTP(){
		try {
			String email=getEmailId();
			emailService.changePasswordOTP(email);
			return new ResponseEntity<>("OTP sent successfully into the registered!!",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some internal problems!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/password-change-verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody MailOTP mailotp){
		try {
			String email=getEmailId();
			mailotp.setEmail(email);
			if(mailOTPService.verifyOTP(mailotp))
				return new ResponseEntity<>("OTP is Valid , Email Validation is successfull!",HttpStatus.OK);
			return new ResponseEntity<>("Invalid OTP!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/set-password")
	public ResponseEntity<?> setPassword(@RequestBody ChangePassword request){
		String email=getEmailId();
		if(userService.setPassword(request,email)) {
			emailService.successfullPasswordChangingMail(getEmailId());
			return new ResponseEntity<>("Your Password is modified successfully!!",HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Credientials!!",HttpStatus.BAD_REQUEST);
	}
	
	public String getEmailId() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Users user=userRepo.findUsersByUsername(username);
		String email=user.getEmail();
		return email;
	}
}
