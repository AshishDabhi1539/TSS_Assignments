package com.tss.service;

import com.tss.model.Discount;

public class DiscountService {
	private Discount discount;

	public DiscountService(Discount discount) {
		this.discount = discount;
	}

	public DiscountService() {
		this.discount = new Discount(500, 50); // fallback default
	}

	public double getDiscountAmount(double total) {
		return discount.applyDiscount(total);
	}
}
