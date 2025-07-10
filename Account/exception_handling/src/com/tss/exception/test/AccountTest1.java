package com.tss.exception.test;

import java.util.Scanner;

import com.tss.exception.model.Account;
import com.tss.exception.model.CurrentAccount;
import com.tss.exception.model.SavingsAccount;

public class AccountTest1 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		Account account = null;
		int choice;
		do {
			System.out.println("\nBanking Options");
			System.out.println("1. Create Current Account");
			System.out.println("2. Create Savings Account");
			System.out.println("3. Credit Amount");
			System.out.println("4. Debit Amount");
			System.out.println("5. Display Account Details");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");
			
			try {
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Acc No: ");
				int currentAccNo = scanner.nextInt();

				scanner.nextLine();
				System.out.print("Enter Name: ");
				String currentAccName = scanner.nextLine();

				System.out.print("Enter Balance: ");
				double currentAccBalance = scanner.nextDouble();

				System.out.print("Enter Overdraft Limit: ");
				double limit = scanner.nextDouble();

				account = new CurrentAccount(currentAccNo, currentAccName, currentAccBalance, limit);
				System.out.println("Current account created.");
				break;

			case 2:
				System.out.print("Enter Acc No: ");
				int savingAccNo = scanner.nextInt();

				scanner.nextLine();
				System.out.print("Enter Name: ");
				String savingName = scanner.nextLine();

				System.out.print("Enter Balance: ");
				double savingBalance = scanner.nextDouble();

				System.out.print("Enter Minimum Balance: ");
				double minBal = scanner.nextDouble();

				account = new SavingsAccount(savingAccNo, savingName, savingBalance, minBal);
				System.out.println("Savings account created.");
				break;

			case 3:
			    if (account != null) {
			        System.out.print("Enter amount to credit: ");
			        double amount = scanner.nextDouble();
			        account.credit(amount); // Uses overridden method
			    } else {
			        System.out.println("Create an account first.");
			    }
			    break;
				
			case 4:
			    if (account != null) {
			        System.out.print("Enter amount to debit: ");
			        double amount = scanner.nextDouble();
			        account.debit(amount);
			    } else {
			        System.out.println("Create an account first.");
			    }
			    break;
                
			case 5:
                if (account != null) {
                    account.displayDetails();
                } else {
                    System.out.println("No account to display.");
                }
                break;

            case 6:
                System.out.println("Exiting...");
                System.exit(0);

            default:
                System.out.println("Invalid choice!");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			scanner.nextLine();
			choice = 0;
		}
		} while (true);
	}
}
