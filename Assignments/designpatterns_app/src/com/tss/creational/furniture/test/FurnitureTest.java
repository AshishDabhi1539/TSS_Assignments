package com.tss.creational.furniture.test;

import java.util.Scanner;

import com.tss.creational.furniture.model.FurnitureFactory;
import com.tss.creational.furniture.model.IFurniture;

public class FurnitureTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter style (Modern / Victorian):");
        String style = scanner.nextLine();

        // Create and print Chair
        IFurniture chair = FurnitureFactory.getFurniture(style, "Chair");
        if (chair != null) chair.describe();

        // Create and print Sofa
        IFurniture sofa = FurnitureFactory.getFurniture(style, "Sofa");
        if (sofa != null) sofa.describe();

        // Create and print Table
        IFurniture table = FurnitureFactory.getFurniture(style, "Table");
        if (table != null) table.describe();
	}
}
