package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Users registerAdmin(Users user) {
        user.setUsername(utilityService.extractUsernameFromEmail(user));  // username = substring of email id, before @ symbol
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        return userRepo.save(user);
    }
}
