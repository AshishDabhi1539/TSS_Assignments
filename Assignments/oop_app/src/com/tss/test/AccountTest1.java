package com.tss.test;

import java.util.Random;
import java.util.Scanner;

import com.tss.model.Account1;
import com.tss.model.AccountType;

public class AccountTest1 {
	private static final Scanner scanner = new Scanner(System.in);
	private static int count = 0;

	public static void main(String[] args) {

		System.out.print("Enter number of Account : ");
		int n = scanner.nextInt();

		Account1 account[] = new Account1[n];

		int choice;
		do {
			System.out.println("\n==== Menu ====");
			System.out.println("1. Create an Account");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposite");
			System.out.println("4. Withdraw");
			System.out.println("5. Transfer");
			System.out.println("6. Exit");
			System.out.println("7. Display All Account Details");

			System.out.print("Enter Your Choice : ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				createAccount(account);
				break;

			case 2:
				viewBalance(account);
				break;

			case 3:
				deposite(account);
				break;

			case 4:
				withdraw(account);
				break;

			case 5:
				transfer(account);
				break;

			case 6:
				System.out.println("Exiting...");
				break;

			case 7:
				if (count == 0) {
					System.out.println("No accounts found");
				} else {
					for (int i = 0; i < count; i++) {
						System.out.println(account[i]);
					}
				}
				break;

			default:
				System.out.println("Invalid choice. Try again.");
			}
		} while (choice != 6);
	}

	private static void transfer(Account1[] account) {
		scanner.nextLine();
		System.out.print("Enter Sender Account Number : ");
		String senderAcc = scanner.nextLine();

		Account1 sender = null, receiver = null;

		for (int i = 0; i < count; i++) {
			if (account[i].getAccountNumber().equals(senderAcc)) {
				sender = account[i];
				break;
			}
		}

		if (sender == null) {
			System.out.println("Sender account not found.");
			return;
		}

		System.out.print("Enter Receiver Account Number : ");
		String receiverAcc = scanner.nextLine();

		if (senderAcc.equals(receiverAcc)) {
			System.out.println("Transfer not possible, both are the same.");
			return;
		}

		for (int i = 0; i < count; i++) {
			if (account[i].getAccountNumber().equals(receiverAcc)) {
				receiver = account[i];
				break;
			}
		}

		if (receiver == null) {
			System.out.println("Receiver account not found.");
			return;
		}

		System.out.print("Enter Amount : ");
		double amount = scanner.nextDouble();

		if (amount <= 0) {
			System.out.println("Amount less than 0.");
		} else if (sender.getAccountBalance() - amount < 500) {
			System.out.println("Transaction failed! Insufficient balance for minimum amounta.");
		} else {
			sender.setAccountBalance(sender.getAccountBalance() - amount);
			receiver.setAccountBalance(receiver.getAccountBalance() + amount);
			System.out.println("Transaction Successfully Done...");
		}

	}

	private static void withdraw(Account1[] account) {
		scanner.nextLine();
		System.out.print("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		for (int i = 0; i < count; i++) {
			if (account[i].getAccountNumber().equals(accountNumber)) {
				System.out.print("Enter amount to withdraw: ");
				double withdrawAmount = scanner.nextDouble();

				double currentBalance = account[i].getAccountBalance();

				if (withdrawAmount <= currentBalance - 500) {
					account[i].setAccountBalance(currentBalance - withdrawAmount);
					System.out.println("Amount withdrawn successfully.");
				} else {
					System.out.println("Withdrawal denied. Because balance is less than 500.");
				}
			} else {
				System.out.println("No account found. Please create an account first.");
			}
		}
	}

	private static void deposite(Account1[] account) {
		scanner.nextLine();
		System.out.print("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (account[i].getAccountNumber().equals(accountNumber)) {
				System.out.print("Enter amount to deposit: ");
				double depositAmount = scanner.nextDouble();

				account[i].setAccountBalance(account[i].getAccountBalance() + depositAmount);
				System.out.println("Amount deposited successfully.");
				found = true;
				break;
			}
		}
		if (!found)
			System.out.println("No account found. Please create an account first.");
	}

	private static void viewBalance(Account1[] account) {
		scanner.nextLine();
		System.out.print("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		boolean found = false;
		for (int i = 0; i < count; i++) {
			if (account[i].getAccountNumber().equals(accountNumber)) {
				System.out.println("Account Holder Name : " + account[i].getHolderName());
				System.out.println("Account Balance : " + account[i].getAccountBalance());
				found = true;
				break;
			}
		}

		if (!found)
			System.out.println("No account found. Please create an account first.");
	}

	private static void createAccount(Account1[] account) {
		if (count >= account.length) {
			System.out.println("Maximum number of accountss reached.");
			return;
		}

		Random generator = new Random();

		String accountNumber = "AXIS1000" + Integer.toString(generator.nextInt(900000) + 100000);

		scanner.nextLine();
		System.out.print("Enter Holder Name: ");
		String holderName = scanner.nextLine();

		System.out.print("Enter Initial Balance: ");
		double accountBalance = scanner.nextInt();
		if (accountBalance < 500) {
			System.out.println("Your initial balance atleast 500");
			return;
		}

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

		account[count] = new Account1(accountNumber, holderName, accountBalance, accountType);
		System.out.println("Account created successfully.");
		System.out.println(account[count]);
		count++;
	}

}
