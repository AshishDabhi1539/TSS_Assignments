package com.tss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Question;
import com.tss.service.QuizService;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizService quizService;

	public QuestionServlet() {
		super();
		this.quizService = new QuizService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}

		try {
			if (quizService.hasNextQuestion(session)) {
				int currentQuestion = (int) session.getAttribute("currentQuestion");
				Question question = quizService.getQuestionById(currentQuestion);

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.println("<!DOCTYPE html>");
				out.println("<html lang='en'>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("<title>Quiz Question</title>");
				out.println("<style>");
				out.println("body {");
				out.println("  font-family: 'Segoe UI', sans-serif;");
				out.println("  background: linear-gradient(to right, #00b4db, #0083b0);");
				out.println("  display: flex;");
				out.println("  justify-content: center;");
				out.println("  align-items: center;");
				out.println("  height: 100vh;");
				out.println("  margin: 0;");
				out.println("}");

				out.println(".container {");
				out.println("  background: white;");
				out.println("  padding: 40px 50px;");
				out.println("  border-radius: 12px;");
				out.println("  width: 600px;");
				out.println("  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);");
				out.println("  transition: all 0.3s ease;");
				out.println("}");

				out.println("h2 {");
				out.println("  color: #333;");
				out.println("  margin-bottom: 20px;");
				out.println("}");

				out.println("p {");
				out.println("  font-size: 1.1rem;");
				out.println("  color: #444;");
				out.println("}");

				out.println(".option {");
				out.println("  margin: 12px 0;");
				out.println("  padding: 10px 15px;");
				out.println("  border: 1px solid #ddd;");
				out.println("  border-radius: 8px;");
				out.println("  transition: background-color 0.3s ease;");
				out.println("}");

				out.println(".option:hover {");
				out.println("  background-color: #f0f8ff;");
				out.println("  cursor: pointer;");
				out.println("}");

				out.println("input[type='radio'] {");
				out.println("  margin-right: 10px;");
				out.println("  cursor: pointer;");
				out.println("}");

				out.println("button {");
				out.println("  width: 100%;");
				out.println("  padding: 12px;");
				out.println("  margin-top: 20px;");
				out.println("  background: linear-gradient(to right, #0083b0, #00b4db);");
				out.println("  color: white;");
				out.println("  font-weight: bold;");
				out.println("  border: none;");
				out.println("  border-radius: 8px;");
				out.println("  font-size: 1rem;");
				out.println("  transition: all 0.3s ease;");
				out.println("}");

				out.println("button:hover {");
				out.println("  background: linear-gradient(to right, #006e90, #00a9c2);");
				out.println("  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);");
				out.println("  transform: translateY(-2px);");
				out.println("  cursor: pointer;");
				out.println("}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<h2>Question " + question.getId() + "</h2>");
				out.println("<form action='question' method='post'>");
				out.println("<input type='hidden' name='questionId' value='" + question.getId() + "' />");
				out.println("<p>" + question.getQuestionText() + "</p>");

				out.println("<div class='option'><input type='radio' name='answer' value='1' required /> "
						+ question.getOption1() + "</div>");
				out.println("<div class='option'><input type='radio' name='answer' value='2' /> "
						+ question.getOption2() + "</div>");
				out.println("<div class='option'><input type='radio' name='answer' value='3' /> "
						+ question.getOption3() + "</div>");
				out.println("<div class='option'><input type='radio' name='answer' value='4' /> "
						+ question.getOption4() + "</div>");

				out.println("<button type='submit'>Next</button>");
				out.println("</form>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");

			} else {
				response.sendRedirect("score");
			}
		} catch (SQLException e) {
			response.setContentType("text/html");
			response.getWriter().println("<h3>Error loading question: " + e.getMessage() + "</h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return;
		}

		try {
			int questionId = Integer.parseInt(request.getParameter("questionId"));
			int selectedOption = Integer.parseInt(request.getParameter("answer"));
			quizService.processAnswer(session, questionId, selectedOption);
			response.sendRedirect("question");
		} catch (SQLException e) {
			response.setContentType("text/html");
			response.getWriter().println("<h3>Error processing answer: " + e.getMessage() + "</h3>");
		}
	}
}
