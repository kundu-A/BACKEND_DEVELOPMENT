package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> saveCourses(List<Course> courses){
        return courseRepository.saveAll(courses);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public List<Students> findStudentList(Integer courseId){
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Not found"));
        List<Students> studentNames=course.getStudents();
        return studentNames;
    }
}
