package com.arpan.JobListing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.JobListing.models.JobPost;
import com.arpan.JobListing.services.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	JobService jobService;
	
	@PostMapping("/addjobs")
	public ResponseEntity<List<JobPost>> createPost(@RequestBody List<JobPost> jobPost){
		return jobService.createPost(jobPost);
	}
	
	@GetMapping("/getjobs")
	public ResponseEntity<List<JobPost>> getPost(){
		return jobService.getPost();
	}
	
	@GetMapping("/search/{text}")
	public ResponseEntity<List<JobPost>>getSearchedData(@PathVariable String text){
		return jobService.getSearchedData(text);
	}
}
