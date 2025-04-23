package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects,Integer> {

    @Query("select cs.course from CourseSubject cs where cs.subjects.id = :subjectId")
    List<Course> findCourseBySubjectId(@Param("subjectId") Integer subjectId);
}
