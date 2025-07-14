package com.tss.model;

public class OrderItem {
	private FoodItem item;
	private int quantity;
	
	public OrderItem(FoodItem item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}

	public FoodItem getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getSubtotal() {
		return item.getPrice() * quantity;
	}
	
}
