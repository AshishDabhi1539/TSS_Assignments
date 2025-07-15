package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;

public class StudentStreamTest {

	public static void main(String[] args) {
		String[] names = { "Jay", "Nimesh", "Mark", "Mahesh", "Ramesh" };

		System.out.print("First 3 sorted: ");
		Arrays.stream(names)
			.limit(3)
			.sorted()
			.forEach(name -> System.out.print(name + " "));
		System.out.println("\n");
		
		System.out.print("First 3 sorted (contain 'a'): ");
		Arrays.stream(names)
			.limit(3)
			.filter(name -> name.toLowerCase().contains("a"))
			.sorted()
			.forEach(name -> System.out.print(name + " "));
		System.out.println("\n");
		
		System.out.print("Sorted in descending order: ");
		Arrays.stream(names)
			.sorted(Comparator.reverseOrder())
			.forEach(name -> System.out.print(name + " "));
		System.out.println("\n");
		
		System.out.print("First 3 characters of each: ");
		Arrays.stream(names)
			.map(name -> name.length() >= 3 ? name.substring(0, 3) : name)
			.forEach(name -> System.out.print(name + " "));
		System.out.println("\n");
		
		System.out.print("Names with <= 4 characters: ");
		Arrays.stream(names)
			.filter(name -> name.length() <= 4)
			.forEach(name -> System.out.print(name + " "));
	}

}
