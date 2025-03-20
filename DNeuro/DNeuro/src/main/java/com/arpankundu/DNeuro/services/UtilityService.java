package com.arpankundu.DNeuro.services;

import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.models.Users;

@Service
public class UtilityService {

	public String extractUsernameFromEmail(Users user) {
        String email = user.getEmail().toLowerCase();
        int endIndex = email.indexOf('@');
        return email.substring(0, endIndex);
    }

	public String extractUsernameFromEmail(String email) {
        int endIndex = email.indexOf('@');
        return email.substring(0, endIndex);
	}
}
