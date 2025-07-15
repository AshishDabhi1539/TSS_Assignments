package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicStreamTest {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		/*numbers.stream()
				.forEach((number) -> System.out.print(number + " "));
		
		System.out.println();
		
		numbers.stream()
				.forEach((number) -> System.out.print(number * number + " "));
		
		System.out.println();
		
		Stream<Integer> numberStram = numbers.stream();
		// numberStram.forEach((number) -> System.out.print(number + " "));
		numberStram.forEach((number) -> System.out.print(number * number + " "));
		
		System.out.println();
		
		List<Integer> oddNumbers = numbers.stream()
											.filter(number -> number % 2 != 0)
											.collect(Collectors.toList());
		System.out.println(oddNumbers);

		Set<Integer> squaredNumbers = numbers.stream()
												.filter(number -> number % 2 == 0)
												.map(number -> number * number)
												.collect(Collectors.toSet());
		System.out.println(squaredNumbers);
		
		int sum = numbers.stream().reduce(0, (number1, number2) -> number1 + number2);
		System.out.println(sum);
		
		System.out.println(numbers.stream().count());*/
		
		System.out.print("Limit 5: ");
        numbers
            .stream()
            .limit(5)
            .forEach(number -> System.out.print(number + " "));
        System.out.println();

        System.out.print("Skip 3: ");
        numbers
            .stream()
            .skip(3)
            .forEach(number -> System.out.print(number + " "));
        System.out.println();

        boolean anyGreaterThanFive = numbers
            .stream()
            .anyMatch(number -> number > 5);
        System.out.println("Any match > 5: " + anyGreaterThanFive);

        boolean allLessThanTen = numbers
            .stream()
            .allMatch(number -> number < 10);
        System.out.println("All match < 10: " + allLessThanTen);

        System.out.print("Sorted: ");
        numbers
            .stream()
            .sorted()
            .forEach(number -> System.out.print(number + " "));
        System.out.println();

        Optional<Integer> minNumber = numbers
            .stream()
            .min(
                Comparator.naturalOrder()
            );
        System.out.println("Min: " + (minNumber.isPresent() ? minNumber.get() : "N/A"));

        Optional<Integer> maxNumber = numbers
            .stream()
            .max(
                Comparator.naturalOrder()
            );
        System.out.println("Max: " + (maxNumber.isPresent() ? maxNumber.get() : "N/A"));
	}
}
