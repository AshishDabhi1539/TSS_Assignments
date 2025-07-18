package com.tss.tictactoe;

public class GameBoard {
	private final char[][] board = new char[3][3];

	public GameBoard() {
		reset();
	}

	public boolean placeMove(int row, int col, char symbol) {
		if (isValidPosition(row, col) && board[row][col] == '-') {
			board[row][col] = symbol;
			return true;
		}
		return false;
	}

	public boolean isFull() {
		for (char[] row : board)
			for (char cell : row)
				if (cell == '-')
					return false;
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public char getCell(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			return board[row][col];
		}
		throw new IllegalArgumentException("Invalid cell position: (" + row + ", " + col + ")");
	}

	public void reset() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = '-';
	}

	private boolean isValidPosition(int row, int col) {
		return row >= 0 && row < 3 && col >= 0 && col < 3;
	}
}
