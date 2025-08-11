package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstController
 */
@WebServlet("/FirstController")
public class FirstController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String firstName = request.getParameter("firstName");
//
//        Cookie cookie = new Cookie("firstName", firstName);
//        response.addCookie(cookie);
//
//        response.sendRedirect("Last.html");
		
	    response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        // Get first name from form
        String firstName = request.getParameter("firstName");

        // Set cookie with first name
//        Cookie cookie = new Cookie("firstName", firstName);
//        response.addCookie(cookie);
        
        HttpSession session = request.getSession();
        session.setAttribute("firstName", firstName);

        // Dynamically generate second form
        writer.print("<html><body>");
        writer.print("<form action='LastController' method='post'>");
        writer.print("Last Name: <input type='text' name='lastNameTxt'><br><br>");
        writer.print("<button type='submit'>Next</button>");
        writer.print("</form>");
        writer.print("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
