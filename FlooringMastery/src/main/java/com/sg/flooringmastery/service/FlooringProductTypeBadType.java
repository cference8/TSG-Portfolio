/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author board
 */
public class FlooringProductTypeBadType extends Exception {

    public FlooringProductTypeBadType(String message) {
        super(message);
    }

    public FlooringProductTypeBadType(String message, Throwable cause) {
        super(message, cause);
    }
}
