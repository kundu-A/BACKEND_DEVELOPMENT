package com.arpan.SpringSecurity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {

	@Id
	private Integer id;
	private String username;
	private String passcode;
	private String role="ROLE_USER";
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", passcode=" + passcode + "]";
	}
	
}
