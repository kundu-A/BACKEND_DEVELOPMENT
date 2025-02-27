package com.arpankundu.journalApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.EmailNotVerifiedException;
import com.arpankundu.journalApp.exceptionHandler.UserAlreadyExistsException;
import com.arpankundu.journalApp.models.Role;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;

@Service
public class AdminService {

	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UtilityService utilityService;
    
    @Autowired
    MailOTPService mailOTPService;
    
    @Autowired
    EmailService emailService;

    public Users registerAdmin(Users user) {
    	String email = user.getEmail();
        if (!mailOTPService.verifiedEmails.contains(email)) 
            throw new EmailNotVerifiedException("Email verification required.");

        if (userRepo.findUsersByEmail(email) != null)
            throw new UserAlreadyExistsException("Admin with this email already exists.");

        user.setUsername(utilityService.extractUsernameFromEmail(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        emailService.welcomeEmail(email);

        return userRepo.save(user);
    }

	public List<?> getAllUserDetails() {
		return userRepo.findAll();
	}
}
