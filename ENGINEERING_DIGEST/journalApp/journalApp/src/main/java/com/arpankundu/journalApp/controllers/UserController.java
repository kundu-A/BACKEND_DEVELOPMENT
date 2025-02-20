package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.MailOTPService;
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
	    
	    @Autowired
	    MailOTPService mailOTPService;

	    /*
	     * PROCESS OF USER REGISTRATION::
	     * 1. Validate your email - Just give you email is , then you receive an OTP in that id.
	     * 2. Verify OTP - Give your email id and OTP , if the provided OTP is valid then you get a message
	     * for validation.
	     * 3. Registration - Go the Register API then give your name,email,password for registration and your account is created.
	     * 
	     * PROCESS OF USER LOGIN::
	     * 1. In the login API URL give the email and password and if those are vaild then you will get 
	     * JWT Token.
	     * 2. If you forgot the password then ->
	     * 	2.a. Go to the login-with-otp API then put your email id which is registered.
	     * 	2.b. Go to the verify-otp-login API then give the email and otp , id both are correct then 
	     * 			you will receive JWT token.
	     *
	     */
	    
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
	        	System.out.println(e.getMessage());
	            return new ResponseEntity<>("Login Failed...", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/login-with-otp")
	    public ResponseEntity<?> loginWithOTP(@RequestBody MailOTP mailOTP){
	    	try {
	    		if(!userRepo.existsByEmail(mailOTP.getEmail()))
	    			return new ResponseEntity<>("This email id not valid!!",HttpStatus.BAD_REQUEST);
	    		mailOTPService.sendOTP(mailOTP);
	    		return new ResponseEntity<>("OTP sent successfully!!",HttpStatus.CREATED);
	    	}catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
	    
	    @PostMapping("/verify-otp-login")
	    public ResponseEntity<?> verifyOTP(@RequestBody MailOTP mailOTP){
	    	try {
				if(mailOTPService.verifyOTP(mailOTP))
					return new ResponseEntity<>(userService.OTPVerifiedLogin(mailOTP),HttpStatus.OK);
				return new ResponseEntity<>("Invalid OTP!!",HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<>("Some issues arises!!",HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
}
