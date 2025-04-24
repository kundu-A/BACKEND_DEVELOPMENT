package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.StudentDTO;
import com.arpan.login.OTPLogin.DTO.SubjectDTO;
import com.arpan.login.OTPLogin.models.*;
import com.arpan.login.OTPLogin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseSubjectRepository courseSubjectRepository;

    @Autowired
    SubjectStudentRepository subjectStudentRepository;

    public Students setStudentDetails(StudentDTO studentDTO){
        if(!courseRepository.existsByCourseName(studentDTO.getCourseName()))
            return null;
        Course course= courseRepository.findCourseByCourseName(studentDTO.getCourseName());
        List<CourseSubject> courseSubjects=courseSubjectRepository.findAllByCourseId(course.getId());
        Students students=new Students();
        students.setName(studentDTO.getName());
        students.setRollNumber(studentDTO.getRollNumber());
        students.setCourse(course);
        studentRepository.save(students);
        for(CourseSubject cs: courseSubjects){
            SubjectStudent subjectStudent=new SubjectStudent();
            subjectStudent.setStudents(students);
            subjectStudent.setSubjects(cs.getSubjects());
            subjectStudentRepository.save(subjectStudent);
        }
        return students;
    }

    public Students getStudentDetails(Integer studentId){
        return studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Not Found"));
    }

    public List<Subjects> fetchSubjectListUsingStudentRollNumber(String rollNumber){
        Students students=studentRepository.findStudentByRollNumber(rollNumber);
        List<SubjectStudent> subjectStudents=subjectStudentRepository.findAllByStudentsId(students.getId());
        return subjectStudents.stream()
                .map(SubjectStudent::getSubjects)
                .toList();
    }
}
