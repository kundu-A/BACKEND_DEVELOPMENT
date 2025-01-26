package com.arpan.SpringSecurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpan.SpringSecurity.models.Student;
import com.arpan.SpringSecurity.repository.StudentRepo;

@Service
public class StudentServices {

	@Autowired
	private StudentRepo studentRepo;

	public List<Student> saveStudentData(List<Student> students) {
		return studentRepo.saveAll(students);
	}

	public List<Student> getStudentData() {
		return studentRepo.findAll();
	}
	
	
}
