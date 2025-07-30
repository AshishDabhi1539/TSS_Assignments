package com.tss.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.tss.batabase.DBConnection;
import com.tss.controller.StudentController;
import com.tss.util.MetaDataUtility;

public class StudentAppTest {
	public static void main(String[] args) {
		/*Scanner scanner = new Scanner(System.in);
		StudentController studentController = new StudentController();
		int choice;

		do {
			System.out.println("\n==== Student Management System ====");
			System.out.println("1. Read All Students");
			System.out.println("2. Add New Student");
			System.out.println("3. Read A Student By ID");
			System.out.println("4. Update Student's Percentage By ID");
			System.out.println("5. Delete A Student By ID");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
                studentController.readAllRecords();
                break;
                
            case 2:
                studentController.addNewStudent();
                break;
                
            case 3:
                studentController.readStudentById();
                break;
                
            case 4:
                studentController.updatePercentageById();
                break;
                
            case 5:
                studentController.deleteStudentById();
                break;
                
            case 6:
                System.out.println("Exiting...");
                break;
				
			default:
				System.out.println("Invalid choice. Please try again.");
			}

		} while (choice != 6);

		scanner.close();*/
		
		Connection connection = DBConnection.connect();

        MetaDataUtility.printDatabaseMetadata(connection);
        MetaDataUtility.printTableMetadata(connection, "students");

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
