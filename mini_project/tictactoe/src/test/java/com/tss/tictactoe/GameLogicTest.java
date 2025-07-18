package com.tss.tictactoe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GameLogicTest {
	@Test
	public void testHorizontalWin() {
		GameBoard board = new GameBoard();
		board.placeMove(1, 0, 'X');
		board.placeMove(1, 1, 'X');
		board.placeMove(1, 2, 'X');

		GameLogic logic = new GameLogic(board);
		assertTrue(logic.checkWin('X'));
	}

	@Test
	public void testNoWin() {
		GameBoard board = new GameBoard();
		GameLogic logic = new GameLogic(board);
		assertFalse(logic.checkWin('O'));
	}
}