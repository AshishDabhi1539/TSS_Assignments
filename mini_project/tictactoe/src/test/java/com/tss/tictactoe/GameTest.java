package com.tss.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

	private GameFacade game;

	@BeforeEach
	public void setUp() {
		game = new GameFacade("Ashish", "Tushar", 'X', 'O');
	}

	@Test
	public void testFirstPlayerStarts() {
		assertEquals('X', game.getCurrentPlayer().getSymbol());
		assertEquals("Ashish", game.getCurrentPlayer().getName());
	}

	@Test
	public void testPlayerMove() {
		assertTrue(game.makeMove(0, 0));
		assertEquals('X', game.getBoard().getCell(0, 0));
	}

	@Test
	public void testInvalidMoveOnOccupiedCell() {
		assertTrue(game.makeMove(0, 0));
		assertFalse(game.makeMove(0, 0));
	}

	@Test
	public void testDrawCondition() {
		game.makeMove(0, 0);
		game.makeMove(0, 1);
		game.makeMove(0, 2);
		game.makeMove(1, 1);
		game.makeMove(1, 0);
		game.makeMove(1, 2);
		game.makeMove(2, 1);
		game.makeMove(2, 0);
		game.makeMove(2, 2);

		assertTrue(game.isGameOver());
		assertNull(game.getWinner());
	}

	@Test
	public void testWinTopRow() {
		game.makeMove(0, 0);
		game.makeMove(1, 0);
		game.makeMove(0, 1);
		game.makeMove(1, 1);
		game.makeMove(0, 2);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinMiddleRow() {
		game.makeMove(1, 0);
		game.makeMove(0, 0);
		game.makeMove(1, 1);
		game.makeMove(0, 1);
		game.makeMove(1, 2);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinBottomRow() {
		game.makeMove(2, 0);
		game.makeMove(0, 0);
		game.makeMove(2, 1);
		game.makeMove(0, 1);
		game.makeMove(2, 2);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinLeftColumn() {
		game.makeMove(0, 0);
		game.makeMove(0, 1);
		game.makeMove(1, 0);
		game.makeMove(0, 2);
		game.makeMove(2, 0);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinMiddleColumn() {
		game.makeMove(0, 1);
		game.makeMove(0, 0);
		game.makeMove(1, 1);
		game.makeMove(0, 2);
		game.makeMove(2, 1);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinRightColumn() {
		game.makeMove(0, 2);
		game.makeMove(0, 0);
		game.makeMove(1, 2);
		game.makeMove(0, 1);
		game.makeMove(2, 2);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinDiagonalLeftToRight() {
		game.makeMove(0, 0);
		game.makeMove(0, 1);
		game.makeMove(1, 1);
		game.makeMove(0, 2);
		game.makeMove(2, 2);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testWinDiagonalRightToLeft() {
		game.makeMove(0, 2);
		game.makeMove(0, 0);
		game.makeMove(1, 1);
		game.makeMove(1, 0);
		game.makeMove(2, 0);

		assertTrue(game.isGameOver());
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testTurnSwitching() {
		assertEquals('X', game.getCurrentPlayer().getSymbol());
		game.makeMove(0, 0);
		assertEquals('O', game.getCurrentPlayer().getSymbol());
		game.makeMove(1, 1);
		assertEquals('X', game.getCurrentPlayer().getSymbol());
	}

	@Test
	public void testGameEndsAfterWin() {
		game.makeMove(0, 0);
		game.makeMove(1, 0);
		game.makeMove(0, 1);
		game.makeMove(1, 1);
		game.makeMove(0, 2);

		assertTrue(game.isGameOver());
		assertFalse(game.makeMove(2, 2));
	}

	@Test
	public void testWinnerIsReturnedCorrectly() {
		game.makeMove(0, 0);
		game.makeMove(1, 0);
		game.makeMove(0, 1);
		game.makeMove(1, 1);
		game.makeMove(0, 2);
		assertEquals("Ashish", game.getWinner().getName());
	}

	@Test
	public void testResetGame() {
		game.makeMove(0, 0);
		game.resetGame();

		assertEquals('X', game.getCurrentPlayer().getSymbol());
		assertFalse(game.isGameOver());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals('-', game.getBoard().getCell(i, j));
			}
		}
	}
}