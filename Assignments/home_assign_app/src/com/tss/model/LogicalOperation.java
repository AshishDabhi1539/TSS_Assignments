package com.tss.model;

import java.util.Scanner;

public class LogicalOperation implements Operation {

	@Override
	public void execute(Scanner scanner) {
		boolean condition1 = true, condition2 = false;
        System.out.println("\n--- Logical Operators ---");
        System.out.println("condition1 is " + condition1 + " & condition2 is " + condition2);
        System.out.println("Logical AND: " + (condition1 && condition2));
        System.out.println("Logical OR: " + (condition1 || condition2));
	}

}
