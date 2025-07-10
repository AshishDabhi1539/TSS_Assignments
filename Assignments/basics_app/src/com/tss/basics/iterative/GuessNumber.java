package com.tss.basics.iterative;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {

		int min = 1, max = 100;

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		int randomNumber = random.nextInt(max - min + 1) + 1;
		int userGuess = 0;
		
		System.out.println("Random Number : " + randomNumber);

		System.out.println("Welcome to Game Zone.");
		int count = 1;
		while (userGuess != randomNumber) {
			System.out.print("Enter the number(1 to 100) : ");
			userGuess = scanner.nextInt();

			if(userGuess < 0 || userGuess > 100)
				System.out.println("Please Enter Number From 1 to 100!");
			else if (userGuess < randomNumber) {
				System.out.println("Too low! Try again.");
				count++;
			}

			else if (userGuess > randomNumber) {
				System.out.println("Too high! Try again.");
				count++;
			}
			else {
				System.out.println("Congratulations! You guessed the correct number.");
				System.out.println("number of try : " + count);
			}

			System.out.println();
			if (count > 5) {
				System.out.println("You 5 try over! Game over");
				break;
			}
		}
	}

}