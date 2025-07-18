package com.tss.tictactoe;

public class Game {
	private char[][] board;
	private Player[] players;
	private int currentPlayerIndex;
	private boolean gameOver;

	public Game(String name1, String name2, char symbol1, char symbol2) {
		players = new Player[] { new Player(name1, symbol1), new Player(name2, symbol2) };
		board = new char[3][3];
		resetGame();
	}

	public boolean makeMove(int row, int col) {
		if (gameOver || row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ')
			return false;

		board[row][col] = getCurrentPlayer().getSymbol();

		if (checkWin() || checkDraw()) {
			gameOver = true;
		} else {
			currentPlayerIndex = 1 - currentPlayerIndex;
		}

		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void resetGame() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';
		gameOver = false;
		currentPlayerIndex = 0;
	}

	private boolean checkWin() {
		char s = getCurrentPlayer().getSymbol();

		for (int i = 0; i < 3; i++)
			if (board[i][0] == s && board[i][1] == s && board[i][2] == s)
				return true;

		for (int j = 0; j < 3; j++)
			if (board[0][j] == s && board[1][j] == s && board[2][j] == s)
				return true;

		return (board[0][0] == s && board[1][1] == s && board[2][2] == s)
				|| (board[0][2] == s && board[1][1] == s && board[2][0] == s);
	}

	private boolean checkDraw() {
		for (char[] row : board)
			for (char cell : row)
				if (cell == ' ')
					return false;
		return true;
	}
}
