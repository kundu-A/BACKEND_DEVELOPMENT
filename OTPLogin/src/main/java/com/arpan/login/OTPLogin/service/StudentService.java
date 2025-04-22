package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.StudentDTO;
import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.repository.CourseRepository;
import com.arpan.login.OTPLogin.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public Students setStudentDetails(StudentDTO studentDTO){
        Course course= courseRepository.findCourseByCourseName(studentDTO.getCourseName());
        Students students=new Students();
        students.setName(studentDTO.getName());
        students.setRollNumber(studentDTO.getRollNumber());
        students.setCourse(course);
        return studentRepository.save(students);
    }

    public Students getStudentDetails(Integer studentId){
        return studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Not Found"));
    }
}
