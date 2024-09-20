package com.arpan.JobListing.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Jobs_List")
public class JobPost {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Job_Id")
	private Integer id;
	@Column(name="Job_Profile")
	private String profile;
	@Column(name="Job_Description")
	private String description;
	@Column(name="Experience")
	private float experience;
	@Column(name="Required_Technologies")
	private String technologies;
	public JobPost() {
		super();
	}
	public JobPost(Integer id, String profile, String description, float experience, String technologies) {
		super();
		this.id = id;
		this.profile = profile;
		this.description = description;
		this.experience = experience;
		this.technologies = technologies;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getExperience() {
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public String getTechnologies() {
		return technologies;
	}
	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
}
