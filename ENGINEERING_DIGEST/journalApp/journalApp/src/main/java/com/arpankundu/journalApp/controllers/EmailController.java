package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.Email;
import com.arpankundu.journalApp.services.EmailService;

@RestController
@RequestMapping("/user")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/send-email")
	public ResponseEntity<?> sendEmail(@RequestBody Email emailRequest){
		try {
			emailService.sendeEmail(emailRequest);
			return new ResponseEntity<>("Email is send successfully!!",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
