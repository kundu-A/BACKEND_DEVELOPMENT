package com.arpankundu.WatchListApplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpankundu.WatchListApplication.models.Movie;
import com.arpankundu.WatchListApplication.repository.MovieRepo;

@Service
public class DatabaseService {

	@Autowired
	MovieRepo movieRepo; 
	@Autowired
	RatingService ratingService;
	public void create(Movie movie) {
		String rating=ratingService.getMovieRating(movie.getTitle());
		if(rating!=null) {
			movie.setRating(Float.parseFloat(rating));
		}
		movieRepo.save(movie);
	}
	
	public List<Movie> getMovies(){
		return movieRepo.findAll();
	}
	
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).get();
	}

	public void update(Movie movie, Integer id) {
		Movie movieToBeUpdated=movieRepo.findById(id).get();
		movieToBeUpdated.setTitle(movie.getTitle());
		movieToBeUpdated.setRating(movie.getRating());
		movieToBeUpdated.setPriority(movie.getPriority());
		movieToBeUpdated.setComment(movie.getComment());
		
		movieRepo.save(movieToBeUpdated);
	}
	
	public void delete(Integer id) {
		movieRepo.deleteById(id);
	}
}
