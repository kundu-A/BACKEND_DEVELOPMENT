package com.arpankundu.DNeuro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.models.Disease;
import com.arpankundu.DNeuro.services.DiseaseServices;

@RestController
public class DiseaseControllers {

	@Autowired
	DiseaseServices diseaseService;
	
	@PostMapping("/setmultiplerecord")
	public ResponseEntity<?> setDiseaseDetails(@RequestBody List<Disease> diseases){
		return new ResponseEntity<>(diseaseService.setDiseaseDetails(diseases),HttpStatus.CREATED);
	}
	@PostMapping("/setsinglerecord")
	public ResponseEntity<?> setOneDisease(@RequestBody Disease disease){
		return new ResponseEntity<>(diseaseService.setOneDisease(disease), HttpStatus.CREATED);
	}
	@GetMapping("/getmultiplerecord")
	public ResponseEntity<?> getDiseaseDetails(){
		return new ResponseEntity<>(diseaseService.getDiseaseDetails(),HttpStatus.OK);
	}
}
