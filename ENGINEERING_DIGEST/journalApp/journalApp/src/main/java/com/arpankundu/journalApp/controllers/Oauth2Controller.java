package com.arpankundu.journalApp.controllers;

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

    @GetMapping("/login")
    public ResponseEntity<?> oauthLogin(@AuthenticationPrincipal OAuth2User user) {
        Map<String,Object> response = new HashMap<>();
        response.put("name",user.getAttribute("name"));
        response.put("email",user.getAttribute("email"));
        response.put("picture",user.getAttribute("picture"));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
