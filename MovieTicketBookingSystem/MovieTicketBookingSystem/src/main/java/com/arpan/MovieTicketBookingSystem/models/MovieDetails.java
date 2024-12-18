package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MovieDetails {

	@Id
	private Integer id;
	private String movieName;
	private double rating;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public MovieDetails(Integer id, String movieName, double rating) {
		super();
		this.id = id;
		this.movieName = movieName;
		this.rating = rating;
	}
	public MovieDetails() {
		
	}
}
