/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dto;

import java.time.LocalDateTime;

/**
 *
 * @author board
 */
public class Round {
    
    
    private Integer roundId;
    private Integer gameId;
    private Integer guess;
    private LocalDateTime timeOfGuess = LocalDateTime.now();
    private Integer exactOfGuess;
    private Integer partialOfGuess;

    /**
     * @return the roundId
     */
    public Integer getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the gameId
     */
    public Integer getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the guess
     */
    public Integer getGuess() {
        return guess;
    }

    /**
     * @param guess the guess to set
     */
    public void setGuess(Integer guess) {
        this.guess = guess;
    }

    /**
     * @return the timeOfGuess
     */
    public LocalDateTime getTimeOfGuess() {
        return timeOfGuess;
    }

    /**
     * @param timeOfGuess the timeOfGuess to set
     */
    public void setTimeOfGuess(LocalDateTime timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }

    /**
     * @return the exactOfGuess
     */
    public Integer getExactOfGuess() {
        return exactOfGuess;
    }

    /**
     * @param exactOfGuess the exactOfGuess to set
     */
    public void setExactOfGuess(Integer exactOfGuess) {
        this.exactOfGuess = exactOfGuess;
    }

    /**
     * @return the partialOfGuess
     */
    public Integer getPartialOfGuess() {
        return partialOfGuess;
    }

    /**
     * @param partialOfGuess the partialOfGuess to set
     */
    public void setPartialOfGuess(Integer partialOfGuess) {
        this.partialOfGuess = partialOfGuess;
    }



        
}
