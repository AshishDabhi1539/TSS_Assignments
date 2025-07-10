package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameListCode {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> names = new ArrayList<>();
		
		System.out.println("How many names do you want to enter?");
		int count = scanner.nextInt();
		scanner.nextLine();
		
		for(int i = 0; i < count; i++) {
			System.out.print("Enter Name " + (i + 1) + ": ");
			String name = scanner.nextLine().toLowerCase();
			names.add(name);
		}
		
		System.out.println("\nEnter name to start from :");
		String inputName = scanner.nextLine().toLowerCase();
		
		int index = names.indexOf(inputName);
		
		if(index == -1) {
			System.out.println("Name not found in list");
		}
		else {
			System.out.println(names.get(index)+" -->");
			for(int i = index + 1; i < names.size(); i++) {
				System.out.println("\t" + names.get(i));
			}
		}
	}

}
