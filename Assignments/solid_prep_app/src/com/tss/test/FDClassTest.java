package com.tss.test;

import java.util.Scanner;

import com.tss.model.DiwaliInterest;
import com.tss.model.FixedDepositCalculator;
import com.tss.model.FixedDepositClass;
import com.tss.model.FixedDepositPrinter;
import com.tss.model.HoliInterest;
import com.tss.model.IFestivalInterest;
import com.tss.model.NewYearInterest;
import com.tss.model.OtherInterest;

public class FDClassTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FixedDepositCalculator calculator = new FixedDepositCalculator();
		FixedDepositPrinter printer = new FixedDepositPrinter();

		System.out.print("How many Fixed Deposits do you want to create? ");
		int n = scanner.nextInt();
		scanner.nextLine();

		for (int i = 1; i <= n; i++) {
			System.out.println("\nEnter details for Fixed Deposit " + i + ":");

			System.out.print("Enter Account Number: ");
			int accNo = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Enter Name: ");
			String name = scanner.nextLine();

			System.out.print("Enter Principal Amount: ");
			double principal = scanner.nextDouble();

			System.out.print("Enter Duration (in years): ");
			int duration = scanner.nextInt();
			scanner.nextLine();

			System.out.println("Select Festival Type:\n1. New Year\n2. Diwali\n3. Holi\n4. Others\n");
			System.out.print("Enter your choice: ");
			int festivalChoice = scanner.nextInt();
			scanner.nextLine();

			IFestivalInterest interest;
			switch (festivalChoice) {
			case 1:
				interest = new NewYearInterest();
				break;
			case 2:
				interest = new DiwaliInterest();
				break;
			case 3:
				interest = new HoliInterest();
				break;
			default:
				interest = new OtherInterest();
				break;
			}

			FixedDepositClass deposit = new FixedDepositClass(accNo, name, principal, duration, interest);
			printer.printDeposit(deposit, calculator);
		}

	}

}
