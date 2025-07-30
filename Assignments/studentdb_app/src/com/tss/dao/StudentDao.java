package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.batabase.DBConnection;
import com.tss.model.Student;

public class StudentDao {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;

	public StudentDao() {
		super();
		this.connection = DBConnection.connect();
	}
	
	public List<Student> readAllStudents() {
        List<Student> students = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM students");

            while (result.next()) {
                Student student = new Student();
                student.setStudentId(result.getInt("student_id"));
                student.setRollNumber(result.getString("roll_number"));
                student.setAge(result.getInt("age"));
                student.setStudentName(result.getString("student_name"));
                student.setPercentage(result.getDouble("percentage"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error while reading students.");
            e.printStackTrace();
        }

        return students;
    }

	public void addNewStudent(Student student) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO STUDENTS VALUES(?,?,?,?,?)");
			preparedStatement.setInt(1, student.getStudentId());
			preparedStatement.setString(2, student.getStudentName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setDouble(4, student.getPercentage());
			preparedStatement.setString(5, student.getRollNumber());
			
			int updates = preparedStatement.executeUpdate();
			if(updates > 0) {
				System.out.println("Student Recored Inserted Successfully.");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert failed.");
			e.printStackTrace();
		}
	}
	
	public Student readStudentById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE student_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new Student(
                	result.getInt("student_id"),
                	result.getString("student_name"),
                	result.getInt("age"),
                	result.getDouble("percentage"),
                	result.getString("roll_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public void updateStudentPercentageById(int id, double percentage) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE students SET percentage = ? WHERE student_id = ?");
            preparedStatement.setDouble(1, percentage);
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows > 0 ? "Percentage updated." : "No student found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void deleteStudentById(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "No student found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
