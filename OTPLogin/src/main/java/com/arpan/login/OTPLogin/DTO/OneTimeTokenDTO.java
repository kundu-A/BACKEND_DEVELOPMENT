package com.arpan.login.OTPLogin.DTO;

import org.springframework.stereotype.Component;

@Component
public class OneTimeTokenDTO {

    private String token;
    private String phoneNumber;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
