package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/set-courses")
    public ResponseEntity<?> saveCourse(@RequestBody List<Course> course){
        try{
            List<Course> response=courseService.saveCourses(course);
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("Data is not saved successfully",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-courses")
    public ResponseEntity<?> getAllCourse(){
        try{
            List<Course> response=courseService.getAllCourses();
            if(response!=null)
                return new ResponseEntity<>(response,HttpStatus.OK);
            return new ResponseEntity<>("May be course is not available",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-student-list/{courseId}")
    public ResponseEntity<?> getStudentList(@PathVariable Integer courseId){
        try{
            List<Students> studentList=courseService.findStudentList(courseId);
            if(studentList!=null)
                return new ResponseEntity<>(studentList,HttpStatus.OK);
            return new ResponseEntity<>("No student found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
