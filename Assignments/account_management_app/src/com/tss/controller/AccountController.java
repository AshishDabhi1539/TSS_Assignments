package com.tss.controller;

import java.util.Scanner;

import com.tss.model.Account;
import com.tss.service.AccountService;

public class AccountController {
	private final AccountService service = new AccountService();
	private final Scanner scanner = new Scanner(System.in);

	public void addAccount() {
		try {
			System.out.print("Enter Account ID: ");
			int id = Integer.parseInt(scanner.nextLine());

			System.out.print("Enter Name: ");
			String name = scanner.nextLine();

			System.out.print("Enter Balance: ");
			double balance = Double.parseDouble(scanner.nextLine());

			Account account = new Account();
			account.setId(id);
			account.setName(name);
			account.setBalance(balance);

			service.createAccount(account);
		} catch (NumberFormatException e) {
			System.out.println("Invalid number entered.");
		} catch (IllegalArgumentException e) {
			System.out.println("Validation failed: " + e.getMessage());
		}
	}
	
	public void transferFunds() {
	    try {
	        System.out.print("Enter Sender ID: ");
	        int senderId = Integer.parseInt(scanner.nextLine());

	        System.out.print("Enter Receiver ID: ");
	        int receiverId = Integer.parseInt(scanner.nextLine());

	        System.out.print("Enter Amount to Transfer: ");
	        double amount = Double.parseDouble(scanner.nextLine());

	        if (amount <= 0) {
	            System.out.println("Amount must be positive.");
	            return;
	        }

	        boolean success = service.transferFunds(senderId, receiverId, amount);
	        if (!success) {
	            System.out.println("Transfer failed.");
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("Invalid number format.");
	    }
	}

	public void showAllAccounts() {
		for (Account acc : service.getAllAccounts()) {
			System.out.println("ID: " + acc.getId() + ", Name: " + acc.getName() + ", Balance: " + acc.getBalance());
		}
	}

	public void close() {
		service.closeConnection();
	}
}
