package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class GuessTheSeason {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Month : ");
		String month = scanner.nextLine().toLowerCase();
		
		switch(month) {
			case "january" : case "february" : case "march" : case "Apr" : System.out.println("Winter");
					break;
					 
			case "may" : case "june" : case "july" : case "augest" : System.out.println("Summer");
					break;
					
			case "september" : case "october" : case "november" : case "december" : System.out.println("Monsoon");
					break;
			 
			default : System.out.println("Nothing is match"); 
		}
	}

}
