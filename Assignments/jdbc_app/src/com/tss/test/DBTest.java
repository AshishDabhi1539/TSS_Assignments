package com.tss.test;

import java.util.Scanner;
import com.tss.database.Database;
import com.tss.model.Student;

public class DBTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database studentDatabase = new Database();

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();

        System.out.print("Enter Percentage: ");
        double percentage = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();

        Student student = new Student(id, name, age, percentage, rollNumber);

        studentDatabase.insertStudent(student);
        studentDatabase.printAllStudents();
        studentDatabase.close();

        scanner.close();
    }
}
