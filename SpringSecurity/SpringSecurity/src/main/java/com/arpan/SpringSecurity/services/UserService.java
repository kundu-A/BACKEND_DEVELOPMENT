package com.arpan.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpan.SpringSecurity.models.Users;
import com.arpan.SpringSecurity.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Users register(Users users) {
		
		users.setPasscode(encoder.encode(users.getPasscode()));
		return repo.save(users);
	}
}
