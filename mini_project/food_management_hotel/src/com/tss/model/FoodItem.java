package com.tss.model;

import java.util.Comparator;

public class FoodItem implements Comparable<FoodItem> {

	private int id;
	private String name;
	private double price;

	public FoodItem(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public int compareTo(FoodItem other) {
		// TODO Auto-generated method stub
		return Double.compare(this.price, other.price);
	}

	public static Comparator<FoodItem> nameComparator() {
		return Comparator.comparing(FoodItem::getName);
	}

	@Override
	public String toString() {
		return id + ". " + name + " - " + price + " rupees";
	}

}
