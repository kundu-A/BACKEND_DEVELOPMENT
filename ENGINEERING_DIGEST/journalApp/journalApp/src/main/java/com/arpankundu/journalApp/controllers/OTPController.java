package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.OTP;
import com.arpankundu.journalApp.services.OTPService;

@RestController
@RequestMapping("/otp")
public class OTPController {

	@Autowired
	OTPService otpService;
	
	@PostMapping("/send")
	public ResponseEntity<?> logigInViaPhone(@RequestBody OTP userRequest){
		try {
			otpService.generateOtp(userRequest);
			return new ResponseEntity<>("OTP sent successfully!!",HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestBody OTP userRequest){
		try {
			if(otpService.verifyOtp(userRequest))
				return new ResponseEntity<>("OTP is verify successfully!!",HttpStatus.OK);
			return new ResponseEntity<>("Invalid OTP!!",HttpStatus.BAD_REQUEST);
			
		}catch(Exception e) {
			return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
