package com.tss.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tss.model.FoodItem;
import com.tss.model.FoodItem.Cuisine;

public class MenuService {
	private List<FoodItem> menu;
	
	public MenuService(List<FoodItem> menu) {
		super();
		this.menu = menu;
	}
	
	public List<FoodItem> getAllMenu() {
		return menu;
	}

	public List<FoodItem> getByCuisine(Cuisine cuisine) {
		List<FoodItem> filtered = menu.stream()
			.filter(i -> i.getCuisine() == cuisine)
			.collect(Collectors.toList());
		if (filtered.isEmpty()) {
			System.out.println("❌ No items available in this cuisine.");
		}
		return filtered;
	}


	public FoodItem getItemById(int id) {
		return menu.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
	}
}