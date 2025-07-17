package com.tss.test;

import java.util.Arrays;
import java.util.List;

import com.tss.model.Product;

public class ProductTest {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Pizza", 250.0, 2),
            new Product("Burger", 120.0, 3),
            new Product("Soda", 60.0, 4)
        );

        System.out.println("--- Individual Product Totals ---");
        products.forEach(p -> 
            System.out.println(p.getName() + " -> " + p.getPrice() + " x " + p.getQuantity() + " = " + p.getTotal())
        );

        double totalBill = products.stream()
                .map(Product::getTotal)
                .reduce(0.0, Double::sum);

        System.out.println("\nTotal Bill Amount: " + totalBill);
    }
}
