package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	    
	    public Users register(Users user) {
	    	String email=user.getEmail();
	    	if(mailOTPService.verifiedEmails.contains(email)) {
	    		user.setUsername(utilityService.extractUsernameFromEmail(user)); // username = substring of email id, before @ symbol
	        	user.setPassword(passwordEncoder.encode(user.getPassword()));
	        	user.setRole(Role.ROLE_USER);
	        	mailOTPService.welcomeEmail(email);
	        	return userRepo.save(user);
	    	}
	    	return null;
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
				return "User not found!!";
		}
}