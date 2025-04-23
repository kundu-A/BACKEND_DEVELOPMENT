package com.arpan.login.OTPLogin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer id;

    @Column(name="course_name")
    private String courseName;

    @Column(name="course_duration")
    private String courseDuration;

    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = CascadeType.PERSIST)
    private List<Students> students=new ArrayList<>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.PERSIST)
    private Set<CourseSubject> courseSubject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
