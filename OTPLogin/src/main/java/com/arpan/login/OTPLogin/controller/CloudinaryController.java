package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.DTO.PersonDTO;
import com.arpan.login.OTPLogin.models.Person;
import com.arpan.login.OTPLogin.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/public")
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/save-media")
    public ResponseEntity<?> saveMedia(@RequestPart PersonDTO personDTO, @RequestPart MultipartFile file){
        try{
            Person savedPerson= cloudinaryService.savePerson(file,personDTO);
            if(savedPerson!=null)
                return new ResponseEntity<>(savedPerson,HttpStatus.OK);
            return new ResponseEntity<>("Data is not saved successfully",HttpStatus.SERVICE_UNAVAILABLE);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
