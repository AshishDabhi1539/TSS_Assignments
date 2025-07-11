package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.AuthorComparator;
import com.tss.model.Book;
import com.tss.model.TitleComparator;

public class BookTest {

	static List<Book> books = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;

		do {
			System.out.println("\n===== Book Management Menu =====");
			System.out.println("1. Add Book");
			System.out.println("2. Issue Book by ID");
			System.out.println("3. Display Available Books");
			System.out.println("4. Display Issued Books");
			System.out.println("5. Return a Book");
			System.out.println("6. Sort Books");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				addBook();
				break;
			case 2:
				issueBook();
				break;
			case 3:
				displayBooks(false);
				break;
			case 4:
				displayBooks(true);
				break;
			case 5:
				returnBook();
				break;
			case 6:
				sortBooksMenu();
				break;
			case 7:
				System.out.println("Exiting... Thank you!");
				break;
			default:
				System.out.println("Invalid choice! Please try again.");
			}

		} while (choice != 7);
	}

	private static void addBook() {
		System.out.print("Enter ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Title: ");
		String title = scanner.nextLine();
		System.out.print("Enter Author: ");
		String author = scanner.nextLine();
		books.add(new Book(id, title, author));
		System.out.println("Book added successfully.");
	}

	private static void issueBook() {
		System.out.print("Enter Book ID to issue: ");
		int id = scanner.nextInt();
		for (Book book : books) {
			if (book.getId() == id) {
				if (!book.isIssued()) {
					book.setIssued(true);
					System.out.println("Book issued successfully.");
				} else {
					System.out.println("Book is already issued.");
				}
				return;
			}
		}
		System.out.println("Book not found!");
	}

	private static void displayBooks(boolean issued) {
		System.out.println(issued ? "\nIssued Books:" : "\nAvailable Books:");
		System.out.println("Id\t\tTitle\t\tAuthor\t\tStatus\n");
		for (Book book : books) {
			if (book.isIssued() == issued) {
				System.out.println(book);
			}
		}
	}

	private static void returnBook() {
		System.out.print("Enter Book ID to return: ");
		int id = scanner.nextInt();
		for (Book book : books) {
			if (book.getId() == id) {
				if (book.isIssued()) {
					book.setIssued(false);
					System.out.println("Book returned successfully.");
				} else {
					System.out.println("Book is not issued.");
				}
				return;
			}
		}
		System.out.println("Book not found!");
	}

	private static void sortBooksMenu() {
		System.out.println("\nSort Books:");
		System.out.println("1. Ascending Order of Author");
		System.out.println("2. Descending Order of Title");
		System.out.print("Enter your choice: ");
		int sortChoice = scanner.nextInt();

		switch (sortChoice) {
		case 1:
			Collections.sort(books, new AuthorComparator());
			System.out.println("\nBooks sorted by Author (Ascending):");
			break;
		case 2:
			Collections.sort(books, new TitleComparator());
			System.out.println("\nBooks sorted by Title (Descending):");
			break;
		default:
			System.out.println("Invalid sort choice.");
			return;
		}

		System.out.println("Id\t\tTitle\t\tAuthor\t\tStatus\n");
		for (Book book : books) {
			System.out.println(book);
		}
	}
}
