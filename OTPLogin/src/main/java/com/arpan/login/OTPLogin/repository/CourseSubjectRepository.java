package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject,Integer> {
}
