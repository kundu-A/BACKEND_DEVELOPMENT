package com.arpankundu.DNeuro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.models.Role;
import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.PublicRepo;

@Service
public class AdminService {

	@Autowired
	private PublicRepo userRepo;
	
	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OTPService otpService;
	
    public Users registerAdmin(Users user) {
    	String email = user.getEmail();
        if (!otpService.verifiedEmails.contains(email)) 
            throw new RuntimeException("Email verification required.");

        if (userRepo.findUsersByEmail(email) != null)
            throw new RuntimeException("Admin with this email already exists.");

        user.setUsername(utilityService.extractUsernameFromEmail(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        try {
        	Users users=userRepo.save(user);
        	emailService.welcomeEmail(email);
        	return users;
        }catch(Exception e) {
        	throw new RuntimeException("User registration failed", e); 
        }
    }
}
