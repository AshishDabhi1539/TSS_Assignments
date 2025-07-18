package com.tss.tictactoe;

public class GameLogic {
	private final GameBoard board;

	public GameLogic(GameBoard board) {
		this.board = board;
	}

	public boolean checkWin(char symbol) {
		char[][] b = board.getBoard();

		for (int i = 0; i < 3; i++) {
			if (b[i][0] == symbol && b[i][1] == symbol && b[i][2] == symbol)
				return true;
			if (b[0][i] == symbol && b[1][i] == symbol && b[2][i] == symbol)
				return true;
		}

		return (b[0][0] == symbol && b[1][1] == symbol && b[2][2] == symbol)
				|| (b[0][2] == symbol && b[1][1] == symbol && b[2][0] == symbol);
	}

	public boolean isDraw() {
		return board.isFull();
	}
}