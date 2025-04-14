package com.arpan.login.OTPLogin.security;

import com.arpan.login.OTPLogin.service.MyUserServiceDetails;
import com.arpan.login.OTPLogin.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    OTPService otpService;

    @Autowired
    MyUserServiceDetails myUserServiceDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phoneNumber=authentication.getPrincipal().toString();
        String otp=authentication.getCredentials().toString();
        if(!otpService.isValid(phoneNumber,otp))
            throw new RuntimeException("Invalid OTP");
        UserDetails users=myUserServiceDetails.loadUserByPhoneNumber(phoneNumber);
        return new UsernamePasswordAuthenticationToken(users,null ,users.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OTPAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
