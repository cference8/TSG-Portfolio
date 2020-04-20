/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

import com.sg.GuessTheNumber.dto.Game;
import com.sg.GuessTheNumber.dto.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sg.GuessTheNumber.dao.GtnGameDao;
import com.sg.GuessTheNumber.dao.GtnRoundDao;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author board
 */
@Service
public class ServiceLayer {

    int roundCount = 0;

    @Autowired
    GtnGameDao gameDao;
    @Autowired
    GtnRoundDao roundDao;

    static Random rng = new Random();

    public ServiceLayer(GtnGameDao gameTest, GtnRoundDao roundTest) {
        this.gameDao = gameTest;
        this.roundDao = roundTest;
    }

    public Game startGame() {

        int target = GenerateUniqueRandom();
        Game toAdd = gameDao.addGame(target);
        if (toAdd.getComplete() == false) {
            toAdd.setTargetNumber(null);
        }

        return toAdd;
    }

    public List<Game> getAllGames() {

        List<Game> toReturn = gameDao.getAllGames();

        for (Game game : toReturn) {
            if (game.getComplete() == false) {
                game.setTargetNumber(null);
            }
        }

        return toReturn;
    }

    public Game getGameById(Integer id) {

        Game toReturn = gameDao.getGameById(id);

        if (toReturn.getComplete() == false) {
            toReturn.setTargetNumber(null);
        }

        return toReturn;

    }

    public List<Round> getAllRoundsById(Integer gameId) {

        return roundDao.getAllRoundsById(gameId);

    }

    public Round makeGuess(Integer gameId, Integer guess) throws NotUniqueNumberException, NumberOutOfBoundsException{
        Round toReturn = new Round();
        int exact = 0;
        int partial = 0;
        Integer numHigh = 9999;
        Integer numLow = 100;
        boolean toCheck = isUnique(guess);

        if (toCheck == false) {
            throw new NotUniqueNumberException("Guess is not a unique 4 digit(0-9) number");
        } else if (guess > numHigh || guess < numLow) {
            throw new NumberOutOfBoundsException("Guess is not a 4 digit(0-9) number");
        } 

        Game game = gameDao.getGameById(gameId);
        
        Integer targetNum = game.getTargetNumber();

        int[] guessDigits = separate(guess);
        int[] targetDigits = separate(targetNum);

        for (int i = 0; i < targetDigits.length; i++) {
            int checkGuess = guessDigits[i];
            for (int j = 0; j < guessDigits.length; j++) {
                int target = targetDigits[j];
                if (target == checkGuess && checkGuess != targetDigits[i]) {
                    partial++;
                }
            }
            if (checkGuess == targetDigits[i]) {
                exact++;
            }

        }

        if (game.getComplete() == false) {
            toReturn.setGameId(gameId);
            toReturn.setGuess(guess);
            toReturn.setExactOfGuess(exact);
            toReturn.setPartialOfGuess(partial);
            toReturn.setTimeOfGuess(toReturn.getTimeOfGuess());
            roundDao.addRound(toReturn);
        }

        if (exact == 4) {
            gameDao.endGame(gameId);
        }

        return toReturn;
    }

    private int[] separate(Integer x) {
        int ones = x % 10;
        int tens = (x % 100) / 10;
        int hundreds = (x % 1000) / 100;
        int thousands = (x % 10000) / 1000;

        int[] digits = {thousands, hundreds, tens, ones};

        return digits;
    }

    private boolean isUnique(int x) {

        // say x = 1234
        boolean unique = true;

        int onesPlace = x % 10;
        int tensPlace = (x / 10) % 10;
        int hundredsPlace = (x / 100) % 10;
        int thousandsPlace = (x / 1000) % 10;

        if (onesPlace == tensPlace || onesPlace == hundredsPlace || onesPlace == thousandsPlace) {
            unique = false;
        }

        if (tensPlace == hundredsPlace || tensPlace == thousandsPlace) {
            unique = false;
        }

        if (hundredsPlace == thousandsPlace) {
            unique = false;
        }

        return unique;
    }

    private int GenerateUniqueRandom() {
        int toReturn = -1;

        List<Integer> availableDigits = new ArrayList<>();
        availableDigits.add(0);
        availableDigits.add(1);
        availableDigits.add(2);
        availableDigits.add(3);
        availableDigits.add(4);
        availableDigits.add(5);
        availableDigits.add(6);
        availableDigits.add(7);
        availableDigits.add(8);
        availableDigits.add(9);

        toReturn = 0;

        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int index = rng.nextInt(availableDigits.size());
            toReturn += availableDigits.get(index);
            availableDigits.remove(index);

        }

        return toReturn;
    }

}
