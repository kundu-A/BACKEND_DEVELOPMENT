package com.arpan.login.OTPLogin.models;

import jakarta.persistence.*;

@Entity
public class OneTimeToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Integer id;

    @Column(name="token")
    private String token;

    @Column(name="is_used")
    private boolean isUsed;

    @Column(name="phone_number")
    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
