package com.arpankundu.QuizApp.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Quiz_Set")
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Quiz_Id")
	private Integer id;
	@Column(name="Quiz_Title")
	private String title;
	@ManyToMany
	@Column(name="Quiz_List")
	private List<Questions> questions;
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
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	
}
