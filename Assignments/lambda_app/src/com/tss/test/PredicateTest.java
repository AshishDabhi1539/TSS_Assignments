package com.tss.test;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateTest {

	public static void main(String[] args) {
		Predicate<Integer> isEven = number -> number % 2 == 0;

		
        System.out.println("For 10 : " + (isEven.test(10) ? "Even" : "Odd"));
        System.out.println("For 7 : " + (isEven.test(7) ? "Even" : "Odd"));
        
        BiPredicate<String, String> isEqualIgnoreCase = (str1, str2) -> str1.equalsIgnoreCase(str2);

        System.out.println(isEqualIgnoreCase.test("Hello", "hello")); // true
        System.out.println(isEqualIgnoreCase.test("Java", "Python")); // false
	}

}
