package com.tss.model;

public class FileLogger implements ILogger {

	@Override
	public void log(String error) {
		System.out.println("Logged to file: " + error);
	}

}
