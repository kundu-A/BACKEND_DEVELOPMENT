package com.arpankundu.journalApp.controllers;

import java.util.HashMap;
import java.util.Map;

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

@RestController
@RequestMapping("/journal")
public class JournalEntryPoint {

	private Map<Long, JournalEntry> journalEntries=new HashMap<>();
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getEntry(){
		return new ResponseEntity<>(journalEntries.values(),HttpStatus.OK);
	}
	
	@PostMapping("/setAll")
	public ResponseEntity<?> createEntry(@RequestBody JournalEntry journalEntry){
		journalEntries.put(journalEntry.getId(), journalEntry);
		return new ResponseEntity<>("A new Journal Entry is created successfully!!",HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEntryById(@PathVariable Long id){
		return new ResponseEntity<>(journalEntries.get(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		journalEntries.remove(id);
		return new ResponseEntity<>("Id no. "+id+" deleted successfully!!",HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id , @RequestBody JournalEntry journalEntry){
		journalEntries.put(id, journalEntry);
		return new ResponseEntity<>("Id no. "+id+" updated successfully!!",HttpStatus.CREATED);
	}
}




