/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.dto;

/**
 *
 * @author board
 */
public class GuessRequest {
    
    private Integer gamesId;
    
    private Integer guess;

    /**
     * @return the gamesId
     */
    public Integer getGamesId() {
        return gamesId;
    }

    /**
     * @param gamesId the gamesId to set
     */
    public void setGamesId(Integer gamesId) {
        this.gamesId = gamesId;
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
}
