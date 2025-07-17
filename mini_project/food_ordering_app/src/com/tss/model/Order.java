package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private final int orderId;
	private final Customer customer;
	private final List<OrderItem> items = new ArrayList<>();
	private static int nextCartId = 1;

	public Order(int orderId, Customer customer) {
		this.orderId = orderId;
		this.customer = customer;
	}

	/**
	 * Adds an item to the order.
	 * 
	 * @param item The OrderItem to add.
	 */
	public void addItem(OrderItem item) {
		item.setCartId(nextCartId++);
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

	/**
	 * Calculates the total order amount.
	 * 
	 * @return The total amount.
	 */
	public double getTotal() {
		return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
	}

	/**
	 * Removes an item from the order or reduces its quantity.
	 * 
	 * @param cartId           The cart ID of the item.
	 * @param quantityToRemove The quantity to remove.
	 * @return True if the item was removed or updated, false otherwise.
	 */
	public boolean removeItem(int cartId, int quantityToRemove) {
		for (OrderItem item : items) {
			if (item.getCartId() == cartId) {
				if (quantityToRemove >= item.getQuantity()) {
					items.remove(item);
				} else {
					item.setQuantity(item.getQuantity() - quantityToRemove);
				}
				reassignCartIds();
				return true;
			}
		}
		return false;
	}

	private void reassignCartIds() {
		int newCartId = 1;
		for (OrderItem item : items) {
			item.setCartId(newCartId++);
		}
		nextCartId = newCartId;
	}
}