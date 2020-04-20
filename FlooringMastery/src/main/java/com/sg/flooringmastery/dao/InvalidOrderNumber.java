/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author board
 */
public class InvalidOrderNumber extends Exception {

    public InvalidOrderNumber(String message) {
        super(message);
    }
    public InvalidOrderNumber(String message, Throwable cause){
        super(message, cause);
    }
}
