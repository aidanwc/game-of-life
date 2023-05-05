package com.interview.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.game.service.GameOfLifeService;

@RestController
public class Controller {
    private final GameOfLifeService gameOfLifeService;

    public Controller(GameOfLifeService gameOfLifeService) {
        this.gameOfLifeService = gameOfLifeService;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/game")
    public boolean[][] newGame(@RequestParam int size) {
        return gameOfLifeService.newGame(size);
    }

    @PostMapping("/evolve")
    public boolean[][] evolve() {
        return gameOfLifeService.evolve();
    }

}