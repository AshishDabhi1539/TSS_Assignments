package com.tss.structural.facade.model;

public class ItalianMenu implements IMenu {

	@Override
	public void displayMenu() {
		System.out.println("\n--- Italian Menu ---");
		System.out.println("Pizza");
		System.out.println("Pasta");
		System.out.println("Risotto");
	}

}
