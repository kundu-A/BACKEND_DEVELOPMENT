package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.Role;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;

@Service
public class UserService {

	 	@Autowired
	    PasswordEncoder passwordEncoder;

	    @Autowired
	    UserRepo userRepo;

	    @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    JwtService jwtService;

	    @Autowired
	    UtilityService utilityService;

	    public Users register(Users user) {
	        user.setUsername(utilityService.extractUsernameFromEmail(user)); // username = substring of email id, before @ symbol
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole(Role.ROLE_USER);
	        return userRepo.save(user);
	    }

	    public String verify(Users user) {
	        String username=utilityService.extractUsernameFromEmail(user);
	        Authentication authentication =
	                authenticationManager.authenticate(
	                        new UsernamePasswordAuthenticationToken(
	                                username,
	                                user.getPassword()
	                        )
	                );
	        if (authentication.isAuthenticated())
	            return jwtService.generateToken(username);
	        return "Login Failed";
	    }
	    
	    public Users getLoggedInUser(String username) {
	    	return userRepo.findUsersByUsername(username);
	    }
}