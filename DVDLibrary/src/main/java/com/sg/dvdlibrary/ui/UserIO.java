/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

/**
 *
 * @author board
 */
public interface UserIO {

    void print(String msg);

    String readString(String prompt);
    
    String editString(String prompt, String oldString);
    
    int editInt(String prompt, int oldValue);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

}
