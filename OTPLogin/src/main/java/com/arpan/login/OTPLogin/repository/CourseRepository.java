package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    Course findCourseByCourseName(String courseName);
    boolean existsByCourseName(String courseName);

    @Query("select cs.subjects from CourseSubject cs where cs.course.id = :courseId")
    List<Subjects> findSubjectsByCourseId(@Param("courseId") Integer courseId);
}
