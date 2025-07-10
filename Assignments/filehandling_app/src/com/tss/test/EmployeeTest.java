package com.tss.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Employee;
import com.tss.model.EmployeeNameComparator;
import com.tss.model.EmployeeSalaryComaparator;

public class EmployeeTest {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		
		readEmployee(employees);
		
		Collections.sort(employees);
		printEmployee(employees);
		
		Collections.sort(employees, new EmployeeNameComparator());
		printEmployee(employees);
		
		Collections.sort(employees, new EmployeeSalaryComaparator());
		printEmployee(employees);
	}

	private static void printEmployee(List<Employee> employees) {
		System.out.println("\nEmployee Details:");
		for (Employee employee : employees) {
	        System.out.println(employee);
	    }
	}

	private static void readEmployee(List<Employee> employees) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Number of Employees : ");
		int number = scanner.nextInt();
		scanner.nextLine();
		
		for(int i = 0; i < number; i++) {
			System.out.print("Enter Id : ");
			int id = scanner.nextInt();
			scanner.nextLine();
			
			System.out.print("Enter Name : ");
			String name = scanner.nextLine();
			
			System.out.print("Enter Salary : ");
			double salary = scanner.nextDouble();
			
			employees.add(new Employee(id, name, salary));
		}
	}

}
