package com.tss.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tss.model.Customer;
import com.tss.model.DeliveryPartner;
import com.tss.model.FoodItem;

public class IDGenerator {
    private static IDGenerator instance;
    private final Map<String, Integer> cuisineCounters;
    private int partnerCounter;
    private int customerCounter;
    private int orderCounter;
    private int cartCounter;

    private IDGenerator() {
        cuisineCounters = new HashMap<>();
        partnerCounter = 1;
        customerCounter = 1;
        orderCounter = 1;
        cartCounter = 1;
    }

    public static IDGenerator getInstance() {
        if (instance == null) {
            instance = new IDGenerator();
        }
        return instance;
    }

    public int generateFoodItemId(String cuisine) {
        String key = cuisine.toUpperCase();
        int counter = cuisineCounters.getOrDefault(key, 1);
        cuisineCounters.put(key, counter + 1);
        return counter;
    }

    public int generatePartnerId() {
        return partnerCounter++;
    }

    public int generateCustomerId() {
        return customerCounter++;
    }

    public int generateOrderId() {
        return orderCounter++;
    }

    public int generateCartId() {
        return cartCounter++;
    }

    public void resetCuisineCounter(String cuisine, List<FoodItem> items) {
        int maxId = items.stream()
                .filter(item -> item.getCuisine().equalsIgnoreCase(cuisine))
                .mapToInt(FoodItem::getId)
                .max()
                .orElse(0);
        cuisineCounters.put(cuisine.toUpperCase(), maxId + 1);
    }
 
    public void resetPartnerCounter(List<DeliveryPartner> partners) {
        int maxId = partners.stream()
                .mapToInt(DeliveryPartner::getId)
                .max()
                .orElse(0);
        partnerCounter = maxId + 1;
    }

    public void resetCustomerCounter(List<Customer> customers) {
        int maxId = customers.stream()
                .mapToInt(Customer::getId)
                .max()
                .orElse(0);
        customerCounter = maxId + 1;
    }

    public void loadInitialCounters(List<FoodItem> foodItems, List<DeliveryPartner> partners, List<Customer> customers) {
        for (FoodItem item : foodItems) {
            resetCuisineCounter(item.getCuisine(), foodItems);
        }
        resetPartnerCounter(partners);
        resetCustomerCounter(customers);
    }
}