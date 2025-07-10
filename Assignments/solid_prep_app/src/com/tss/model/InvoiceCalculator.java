package com.tss.model;

public class InvoiceCalculator {

	private static final double PERCENT = 10;

	public double calculateDiscount(Invoice invoice) {
		return (invoice.getCost() * invoice.getDiscountPercent()) / 100;
	}

	public double calculateTaxOnDiscountedPrice(Invoice invoice) {
		double discountedPrice = invoice.getCost() - calculateDiscount(invoice);
		return (discountedPrice * PERCENT) / 100;
	}

	public double calculateTotal(Invoice invoice) {
		double discountedPrice = invoice.getCost() - calculateDiscount(invoice);
		return discountedPrice + calculateTaxOnDiscountedPrice(invoice);
	}

	public double getTaxPercent() {
		return PERCENT;
	}
}
