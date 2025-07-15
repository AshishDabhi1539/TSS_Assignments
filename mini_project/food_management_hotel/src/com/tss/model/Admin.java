package com.tss.model;

import java.io.Serializable;

public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
