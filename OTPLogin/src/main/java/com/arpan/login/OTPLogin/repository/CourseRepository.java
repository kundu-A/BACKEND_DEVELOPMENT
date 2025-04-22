package com.arpan.login.OTPLogin.repository;

import com.arpan.login.OTPLogin.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    Course findCourseByCourseName(String courseName);
}
