package com.arpankundu.DNeuro.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.arpankundu.DNeuro.components.ChangePassword;
import com.arpankundu.DNeuro.models.Role;
import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.PublicRepo;

@Service
public class PublicService {

	@Autowired
	private PublicRepo userRepo;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    private OTPService otpService;
	
	public Users register(Users user) {
		 String email = user.getEmail();
	        if (!otpService.verifiedEmails.contains(email)) 
	            throw new RuntimeException("Email verification required.");

	        if (userRepo.findUsersByEmail(email) != null)
	            throw new RuntimeException("User with this email already exists.");

	        user.setUsername(utilityService.extractUsernameFromEmail(user));
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole(Role.ROLE_USER);
	        try {
	        	Users users=userRepo.save(user);
	        	if(users!=null) {
	        		emailService.welcomeEmail(email);
	        		return users;
	        	}
	        	throw new RuntimeException("User registration failed");
	        }catch(Exception e) {
	        	throw new RuntimeException("User registration failed", e); 
	        }
	}
	public String verify(Users user) {
		String username=utilityService.extractUsernameFromEmail(user);
        try {
        	Authentication authentication =
        			authenticationManager.authenticate(
        					new UsernamePasswordAuthenticationToken(
        							username,
        							user.getPassword()
        					)
        			);
        	if (authentication.isAuthenticated()) {
        		String token=jwtService.generateToken(username);
        		if(token!=null)
        			user.setTokenIssueTime(LocalDateTime.now());
        		System.out.println(user.getTokenIssueTime());
        		return token;
        	}
        }catch(Exception e) {
        	throw new RuntimeException("Invalid username or password.");
        }
        throw new RuntimeException("Invalid username or password.");
    }
	public boolean changePassword(ChangePassword request) {
		String newPassword=passwordEncoder.encode(request.getNewPassword());
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Users user=userRepo.findByUsername(username);
		if(!passwordEncoder.matches(request.getOlderPassword(), user.getPassword()))
			return false;
		if(!request.getNewPassword().equals(request.getConfirmPassword()))
			return false;
		try {
			user.setPassword(newPassword);
			user.setTokenIssueTime(LocalDateTime.now());
			userRepo.save(user);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
