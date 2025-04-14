package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.OTP;
import com.arpan.login.OTPLogin.models.Role;
import com.arpan.login.OTPLogin.models.Users;
import com.arpan.login.OTPLogin.repository.UserRepository;
import com.arpan.login.OTPLogin.security.OTPAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OTPService otpService;

    @Autowired
    AuthenticationManager authenticationManager;

    public Users registration(Users users){
        if(!otpService.verifiedPhoneNumber.contains(users.getPhoneNumber())){
            System.out.println("Phone Number verification is needed");
            return null;
        }
        users.setRole(Role.ROLE_USER);
        return userRepository.save(users);
    }

    public String login(OTP otp,String status){
        if(!status.equals(otpService.otpStatus.get(otp.getPhoneNumber()))){
            System.out.println("Please generate OTP for Login");
            return null;
        }
        String phoneNumber=otp.getPhoneNumber();
        String providedOtp=otp.getOtp();

        Authentication authentication=new OTPAuthenticationToken(phoneNumber,providedOtp);
        Authentication authResult=authenticationManager.authenticate(authentication);

        if(authResult.isAuthenticated())
            return "Login successful";
        return null;
    }
}
