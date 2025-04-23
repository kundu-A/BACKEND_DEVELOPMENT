package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.SubjectDTO;
import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.CourseSubject;
import com.arpan.login.OTPLogin.models.Subjects;
import com.arpan.login.OTPLogin.repository.CourseRepository;
import com.arpan.login.OTPLogin.repository.CourseSubjectRepository;
import com.arpan.login.OTPLogin.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubjectService {

    @Autowired
    CourseSubjectRepository courseSubjectRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CourseRepository courseRepository;

    public Subjects setSubject(SubjectDTO subjectDTO){
        Course course= courseRepository.findCourseByCourseName(subjectDTO.getCourseName());
        if(course==null){
            System.out.println("Course is not available");
            return null;
        }

        Subjects subjects=new Subjects();
        subjects.setSubjectName(subjectDTO.getSubjectName());
        subjects.setSubjectCode(subjectDTO.getSubjectCode());
        subjectRepository.save(subjects);

        CourseSubject courseSubject=new CourseSubject();
        courseSubject.setSubjects(subjects);
        courseSubject.setCourse(course);
        courseSubjectRepository.save(courseSubject);

        return subjects;
    }

    public List<Subjects> getSubject(){
        return subjectRepository.findAll();
    }

    public List<Course> getCourseBySubjectId(Integer subjectId) {
        return subjectRepository.findCourseBySubjectId(subjectId);
    }
}
