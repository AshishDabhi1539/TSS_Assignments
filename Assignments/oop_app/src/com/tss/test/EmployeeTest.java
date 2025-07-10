package com.tss.test;

import java.util.Scanner;
import com.tss.model.Employee;
import java.time.LocalDate;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee emp1 = new Employee();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Employee Details.");
		
		System.out.print("Enter EmpId : ");
		int empId = scanner.nextInt();
		
		scanner.nextLine();
		System.out.print("Enter Name : ");
		String empName = scanner.nextLine();
		
		System.out.print("Enter Salary : ");
		double salary = scanner.nextDouble();
		
		scanner.nextLine();
		System.out.print("Enter Joinig Date (yyyy-MM-dd): ");
		String joinigDateStr = scanner.nextLine();
		
		LocalDate joiningDate = null;
		
		try {
			joiningDate = LocalDate.parse(joinigDateStr);
		}catch(Exception e) {
			System.out.println("Enter the Date in the valid formate only.");
			return;
		}
				
		emp1.setEmployeeId(empId);
		emp1.setName(empName);
		emp1.setSalary(salary);
		emp1.setJoiningDate(joiningDate);
		emp1.display();
	}

}
