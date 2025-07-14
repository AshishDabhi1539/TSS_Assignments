package com.tss.model;

public class Admin {
	private String username;
	private String password;
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public boolean authenticate(String inputUser, String inputPass) {
		return username.equals(inputUser) && password.equals(inputPass);
	}
	
}
