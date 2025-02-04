package com.arpankundu.journalApp.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Users {

	@Id
	@Column(name="user_id")
	private Integer id;
	
	@NotBlank(message="Username name can't be null")
	@Column(name="username",nullable=false,unique=true)
	private String username;
	
	@NotBlank(message="Password can't be null")
	@Column(name="password",nullable=false)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$"
    , message = "At least one lowercase letter [a-z]\n" +
            "    At least one uppercase letter [A-Z]\n" +
            "    At least one special character [@#$%^&+=]\n" +
            "    Minimum length of 8 characters.\n" +
            "    At least one digit [0-9]")
	private String password;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name="user_role",nullable=false)
	private Role role;
	 
	@Column(name="user_email",nullable=false)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    , message = "Please enter a valid email")
	private String email;
	
	@Column(name="user_name",nullable=false)
	@NotBlank(message="Name name can't be null")
	private String name;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
