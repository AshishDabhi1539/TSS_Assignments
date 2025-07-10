package com.tss.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderClass {
	private int id;
	private Date date;
	private List<LineItemClass> items = new ArrayList<>();
	public OrderClass(int id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	
	public void addItem(LineItemClass item) {
		items.add(item);
	}
	
	public double calculateOrderPrice() {
		return items.stream().mapToDouble(LineItemClass::calculateLineItemCost).sum();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<LineItemClass> getItems() {
		return items;
	}

	public void setItems(List<LineItemClass> items) {
		this.items = items;
	}
	
	
}
