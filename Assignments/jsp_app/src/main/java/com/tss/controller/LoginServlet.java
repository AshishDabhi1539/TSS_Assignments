package com.tss.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if ("ashish".equals(username) && "ashish123".equals(password)) {
			request.setAttribute("username", username);
			request.setAttribute("course", "Computer Engineering");
			request.setAttribute("loginTime", new Date());

			request.getRequestDispatcher("Success.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
