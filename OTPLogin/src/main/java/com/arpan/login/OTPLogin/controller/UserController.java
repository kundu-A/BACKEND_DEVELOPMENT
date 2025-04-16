package com.arpan.login.OTPLogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/testing")
    public ResponseEntity<?> testing(){
        try{
            String phoneNumber= SecurityContextHolder.getContext().getAuthentication().getName();
            return new ResponseEntity<>(phoneNumber,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
