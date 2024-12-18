package com.arpan.MovieTicketBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpan.MovieTicketBookingSystem.models.MovieDetails;

@Repository
public interface UserRepo extends JpaRepository<MovieDetails, Integer>{

	@Query("SELECT m.movieName FROM MovieDetails m")
	List<String> findByName();

}
