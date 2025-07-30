package com.tss.batabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;

	private DBConnection() {

	}

	public static Connection connect() {
		try {
			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss_students_db", "root","Temp@123456");

				System.out.println("Connected to the database successfully!");
			}
		}
		catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
		
		return connection;
	}

}
