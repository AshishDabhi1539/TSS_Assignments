package com.tss.creational.singleton.test;

import com.tss.creational.singleton.model.DatabaseConnection;

public class DatabaseConnectionTest {

	public static void main(String[] args) {
		DatabaseConnection connection = DatabaseConnection.status();
		System.out.println(connection.hashCode());
		
		DatabaseConnection connection1 = DatabaseConnection.status();
		System.out.println(connection1.hashCode());
		
		DatabaseConnection connection2 = DatabaseConnection.status();
		System.out.println(connection2.hashCode());
		
		DatabaseConnection connection3 = DatabaseConnection.status();
		System.out.println(connection3.hashCode());
		
	}

}
