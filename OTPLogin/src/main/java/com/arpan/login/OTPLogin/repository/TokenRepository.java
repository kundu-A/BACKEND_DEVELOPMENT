package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Integer> {
    Tokens findTokensByAccessToken(String accessToken);
    Tokens findTokensByUserId(Integer userId);
}
