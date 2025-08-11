package com.tss.service;

import java.util.List;

import com.tss.dao.UserDao;
import com.tss.model.User;

public class UserService {
	private UserDao userDao = new UserDao();

	public boolean isUserExists(String username) {
		return userDao.isUserExists(username);
	}

	public boolean registerUser(String username, String password, String role) {
		User user = new User(username, password, role, null);
		return userDao.insertUser(user);
	}

	public boolean validateCredentials(String username, String password) {
		return userDao.validateCredentials(username, password);
	}

	public String getUserRole(String username) {
		return userDao.getUserRole(username);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}