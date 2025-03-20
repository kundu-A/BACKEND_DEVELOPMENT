package com.arpankundu.DNeuro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.PublicRepo;
import com.arpankundu.DNeuro.services.PublicService;
import com.arpankundu.DNeuro.services.UtilityService;

import jakarta.validation.Valid;

@RestController
public class PublicController {

	@Autowired
	private PublicService userService;
	
	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private PublicRepo userRepo;
	
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody @Valid Users user) {
        try {
            String username = utilityService.extractUsernameFromEmail(user);
            if (userRepo.findByUsername(username)==null) {
            	Users record=userService.register(user);
            	if(record!=null)
            		return new ResponseEntity<>("Registration successfull!!", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("User Registration Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody @Valid Users user) {
        try {
            return new ResponseEntity<>(userService.verify(user), HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>("Login Failed...", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

