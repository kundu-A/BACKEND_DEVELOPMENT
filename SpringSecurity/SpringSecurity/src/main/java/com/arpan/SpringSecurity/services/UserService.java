package com.arpan.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.arpan.SpringSecurity.models.Users;
import com.arpan.SpringSecurity.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public Users register(Users users) {
		
		users.setPasscode(encoder.encode(users.getPasscode()));
		return repo.save(users);
	}

	public String verify(Users users) {
		 Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPasscode()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(users.getUsername());
		return "Failed to login!!";
	}
}
