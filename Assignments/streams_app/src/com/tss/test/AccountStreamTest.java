package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Account;

public class AccountStreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		List<Account> accounts = new ArrayList<>();
		
		System.out.print("Enter Numbers of Accounts: ");
		int numbers = scanner.nextInt();
		
		for(int i = 1; i <= numbers; i++) {
			System.out.println("\nEnter details for Account " + i);
			System.out.print("ID : ");
			int id = scanner.nextInt();
			
			System.out.print("Name: ");
			scanner.nextLine();
			String name = scanner.nextLine();
			
			System.out.print("Salary: ");
			double salary = scanner.nextDouble();
			
			accounts.add(new Account(id, name, salary));
		}
		
        Account minAcc = Collections.min(accounts, Comparator.comparing(Account::getSalary));
        System.out.println("\nMinimum Balance Account:");
        System.out.println(minAcc);

        Account maxAcc = Collections.max(accounts, Comparator.comparing(Account::getSalary));
        System.out.println("\nMaximum Balance Account:");
        System.out.println(maxAcc);

        System.out.println("\nNames longer than 6 characters:");
        accounts.stream()
            .filter(acc -> acc.getName().length() > 6)
            .forEach(acc -> System.out.print(acc.getName() + " "));
        System.out.println();

        double totalBalance = accounts.stream()
            .mapToDouble(Account::getSalary)
            .sum();
        System.out.println("\nTotal Balance of All Accounts: " + totalBalance);

        scanner.close();
	}

}
