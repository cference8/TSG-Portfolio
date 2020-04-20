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
public class Game {
    
    
    private Integer id;
    private Integer targetNumber;
    private boolean isComplete;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the targetNumber
     */
    public Integer getTargetNumber() {
        return targetNumber;
    }

    /**
     * @param targetNumber the targetNumber to set
     */
    public void setTargetNumber(Integer targetNumber) {
        this.targetNumber = targetNumber;
    }

    /**
     * @return the isComplete
     */
    public boolean getComplete() {
        return isComplete;
    }

    /**
     * @param isComplete the isComplete to set
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }


    
}
