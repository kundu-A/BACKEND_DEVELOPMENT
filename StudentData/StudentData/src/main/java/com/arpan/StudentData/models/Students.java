package com.arpan.StudentData.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="Student_Data")
public class Students {

	@Id
	@Column(name="Student_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="First_Name")
	@NotBlank(message="Please Enter the First Name")
	private String firstName;
	
	@Column(name="Last_Name")
	@NotBlank(message="Please Enter the Last Name")
	private String lastName;
	
	@Column(name="Data_of_Birth")
	@NotBlank(message="Please Enter the Date of Birth")
	private String dob;
	
	@Column(name="Email_ID")
	@NotBlank(message="Please Enter the Email Id")
	private String email;
	
	@Column(name="Roll_No")
	@NotNull(message="Please Enter the Roll Number")
	@Positive
	private BigDecimal rollNo;
	
	@Column(name="Registration_No")
	@NotNull(message="Please Enter the Registration number")
	@Positive
	private BigDecimal regNo;
	
	@Column(name="College_Name")
	@NotBlank(message="Please Enter the College Name")
	private String collegeName;
	
	@Column(name="Stream")
	private String stream;
	
	@Lob
	@Column(name="Resume" , columnDefinition="LONGBLOB")
	private byte[] resume;
	
	@Column(name="File_Name")
	private String fileName;
	
	@Column(name="File_Type")
	private String fileType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getRollNo() {
		return rollNo;
	}
	public void setRollNo(BigDecimal rollNo) {
		this.rollNo = rollNo;
	}
	public BigDecimal getRegNo() {
		return regNo;
	}
	public void setRegNo(BigDecimal regNo) {
		this.regNo = regNo;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
