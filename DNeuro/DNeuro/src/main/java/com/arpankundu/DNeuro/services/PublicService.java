package com.arpankundu.DNeuro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JWTService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	public Users register(Users users) {
		users.setPassword(encoder.encode(users.getPassword()));
		return userRepo.save(users);
	}
	public String verify(Users users) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(users.getUsername());
		return "Failed to Login!!";
	}
}
