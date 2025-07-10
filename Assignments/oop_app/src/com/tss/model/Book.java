package com.tss.model;

public class Book {

	private int bookId;
	private float price;
	private String name,author,publication;
	
	public Book(){
		bookId = 1;
		price = 149;
		name = 	"Animal Farm";
		author = "George Orwell";
		publication = "Secker and Warburg";
	}
	
	public Book(int bookId, float price, String name, String author, String publication) {
		this.bookId = bookId;
		this.price = price;
		this.name = name;
		this.author = author;
		this.publication = publication;
	}
	
	
	public float getDiscount() {
		return price -= (price * 10)/100.0f;
	}
	
	public void display() {
		System.out.println();
		System.out.println("Book Id : " + bookId);
		System.out.println("Book Name : " + name);
		System.out.println("Author : " + author);
		System.out.println("Publication : " + publication);
		System.out.println("Price : " + getDiscount());
	}
	
}
