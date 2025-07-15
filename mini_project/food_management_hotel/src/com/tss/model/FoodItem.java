package com.tss.model;

import java.io.Serializable;
import java.util.Comparator;

public class FoodItem implements Comparable<FoodItem>, Serializable {
	private static final long serialVersionUID = 1L;

	public enum Cuisine {
		INDIAN, ITALIAN
	}

	private int id;
	private String name;
	private double price;
	private Cuisine cuisine;

	public FoodItem(int id, String name, double price, Cuisine cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.cuisine = cuisine;
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

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
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
		return id + ". " + name + " - " + price + " rupees (" + cuisine + ")";
	}

}
