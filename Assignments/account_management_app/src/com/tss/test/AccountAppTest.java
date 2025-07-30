package com.tss.test;

import java.util.Scanner;

import com.tss.controller.AccountController;

public class AccountAppTest {
	public static void main(String[] args) {
		AccountController controller = new AccountController();
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n--- Account Management ---");
			System.out.println("1. Add Account");
			System.out.println("2. View All Accounts");
			System.out.println("3. Transfer Funds");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1 -> controller.addAccount();
			case 2 -> controller.showAllAccounts();
			case 3 -> controller.transferFunds();
			case 0 -> {
				controller.close();
				System.out.println("Exiting...");
			}
			default -> System.out.println("Invalid choice.");
			}
		} while (choice != 0);

		scanner.close();
	}
}
