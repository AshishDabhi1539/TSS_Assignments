package com.tss.basics.selectionstatements;

import java.util.Scanner;

public class TreasureIslanbd {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Direction Right or Left : ");
		String direction = scanner.nextLine();

		if (direction.equalsIgnoreCase("left")) {
			System.out.print("Enter Swim or Wait : ");
			String condition = scanner.nextLine();

			if (condition.equalsIgnoreCase("Swim")) {
				System.out.print("Enter Door Colour (Red/Blue/Yellow/Anything) : ");
				String colour = scanner.nextLine();

				if (colour.equalsIgnoreCase("Yellow")) {
					System.out.println("You Win!");
				} else if (colour.equalsIgnoreCase("Red")) {
					System.out.println("Burned by fire Game Over...");
				} else if (colour.equalsIgnoreCase("Blue")) {
					System.out.println("Eaten by beasts. Game Over...");
				} else {
					System.out.println("Game Over...");
				}
			} else {
				System.out.println("Attacked by trout Game Over...");
			}
		} else {
			System.out.println("You fall in the hole Game Over...");
		}

	}

}
