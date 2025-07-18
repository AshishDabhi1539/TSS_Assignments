package com.tss.model;

import java.util.ArrayList;
import java.util.List;

import com.tss.service.OrderService;

public class Order {
	private final int orderId;
	private final Customer customer;
	private final List<OrderItem> items = new ArrayList<>();
	private int starRating;
	private String feedbackNote;

	public Order(int orderId, Customer customer) {
		this.orderId = orderId;
		this.customer = customer;
		this.starRating = 0;
		this.feedbackNote = "";
	}

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

	public double getTotal() {
		return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
	}

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

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}

	public String getFeedbackNote() {
		return feedbackNote;
	}

	public void setFeedbackNote(String feedbackNote) {
		this.feedbackNote = feedbackNote;
	}

	@Deprecated
	public String getFeedback() {
		return feedbackNote;
	}

	@Deprecated
	public void setFeedback(String feedback) {
		this.feedbackNote = feedback;
	}
}