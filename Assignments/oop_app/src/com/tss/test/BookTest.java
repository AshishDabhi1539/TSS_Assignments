package com.tss.test;

import java.util.Scanner;
import com.tss.model.Book;

public class BookTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Book book1 = new Book();
		
		book1.display();
		
		Book book2 = null;
		book2 = read(scanner);
		book2.display();
		
		scanner.close();
	}

	private static Book read(Scanner scanner) {
		System.out.println("Enter the book details.");
		
		System.out.print("Enter Book Id : ");
		int bookId = scanner.nextInt();
		
		scanner.nextLine();
		System.out.print("Enter Book Name : ");
		String name = scanner.nextLine();
		
		System.out.print("Enter Book Price : ");
		float price = scanner.nextFloat();
		
		scanner.nextLine();
		System.out.print("Enter Book Author Name : ");
		String author = scanner.nextLine();
		
		System.out.print("Enter Book Publication : ");
		String publication = scanner.nextLine();
		
		return null;
	}
}
