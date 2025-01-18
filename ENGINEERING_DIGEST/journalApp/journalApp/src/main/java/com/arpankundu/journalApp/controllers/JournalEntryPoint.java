package com.arpankundu.journalApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.services.JournalServices;

@RestController
@RequestMapping("/journal")
public class JournalEntryPoint {

	@Autowired
	private JournalServices journalServices;
	
	//Get All Records : http:8080/journal/get
	@GetMapping("/get")
	public ResponseEntity<?> getEntry(){
		try {
			List<JournalEntry> entries=journalServices.getEntry();
		if(!entries.isEmpty())
			return new ResponseEntity<>(entries,HttpStatus.OK);
		else
			return new ResponseEntity<>("No Record Found!!",HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Sorry ,for not helping you :)!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Set A Record : http:8080/journal/set
	@PostMapping("/set")
	public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry){
		try {
			boolean check=journalServices.createEntry(journalEntry);
			if(check==true)
				return new ResponseEntity<>("Record is added successfully!!",HttpStatus.CREATED);
			else
				return new ResponseEntity<>("Something went wrong!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Sorry ,for not helping you :)!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Get A Record By Id : http:8080/journal/get/1
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEntryById(@PathVariable Integer id){
		try {
			Optional<JournalEntry> elementById=journalServices.getEntryById(id);
			if(elementById.isPresent())
				return new ResponseEntity<>(elementById,HttpStatus.OK);
			else
				return new ResponseEntity<>("No Record Found!!",HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Sorry ,for not helping you :)!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Delete A Record By Id : http:8080/journal/delete/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		try {
			boolean isDeleted=journalServices.deleteById(id);
			if(isDeleted==true)
				return new ResponseEntity<>("The Item is deleted successfully!!",HttpStatus.OK);
			else
				return new ResponseEntity<>("No Record Found/Something Went Wrong!!",HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>("Sorry ,for not helping you :)!!",HttpStatus.OK);
		}
	}
	
	//Get Update Record By Id : http:8080/journal/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable Integer id , @RequestBody JournalEntry journalEntry){
		try {
			boolean elementById=journalServices.updateEntryById(id,journalEntry);
			if(elementById==true)
				return new ResponseEntity<>("The Record updated successfully!!",HttpStatus.CREATED);
			else
				return new ResponseEntity<>("No Record Found/Something Went Wrong!!",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			return new ResponseEntity<>("Sorry ,for not helping you :)!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}




