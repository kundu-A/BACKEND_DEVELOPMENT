package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.SubjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectStudentRepository extends JpaRepository<SubjectStudent,Integer> {
    List<SubjectStudent> findAllByStudentsId(Integer studentId);
    List<SubjectStudent> findAllBySubjectsId(Integer subjectId);
}
