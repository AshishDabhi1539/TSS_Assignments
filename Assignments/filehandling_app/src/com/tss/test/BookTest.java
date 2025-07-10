package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Book;
import com.tss.model.TitleComparator;
import com.tss.model.AuthorComparator;

public class BookTest {
	static List<Book> books = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice;

		do {
			System.out.println("\n1. Add book.");
			System.out.println("2. Issue book by ID.");
			System.out.println("3. Display all available books.");
			System.out.println("4. Display all issued books.");
			System.out.println("5. Return a book.");
			System.out.println("6. Sort Books:");
			System.out.println("   Ascending Order of Author");
			System.out.println("   Descending Order of Title");
			System.out.println("7. Exit");
			System.out.print("\nEnter your choice: ");
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
				sortBooks();
				break;

			case 7:
				System.out.println("Exiting...");
				break;

			default:
				System.out.println("Invalid choice!");
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
	}

	private static void issueBook() {
		System.out.print("Enter Book ID to issue: ");
		int id = scanner.nextInt();
		for (Book book : books) {
			if (book.getId() == id) {
				if (!book.isIssued()) {
					book.setIssued(true);
					System.out.println("Book issued successfully.");
					return;
				} else {
					System.out.println("Book is already issued.");
					return;
				}
			}
		}
		System.out.println("Book not found!");
	}

	private static void displayBooks(boolean issued) {
		System.out.println(issued ? "Issued Books:" : "Available Books:");
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
					return;
				} else {
					System.out.println("Book is not issued.");
					return;
				}
			}
		}
		System.out.println("Book not found!");
	}

	private static void sortBooks() {

		Collections.sort(books, new AuthorComparator());
		System.out.println("Sorted Book in Author List");
		System.out.println("Id\t\tTitle\t\tAuthor\t\tStatus\n");
		for (Book book : books) {
			System.out.println(book);
		}
		
		Collections.sort(books, new TitleComparator());
		System.out.println("Sorted Book in Title List");
		System.out.println("Id\t\tTitle\t\tAuthor\t\tStatus\n");
		for (Book book : books) {
			System.out.println(book);
		}
	}
}
