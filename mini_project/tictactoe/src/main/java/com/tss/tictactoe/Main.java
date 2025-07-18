package com.tss.tictactoe;

import java.util.Scanner;

public class Main {
	private static final String[] ROW_LABELS = { "A", "B", "C" };

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\uD83C\uDFAE Welcome to Tic Tac Toe!");

		System.out.print("Enter Player 1 name: ");
		String name1 = scanner.nextLine().trim();

		System.out.print("Enter Player 2 name: ");
		String name2 = scanner.nextLine().trim();

		boolean playAgain;
		do {
			char symbol1;
			while (true) {
				System.out.print(name1 + ", choose your symbol (X/O): ");
				String input = scanner.nextLine().trim().toUpperCase();
				if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
					symbol1 = input.charAt(0);
					break;
				}
				System.out.println("❌ Invalid input! Please enter X or O.");
			}

			char symbol2 = (symbol1 == 'X') ? 'O' : 'X';
			System.out.println(name2 + ", your symbol is " + symbol2);

			GameFacade game = new GameFacade(name1, name2, symbol1, symbol2);
			printBoard(game.getBoardState());

			while (!game.isGameOver()) {
				Player current = game.getCurrentPlayer();
				boolean validMove = false;

				while (!validMove) {
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
							System.out.println("⚠️ Cell already taken or out of range! Try again.");
						} else {
							printBoard(game.getBoardState());
							validMove = true;
						}
					} catch (Exception e) {
						System.out.println("❌ " + e.getMessage());
					}
				}
			}

			Player winner = game.getWinner();
			if (winner != null) {
				System.out.println("🎉 " + winner.getName() + " wins!");
			} else {
				System.out.println("🤝 It's a draw!");
			}

			System.out.print("\nDo you want to play again? (yes/no): ");
			playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");

		} while (playAgain);

		System.out.println("👋 Thanks for playing!");
		scanner.close();
	}

	private static void printBoard(char[][] board) {
		System.out.println("\n    1   2   3");
		System.out.println("  -------------");
		for (int i = 0; i < 3; i++) {
			System.out.print(ROW_LABELS[i] + " | ");
			for (int j = 0; j < 3; j++) {
				System.out.print((board[i][j] == ' ' ? ' ' : board[i][j]) + " | ");
			}
			System.out.println("\n  -------------");
		}
	}
}
