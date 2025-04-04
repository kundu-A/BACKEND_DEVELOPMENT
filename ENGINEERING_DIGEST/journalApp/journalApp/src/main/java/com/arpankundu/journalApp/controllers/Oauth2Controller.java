package com.arpankundu.journalApp.controllers;

import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth2")
@CrossOrigin(origins = "http://localhost:3000")
public class Oauth2Controller {

    @Autowired
    UserService userService;

    @Autowired
    UtilityService utilityService;

    @Autowired
    MyUserServiceDetails userDetailsService;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/login")
    public ResponseEntity<?> oauthLogin(@AuthenticationPrincipal OAuth2User user , HttpServletResponse res) {
        String email = user.getAttribute("email");
        String username = utilityService.extractUsernameFromEmail(email);
        try {
            userDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            Users newUser = userService.oauthRegister(user);
            if (newUser != null)
                emailService.welcomeEmail(email);
        }
        String accessToken = jwtService.generateToken(username);
        String refreshToken=jwtService.generateRefreshToken(username);
        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getAttribute("name"));
        response.put("email", user.getAttribute("email"));
        response.put("picture", user.getAttribute("picture"));
        response.put("jwt_token", accessToken);
        userService.setCookie(res, "Access-Token", accessToken, 60 * 60);
        userService.setCookie(res, "Refresh-Token", refreshToken, 7 * 24 * 60 * 60);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
