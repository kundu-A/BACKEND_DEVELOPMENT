package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.SubjectDTO;
import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Subjects;
import com.arpan.login.OTPLogin.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    //Set a subject.
    @PostMapping("/set-subject")
    public ResponseEntity<?> setSubject(@RequestBody SubjectDTO subjectDTO){
        try{
            Subjects response=subjectService.setSubject(subjectDTO);
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("Subject is not saved successfully",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get a list of subjects.
    @GetMapping("/get-subject")
    public ResponseEntity<?> getSubject(){
        try{
            List<Subjects> subjects=subjectService.getSubject();
            if(subjects!=null)
                return new ResponseEntity<>(subjects,HttpStatus.OK);
            return new ResponseEntity<>("No subjects are available",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get all the courses related to specific subject by using subject id.
    @GetMapping("/get-all-courses/{subjectId}")
    public ResponseEntity<?> fetchAllCourses(@PathVariable Integer subjectId){
        try{
            List<Course> courses=subjectService.getCourseBySubjectId(subjectId);
            if(courses!=null)
                return new ResponseEntity<>(courses,HttpStatus.OK);
            return new ResponseEntity<>("No subjects are available",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
