package com.arpan.login.OTPLogin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="course_subject")
public class CourseSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_subject_id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subjects subjects;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }
}
