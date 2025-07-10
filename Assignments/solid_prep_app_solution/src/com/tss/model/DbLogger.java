package com.tss.model;

public class DbLogger implements ILogger {

	@Override
	public void log(String error) {
		System.out.println("Logged to database: " + error);
	}

}
