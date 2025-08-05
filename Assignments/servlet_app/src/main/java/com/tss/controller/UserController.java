package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.User;
import com.tss.service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public UserController() {
		super();
		this.userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter write = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		username = username.trim();
		password = password.trim();

		if (username.equals("admin") && password.equals("admin123")) {
			write.println("<h2>Welcome Admin!</h2>");
			write.println("<h3>User List:</h3>");

			List<User> users = userService.getAllUsers();

			write.println("<table border='1' cellpadding='10'>");
			write.println("<tr><th>User Id</th><th>Username</th><th>Password</th></tr>");

			for (User u : users) {
				write.println("<tr>");
				write.println("<td>" + u.getUserId() + "</td>");
				write.println("<td>" + u.getUsername() + "</td>");
				write.println("<td>" + u.getPassword() + "</td>");
				write.println("</tr>");
			}

			write.println("</table>");
			write.println("<br><a href='Login.html'>Logout</a>");
			write.close();
			return;
		}

		// User registration
		if (userService.isUserExists(username)) {
			write.println("<h3>User already exists. Please choose another username.</h3>");
		} else {
			boolean success = userService.registerUser(username, password);
			if (success) {
				write.println("<h3>Registration successful!</h3>");
				write.println("<p>Welcome, <b>" + username + "</b>!</p>");
			} else {
				write.println("<h3>Registration failed. Please try again later.</h3>");
			}
		}

		write.println("<br><a href='Login.html'>Go back</a>");
		write.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
