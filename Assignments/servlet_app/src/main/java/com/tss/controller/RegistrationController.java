package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response type
		response.setContentType("text/html");
		PrintWriter write = response.getWriter();

		// Get parameters from form
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String[] languages = request.getParameterValues("language");

		// Validate password
		if (!password.equals(confirmpassword)) {
			write.println("<h3 style='color:red;'>Passwords do not match!</h3>");
			return;
		}

		// Display submitted data
		write.println("<h2>Registration Successful!</h2>");
		write.println("<p><strong>Full Name:</strong> " + fullname + "</p>");
		write.println("<p><strong>Email:</strong> " + email + "</p>");
		write.println("<p><strong>Username:</strong> " + username + "</p>");
		write.println("<p><strong>Address:</strong> " + address + "</p>");
		write.println("<p><strong>Gender:</strong> " + gender + "</p>");
		write.println("<p><strong>City:</strong> " + city + "</p>");

		write.println("<p><strong>Languages Known:</strong> ");
		if (languages != null) {
			for (String lang : languages) {
				write.print(lang + " ");
			}
		} else {
			write.print("None");
		}
		write.println("</p>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
