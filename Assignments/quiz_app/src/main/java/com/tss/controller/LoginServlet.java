package com.tss.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.UserDAO;
import com.tss.model.User;
import com.tss.service.QuizService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public LoginServlet() {
		super();
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = userDAO.loginUser(username, password);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				new QuizService().initializeQuiz(session);
				response.sendRedirect("question");
			} else {
				request.setAttribute("error", "Invalid username or password");
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("error", "Login failed: " + e.getMessage());
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
