package com.arpan.SpringSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.SpringSecurity.models.Users;
import com.arpan.SpringSecurity.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users users) {
		return userService.register(users);
	}
	@PostMapping("/login")
	public String login(@RequestBody Users users) {
		return userService.verify(users);
	}
}
