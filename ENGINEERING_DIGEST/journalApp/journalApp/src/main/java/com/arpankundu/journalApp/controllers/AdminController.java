package com.arpankundu.journalApp.controllers;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.models.JournalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.AdminService;
import com.arpankundu.journalApp.services.UtilityService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    AdminService adminService;

    @Autowired
    UserRepo  userRepo;

    @Autowired
    UtilityService utilityService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid Users user) {
        try {
            String username = utilityService.extractUsernameFromEmail(user);
            if (userRepo.findUsersByUsername(username)==null)
                return new ResponseEntity<>(adminService.registerAdmin(user), HttpStatus.CREATED);
            return new ResponseEntity<>("Admin already exist",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Admin Registration Unsuccessful",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/get-user-details")
    public ResponseEntity<?> getUserDetails(){
    	return new ResponseEntity<>(adminService.getAllUserDetails(),HttpStatus.OK);
    }

    //localhost:8080/admin/get-all-notes?pageNumber=0&pageSize=6 , OR
    //localhost:8080/admin/get-all-notes
    @GetMapping("/get-all-notes")
    public  ResponseEntity<?> getAllNotes(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy ,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        JournalResponse records = adminService.getAllNotes(pageNumber,pageSize,sortBy,sortDir);
        if (records != null)
            return new ResponseEntity<>(records, HttpStatus.OK);
        return new ResponseEntity<>("No Data Found!!",HttpStatus.OK);
    }
}
