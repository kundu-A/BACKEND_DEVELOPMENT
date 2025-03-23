package com.arpankundu.journalApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

    @GetMapping("/login")
    public ResponseEntity<?> oauthLogin(@AuthenticationPrincipal OAuth2User user){
        return new ResponseEntity<>("Hello "+user.getAttribute("name"), HttpStatus.OK);
    }
}
