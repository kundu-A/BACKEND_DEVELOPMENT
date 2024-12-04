package com.arpan.SpringSecurity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GreetController {

	@GetMapping("/greet")
	public ResponseEntity<?> greet(HttpServletRequest request){
		return new ResponseEntity<>("Welcome to Spring Security Project"+"The Sessoin Id is = "+request.getSession().getId(),HttpStatus.OK);
	}
}
