package com.arpan.login.OTPLogin.DTO;

import com.arpan.login.OTPLogin.models.Course;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SubjectDTO {

    private String subjectName;
    private String subjectCode;
    private String courseName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
