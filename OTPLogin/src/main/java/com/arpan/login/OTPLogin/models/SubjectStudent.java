package com.arpan.login.OTPLogin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="subject_student")
public class SubjectStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_student_id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subjects subjects;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="student_id")
    private Students students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }
}
