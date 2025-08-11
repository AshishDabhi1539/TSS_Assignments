package com.tss.model;

import java.sql.Timestamp;

public class Result {
	private int id;
	private int userId;
	private int score;
	private Timestamp quizDate;
	private String userName;

	public Result() {
	}

	public Result(int id, int userId, int score, Timestamp quizDate,String userName) {
		this.id = id;
		this.userId = userId;
		this.score = score;
		this.quizDate = quizDate;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Timestamp getQuizDate() {
		return quizDate;
	}

	public void setQuizDate(Timestamp quizDate) {
		this.quizDate = quizDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
