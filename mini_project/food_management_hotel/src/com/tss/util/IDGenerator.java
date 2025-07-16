package com.tss.util;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Comparator;

import com.tss.model.FoodItem;
import com.tss.model.DeliveryPartner;
import com.tss.model.Customer;

public class IDGenerator {
    private static AtomicInteger foodItemCounter = new AtomicInteger(1);
    private static AtomicInteger partnerCounter = new AtomicInteger(1);
    private static AtomicInteger customerCounter = new AtomicInteger(1);
    private static AtomicInteger orderCounter = new AtomicInteger(1);

    public static void loadInitialCounters(List<FoodItem> menu, List<DeliveryPartner> partners, List<Customer> customers) {
        foodItemCounter.set(menu.stream().mapToInt(FoodItem::getId).max().orElse(0) + 1);
        partnerCounter.set(partners.stream().mapToInt(DeliveryPartner::getId).max().orElse(0) + 1);
        customerCounter.set(customers.stream().mapToInt(Customer::getId).max().orElse(0) + 1);
    }

    public static int generateFoodItemId() {
        return foodItemCounter.getAndIncrement();
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

    public static void resetFoodItemCounter(List<FoodItem> menu) {
        if (menu == null || menu.isEmpty()) return;
        menu.sort(Comparator.comparingInt(FoodItem::getId));
        for (int i = 0; i < menu.size(); i++) {
            menu.get(i).setId(i + 1);
        }
        foodItemCounter.set(menu.size() + 1);
    }

    public static void resetPartnerCounter(List<DeliveryPartner> partners) {
        if (partners == null || partners.isEmpty()) return;
        partners.sort(Comparator.comparingInt(DeliveryPartner::getId));
        for (int i = 0; i < partners.size(); i++) {
            partners.get(i).setId(i + 1);
        }
        partnerCounter.set(partners.size() + 1);
    }

    public static void resetCustomerCounter(List<Customer> customers) {
        if (customers == null || customers.isEmpty()) return;
        customers.sort(Comparator.comparingInt(Customer::getId));
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).setId(i + 1);
        }
        customerCounter.set(customers.size() + 1);
    }
}