package com.tss.basics.iterative;

import java.util.Random;
import java.util.Scanner;

public class PIG {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random randomNumber = new Random();

		int turn = 0;
		int totalScore = 0;

		System.out.println("Welcome to PIG");
		while (turn < 5) {
			turn++;
			int currScore = 0;

			System.out.println("Turn : " + turn);

			while (true) {
				System.out.print("Roll or Hold? (r or h) : ");
				char choice = scanner.next().charAt(0);

				if (choice == 'r') {
					int die = randomNumber.nextInt(6) + 1;
					System.out.println("Die : " + die);

					if (die == 1) {
						System.out.println("Turn over. No score.\n");
						currScore = 0;
						break;
					} else {
						currScore += die;
						System.out.println("Current score : " + currScore + "\n");
						if (totalScore + currScore >= 20) {
							totalScore += currScore;
							System.out.println("You finished game in " + turn + " turns.");
							return;
						}
					}
				} else if (choice == 'h') {
					totalScore += currScore;
					System.out.println("Current score : " + currScore);
					System.out.println("Total Score : " + totalScore + "\n");
					break;
				} else {
					System.out.println("Invalid Input Enter r/h");
				}
			}
		}
		System.out.println("Turn limit over! Game Over...!");
	}

}
