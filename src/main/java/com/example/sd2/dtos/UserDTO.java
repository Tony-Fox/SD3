package com.example.sd2.dtos;

public class UserDTO {
	private String username;
	private String password;

	public UserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserDTO() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
