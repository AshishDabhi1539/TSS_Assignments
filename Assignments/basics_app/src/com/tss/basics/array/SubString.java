package com.tss.basics.array;

import java.util.Scanner;

public class SubString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter 1st String : ");
		String str1 = scanner.nextLine().toLowerCase();
		
		System.out.print("Enter 2nd String : ");
		String str2 = scanner.nextLine().toLowerCase();
		
		boolean result = isSubstring(str1,str2);
		
		System.out.println("Result : " + result);
	}

	private static boolean isSubstring(String str1, String str2) {
		int n = str1.length();
		int m = str2.length();
		
		if(m == 0) return true;
		if(m > n) return false;
		
		for(int i = 0; i <= n - m; i++) {
			int j;
			for(j = 0; j < m; j++) {
				if(str1.charAt(i + j) != str2.charAt(j)) 
					break;
			}
			if(j == m) return true;
		}
		
		return false;
	}
	
}
