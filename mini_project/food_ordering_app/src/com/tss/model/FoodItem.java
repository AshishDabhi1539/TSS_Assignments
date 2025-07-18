package com.tss.model;

import java.io.Serial;
import java.io.Serializable;

public class FoodItem implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double price;
	private String cuisine;
	private String description;

	public FoodItem(int id, String name, double price, String cuisine) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.cuisine = cuisine;
		this.description = "";
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

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return id + ". " + name + " - " + price + " rupees (" + cuisine + ")";
	}
}