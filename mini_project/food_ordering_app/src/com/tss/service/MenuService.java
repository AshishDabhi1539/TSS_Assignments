package com.tss.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tss.model.FoodItem;

public class MenuService {
    private List<FoodItem> menu;

    /**
     * Sets the menu for the service.
     * @param menu The list of food items.
     */
    public void setMenu(List<FoodItem> menu) {
        this.menu = menu;
    }

    /**
     * Gets all food items in the menu.
     * @return List of food items.
     */
    public List<FoodItem> getAllMenu() {
        return menu;
    }

    /**
     * Gets food items by cuisine.
     * @param cuisine The cuisine to filter by.
     * @return List of food items for the specified cuisine.
     */
    public List<FoodItem> getByCuisine(String cuisine) {
        List<FoodItem> filtered = menu.stream()
                .filter(i -> i.getCuisine().equalsIgnoreCase(cuisine))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            System.out.println("❌ No items available in this cuisine.");
        }
        return filtered;
    }

    /**
     * Gets a food item by its ID.
     * @param id The ID of the food item.
     * @return The food item or null if not found.
     */
    public FoodItem getItemById(int id) {
        return menu.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}