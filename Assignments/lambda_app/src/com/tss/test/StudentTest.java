package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Ashish", 1, 99));
		students.add(new Student("Deep", 2, 72));
		students.add(new Student("Dhyey", 3, 90));
		students.add(new Student("Mahek", 4, 67));
		students.add(new Student("Harshad", 5, 78));
		students.add(new Student("Tushar", 6, 55));
		students.add(new Student("Priyank", 7, 95));
		students.add(new Student("Rishit", 8, 74));
		students.add(new Student("Hemanshi", 9, 88));
		students.add(new Student("Yash", 10, 81));

		Predicate<Student> highScorers = student -> student.getMarks() > 75;

		Consumer<Student> congratulate = student -> System.out.println("Congratulations " + student.getName()
				+ " for scoring " + student.getMarks() + " marks");

		for (Student student : students) {
			if (highScorers.test(student)) {
				congratulate.accept(student);
			}
		}
	}

}
