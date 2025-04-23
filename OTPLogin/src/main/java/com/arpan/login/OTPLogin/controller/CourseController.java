package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.CourseDTO;
import com.arpan.login.OTPLogin.models.Course;
import com.arpan.login.OTPLogin.models.Students;
import com.arpan.login.OTPLogin.models.Subjects;
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


    //Set a single Course without any subject.
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

    //Get a list of all courses.
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

    //Get a list of student of a specific course id.
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

    //Set course and subject list.
    @PostMapping("/set-course-and-subjects")
    public ResponseEntity<?> saveCourseWithSubject(@RequestBody CourseDTO courseDTO){
        try{
            boolean response= courseService.saveCourseWithSubjects(courseDTO);
            if(response)
                return new ResponseEntity<>("Course and subjects are saved successfully",HttpStatus.OK);
            return new ResponseEntity<>("Data are not saved successfully",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get a list of subjects related to a specific course using course id.
    @GetMapping("/get-all-subjects/{courseId}")
    public ResponseEntity<?> fetchAllSubjects(@PathVariable Integer courseId){
        try{
            List<Subjects> courses=courseService.findSubjectsByCourseId(courseId);
            if(courses!=null)
                return new ResponseEntity<>(courses,HttpStatus.OK);
            return new ResponseEntity<>("No courses are found",HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
