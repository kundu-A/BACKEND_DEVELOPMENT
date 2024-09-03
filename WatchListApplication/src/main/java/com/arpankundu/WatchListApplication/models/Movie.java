package com.arpankundu.WatchListApplication.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.arpankundu.WatchListApplication.models.validation.Priority;
import com.arpankundu.WatchListApplication.models.validation.Rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Movies")
public class Movie {

	@Id
	@Column(name="Movie_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="Movie_Title")
	@NotBlank(message="Please enter movie title")
	private String title;
	@Column(name="Movie_Rating")
	@Rating
	private float rating;
	@Column(name="Movie_priority")
	@Priority
	private String priority;
	@Column(name="Movie_Comment")
	@Size(max=50 , message="Comment should be in 50 Characters..")
	private String comment;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
