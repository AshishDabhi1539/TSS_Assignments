package com.tss.tictactoe;

public class GameFacade {
	private final GameBoard board = new GameBoard();
	private final GameLogic logic = new GameLogic(board);
	private final Player[] players;
	private int currentPlayerIndex;

	public GameFacade(String name1, String name2, char symbol1, char symbol2) {
		this.players = new Player[] { new Player(name1, symbol1), new Player(name2, symbol2) };
		this.currentPlayerIndex = 0;
	}

	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}

	public boolean makeMove(int row, int col) {
		Player player = getCurrentPlayer();
		boolean movePlaced = board.placeMove(row, col, player.getSymbol());
		if (movePlaced)
			currentPlayerIndex = 1 - currentPlayerIndex;
		return movePlaced;
	}

	public boolean hasPlayerWon(Player player) {
		return logic.checkWin(player.getSymbol());
	}

	public boolean isGameOver() {
		return logic.checkWin(players[0].getSymbol()) || logic.checkWin(players[1].getSymbol()) || logic.isDraw();
	}

	public Player getWinner() {
		for (Player player : players) {
			if (logic.checkWin(player.getSymbol())) {
				return player;
			}
		}
		return null;
	}

	public void resetGame() {
		board.reset();
		currentPlayerIndex = 0;
	}

	public char[][] getBoardState() {
		return board.getBoard();
	}

	public GameBoard getBoard() {
		return board;
	}
}
