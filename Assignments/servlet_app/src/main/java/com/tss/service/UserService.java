package com.tss.service;

import java.util.List;

import com.tss.dao.UserDao;
import com.tss.model.User;

public class UserService {
	private UserDao userDao = new UserDao();

	public boolean isUserExists(String username) {
		if ("admin".equalsIgnoreCase(username)) {
			return true;
		}
		return userDao.isUserExists(username);
	}

	public boolean registerUser(String username, String password) {
		if ("admin".equalsIgnoreCase(username)) {
			return false;
		}
		return userDao.insertUser(new User(username, password));
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}
