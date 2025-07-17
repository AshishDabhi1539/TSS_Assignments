package com.tss.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.FoodItem;

public class IDGenerator {
    private static IDGenerator instance;
    private final Map<String, AtomicInteger> cuisineCounters = new HashMap<>();
    private final AtomicInteger partnerCounter = new AtomicInteger(1);
    private final AtomicInteger customerCounter = new AtomicInteger(1);
    private final AtomicInteger orderCounter = new AtomicInteger(1);

    private IDGenerator() {
    }

    /**
     * Gets the singleton instance of IDGenerator.
     * @return The singleton instance.
     */
    public static IDGenerator getInstance() {
        if (instance == null) {
            synchronized (IDGenerator.class) {
                if (instance == null) {
                    instance = new IDGenerator();
                }
            }
        }
        return instance;
    }

    /**
     * Initializes counters based on existing data.
     * @param menu List of food items.
     * @param partners List of delivery partners.
     * @param customers List of customers.
     */
    public void loadInitialCounters(List<FoodItem> menu, List<DeliveryPartner> partners, List<Customer> customers) {
        menu.forEach(item -> cuisineCounters.compute(item.getCuisine(), (k, v) -> v == null ? new AtomicInteger(1) : v));
        partners.forEach(p -> partnerCounter.set(Math.max(partnerCounter.get(), p.getId() + 1)));
        customers.forEach(c -> customerCounter.set(Math.max(customerCounter.get(), c.getId() + 1)));
    }

    /**
     * Generates a unique food item ID for a given cuisine.
     * @param cuisine The cuisine type.
     * @return The generated ID.
     */
    public int generateFoodItemId(String cuisine) {
        return cuisineCounters.computeIfAbsent(cuisine, k -> new AtomicInteger(1)).getAndIncrement();
    }

    /**
     * Generates a unique delivery partner ID.
     * @return The generated ID.
     */
    public int generatePartnerId() {
        return partnerCounter.getAndIncrement();
    }

    /**
     * Generates a unique customer ID.
     * @return The generated ID.
     */
    public int generateCustomerId() {
        return customerCounter.getAndIncrement();
    }

    /**
     * Generates a unique order ID.
     * @return The generated ID.
     */
    public int generateOrderId() {
        return orderCounter.getAndIncrement();
    }

    /**
     * Resets the cuisine counter for a specific cuisine.
     * @param cuisine The cuisine type.
     * @param items List of food items for the cuisine.
     */
    public void resetCuisineCounter(String cuisine, List<FoodItem> items) {
        if (items == null || items.isEmpty()) return;
        items.sort(Comparator.comparingInt(FoodItem::getId));
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setId(i + 1);
        }
        cuisineCounters.computeIfAbsent(cuisine, k -> new AtomicInteger(1)).set(items.size() + 1);
    }

    /**
     * Resets the partner counter based on the current partners.
     * @param partners List of delivery partners.
     */
    public void resetPartnerCounter(List<DeliveryPartner> partners) {
        if (partners == null || partners.isEmpty()) return;
        partners.sort(Comparator.comparingInt(DeliveryPartner::getId));
        for (int i = 0; i < partners.size(); i++) {
            partners.get(i).setId(i + 1);
        }
        partnerCounter.set(partners.size() + 1);
    }

    /**
     * Resets the customer counter based on the current customers.
     * @param customers List of customers.
     */
    public void resetCustomerCounter(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) return;
        customers.sort(Comparator.comparingInt(Customer::getId));
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).setId(i + 1);
        }
        customerCounter.set(customers.size() + 1);
    }
}