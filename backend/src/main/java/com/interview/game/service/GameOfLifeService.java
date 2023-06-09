package com.interview.game.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.interview.game.model.GameOfLife;

@Service
public class GameOfLifeService {

    private final Map<UUID, GameOfLife> games;

    public record GameRequest(boolean[][] board) {
    }

    public record GameResponse(UUID gameId, boolean[][] board) {
    }

    @Autowired
    public GameOfLifeService(Map<UUID, GameOfLife> games) {
        this.games = games;
    }

    public GameResponse newGame(int n) {
        UUID gameId = UUID.randomUUID();
        GameOfLife gameOfLife = new GameOfLife(n);
        games.put(gameId, gameOfLife);
        return new GameResponse(gameId, gameOfLife.getBoard());
    }

    public GameResponse newCustomGame(boolean[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Board cannot be null");
        }
        if (board.length != board[0].length) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Board must have same height and width.");
        }

        UUID gameId = UUID.randomUUID();
        GameOfLife gameOfLife = new GameOfLife(board);
        games.put(gameId, gameOfLife);
        return new GameResponse(gameId, gameOfLife.getBoard());
    }

    public GameResponse getGame(UUID gameId) {
        GameOfLife gameOfLife = games.get(gameId);
        if (gameOfLife == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return new GameResponse(gameId, gameOfLife.getBoard());
    }

    public GameResponse evolve(UUID gameId) {
        GameOfLife gameOfLife = games.get(gameId);
        if (gameOfLife == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        gameOfLife.evolve();
        return new GameResponse(gameId, gameOfLife.getBoard());
    }

    public void deleteGame(UUID gameId) {
        games.remove(gameId);
    }

}
