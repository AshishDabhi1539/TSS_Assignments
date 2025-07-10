package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class BillCalculation {
	
	public static void billCalculation(float units) {
		float charge;
		int meter_charge = 75;
		
		if(units <= 100) {
			charge = units * 5;
		}else {
			if(units <= 250) {
				charge = units * 10;
			}else {
				charge = units * 20;
			}
		}
		charge = charge + meter_charge;
		
		System.out.println("Total Water Bill = " + charge);
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the number of Units : ");
		float units = scanner.nextFloat();
		
		billCalculation(units);
	}

}
