package com.arpankundu.DNeuro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.components.ChangePassword;
import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.PublicRepo;
import com.arpankundu.DNeuro.services.EmailService;
import com.arpankundu.DNeuro.services.PublicService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private PublicRepo userRepo;
	
	@Autowired
	private PublicService userService;
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody ChangePassword request){
		try {
			if(userService.changePassword(request)) {
				emailService.successfullPasswordChangingMail(getEmailId());
				return new ResponseEntity<>("Password changed successfully!!",HttpStatus.OK);
			}
			return new ResponseEntity<>("Please enter valid credintials!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some internal problem!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public String getEmailId() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Users user=userRepo.findByUsername(username);
		String email=user.getEmail();
		return email;
	}
}
