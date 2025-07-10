package com.tss.creational.singleton.model;

public class DatabaseConnection {

	private static DatabaseConnection connection = null;
	
	private DatabaseConnection() {
		super();
	}

	public static DatabaseConnection status() {
		if(connection == null)
			connection = new DatabaseConnection();
		
		return connection;
	}
	
}
