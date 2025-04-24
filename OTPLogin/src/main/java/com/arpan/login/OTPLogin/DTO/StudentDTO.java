package com.arpan.login.OTPLogin.DTO;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StudentDTO {

    private String name;
    private String rollNumber;
    private String courseName;
    private Set<SubjectDTO> subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}
