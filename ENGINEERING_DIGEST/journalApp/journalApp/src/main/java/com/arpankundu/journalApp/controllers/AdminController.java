package com.arpankundu.journalApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;
import com.arpankundu.journalApp.services.AdminService;
import com.arpankundu.journalApp.services.UtilityService;

import jakarta.validation.Valid;

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
            if (userRepo.findUserByUsername(username)==null)
                return new ResponseEntity<>(adminService.registerAdmin(user), HttpStatus.CREATED);
            return new ResponseEntity<>("Admin already exist",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Admin Registration Unsuccessful",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
