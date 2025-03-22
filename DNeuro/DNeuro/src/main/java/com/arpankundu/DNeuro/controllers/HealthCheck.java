package com.arpankundu.DNeuro.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthCheck {

	@PostMapping("/healt-check")
	public ResponseEntity<?> health_check(){
		return new ResponseEntity<>("The server is runnig smoothly!!",HttpStatus.OK);
	}
}
