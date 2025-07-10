package com.tss.model;

import java.util.Scanner;

public class AssignmentOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		int x = 20, y = 5;
		int result = x + y;
		System.out.println("\n--- Assignment Operator ---");
		System.out.println("x : " + x + " & y : " + y);
		System.out.println("Assignment result = x + y: " + result);
	}

}
