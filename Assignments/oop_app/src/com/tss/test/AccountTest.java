package com.tss.test;

import java.util.Scanner;

import com.tss.model.Account;
import com.tss.model.AccountType;

public class AccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Account account = null;
		int choice;

		do {
			System.out.println("\nEnter your choice");
			System.out.println("1. Create Account");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposite");
			System.out.println("4. Withdraw");
			System.out.println("5. Exit");

			System.out.print("Enter Your Choice : ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Account ID: ");
				int accountId = scanner.nextInt();

				System.out.print("Enter Account Number: ");
				int accountNumber = scanner.nextInt();

				scanner.nextLine();
				System.out.print("Enter Holder Name: ");
				String holderName = scanner.nextLine();

				System.out.print("Enter Initial Balance: ");
				int accounBalance = scanner.nextInt();

				scanner.nextLine();
				System.out.print("Enter Account Type 1. savings 2. current : ");
				int typeInput = scanner.nextInt();

				AccountType accountType = null;

				switch (typeInput) {
				    case 1:
				        accountType = AccountType.SAVINGS;
				        break;
				    case 2:
				        accountType = AccountType.CURRENT;
				        break;
				    default:
				        System.out.println("Invalid account type entered.");
				        return;
				}
				account = new Account(accountId, accountNumber, holderName, accounBalance, accountType);
				System.out.println("Account created successfully.");
				break;

			case 2:
				if (account != null) {
					System.out.println("Current Balance: " + account.getAccountBalance());
				} else {
					System.out.println("No account found. Please create an account first.");
				}
				break;

			case 3:
				if (account != null) {
					System.out.print("Enter amount to deposit: ");
					double depositAmount = scanner.nextDouble();

					account.setAccountBalance(account.getAccountBalance() + depositAmount);
					System.out.println("Amount deposited successfully.");
				} else {
					System.out.println("No account found. Please create an account first.");
				}
				break;

			case 4:
				if (account != null) {
					System.out.print("Enter amount to withdraw: ");
					double withdrawAmount = scanner.nextDouble();

					double currentBalance = account.getAccountBalance();

					if (withdrawAmount <= currentBalance - 500) {
						account.setAccountBalance(currentBalance - withdrawAmount);
						System.out.println("Amount withdrawn successfully.");
					} else {
						System.out.println("Withdrawal denied. You must maintain a minimum balance of 500.");
					}

				} else {
					System.out.println("No account found. Please create an account first.");
				}
				break;

			case 5:
				System.out.println("Exiting...");
				break;

			default:
				System.out.println("Invalid choice. Try again.");
			}
		} while (choice != 5);

	}
}
