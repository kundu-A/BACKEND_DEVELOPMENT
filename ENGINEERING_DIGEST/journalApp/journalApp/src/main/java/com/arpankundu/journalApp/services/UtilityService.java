package com.arpankundu.journalApp.services;

import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.Users;

@Service
public class UtilityService {

	public String extractUsernameFromEmail(Users user) {
        String email = user.getEmail().toLowerCase();
        int endIndex = email.indexOf('@');
        return email.substring(0, endIndex);
    }
}
