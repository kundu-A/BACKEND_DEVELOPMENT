package com.arpankundu.journalApp.models;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="journals_list")
public class JournalEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="journal_id")
	private Integer id;
	
	@NotBlank(message="Journal Title Cant' be Null")
	@Column(name="journal_title",nullable=false)
	private String title;
	
	@NotBlank(message="Journal Title Cant' be Null")
	@Column(name="journal_content")
	private String content;
	
	@Column(name="shared_by")
	private String sharedBy;
	
	@Column(name="uploaded_date")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	@JsonIgnore
	private Users users;
	
	public String getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(String sharedBy) {
		this.sharedBy = sharedBy;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
