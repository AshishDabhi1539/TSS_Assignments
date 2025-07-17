package com.tss.model;

public class Admin {
	private final String username;
	private final String password;

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Authenticates the admin user.
	 * 
	 * @param inputUser The input username.
	 * @param inputPass The input password.
	 * @return True if authentication is successful, false otherwise.
	 */
	public boolean authenticate(String inputUser, String inputPass) {
		return username.equals(inputUser) && password.equals(inputPass);
	}

	public String getPassword() {
		return password;
	}

}