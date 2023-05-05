package com.interview.game.model;

public class GameOfLife {
    private final int size;
    private boolean[][] board;

    public GameOfLife(int size) {
        this.size = size;
        this.board = new boolean[size][size];
        this.randomizeBoard();
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getBoard() {
        return board;
    }

    // Update the board to the next generation based on the rules of the game
    public void evolve() {
        boolean[][] newBoard = new boolean[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int neighbors = countNeighbors(x, y);
                boolean isAlive = isAlive(x, y);

                if (isAlive && (neighbors == 2 || neighbors == 3)) {
                    newBoard[x][y] = true;
                } else if (!isAlive && neighbors == 3) {
                    newBoard[x][y] = true;
                }
            }
        }
        board = newBoard;
    }

    private int countNeighbors(int x, int y) {
        int count = 0;
        int[] cellsToCheck = new int[] { -1, 1 };
        for (int i : cellsToCheck) {
            for (int j : cellsToCheck) {
                int neighborX = x + i;
                int neighborY = y + j;
                if (isOnBoard(neighborX, neighborY) && isAlive(neighborX, neighborY)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isAlive(int x, int y) {
        return board[x][y];
    }

    private boolean isOnBoard(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private void randomizeBoard() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // Set each cell of the board to a random value (either true or false)
                board[x][y] = Math.random() < 0.5;
            }
        }
    }

}
