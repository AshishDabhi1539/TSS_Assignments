package com.tss.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryMenu {
	private final List<Operation> binaryOperations = new ArrayList<>();

	public BinaryMenu() {
		binaryOperations.add(new ArithmeticOperation());
		binaryOperations.add(new RelationalOperation());
		binaryOperations.add(new LogicalOperation());
		binaryOperations.add(new BitwiseOperation());
		binaryOperations.add(new AssignmentOperation());
	}
	
	public void display(Scanner scanner) {
		boolean back = false;

        while (!back) {
            System.out.println("\n--- Binary Operator Types ---");
            System.out.println("1. Arithmetic");
            System.out.println("2. Relational");
            System.out.println("3. Logical");
            System.out.println("4. Bitwise");
            System.out.println("5. Assignment");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose operator type: ");

            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= binaryOperations.size()) {
                binaryOperations.get(choice - 1).execute(scanner);
            } else if (choice == 6) {
                back = true;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
	}
	
}
