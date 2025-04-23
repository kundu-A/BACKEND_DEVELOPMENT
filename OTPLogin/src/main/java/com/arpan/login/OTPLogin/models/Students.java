package com.arpan.login.OTPLogin.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Integer id;

    @Column(name="student_name")
    private String name;

    @Column(name="roll_number")
    private String rollNumber;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @OneToMany(mappedBy = "students",cascade = CascadeType.PERSIST)
    private Set<SubjectStudent> subjectStudent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
