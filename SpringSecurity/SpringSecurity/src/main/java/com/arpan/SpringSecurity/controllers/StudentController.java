package com.arpan.SpringSecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.SpringSecurity.models.Student;
import com.arpan.SpringSecurity.services.StudentServices;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServices studentService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveStudentData(@RequestBody List<Student> students){
		try {
			List<Student> student=studentService.saveStudentData(students);
			if(student!=null)
				return new ResponseEntity<>("Students data is saved successfully!!",HttpStatus.CREATED);
			else
				return new ResponseEntity<>("Something went wrong!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("May be some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/")
	public ResponseEntity<?> getStudentData(){
		try {
			List<Student> student=studentService.getStudentData();
			if(student!=null)
				return new ResponseEntity<>(student,HttpStatus.OK);
			else
				return new ResponseEntity<>("No Data Found!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("May be some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
