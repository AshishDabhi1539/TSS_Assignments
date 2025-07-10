package com.tss.test;

public class StringTest {

	public static void main(String[] args) {
		StringBuilder str1 = new StringBuilder("abc");
		StringBuilder str2 = new StringBuilder("xyz");

		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());

		System.out.println(str1 == str2);
		str1 = str2;

		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		System.out.println(str1 == str2);
		System.out.println(str2);
	}
}
