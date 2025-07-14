package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int orderId;
	private Customer customer;
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(int orderId, Customer customer) {
		super();
		this.orderId = orderId;
		this.customer = customer;
	}
	
	private void addItem(OrderItem item) {
		// TODO Auto-generated method stub
		items.add(item);
	}

	public int getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}
	
	public double getTotal() {
		return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
	}
}
