package com.interview.game.service;

import org.springframework.stereotype.Service;
import com.interview.game.model.GameOfLife;

@Service
public class GameOfLifeService {

    private GameOfLife gameOfLife;

    public GameOfLifeService() {
    }

    public boolean[][] newGame(int n) {
        gameOfLife = new GameOfLife(n);
        return gameOfLife.getBoard();
    }

    public boolean[][] getBoard() {
        return gameOfLife.getBoard();
    }

    public boolean[][] evolve() {
        gameOfLife.evolve();
        return gameOfLife.getBoard();

    }

}
