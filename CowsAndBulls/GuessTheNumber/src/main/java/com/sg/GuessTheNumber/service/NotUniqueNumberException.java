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
public class NotUniqueNumberException extends Exception{
    
    public NotUniqueNumberException( String message ){
        super(message);
    }
    
    public NotUniqueNumberException( String message, Throwable inner ){
        super( message, inner );
    }
        
    
}
