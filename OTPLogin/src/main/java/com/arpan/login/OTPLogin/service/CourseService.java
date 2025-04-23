package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.CourseDTO;
import com.arpan.login.OTPLogin.DTO.SubjectDTO;
import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.CourseSubject;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.models.Subjects;
import com.arpan.login.OTPLogin.repository.CourseRepository;
import com.arpan.login.OTPLogin.repository.CourseSubjectRepository;
import com.arpan.login.OTPLogin.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseSubjectRepository courseSubjectRepository;

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

    @Transactional
    public boolean saveCourseWithSubjects(CourseDTO courseDTO){
        try {
            Course course=null;
            if(courseRepository.existsByCourseName(courseDTO.getCourseName()))
                course=courseRepository.findCourseByCourseName(courseDTO.getCourseName());
            else {
                course = new Course();
                course.setCourseName(courseDTO.getCourseName());
                course.setCourseDuration(courseDTO.getCourseDuration());
                courseRepository.save(course);
            }

            for (SubjectDTO subjectDTO : courseDTO.getSubjects()) {
                Subjects subjects = new Subjects();
                subjects.setSubjectName(subjectDTO.getSubjectName());
                subjects.setSubjectCode(subjectDTO.getSubjectCode());
                subjectRepository.save(subjects);

                CourseSubject courseSubject = new CourseSubject();
                courseSubject.setCourse(course);
                courseSubject.setSubjects(subjects);
                courseSubjectRepository.save(courseSubject);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Subjects> findSubjectsByCourseId(Integer courseId) {
        return courseRepository.findSubjectsByCourseId(courseId);
    }
}
