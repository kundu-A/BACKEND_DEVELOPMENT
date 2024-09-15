package com.arpankundu.QuizApp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Questions_Table")
public class Questions {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="Question_Id")
	private Integer id;
	
	@Column(name="Catagory_of_questions")
	@NotBlank(message="Catagory is mandatory")
	private String catagory;
	
	@Column(name="Deficulty_level")
	@NotBlank(message="Level is mandatory")
	private String level;
	
	@Column(name="Questions")
	@NotBlank(message="Question is mandatory")
	private String question;
	
	@Column(name="Option_1")
	@NotBlank(message="Option 1 is mandatory")
	private String option1;
	
	@Column(name="Option_2")
	@NotBlank(message="Option 2 is mandatory")
	private String option2;
	
	@Column(name="Option_3")
	@NotBlank(message="Option 3 is mandatory")
	private String option3;
	
	@Column(name="Right_Answer")
	@NotBlank(message="Right Answer is mandatory")
	private String rightAnswer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	
}
