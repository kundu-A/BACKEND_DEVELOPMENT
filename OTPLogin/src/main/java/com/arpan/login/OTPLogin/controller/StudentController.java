package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.StudentDTO;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.models.Subjects;
import com.arpan.login.OTPLogin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/set-student-details")
    public ResponseEntity<?> setStudentDetails(@RequestBody StudentDTO studentDTO){
        try{
            Students response= studentService.setStudentDetails(studentDTO);
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("May be the student record is found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-student-details/{studentId}")
    public ResponseEntity<?> getStudentDetails(@PathVariable Integer studentId){
        try{
            Students students= studentService.getStudentDetails(studentId);
            return new ResponseEntity<>(students,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-subjectList")
    public ResponseEntity<?> fetchSubjectListUsingSubjectId(@RequestBody String rollNumber){
        try{
            List<Subjects> subjects=studentService.fetchSubjectListUsingStudentRollNumber(rollNumber);
            if(subjects!=null)
                return new ResponseEntity<>(subjects,HttpStatus.OK);
            return new ResponseEntity<>("Subject are not available for this student",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
