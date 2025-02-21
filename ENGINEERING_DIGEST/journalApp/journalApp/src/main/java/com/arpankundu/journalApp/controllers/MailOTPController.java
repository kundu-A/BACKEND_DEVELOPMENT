package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.services.MailOTPService;

@RestController
@RequestMapping("/user")
public class MailOTPController {

	@Autowired
	MailOTPService mailOTPService;
	
	/*
	 * PROCESS OF VALIDATE YOUR EMAIL::
	 * 1. Go to the validate-your-email -> Generate otp and send it to the email id.
	 */
	
	@PostMapping("/validate-your-email")
	public ResponseEntity<?> validateOTP(@RequestBody MailOTP mailRequest){
		try {
			mailOTPService.sendOTP(mailRequest);
			return new ResponseEntity<>("OTP sent successfully!!",HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * PROCESS OF VERIFY THE OTP::
	 * 1. Go to the verify-otp -> Put the email id and otp -> Check , if the provided otp is valid then it will send the successfull message.
	 */
	
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOTP(@RequestBody MailOTP mailRequest){
		try {
			if(mailOTPService.verifyOTP(mailRequest))
				return new ResponseEntity<>("OTP is Valid , Email Validation is successfull!",HttpStatus.OK);
			return new ResponseEntity<>("Invalid OTP!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
