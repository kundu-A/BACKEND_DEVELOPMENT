package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.OneTimeTokenDTO;
import com.arpan.login.OTPLogin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> sendToken(@RequestBody OneTimeTokenDTO oneTimeTokenDTO){
        try{
            String response=tokenService.generateToken(oneTimeTokenDTO);
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("Token is not saved successfully",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-token")
     public ResponseEntity<?> verifyToken(@RequestBody OneTimeTokenDTO oneTimeTokenDTO){
        try{
            boolean response = tokenService.verifyToken(oneTimeTokenDTO);
            if(response)
                return new ResponseEntity<>("Verification successful",HttpStatus.OK);
            return new ResponseEntity<>("Verification unsuccessful",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
}
