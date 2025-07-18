package com.tss.model;

public class Admin {
	private final String username;
	private final String password;

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean authenticate(String inputUser, String inputPass) {
		return username.equals(inputUser) && password.equals(inputPass);
	}

	public String getPassword() {
		return password;
	}

}