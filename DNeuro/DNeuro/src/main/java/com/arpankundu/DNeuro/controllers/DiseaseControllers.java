package com.arpankundu.DNeuro.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.DNeuro.models.Disease;
import com.arpankundu.DNeuro.services.DiseaseServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disease")
public class DiseaseControllers {

	@Autowired
	private DiseaseServices diseaseService;

	
	@GetMapping("/get-multiple-record")
	public ResponseEntity<?> getDiseaseDetails(){
		try {
			List<Disease> result=diseaseService.getDiseaseDetails();
			if(result.isEmpty()){
				return new ResponseEntity<>("May be no record present in the database or somthing else!!",HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Somthing problem is happing internally!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-single-record/{id}")
	public ResponseEntity<?> getOneDisease(@PathVariable Integer id){
		try {
			Optional<Disease> result = diseaseService.getOneDisease(id);
			if(result.isPresent()) {
				return new ResponseEntity<>(result.get(),HttpStatus.OK);
			}else {
				return new ResponseEntity<>("May be no record present in the database or something else!!" , HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Somthing problem is happing internally!!" , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-all-names")
	public ResponseEntity<?> getDiseaseName(){
		try {
			List<String> result = diseaseService.getDiseaseName();
			if(result.isEmpty()) {
				return new ResponseEntity<>("May be on records present in the database or something else!!",HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Somthing problem is happing internally!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchEngine(@RequestBody String text){
		try {
			List<Disease> result = diseaseService.searchEngine(text);
			if(result.isEmpty()) {
				return new ResponseEntity<>("No record find related to this searched items!!" , HttpStatus.BAD_REQUEST);	
			}else {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Somthing problem is happing internally!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/search-by-alphabet")
	public ResponseEntity<?> searchByAlphabate(@RequestBody String text){
		try {
			List<Disease> result=diseaseService.searchByAlphabate(text);
			if(result.isEmpty()) {
				return new ResponseEntity<>("No result found which starts with "+text+"!!",HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<>(result,HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<>("Something Problem is happing internally!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
