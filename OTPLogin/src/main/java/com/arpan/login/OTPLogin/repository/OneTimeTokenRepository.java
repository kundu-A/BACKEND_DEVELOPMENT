package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.OneTimeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimeTokenRepository extends JpaRepository<OneTimeToken,Integer> {
    OneTimeToken findOneTimeTokenByPhoneNumber(String phoneNumber);
}
