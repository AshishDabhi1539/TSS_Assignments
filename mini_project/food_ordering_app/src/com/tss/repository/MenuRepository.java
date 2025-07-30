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

    public List<FoodItem> getAll() {
        return menu;
    }

    public void add(FoodItem item) {
        menu.add(item);
        save();
    }

    public void update(FoodItem item) {
        menu.removeIf(f -> f.getId() == item.getId() && f.getCuisine().equalsIgnoreCase(item.getCuisine()));
        menu.add(item);
        save();
    }

    public boolean remove(int id, String cuisine) {
        boolean removed = menu.removeIf(f -> f.getId() == id && f.getCuisine().equalsIgnoreCase(cuisine));
        if (removed) {
            save();
        }
        return removed;
    }

    public void save() {
        SerializationUtil.saveList(menu, MENU_FILE);
    }
}