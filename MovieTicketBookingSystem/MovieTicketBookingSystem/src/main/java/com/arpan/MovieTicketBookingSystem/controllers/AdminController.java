package com.arpan.MovieTicketBookingSystem.controllers;


import com.arpan.MovieTicketBookingSystem.DTO.UserDTO;
import com.arpan.MovieTicketBookingSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public ResponseEntity<?> register(UserDTO user){
        try{
            UserDTO userResponse=adminService.register(user);
            if(userResponse!=null)
                return new ResponseEntity<>("Admin registration successful",HttpStatus.CREATED);
            return new ResponseEntity<>("Admin registration unsuccessfull",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
