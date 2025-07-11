package com.tss.structural.facade.model;

public class IndianMenu implements IMenu {

	@Override
	public void displayMenu() {
		System.out.println("\n---Indian Menu---");
		System.out.println("Paneer Tikka");
		System.out.println("Butter Naan");
		System.out.println("Dal Makhani");
	}

}
