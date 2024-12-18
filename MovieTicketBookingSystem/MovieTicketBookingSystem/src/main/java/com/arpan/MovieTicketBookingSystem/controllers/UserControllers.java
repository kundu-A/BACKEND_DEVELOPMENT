package com.arpan.MovieTicketBookingSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.MovieTicketBookingSystem.models.MovieDetails;
import com.arpan.MovieTicketBookingSystem.services.UserService;

@RestController
@RequestMapping("/user")
public class UserControllers {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/movie-list")
	public ResponseEntity<?> getMovieList(){
		return new ResponseEntity<>(userService.getMovieList(),HttpStatus.OK);
	}
	
	@PostMapping("/movie-details")
	public ResponseEntity<?> setMovieDetails(@RequestBody List<MovieDetails> movieDetails){
		return new ResponseEntity<>(userService.setMovieDetails(movieDetails),HttpStatus.CREATED);
	}
	
	@GetMapping("/movie-info/{id}")
	public ResponseEntity<?> getMovieInfo(@PathVariable Integer id){
		return new ResponseEntity<>(userService.getMovieInfo(id),HttpStatus.OK);
	}
}
