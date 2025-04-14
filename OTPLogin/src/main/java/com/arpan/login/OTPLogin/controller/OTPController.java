package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.OTP;
import com.arpan.login.OTPLogin.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class OTPController {

    @Autowired
    OTPService otpService;

    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOTP(@RequestBody OTP otp){
        try{
            String otpResponse= otpService.generateOTP(otp,"registration");
            if(otpResponse!=null)
                return new ResponseEntity<>(otpResponse,HttpStatus.OK);
            return new ResponseEntity<>("OTP generation unsuccessful",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOTP(@RequestBody OTP otp){
        try{
            boolean otpResponse= otpService.verifyOTP(otp,"registration");
            if(otpResponse)
                return new ResponseEntity<>("OTP verification is successful",HttpStatus.OK);
            return new ResponseEntity<>("OTP verification is not successful",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
