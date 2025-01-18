package com.arpankundu.journalApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="journals_list")
public class JournalEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="journal_id")
	private Integer id;
	@Column(name="journal_title")
	private String title;
	@Column(name="journal_content")
	private String content;
	
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
