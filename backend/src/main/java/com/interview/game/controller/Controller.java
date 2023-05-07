package com.interview.game.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;

import com.interview.game.service.GameOfLifeService;
import com.interview.game.service.GameOfLifeService.GameResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    private final GameOfLifeService gameOfLifeService;

    public Controller(GameOfLifeService gameOfLifeService) {
        this.gameOfLifeService = gameOfLifeService;
    }

    @GetMapping("/")
    public String index() {
        return "Welcome to the Game of Life!";
    }

    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse newGame(@RequestParam(required = false, defaultValue = "10") int size) {
        return gameOfLifeService.newGame(size);
    }

    @GetMapping("/game/{gameId}")
    public GameResponse getGame(@PathVariable UUID gameId) {
        return gameOfLifeService.getGame(gameId);
    }

    @PostMapping("/game/{gameId}/evolve")
    public GameResponse evolve(@PathVariable UUID gameId) {
        return gameOfLifeService.evolve(gameId);
    }

    @DeleteMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable UUID gameId) {
        gameOfLifeService.deleteGame(gameId);
    }

}