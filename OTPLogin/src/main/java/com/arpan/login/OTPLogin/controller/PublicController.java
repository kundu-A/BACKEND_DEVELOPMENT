package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.OTP;
import com.arpan.login.OTPLogin.models.Users;
import com.arpan.login.OTPLogin.repository.UserRepository;
import com.arpan.login.OTPLogin.service.OTPService;
import com.arpan.login.OTPLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    OTPService otpService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody Users users){
        try{
            Users userResponse=userService.registration(users);
            return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody OTP otp){
        try{
            String response= userService.login(otp,"login");
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("Login unsuccessful",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/send-otp-for-login")
    public ResponseEntity<?> sendOtpForLogin(@RequestBody OTP otp){
        try{
            if(!userRepository.existsByPhoneNumber(otp.getPhoneNumber()))
                return new ResponseEntity<>("Requested Phone Number is not registered",HttpStatus.BAD_REQUEST);
            String otpResponse= otpService.generateOTP(otp,"login");
            if(otpResponse!=null) {
                System.out.println(otpResponse);
                return new ResponseEntity<>("OTP sent successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("OTP sent successful",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
