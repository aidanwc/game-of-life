package com.interview.game.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameOfLifeTests {
    private GameOfLife game;

    @BeforeEach
    public void setUp() {
        game = new GameOfLife(3);

        // set the board to a known state that will allow us to test methods
        boolean[][] board = {
                { true, false, false },
                { false, false, true },
                { false, true, true }
        };

        game.setBoard(board);
    }

    @Test
    public void testIsOnBoardValid() {
        assertTrue(game.isOnBoard(0, 0));
        assertTrue(game.isOnBoard(1, 1));
        assertTrue(game.isOnBoard(2, 1));
    }

    @Test
    public void testIsOnBoardNotValid() {
        assertFalse(game.isOnBoard(-1, 0));
        assertFalse(game.isOnBoard(0, -1));
        assertFalse(game.isOnBoard(3, 0));
        assertFalse(game.isOnBoard(0, 3));
    }

    @Test
    public void testNeighboursCount() {
        assertEquals(0, game.countNeighbors(0, 0));
        assertEquals(4, game.countNeighbors(1, 1));
        assertEquals(2, game.countNeighbors(2, 1));
    }

    @Test
    public void testIsAlive() {
        assertTrue(game.isAlive(0, 0));
        assertFalse(game.isAlive(0, 1));
    }

    private void testEvolve(boolean[][] board, boolean[][] expected) {
        game.setBoard(board);
        game.evolve();
        assertArrayEquals(expected, game.getBoard());
    }

    @Test
    public void testOverPopulation() {
        boolean[][] board = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };

        boolean[][] expected = {
                { true, false, true },
                { false, false, false },
                { true, false, true }
        };

        testEvolve(board, expected);
    }

    @Test
    public void testUnderPopulation() {
        boolean[][] board = {
                { true, false, false },
                { false, true, false },
                { false, false, false }
        };

        boolean[][] expected = {
                { false, false, false },
                { false, false, false },
                { false, false, false }
        };

        testEvolve(board, expected);
    }

    @Test
    public void testReproduction() {
        boolean[][] board = {
                { false, true, false },
                { true, true, false },
                { false, false, false }
        };

        boolean[][] expected = {
                { true, true, false },
                { true, true, false },
                { false, false, false }
        };

        testEvolve(board, expected);
    }

}
