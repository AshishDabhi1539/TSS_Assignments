package com.tss.util;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Comparator;

import com.tss.model.FoodItem;
import com.tss.model.DeliveryPartner;
import com.tss.model.Customer;

public class IDGenerator {
	private static Map<String, AtomicInteger> cuisineCounters = new HashMap<>();
	private static AtomicInteger partnerCounter = new AtomicInteger(1);
	private static AtomicInteger customerCounter = new AtomicInteger(1);
	private static AtomicInteger orderCounter = new AtomicInteger(1);

	public static void loadInitialCounters(List<FoodItem> menu, List<DeliveryPartner> partners,
			List<Customer> customers) {
		menu.forEach(
				item -> cuisineCounters.compute(item.getCuisine(), (k, v) -> v == null ? new AtomicInteger(1) : v));
		partners.forEach(p -> partnerCounter.set(Math.max(partnerCounter.get(), p.getId() + 1)));
		customers.forEach(c -> customerCounter.set(Math.max(customerCounter.get(), c.getId() + 1)));
		// Removed orderCounter.set(1) to persist order IDs across sessions
	}

	public static int generateFoodItemId(String cuisine) {
		return cuisineCounters.computeIfAbsent(cuisine, k -> new AtomicInteger(1)).getAndIncrement();
	}

	public static int generatePartnerId() {
		return partnerCounter.getAndIncrement();
	}

	public static int generateCustomerId() {
		return customerCounter.getAndIncrement();
	}

	public static int generateOrderId() {
		return orderCounter.getAndIncrement();
	}

	public static void resetCuisineCounter(String cuisine, List<FoodItem> items) {
		if (items == null || items.isEmpty())
			return;
		items.sort(Comparator.comparingInt(FoodItem::getId));
		for (int i = 0; i < items.size(); i++) {
			items.get(i).setId(i + 1);
		}
		cuisineCounters.computeIfAbsent(cuisine, k -> new AtomicInteger(1)).set(items.size() + 1);
	}

	public static void resetPartnerCounter(List<DeliveryPartner> partners) {
		if (partners == null || partners.isEmpty())
			return;
		partners.sort(Comparator.comparingInt(DeliveryPartner::getId));
		for (int i = 0; i < partners.size(); i++) {
			partners.get(i).setId(i + 1);
		}
		partnerCounter.set(partners.size() + 1);
	}

	public static void resetCustomerCounter(List<Customer> customers) {
		if (customers == null || customers.isEmpty())
			return;
		customers.sort(Comparator.comparingInt(Customer::getId));
		for (int i = 0; i < customers.size(); i++) {
			customers.get(i).setId(i + 1);
		}
		customerCounter.set(customers.size() + 1);
	}
}