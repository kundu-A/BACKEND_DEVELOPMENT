package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
