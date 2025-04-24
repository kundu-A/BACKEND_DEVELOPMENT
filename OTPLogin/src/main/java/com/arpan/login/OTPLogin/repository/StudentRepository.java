package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Integer> {

    Students findStudentByRollNumber(String rollNumber);
}
