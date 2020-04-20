/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author board
 */
public interface UserIO {

    void print(String msg);

    //add a readDate date formatter
    LocalDate readLocalDate(String prompt);

    LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max);

    String readString(String prompt);
    
    public BigDecimal editBigDecimal(String prompt, BigDecimal oldBigDecimal);

    String editString(String prompt, String oldString);

    int editInt(String prompt, int oldValue);

    BigDecimal readBigDecimal(String prompt);

    BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

}
