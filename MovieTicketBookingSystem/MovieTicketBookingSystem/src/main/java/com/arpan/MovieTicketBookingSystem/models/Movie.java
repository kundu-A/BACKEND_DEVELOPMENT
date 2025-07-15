package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Integer id;

	@Column(name="movie_title",nullable = false)
	@NotBlank(message = "Movie title can;t be null")
	private String title;

	@Column(name="imdb_rating")
	private double rating;

	@Column(name="movie_description",nullable = false)
	@Size(max = 500, message = "Description too long")
	@NotBlank(message = "Description shouldn't be null")
	private String description;

	@Column(name="movie_duration",nullable = false)
	@NotBlank(message = "Duration can't be null")
	private String duration;

	@Column(name="movie_release_date",nullable = false)
	@NotBlank(message = "Release Date can't be null")
	private LocalDateTime releaseDate;

	@Column(name="language",nullable = false)
	@NotBlank(message = "Preferred language can't be null")
	private String language;

	@Column(name="movie_poster",nullable = false)
	@NotBlank(message = "Movie Poster should be present")
	private String posterUrl;

}
