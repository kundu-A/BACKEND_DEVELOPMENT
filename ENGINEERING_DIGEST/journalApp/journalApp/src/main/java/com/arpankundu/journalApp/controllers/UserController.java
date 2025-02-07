package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.UserService;
import com.arpankundu.journalApp.services.UtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	 @Autowired
	    UserService userService;

	    @Autowired
	    UserRepo userRepo;

	    @Autowired
	    UtilityService utilityService;

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody @Valid Users user) {
	        try {
	            String username = utilityService.extractUsernameFromEmail(user);
	            if (userRepo.findUsersByUsername(username)==null)
	                return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
	            return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return new ResponseEntity<>("User Registration Unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody @Valid Users user) {
	        try {
	            return new ResponseEntity<>(userService.verify(user), HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Login Failed", HttpStatus.INTERNAL_SERVER_ERROR);
	        }

	    }
}
