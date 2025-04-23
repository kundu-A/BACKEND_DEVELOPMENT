package com.arpan.login.OTPLogin.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="subjects")
public class Subjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id")
    private Integer id;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="subject_code")
    private String subjectCode;

    @OneToMany(mappedBy = "subjects",cascade = CascadeType.PERSIST)
    private Set<CourseSubject> courseSubject;

    @OneToMany(mappedBy = "subjects",cascade = CascadeType.PERSIST)
    private Set<SubjectStudent> subjectStudent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Set<CourseSubject> getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(Set<CourseSubject> courseSubject) {
        this.courseSubject = courseSubject;
    }

    public Set<SubjectStudent> getSubjectStudent() {
        return subjectStudent;
    }

    public void setSubjectStudent(Set<SubjectStudent> subjectStudent) {
        this.subjectStudent = subjectStudent;
    }
}
