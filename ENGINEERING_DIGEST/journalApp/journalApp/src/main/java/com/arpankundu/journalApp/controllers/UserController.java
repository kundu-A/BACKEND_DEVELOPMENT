package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.ForgotPassword;
import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.EmailService;
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
	    
	    @Autowired
	    EmailService emailService;
	    
	    

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
	            if (userRepo.findUsersByUsername(username)==null) {
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

	    /*
	     * PROCESS OF LOGIN::
	     * 1. Go to login URL -> Put email and password -> Get JWT Token.
	     */
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody @Valid Users user) {
	        try {
	            return new ResponseEntity<>(userService.verify(user), HttpStatus.OK);
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            return new ResponseEntity<>("Login Failed...", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    /*
	     * PROCESS OF LOGIN WITH OTP::
	     * 1. Go to login-with-otp URL -> Receive OTP into provided email id.
	     */
	    
	    @PostMapping("/login-with-otp")
	    public ResponseEntity<?> loginWithOTP(@RequestBody MailOTP mailOTP){
	    	try {
	    		if(!userRepo.existsByEmail(mailOTP.getEmail()))
	    			return new ResponseEntity<>("This email id not valid!!",HttpStatus.BAD_REQUEST);
	    		emailService.sendOTP(mailOTP);
	    		return new ResponseEntity<>("OTP sent successfully!!",HttpStatus.CREATED);
	    	}catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
	    
	    /*
	     * PROCESS OF VERIFY OTP::
	     * 1. Go to verify-otp-login URL -> verify the provided otp and email.
	     * 		If the provided otp is verified the  JWT token is generated.
	     */
	    
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
	    
	    /*
	     * PROCESS OF FORGOT PASSWORD::
	     * 1. Go to the login-your-otp URL -> put your password and you receive an OTP.
	     * 2. Go to the verify-otp URL -> Put that email id and OTP to validate your email.
	     * 3. Go to the forgot-password -> Put your email id and password to update the password.
	     */
	    
	    @PostMapping("/forgot-password")
	    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword request){
	    	try {
	    		String email=request.getEmail();
	    		String password=request.getPassword();
	    		if(userRepo.existsByEmail(email))
	    			return new ResponseEntity<>(userService.forgotPassword(email,password),HttpStatus.OK);
	    		return new ResponseEntity<>("Provided email is not registered!!",HttpStatus.BAD_REQUEST);
	    	}catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
}
