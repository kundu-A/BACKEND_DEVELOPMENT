package com.arpan.SpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpan.SpringSecurity.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

}
