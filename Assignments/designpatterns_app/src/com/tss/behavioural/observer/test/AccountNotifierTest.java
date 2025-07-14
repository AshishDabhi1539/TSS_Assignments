package com.tss.behavioural.observer.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.tss.behavioural.observer.model.Account;
import com.tss.behavioural.observer.model.EmailNotifier;
import com.tss.behavioural.observer.model.InsufficientFundsException;
import com.tss.behavioural.observer.model.SMSNotifier;
import com.tss.behavioural.observer.model.WhatsappNotifier;

public class AccountNotifierTest {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Account> accounts = new LinkedList<>();
    private static Account currentAccount = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Create New Account");
            System.out.println("2. Select Existing Account");
            System.out.println("3. Manage Current Account");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1 -> createNewAccount();
                case 2 -> selectAccount();
                case 3 -> {
                    if (currentAccount == null) {
                        System.out.println("Please select an account first.");
                    } else {
                        manageAccount(currentAccount);
                    }
                }
                case 4 -> showAllAccounts();
                case 5 -> {
                    System.out.println("Thank you for using the system.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void createNewAccount() {
        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        Account account = new Account(accNum, name);
        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    private static void selectAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found. Create one first.");
            return;
        }

        System.out.println("Available Accounts:");
        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " - " + acc.getName());
        }

        System.out.print("Enter account number to select: ");
        int accNum = sc.nextInt();
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNum) {
                currentAccount = acc;
                System.out.println("Account selected: " + acc.getName());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
        } else {
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    private static void manageAccount(Account account) {
        sc.nextLine();
        System.out.println("Select notification modes (separate by commas):");
        System.out.println("1. SMS");
        System.out.println("2. Email");
        System.out.println("3. WhatsApp");
        System.out.print("Enter your choices (e.g., 1,3 for SMS and WhatsApp): ");
        String[] choices = sc.nextLine().split(",");

        for (String choice : choices) {
            switch (choice.trim()) {
                case "1" -> account.registerNotifier(new SMSNotifier());
                case "2" -> account.registerNotifier(new EmailNotifier());
                case "3" -> account.registerNotifier(new WhatsappNotifier());
                default -> System.out.println("Invalid notifier option: " + choice.trim());
            }
        }

        while (true) {
            System.out.println("\n-- Managing Account: " + account.getName() + " (" + account.getAccountNumber() + ")");
            System.out.println("1. Add Notifier");
            System.out.println("2. Remove Notifier");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Account Details");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Add Notifier:");
                    System.out.println("1. SMS");
                    System.out.println("2. Email");
                    System.out.println("3. WhatsApp");
                    int opt = sc.nextInt();
                    switch (opt) {
                        case 1 -> account.registerNotifier(new SMSNotifier());
                        case 2 -> account.registerNotifier(new EmailNotifier());
                        case 3 -> account.registerNotifier(new WhatsappNotifier());
                        default -> System.out.println("Invalid notifier option.");
                    }
                }
                case 2 -> {
                    account.printRegisteredNotifiers();
                    System.out.println("Remove Notifier:");
                    System.out.println("1. SMS");
                    System.out.println("2. Email");
                    System.out.println("3. WhatsApp");
                    int opt = sc.nextInt();
                    switch (opt) {
                        case 1 -> account.removeNotifier("SMS");
                        case 2 -> account.removeNotifier("Email");
                        case 3 -> account.removeNotifier("WhatsApp");
                        default -> System.out.println("Invalid notifier option.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter deposit amount: ");
                    double amount = sc.nextDouble();
                    account.deposit(amount, "");
                }
                case 4 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = sc.nextDouble();
                    try {
                        account.withdraw(amount, "");
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> System.out.println(account);
                case 6 -> {
                    currentAccount = null;
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
