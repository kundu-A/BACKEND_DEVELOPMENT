package com.arpan.SpringSecurity.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.SpringSecurity.models.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/user")
public class StudentController {

	private List<Student> students=new ArrayList<>(List.of(
			new Student(1,"Rup",70),
			new Student(2,"Sunny",70),
			new Student(3,"Arpan",65)
			));
	@GetMapping("/student")
	public ResponseEntity<?> getStudents(){
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
	@PostMapping("/student")
	public ResponseEntity<?> setStudents(@RequestBody Student student){
		students.add(student);
		return new ResponseEntity<>(student,HttpStatus.CREATED);
	}
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}
}
