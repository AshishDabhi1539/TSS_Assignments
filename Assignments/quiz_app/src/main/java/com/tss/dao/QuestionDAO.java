package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Question;

public class QuestionDAO {
	public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sqlQuery = "SELECT * FROM questions";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlQuery);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                questions.add(new Question(
                    result.getInt("id"),
                    result.getString("question_text"),
                    result.getString("option1"),
                    result.getString("option2"),
                    result.getString("option3"),
                    result.getString("option4"),
                    result.getInt("correct_option")
                ));
            }
        }
        return questions;
    }

    public Question getQuestionById(int id) throws SQLException {
        String sqlQuery = "SELECT * FROM questions WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Question(
                    result.getInt("id"),
                    result.getString("question_text"),
                    result.getString("option1"),
                    result.getString("option2"),
                    result.getString("option3"),
                    result.getString("option4"),
                    result.getInt("correct_option")
                );
            }
            return null;
        }
    }
}
