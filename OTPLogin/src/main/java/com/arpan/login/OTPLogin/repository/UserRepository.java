package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users ,Integer> {

    Users findUsersByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
