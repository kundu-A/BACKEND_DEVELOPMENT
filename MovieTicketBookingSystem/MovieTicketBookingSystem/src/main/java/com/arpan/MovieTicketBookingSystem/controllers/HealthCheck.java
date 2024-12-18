package com.arpan.MovieTicketBookingSystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

	@GetMapping("/health-check")
	public ResponseEntity<?> healthCheck(){
		return new ResponseEntity<>("Hey!! ,Welcome to the Movie Ticket Booking System!!",HttpStatus.OK);
	}
}
