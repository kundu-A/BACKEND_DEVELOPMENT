package com.arpankundu.DNeuro.controllers;

import com.arpankundu.DNeuro.models.Disease;
import com.arpankundu.DNeuro.services.DiseaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.PublicRepo;
import com.arpankundu.DNeuro.services.AdminService;
import com.arpankundu.DNeuro.services.UtilityService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UtilityService utilityService;
	
	@Autowired
	private PublicRepo userRepo;
	
	@Autowired
	private AdminService adminService;

    @Autowired
    private DiseaseServices diseaseService;
	
	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid Users user) {
        try {
            String username = utilityService.extractUsernameFromEmail(user);
            if (userRepo.findByUsername(username)==null)
                return new ResponseEntity<>(adminService.registerAdmin(user), HttpStatus.CREATED);
            return new ResponseEntity<>("Admin already exist",HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Admin Registration Unsuccessful",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/set-multiple-record")
    public ResponseEntity<?> setDiseaseDetails(@Valid @RequestBody List<Disease> diseases){
        try {
            return new ResponseEntity<>(diseaseService.setDiseaseDetails(diseases),HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("Something problem is happing internally!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/set-single-record")
    public ResponseEntity<?> setOneDisease(@Valid @RequestBody Disease disease){
        try {
            return new ResponseEntity<>(diseaseService.setOneDisease(disease), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>("Something problem is happing internally!!" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
