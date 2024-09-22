package com.arpan.JobListing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arpan.JobListing.models.JobPost;
import com.arpan.JobListing.repository.JobRepo;

import jakarta.transaction.Transactional;

@Service
public class JobService {
	
	@Autowired
	JobRepo jobRepo;

	@Transactional
	public ResponseEntity<List<JobPost>> createPost(List<JobPost> jobPost){
		try {
			return new ResponseEntity<>(jobRepo.saveAll(jobPost),HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<List<JobPost>> getPost() {
		try {
			return new ResponseEntity<>(jobRepo.findAll(),HttpStatus.OK);	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<List<JobPost>> getSearchedData(String text) {
		try {
			return new ResponseEntity<>(jobRepo.findByText(text),HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<JobPost> updatePost(JobPost post,Integer id) {
		try {
			JobPost postToBeUpdated=jobRepo.findById(id).get();
			postToBeUpdated.setProfile(post.getProfile());
			postToBeUpdated.setDescription(post.getDescription());
			postToBeUpdated.setExperience(post.getExperience());
			postToBeUpdated.setTechnologies(post.getTechnologies());
			System.out.println(postToBeUpdated.getDescription());
			System.out.println(post.getDescription());
			return new ResponseEntity<>(jobRepo.save(postToBeUpdated),HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<JobPost> deletePost(Integer id) {
		try {
			jobRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
