package com.arpan.login.OTPLogin.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Tokens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Integer id;

    @Column(name="access_token")
    private String accessToken;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="expiration_duration")
    private long expirationDuration;

    @Column(name="logged_in_at")
    private LocalDateTime loggedInAt;

    @Column(name="logged_out_at")
    private LocalDateTime loggedOutAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoggedInAt() {
        return loggedInAt;
    }

    public void setLoggedInAt(LocalDateTime loggedInAt) {
        this.loggedInAt = loggedInAt;
    }

    public LocalDateTime getLoggedOutAt() {
        return loggedOutAt;
    }

    public void setLoggedOutAt(LocalDateTime loggedOutAt) {
        this.loggedOutAt = loggedOutAt;
    }

    public long getExpirationDuration() {
        return expirationDuration;
    }

    public void setExpirationDuration(long expirationDuration) {
        this.expirationDuration = expirationDuration;
    }
}
