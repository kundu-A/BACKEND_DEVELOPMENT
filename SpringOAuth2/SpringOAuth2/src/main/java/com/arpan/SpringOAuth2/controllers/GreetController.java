package com.arpan.SpringOAuth2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	@GetMapping("/greet")
	public ResponseEntity<?> greet() {
		return new ResponseEntity<>("Welcome to the OAuth2 applied application!!",HttpStatus.OK);
	}
}
