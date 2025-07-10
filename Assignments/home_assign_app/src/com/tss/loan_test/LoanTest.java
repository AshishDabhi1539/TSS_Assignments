package com.tss.loan_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.loan_model.ApprovalPrinter;
import com.tss.loan_model.CustomerClass;
import com.tss.loan_model.LoanCalculator;
import com.tss.loan_model.LoanEligibilityChecker;

public class LoanTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();
        LoanCalculator loanCalculator = new LoanCalculator();
        ApprovalPrinter approvalPrinter = new ApprovalPrinter(loanCalculator);

        List<CustomerClass> customers = new ArrayList<>();

        System.out.print("How many customers do you want to enter? ");
        int numberOfCustomers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("\nEnter details for Customer " + (i + 1));

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();

            System.out.print("Income: ");
            double income = scanner.nextDouble();

            System.out.print("Credit Score: ");
            int creditScore = scanner.nextInt();
            scanner.nextLine();

            CustomerClass customer = new CustomerClass(name, age, income, creditScore);
            customers.add(customer);
        }

        System.out.println("\n-- Final Results --");

        for (CustomerClass customer : customers) {
            if (eligibilityChecker.test(customer)) {
                approvalPrinter.accept(customer);
            } else {
                System.out.println("Loan Rejected for " + customer.getName() + ". Not eligible.\n");
            }
        }
	}

}
