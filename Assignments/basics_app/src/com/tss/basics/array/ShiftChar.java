package com.tss.basics.array;

import java.util.Scanner;

public class ShiftChar {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the string : ");
		String str = scanner.nextLine();

		System.out.print("Enter the shift number : ");
		int number = scanner.nextInt();

		String shiftedStr = shiftedString(str, number);

		System.out.println("Original String : " + str);
		System.out.println("Shifted String : " + shiftedStr);

	}

	private static String shiftedString(String str, int number) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		String result = "";
		number = number % 26;
		if(number < 0)
			number += 26;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int shifted = 0;

			if (c >= 'a' && c <= 'z') {
				shifted = ((c - 'a' + number) % 26) + 'a';
			}
			else if (c >= 'A' && c <= 'Z') {
				shifted = ((c - 'A' + number) % 26) + 'A';
			}
			
			result += (char) shifted;
		}

		return result;
	}

}