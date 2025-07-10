package com.tss.test;

import java.util.Scanner;

import com.tss.model.ElectricityBill;

public class ElectricityBillTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int choice;
		
		System.out.print("Enter the number of apartments : ");
		int apartments = scanner.nextInt();
		
		ElectricityBill[] bills = new ElectricityBill[apartments];
        int billCount = 0;
        
        System.out.print("Enter initial cost per unit: ");
        double initialRate = scanner.nextDouble();
        
        ElectricityBill.setCostPerUnit(initialRate);
        
		do {
			System.out.println("\nElectricity Bill Menu");
			System.out.println("1. Calculate Bill");
			System.out.println("2. Display the Bill");
			System.out.println("3. Government Rate Change");
			System.out.println("4. Exit ");
			
			System.out.print("Enter Your Choice : ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				if(billCount >= bills.length) {
					System.out.println("Storage Full no more bills");
					break;
				}
				
				System.out.print("Enter Appartment Number : ");
				int appartmentNumber = scanner.nextInt();
				
				System.out.print("Enter Units Consumed : ");
				int units = scanner.nextInt();
				
				bills[billCount] = new ElectricityBill(units, appartmentNumber);
				billCount++;
				System.out.println("Bill calculated and stored...");				
				break;
				
			case 2:
				if(billCount == 0)
					System.out.println("No bills to calculate!");
				else {
					for(int i = 0; i < billCount; i++) {
						System.out.println("\nBill Number = " + (i+1));
						bills[i].displayBill();
					}
				}
				break;
				
			case 3:
				System.out.print("Enter new cost per unit :");
				double newRate = scanner.nextDouble();
				
				ElectricityBill.setCostPerUnit(newRate);
				
				System.out.println("Rate updated successfully...");
				ElectricityBill.displayCurrentRate();
				break;
				
			case 4:
				System.out.println("Exiting...");
				break;
				
			default :
				System.out.println("Invalid choice ! Try Again");
			}
		}while(choice != 4);
	}

}
