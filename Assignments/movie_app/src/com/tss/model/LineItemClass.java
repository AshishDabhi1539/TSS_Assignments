package com.tss.model;

public class LineItemClass {
	private int id;
	private int quantity;
	private ProductClass product;
	
	public LineItemClass(int id, int quantity, ProductClass product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductClass getProduct() {
		return product;
	}

	public void setProduct(ProductClass product) {
		this.product = product;
	}
	
	public double calculateLineItemCost() {
		return quantity * product.calculateDiscountPrice();
	}
}
