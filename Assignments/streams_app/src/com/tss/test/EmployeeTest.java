
package com.tss.test;

import com.tss.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeTest {
	public static void main(String[] args) {
		List<Employee> employees = List.of(new Employee(1, "Tushar", 75000, "HR"),
				new Employee(2, "Ashish", 90000, "Developer"),
				new Employee(3, "Dishant", 90000, "IT"),
				new Employee(4, "Devansh", 60000, "IT"), 
				new Employee(5, "Kuldeep", 85000, "HR"),
				new Employee(6, "Anami", 60000, "Developer"));

		System.out.println("--- Sorted by Salary Desc and Name ---");
		List<Employee> sorted = employees.stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName))
				.collect(Collectors.toList());

		sorted.forEach(System.out::println);

		System.out.println("\n--- Highest Paid Employee per Department ---");
		Map<String, Optional<Employee>> topEmployees = employees.stream().collect(
				Collectors.groupingBy(Employee::getDept, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));

		topEmployees.forEach((dept, empOpt) -> System.out.println(dept + " -> " + empOpt.orElse(null)));
	}
}
