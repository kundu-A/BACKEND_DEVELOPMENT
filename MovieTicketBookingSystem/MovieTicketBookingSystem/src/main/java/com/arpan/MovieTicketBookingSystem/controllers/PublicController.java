package com.arpan.MovieTicketBookingSystem.controllers;


import com.arpan.MovieTicketBookingSystem.DTO.UserDTO;
import com.arpan.MovieTicketBookingSystem.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private PublicService publicService;

    @PostMapping("/register")
    public ResponseEntity<?> register(UserDTO user){
        try{
            UserDTO userResponse=publicService.register(user);
            if(userResponse!=null)
                return new ResponseEntity<>("User is registeres successfully!!",HttpStatus.CREATED);
            return new ResponseEntity<>("User is not registered successfully",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal problems", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(UserDTO user){
        try{
            if(publicService.verify(user))
                return new ResponseEntity<>("Login successful",HttpStatus.OK);
            return new ResponseEntity<>("Login unsuccessful",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal problems",HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
