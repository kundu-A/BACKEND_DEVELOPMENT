package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.models.Tokens;
import com.arpan.login.OTPLogin.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    TokenRepository tokenRepository;

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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        try{
            String authHeader=request.getHeader("Authorization");
            if(authHeader==null || !authHeader.startsWith("Bearer "))
                return new ResponseEntity<>("Please provide token",HttpStatus.BAD_REQUEST);
            String accessToken = authHeader.substring(7);
            Tokens tokens = tokenRepository.findTokensByAccessToken(accessToken);
            tokens.setAccessToken(null);
            tokens.setRefreshToken(null);
            tokens.setExpirationDuration(0);
            tokens.setLoggedOutAt(LocalDateTime.now());
            tokenRepository.save(tokens);
            return new ResponseEntity<>("Logout successfully",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
