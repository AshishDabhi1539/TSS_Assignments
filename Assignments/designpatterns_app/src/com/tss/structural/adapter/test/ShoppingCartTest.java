package com.tss.structural.adapter.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.structural.adapter.model.Biscuit;
import com.tss.structural.adapter.model.Chocolate;
import com.tss.structural.adapter.model.Hat;
import com.tss.structural.adapter.model.HatAdapter;
import com.tss.structural.adapter.model.IItems;
import com.tss.structural.adapter.model.ShoppingCart;

public class ShoppingCartTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        List<IItems> tempItems = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== Shopping Cart Menu =====");
            System.out.println("1. Add Biscuit");
            System.out.println("2. Add Chocolate");
            System.out.println("3. Add Hat");
            System.out.println("4. Display Cart");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    tempItems.add(new Biscuit("Biscuits", 10));
                    System.out.println("Biscuit added to cart.");
                    break;
                case 2:
                    tempItems.add(new Chocolate("Chocolate", 20));
                    System.out.println("Chocolate added to cart.");
                    break;
                case 3:
                    Hat hat = new Hat("Hat", "BrandX", 50, 5);
                    tempItems.add(new HatAdapter(hat));
                    System.out.println("Hat added to cart.");
                    break;
                case 4:
                    if (tempItems.isEmpty() && cart.getCartItems().isEmpty()) {
                        System.out.println("Cart is empty. Please add some items first.");
                    } else {
                        if (!tempItems.isEmpty()) {
                            cart.addItemsToCart(tempItems);
                            tempItems.clear();
                        }
                        cart.displayCart();
                    }
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
