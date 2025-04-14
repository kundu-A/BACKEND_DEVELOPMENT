package com.arpan.login.OTPLogin.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OTPAuthenticationToken extends AbstractAuthenticationToken {

    private String phoneNumber;
    private String otp;
    public OTPAuthenticationToken(String phoneNumber , String otp) {
        super(null);
        this.phoneNumber=phoneNumber;
        this.otp=otp;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return otp;
    }

    @Override
    public Object getPrincipal() {
        return phoneNumber;
    }
}
