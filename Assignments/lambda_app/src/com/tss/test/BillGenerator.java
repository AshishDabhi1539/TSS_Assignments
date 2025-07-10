package com.tss.test;

import java.util.function.Function;
import java.util.function.Supplier;

import com.tss.model.Item;

public class BillGenerator {

	public static void main(String[] args) {
		Supplier<Double> shippingChargeSupplier = () -> 250.0;

		Function<Item, String> generateBill = (item) -> {
			double basePrice = item.getPrice();
			double tax = basePrice * 0.18;
			double shipping = shippingChargeSupplier.get();
			double finalAmount = basePrice + tax + shipping;

			return "------ Bill Details ------\n" + "Item Name   : " + item.getName() + "\n" + "Base Price  : "
					+ basePrice + "\n" + "Tax (18%)   : " + tax + "\n" + "Shipping    : " + shipping + "\n\n"
					+ "Final Amount: " + finalAmount;
		};

		Item item = new Item("DELL Laptop", 65000);
		String bill = generateBill.apply(item);
		System.out.println(bill);
	}

}
