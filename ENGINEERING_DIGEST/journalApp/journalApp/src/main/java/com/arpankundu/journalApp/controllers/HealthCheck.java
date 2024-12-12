package com.arpankundu.journalApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

	@GetMapping("/greet")
	public ResponseEntity<?> healthCheck(){
		return new ResponseEntity<>("Hello ,I am Journal App",HttpStatus.OK);
	}
}
