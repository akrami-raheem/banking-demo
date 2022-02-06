package com.bank.model;

import java.io.Serializable;

public class UserDetailsDto implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String firstName;
	private String lastName;
	private String username;
	private String password;

	// default constructor for JSON Parsing
	public UserDetailsDto() {
	}

	public UserDetailsDto(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}