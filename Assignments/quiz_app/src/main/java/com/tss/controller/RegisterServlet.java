package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.dao.UserDAO;
import com.tss.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public RegisterServlet() {
		super();
		this.userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();

		try {
			boolean exists = userDAO.isUserExists(username, email);
			if (exists) {
				response.sendRedirect("register.html?error=User already exists!");
			} else {
				userDAO.registerUser(new User(0, username, password, email));
				response.sendRedirect("login.html");
			}
		} catch (SQLException e) {
			response.sendRedirect("register.html?error=Registration failed: " + e.getMessage());
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("register.html"); // redirect to registration page on GET
	}
}
