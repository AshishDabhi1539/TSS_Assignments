package com.tss.tictactoe;

import java.util.Scanner;

public class Main {
	private static final String[] ROW_LABELS = { "A", "B", "C" };
	private static final int MAX_ATTEMPTS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Welcome to Tic Tac Toe! ===");

		System.out.print("Enter Player 1 name: ");
		String name1 = scanner.nextLine().trim();

		System.out.print("Enter Player 2 name: ");
		String name2 = scanner.nextLine().trim();

		boolean playAgain;
		do {
			char symbol1 = 'X';
			int attempts = 0;
			while (attempts < MAX_ATTEMPTS) {
				System.out.print(name1 + ", choose your symbol (X/O): ");
				String input = scanner.nextLine().trim().toUpperCase();
				if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
					symbol1 = input.charAt(0);
					break;
				}
				attempts++;
				if (attempts < MAX_ATTEMPTS) {
					System.out.println(
							"Invalid input! Please enter X or O. " + (MAX_ATTEMPTS - attempts) + " attempts left.");
				} else {
					System.out.println("Max attempts reached! Defaulting to X for " + name1 + ".");
				}
			}

			char symbol2 = (symbol1 == 'X') ? 'O' : 'X';
			System.out.println(name2 + ", your symbol is " + symbol2);

			GameFacade game = new GameFacade(name1, name2, symbol1, symbol2);
			printBoard(game.getBoardState());

			boolean lastPlayerSkipped = false;
			while (!game.isGameOver()) {
				Player current = game.getCurrentPlayer();
				int moveAttempts = 0;
				boolean validMove = false;
				boolean currentPlayerSkipped = false;

				while (!validMove && moveAttempts < MAX_ATTEMPTS) {
					try {
						System.out
								.print(current.getName() + " (" + current.getSymbol() + ") - Enter cell (e.g., A1): ");
						String move = scanner.nextLine().trim().toUpperCase();

						if (move.length() != 2 || move.charAt(0) < 'A' || move.charAt(0) > 'C' || move.charAt(1) < '1'
								|| move.charAt(1) > '3') {
							throw new IllegalArgumentException("Invalid cell format. Use A1 to C3.");
						}

						int row = move.charAt(0) - 'A';
						int col = move.charAt(1) - '1';

						if (!game.makeMove(row, col)) {
							System.out.println("Cell already taken or out of range! Try again.");
						} else {
							printBoard(game.getBoardState());
							validMove = true;
						}
					} catch (Exception e) {
						moveAttempts++;
						if (moveAttempts < MAX_ATTEMPTS) {
							System.out
									.println(e.getMessage() + " " + (MAX_ATTEMPTS - moveAttempts) + " attempts left.");
						} else {
							System.out.println("Max attempts reached! Move skipped for " + current.getName() + ".");
							validMove = true;
							currentPlayerSkipped = true;
							game.makeMove(-1, -1); // Dummy move to switch players
						}
					}
				}

				if (currentPlayerSkipped && lastPlayerSkipped) {
					System.out.println("Both players skipped their moves! Game ends in a draw.");
					break;
				}
				lastPlayerSkipped = currentPlayerSkipped;

				if (game.isGameOver()) {
					break;
				}
			}

			Player winner = game.getWinner();
			if (winner != null) {
				System.out.println("Yoooo! Player " + winner.getName() + " wins!");
			} else if (!lastPlayerSkipped) {
				System.out.println("It's a draw!");
			}

			playAgain = false;
			int playAgainAttempts = 0;
			while (playAgainAttempts < MAX_ATTEMPTS) {
				System.out.print("\nDo you want to play again? (yes/no): ");
				String playAgainInput = scanner.nextLine().trim().toLowerCase();
				if (playAgainInput.equals("yes")) {
					playAgain = true;
					break;
				} else if (playAgainInput.equals("no")) {
					playAgain = false;
					break;
				} else {
					playAgainAttempts++;
					if (playAgainAttempts < MAX_ATTEMPTS) {
						System.out.println("Invalid input! Please enter 'yes' or 'no'. "
								+ (MAX_ATTEMPTS - playAgainAttempts) + " attempts left.");
					} else {
						System.out.println("Max attempts reached! Exiting game.");
						playAgain = false;
					}
				}
			}

		} while (playAgain);

		System.out.println("Thanks for playing!");
		scanner.close();
	}

	private static void printBoard(char[][] board) {
		System.out.println("\n    1   2   3");
		System.out.println("  -------------");
		for (int i = 0; i < 3; i++) {
			System.out.print(ROW_LABELS[i] + " | ");
			for (int j = 0; j < 3; j++) {
				System.out.print((board[i][j] == '-' ? ' ' : board[i][j]) + " | ");
			}
			System.out.println("\n  -------------");
		}
	}
}