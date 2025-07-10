package com.tss.model;

public class InvoiceViolateClass {
	private int id;
	private String description;
	private double cost;
	private double discountPercent;
	private static final double PERCENT = 10;
	
	public InvoiceViolateClass(int id, String description, double cost, double discountPercent) {
		super();
		this.id = id;
		this.description = description;
		this.cost = cost;
		this.discountPercent = discountPercent;
	}

	public int getId() {
		return id;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	public double calculateTax() {
		return (cost * PERCENT) / 100; 
	}
	
	public double calculateDiscount() {
		return (cost * discountPercent) / 100;
	}
	
	public double calculateTotal() {
		return cost + calculateTax() - calculateDiscount();
	}
	
	public void printInvoice() {
        System.out.println("ID\tDescription\tAmount\t\tTotal Tax(%)\tDiscount Percentage\tFinal Amount");
        System.out.println("--\t-----------\t------\t\t------------\t-------------------\t------------");

        System.out.println(id + "\t" + description + "\t\t" + cost + "\t\t" + calculateTax() + "\t\t" + discountPercent + "\t\t\t" + calculateTotal());
    }
}
