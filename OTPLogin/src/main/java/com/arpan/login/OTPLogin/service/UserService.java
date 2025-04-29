package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.OTP;
import com.arpan.login.OTPLogin.models.Role;
import com.arpan.login.OTPLogin.models.Tokens;
import com.arpan.login.OTPLogin.models.Users;
import com.arpan.login.OTPLogin.repository.TokenRepository;
import com.arpan.login.OTPLogin.repository.UserRepository;
import com.arpan.login.OTPLogin.security.OTPAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OTPService otpService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    RedisTemplate<String , String> redisTemplate;

    public Users registration(Users users){
        if(!otpService.isPhoneNumberVerified(users.getPhoneNumber())){
            System.out.println("Phone Number verification is needed");
            return null;
        }
        users.setRole(Role.ROLE_USER);
        otpService.deleteVerificationStatus(users.getPhoneNumber());
        return userRepository.save(users);
    }

    public String login(OTP otp,String status){
        if(!otpService.isOtpPresent(otp.getPhoneNumber())){
            System.out.println("OTP is not generated for this Phone Number");
            return null;
        }

        if(!otpService.checkingWithStatus(otp.getPhoneNumber(),status)){
            System.out.println("Please generate OTP for Login");
            return null;
        }
        String phoneNumber=otp.getPhoneNumber();
        String providedOtp=otp.getOtp();

        Users users= userRepository.findUsersByPhoneNumber(phoneNumber);
        Integer userId=users.getId();

        Authentication authentication=new OTPAuthenticationToken(phoneNumber,providedOtp);
        Authentication authResult=authenticationManager.authenticate(authentication);

        String accessToken=null;
        String refreshToken=null;
        if(authResult.isAuthenticated()) {
            accessToken = jwtService.generateAccessToken(phoneNumber);
            refreshToken= jwtService.generateRefreshToken(phoneNumber);
            setTokens(accessToken,refreshToken,userId);
            otpService.deleteOtp(phoneNumber);
            otpService.deleteStatus(phoneNumber);
            return "Access Token :"+accessToken+"\n"+"Refresh Token :"+refreshToken;
        }
        return null;
    }

    private void setTokens(String accessToken , String refreshToken , Integer userId){
        try{
            Tokens tokens= tokenRepository.findTokensByUserId(userId);
            if(tokens==null){
                tokens=new Tokens();
                tokens.setAccessToken(accessToken);
                tokens.setRefreshToken(refreshToken);
                tokens.setUserId(userId);
                tokens.setExpirationDuration(System.currentTimeMillis()+1*60*60*1000);
                tokens.setLoggedInAt(LocalDateTime.now());
                tokenRepository.save(tokens);
            }
            tokens.setAccessToken(accessToken);
            tokens.setRefreshToken(refreshToken);
            tokens.setLoggedInAt(LocalDateTime.now());
            tokens.setExpirationDuration(System.currentTimeMillis()+1*60*60*1000);
            tokenRepository.save(tokens);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
