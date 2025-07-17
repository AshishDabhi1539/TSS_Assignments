package com.tss.model;

import java.util.ArrayList;
import java.util.List;

import com.tss.service.OrderService;

public class Order {
	private final int orderId;
	private final Customer customer;
	private final List<OrderItem> items = new ArrayList<>();

	public Order(int orderId, Customer customer) {
		this.orderId = orderId;
		this.customer = customer;
	}

	/**
	 * Adds an item to the order.
	 * 
	 * @param item The OrderItem to add.
	 */
	public void addItem(OrderItem item, OrderService orderService) {
		item.setCartId(orderService.generateCartId());
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
				return true;
			}
		}
		return false;
	}
}