package com.arpankundu.journalApp.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.BadCredentialsException;
import com.arpankundu.journalApp.exceptionHandler.EmailNotVerifiedException;
import com.arpankundu.journalApp.exceptionHandler.RegistrationRelatedException;
import com.arpankundu.journalApp.exceptionHandler.UserAlreadyExistsException;
import com.arpankundu.journalApp.exceptionHandler.UserNotFoundException;
import com.arpankundu.journalApp.models.ChangePassword;
import com.arpankundu.journalApp.models.ForgotPassword;
import com.arpankundu.journalApp.models.MailOTP;
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

	    @Autowired
	    MailOTPService mailOTPService;
	    
	    @Autowired
	    EmailService emailService;
	    
	    public Users register(Users user) {
	        String email = user.getEmail();
	        if (!mailOTPService.verifiedEmails.contains(email)) 
	            throw new EmailNotVerifiedException("Email verification required.");

	        if (userRepo.findUsersByEmail(email) != null)
	            throw new UserAlreadyExistsException("User with this email already exists.");

	        user.setUsername(utilityService.extractUsernameFromEmail(user));
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole(Role.ROLE_USER);
	        try {
	        	Users users=userRepo.save(user);
	        	if(users!=null) {
	        		emailService.welcomeEmail(email);
	        		return users;
	        	}
	        	throw new RegistrationRelatedException("User registration failed");
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
	        	throw new BadCredentialsException("Invalid username or password.");
	        }
	        throw new BadCredentialsException("Invalid username or password.");
	    }
	    
	    public Users getLoggedInUser(String username) {
	        Users user = userRepo.findUsersByUsername(username);
	        if (user == null) 
	            throw new UserNotFoundException("User not found!!");
	        return user;
	    }

	    
	    public String OTPVerifiedLogin(MailOTP mailOTP) {
	    	try {
	    	String username=utilityService.extractUsernameFromEmail(mailOTP.getEmail());
	    	Users user=userRepo.findUsersByUsername(username);
	    	if(user!=null)
	    		return jwtService.generateToken(username);
	    	return "Provide the valid email!!";
	    	}catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		return "Some issues aries!!";
	    	}
	    }

		public String forgotPassword(String email, String password) {
				if(mailOTPService.verifiedEmails.contains(email)){
					Users user=userRepo.findUsersByEmail(email);
					user.setPassword(passwordEncoder.encode(password));
					userRepo.save(user);
					return "Password is saved successfully!!";
				}
				 throw new EmailNotVerifiedException("Email verification required.");
		}
		
		public boolean changePassword(ChangePassword request) {
			String newPassword=passwordEncoder.encode(request.getNewPassword());
			String username=SecurityContextHolder.getContext().getAuthentication().getName();
			Users user=userRepo.findUsersByUsername(username);
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

		public boolean setPassword(ChangePassword request,String email) {
			if(!request.getNewPassword().equals(request.getConfirmPassword()))
				return false;
			if(mailOTPService.verifiedEmails.contains(email)){
				Users user=userRepo.findUsersByEmail(email);
				user.setPassword(passwordEncoder.encode(request.getNewPassword()));
				user.setTokenIssueTime(LocalDateTime.now());
				userRepo.save(user);
				return true;
			}
			return false;
		}
		
		public boolean resetPassword(ForgotPassword request) {
			if(!request.getPassword().equals(request.getConfirmPassword()))
				return false;
			if(mailOTPService.verifiedEmails.contains(request.getEmail())){
				Users user=userRepo.findUsersByEmail(request.getEmail());
				user.setPassword(passwordEncoder.encode(request.getPassword()));
				user.setTokenIssueTime(LocalDateTime.now());
				userRepo.save(user);
				return true;
			}
			return false;
		}
}