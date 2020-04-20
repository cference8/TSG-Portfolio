/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GuessTheNumber.service;

/**
 *
 * @author board
 */
public class NumberOutOfBoundsException extends Exception {

    public NumberOutOfBoundsException(String message) {
        super(message);
    }

    public NumberOutOfBoundsException(String message, Throwable inner) {
        super(message, inner);
    }

}
