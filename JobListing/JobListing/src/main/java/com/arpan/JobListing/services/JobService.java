package com.arpan.JobListing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arpan.JobListing.models.JobPost;
import com.arpan.JobListing.repository.JobRepo;

@Service
public class JobService {
	
	@Autowired
	JobRepo jobRepo;

	public ResponseEntity<List<JobPost>> createPost(List<JobPost> jobPost){
		return new ResponseEntity<>(jobRepo.saveAll(jobPost),HttpStatus.CREATED);
	}

	public ResponseEntity<List<JobPost>> getPost() {
		return new ResponseEntity<>(jobRepo.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<List<JobPost>> getSearchedData(String text) {
		return new ResponseEntity<>(jobRepo.findByText(text),HttpStatus.OK);
	}
}
