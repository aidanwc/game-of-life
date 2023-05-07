package com.interview.game.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.interview.game.model.GameOfLife;
import com.interview.game.service.GameOfLifeService.GameResponse;

public class GameOfLifeServiceTests {

    private Map<UUID, GameOfLife> games;
    private GameOfLifeService service;

    @BeforeEach
    public void setUp() {
        games = new HashMap<>();
        service = new GameOfLifeService(games);
    }

    @Test
    public void testNewGame() {
        int size = 3;
        GameResponse response = service.newGame(size);
        UUID gameId = response.gameId();
        assertTrue(games.containsKey(gameId));
        assertEquals(size, games.get(gameId).getSize());
    }

    @Test
    public void testGetGame() {
        UUID gameId = UUID.randomUUID();
        GameOfLife gameOfLifeMock = mock(GameOfLife.class);
        when(gameOfLifeMock.getBoard()).thenReturn(new boolean[][] { { true, false }, { false, true } });
        games.put(gameId, gameOfLifeMock);

        GameResponse response = service.getGame(gameId);

        assertEquals(gameId, response.gameId());
        assertEquals(gameOfLifeMock.getBoard(), response.board());
    }

    @Test
    public void testGetGameNotFound() {
        UUID gameId = UUID.randomUUID();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.getGame(gameId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Game not found", exception.getReason());
    }

    @Test
    public void testEvolve() {
        UUID gameId = UUID.randomUUID();
        boolean[][] board = new boolean[][] { { true, false }, { false, true } };
        GameOfLife gameOfLifeMock = mock(GameOfLife.class);
        when(gameOfLifeMock.getBoard()).thenReturn(board);
        games.put(gameId, gameOfLifeMock);

        GameResponse response = service.evolve(gameId);

        assertEquals(gameId, response.gameId());
        assertEquals(board, response.board());
        verify(gameOfLifeMock).evolve();
    }

    @Test
    public void testEvolveNotFound() {
        UUID gameId = UUID.randomUUID();

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.evolve(gameId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Game not found", exception.getReason());
    }

    @Test
    public void testDeleteGame() {
        UUID gameId = UUID.randomUUID();
        games.put(gameId, mock(GameOfLife.class));

        assertEquals(1, games.size());
        service.deleteGame(gameId);
        assertEquals(0, games.size());
    }

}
