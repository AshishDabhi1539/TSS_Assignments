package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.dao.ResultDAO;
import com.tss.model.Result;
import com.tss.model.User;
import com.tss.service.QuizService;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizService quizService;
	private ResultDAO resultDAO;

	public ScoreServlet() {
		super();
		this.quizService = new QuizService();
		this.resultDAO = new ResultDAO(); // ✅ Ensure DAO is initialized
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}

		int score = quizService.getFinalScore(session);
		User user = (User) session.getAttribute("user");

		try {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Result result = new Result(0, user.getId(), score, now, user.getUsername());
			resultDAO.saveResult(result);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// 🔥 Stylish UI HTML
			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			out.println("<title>Quiz Result</title>");
			out.println("<style>");
			out.println(
					"body { font-family: 'Segoe UI', sans-serif; background: linear-gradient(to right, #6a11cb, #2575fc); display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }");
			out.println(
					".score-box { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 30px rgba(0,0,0,0.3); width: 380px; text-align: center; }");
			out.println("h2 { color: #333; }");
			out.println("p { font-size: 1.1rem; margin: 10px 0; }");
			out.println("strong { color: #2575fc; }");
			out.println(
					"button { width: 100%; padding: 12px; background-color: #2575fc; color: white; font-weight: bold; border: none; border-radius: 5px; cursor: pointer; transition: 0.3s; }");
			out.println("button:hover { background-color: #174cb3; }");
			out.println("form { margin-top: 20px; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='score-box'>");
			out.println("<h2>Quiz Completed</h2>");
			out.println("<p>Hello, <strong>" + user.getUsername() + "</strong>!</p>");
			out.println("<p>Your Score: <strong>" + score + "</strong></p>");
			out.println("<p>Time: <strong>" + now + "</strong></p>");

			out.println("<form action='logout' method='post'>");
			out.println("<button type='submit'>Logout</button>");
			out.println("</form>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

			session.invalidate();

		} catch (SQLException e) {
			response.setContentType("text/html");
			response.getWriter().println("<h3>Error saving score: " + e.getMessage() + "</h3>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
