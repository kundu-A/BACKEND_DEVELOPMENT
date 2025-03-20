package com.arpankundu.DNeuro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.components.OTP;
import com.arpankundu.DNeuro.services.EmailService;
import com.arpankundu.DNeuro.services.OTPService;

@RestController
@RequestMapping("/public")
public class OTPController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OTPService otpService;
	
	@PostMapping("/validate-your-email")
	public ResponseEntity<?> validateOTP(@RequestBody OTP request){
		try {
			emailService.sendOTP(request);
			return new ResponseEntity<>("OTP sent successfully!!",HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestBody OTP request){
		try {
			if(otpService.verifyOTP(request))
				return new ResponseEntity<>("OTP is Valid , Email Validation is successfull!",HttpStatus.OK);
			return new ResponseEntity<>("Invalid OTP!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
