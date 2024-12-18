package com.arpan.MovieTicketBookingSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpan.MovieTicketBookingSystem.models.MovieDetails;
import com.arpan.MovieTicketBookingSystem.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public List<String> getMovieList() {
		return userRepo.findByName();
	}

	public List<MovieDetails> setMovieDetails(List<MovieDetails> movieDetails) {
		return userRepo.saveAll(movieDetails);
	}

	public Optional<MovieDetails> getMovieInfo(Integer id) {
		return userRepo.findById(id);
	}

}
