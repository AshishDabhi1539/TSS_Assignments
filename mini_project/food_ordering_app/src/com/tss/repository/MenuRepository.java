package com.tss.repository;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.FoodItem;
import com.tss.util.SerializationUtil;

public class MenuRepository {
    private static final String MENU_FILE = "menu.ser";
    private List<FoodItem> menu;

    public MenuRepository() {
        this.menu = SerializationUtil.readList(MENU_FILE);
        if (menu == null) {
            menu = new ArrayList<>();
        }
    }

    /**
     * Gets all food items in the menu.
     * @return List of food items.
     */
    public List<FoodItem> getAll() {
        return menu;
    }

    /**
     * Adds a food item to the menu.
     * @param item The food item to add.
     */
    public void add(FoodItem item) {
        menu.add(item);
        save();
    }

    /**
     * Updates a food item in the menu.
     * @param item The updated food item.
     */
    public void update(FoodItem item) {
        menu.removeIf(f -> f.getId() == item.getId() && f.getCuisine().equalsIgnoreCase(item.getCuisine()));
        menu.add(item);
        save();
    }

    /**
     * Removes a food item from the menu.
     * @param id The ID of the food item.
     * @param cuisine The cuisine of the food item.
     * @return True if removed, false otherwise.
     */
    public boolean remove(int id, String cuisine) {
        boolean removed = menu.removeIf(f -> f.getId() == id && f.getCuisine().equalsIgnoreCase(cuisine));
        if (removed) {
            save();
        }
        return removed;
    }

    /**
     * Saves the menu to persistent storage.
     */
    public void save() {
        SerializationUtil.saveList(menu, MENU_FILE);
    }
}