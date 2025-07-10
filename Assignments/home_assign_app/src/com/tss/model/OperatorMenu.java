package com.tss.model;

import java.util.Scanner;

public class OperatorMenu {
	private final Operation unaryOperation;
	private final BinaryMenu binaryMenu;
	private final Operation ternaryOperation;
	
	public OperatorMenu() {
		super();
		this.unaryOperation = new UnaryOperation();
		this.binaryMenu = new BinaryMenu();
		this.ternaryOperation = new TernaryOperation();
	}
	
	public void display(Scanner scanner) {
		boolean exit = false;
		
		while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Unary Operators");
            System.out.println("2. Binary Operators");
            System.out.println("3. Ternary Operator");
            System.out.println("4. Exit");
            System.out.print("Enter your choice : ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> unaryOperation.execute(scanner);
                case 2 -> binaryMenu.display(scanner);
                case 3 -> ternaryOperation.execute(scanner);
                case 4 -> {
                    exit = true;
                    System.out.println("Exiting the program...");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
	}
}
