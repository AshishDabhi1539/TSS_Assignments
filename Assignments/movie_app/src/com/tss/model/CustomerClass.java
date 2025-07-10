package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerClass {
	private int id;
	private String name;
	private List<OrderClass> orders = new ArrayList<>();
	
	public CustomerClass(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderClass> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderClass> orders) {
		this.orders = orders;
	}
	
	public void addOrder(OrderClass order) {
		orders.add(order);
	}
}
