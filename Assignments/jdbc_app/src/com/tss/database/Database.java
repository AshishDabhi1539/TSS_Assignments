package com.tss.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tss.model.Student;

public class Database {
    private Connection connection = null;
    private Statement statement = null;

    public Database() {
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tss_students_db", "root", "Temp@123456");
            
            System.out.println("Connected to the database successfully!");
            statement = connection.createStatement();
            
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public void insertStudent(Student student) {
        
    	String insertQuery = "INSERT INTO students (student_id, student_name, age, percentage, roll_number) VALUES ("
                + student.getStudentId() + ", '" + student.getStudentName() + "', " + student.getAge() + ", "
                + student.getPercentage() + ", '" + student.getRollNumber() + "')";

        try {
            int rows = statement.executeUpdate(insertQuery);
            if (rows > 0) {
                System.out.println("Student inserted successfully.");
            } else {
                System.out.println("Student insert failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting student.");
            e.printStackTrace();
        }
    }

    public void printAllStudents() {
    	
        String query = "SELECT * FROM students";

        try (ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("\n+------------+----------------------+-----+------------+----------------+");
            System.out.println("| Student ID | Name                 | Age | Percentage | Roll Number    |");
            System.out.println("+------------+----------------------+-----+------------+----------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                int age = resultSet.getInt("age");
                float percentage = resultSet.getFloat("percentage");
                String roll = resultSet.getString("roll_number");

                System.out.printf("| %-10d | %-20s | %-3d | %-10.2f | %-14s |\n", id, name, age, percentage, roll);
            }

            System.out.println("+------------+----------------------+-----+------------+----------------+");

        } catch (SQLException e) {
            System.out.println("Error fetching students.");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
