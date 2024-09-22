package com.arpan.JobListing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arpan.JobListing.models.JobPost;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer>{

	@Query(value = "SELECT * FROM jobs_list j WHERE j.job_description LIKE %:text% OR j.job_profile LIKE %:text% OR j.required_technologies LIKE %:text" , nativeQuery=true)
	List<JobPost> findByText(@Param(value = "text") String text);

	void save(Optional<JobPost> postToBeUpdated);

}
