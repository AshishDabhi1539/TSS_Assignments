package com.tss.model.inheritance.model;

public class Vehicle {
	private String model;
	private int year, price;
	
	public Vehicle(String model, int year, int price) {
		this.model = model;
		this.year = year;
		this.price = price;
	}

	public void startEngine(){
		System.out.println("Engine Started...");
	}
	
	public void stopEngine() {
		System.out.println("Engine Stopped...");
	}
	
	public void displayInfo() {
		System.out.println("Model :" + model);
		System.out.println("Year : " + year);
		System.out.println("Price : " + price);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
