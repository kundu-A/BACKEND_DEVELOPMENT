package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.OTP;
import com.arpan.login.OTPLogin.models.UserPrinciple;
import com.arpan.login.OTPLogin.models.Users;
import com.arpan.login.OTPLogin.repository.UserRepository;
import com.arpan.login.OTPLogin.service.JWTService;
import com.arpan.login.OTPLogin.service.MyUserServiceDetails;
import com.arpan.login.OTPLogin.service.OTPService;
import com.arpan.login.OTPLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    OTPService otpService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    MyUserServiceDetails myUserServiceDetails;

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

    @GetMapping("/refresh-button")
    public ResponseEntity<?> refreshButton(@RequestHeader("Authorization") String authHeader){
        try{
            String refreshToken=null;
            String accessToken=null;
            if(authHeader.startsWith("Bearer ")){
                refreshToken=authHeader.substring(7);
                String phoneNumber= jwtService.extractPhoneNumber(refreshToken);
                UserDetails userDetails=myUserServiceDetails.loadUserByPhoneNumber(phoneNumber);
                if(!jwtService.validateRefreshToken(refreshToken,userDetails))
                    return new ResponseEntity<>("Please login again",HttpStatus.UNAUTHORIZED);
                accessToken= jwtService.generateAccessToken(phoneNumber);
                return new ResponseEntity<>("Access Token: "+accessToken,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
