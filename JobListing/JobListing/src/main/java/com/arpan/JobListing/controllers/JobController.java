package com.arpan.JobListing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.JobListing.models.JobPost;
import com.arpan.JobListing.services.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	JobService jobService;
	
	@PostMapping("/addjobposts")
	public ResponseEntity<List<JobPost>> createPost(@Valid @RequestBody List<JobPost> jobPost){
		return jobService.createPost(jobPost);
	}
	
	@GetMapping("/getjobposts")
	public ResponseEntity<List<JobPost>> getPost(){
		return jobService.getPost();
	}
	
	@GetMapping("/search/{text}")
	public ResponseEntity<List<JobPost>>getSearchedData(@PathVariable String text){
		return jobService.getSearchedData(text);
	}
	
	@PutMapping("updatepost/{id}")
	public ResponseEntity<JobPost> updatePost(@Valid @RequestBody JobPost post ,@PathVariable Integer id){
		return jobService.updatePost(post,id);
	}
	
	@DeleteMapping("deletepost/{id}")
	public ResponseEntity<JobPost> deletePost(@PathVariable Integer id){
		return jobService.deletePost(id);
	}
}
