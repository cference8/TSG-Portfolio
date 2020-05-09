/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.controller;

import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.GuessRequest;
import com.sg.GuessTheNumber.dto.Round;
import com.sg.GuessTheNumber.service.NotUniqueNumberException;
import com.sg.GuessTheNumber.service.NumberOutOfBoundsException;
import com.sg.GuessTheNumber.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author board
 */
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    ServiceLayer service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game startGame() {       
        Game toReturn = service.startGame();
        return toReturn;
    }

    @PostMapping("/guess")
    public Round getGuess(@RequestBody GuessRequest request) throws NotUniqueNumberException, NumberOutOfBoundsException {
        Round toReturn = service.makeGuess(request.getGamesId(), request.getGuess());
        return toReturn;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {       
        List<Game> result = service.getAllGames();
        return result;
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable Integer gameId) {      
        Game toReturn = service.getGameById(gameId);       
        return toReturn;
    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getAllRoundsByTime(@PathVariable Integer gameId) {
        List<Round> toReturn = service.getAllRoundsById(gameId);
        return toReturn;
    }

}
