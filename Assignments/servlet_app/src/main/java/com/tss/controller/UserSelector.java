package com.tss.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserSelector
 */
@WebServlet("/UserSelector")
public class UserSelector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSelector() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String userType = request.getParameter("userType");

        if (name == null || name.isEmpty() || userType == null || userType.isEmpty()) {
            response.getWriter().println("Name or user type missing.");
            return;
        }

        // Store name in session
        HttpSession session = request.getSession();
        session.setAttribute("userName", name);

        // Forward to appropriate controller
        RequestDispatcher dispatcher;
        if (userType.equalsIgnoreCase("admin")) {
            dispatcher = request.getRequestDispatcher("/AdminController");
        } else if (userType.equalsIgnoreCase("customer")) {
            dispatcher = request.getRequestDispatcher("/CustomerController");
        } else {
            response.getWriter().println("Invalid user type.");
            return;
        }

        dispatcher.forward(request, response);
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
