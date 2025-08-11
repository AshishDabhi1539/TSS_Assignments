package com.tss.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.tss.dao.QuestionDAO;
import com.tss.model.Question;

public class QuizService {
	private QuestionDAO questionDAO = new QuestionDAO();
	private static final int TOTAL_QUESTIONS = 5;

	public List<Question> getQuestions() throws SQLException {
		return questionDAO.getAllQuestions();
	}

	public Question getQuestionById(int id) throws SQLException {
		return questionDAO.getQuestionById(id);
	}

	public void initializeQuiz(HttpSession session) {
		session.setAttribute("currentQuestion", 1);
		session.setAttribute("score", 0);
		session.setAttribute("answers", new ArrayList<Integer>());
	}

	@SuppressWarnings("unchecked")
	public void processAnswer(HttpSession session, int questionId, int selectedOption) throws SQLException {
		Question question = questionDAO.getQuestionById(questionId);
		if (question != null && selectedOption == question.getCorrectOption()) {
			session.setAttribute("score", (int) session.getAttribute("score") + 1);
		}
		List<Integer> answers = (List<Integer>) session.getAttribute("answers");
		answers.add(selectedOption);
		session.setAttribute("answers", answers);
		session.setAttribute("currentQuestion", (int) session.getAttribute("currentQuestion") + 1);
	}

	public boolean hasNextQuestion(HttpSession session) {
		return (int) session.getAttribute("currentQuestion") <= TOTAL_QUESTIONS;
	}

	public int getFinalScore(HttpSession session) {
		return (int) session.getAttribute("score");
	}
}
