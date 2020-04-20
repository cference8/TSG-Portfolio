/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author board
 */
public class ConsoleIO implements UserIO{

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String prompt) {
        print( prompt );
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        
        return userInput;            
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid) {
            String userInput = readString(prompt);
            try{
                toReturn = Integer.parseInt(userInput);
                isValid = true;
            }catch (NumberFormatException e) {
                
            }
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            toReturn = readInt(prompt);
            
            if ( toReturn >= min && toReturn <= max ) {
                isValid = true;
            }
        }
        
        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            String userInput = readString(prompt);
            
            try{
                toReturn = Double.parseDouble(userInput);
                isValid = true;
            } catch( NumberFormatException e ) {
                
            }    
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Double toReturn = Double.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            toReturn = readDouble(prompt);
            
            if ( toReturn >= min && toReturn <= max ) {
                isValid = true;
            }
        }
        
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            String userInput = readString(prompt);
            
            try{
                toReturn = Long.parseLong(userInput);
                isValid = true;
            } catch( NumberFormatException e ) {
                
            }    
        }
        
        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Long toReturn = Long.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            toReturn = readLong(prompt);
            
            if ( toReturn >= min && toReturn <= max ) {
                isValid = true;
            }
        }
        
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            String userInput = readString(prompt);
            
            try{
                toReturn = Float.parseFloat(userInput);
                isValid = true;
            } catch( NumberFormatException e ) {
                
            }    
        }
        
        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.MIN_VALUE;
        
        boolean isValid = false;
        
        while(!isValid){
            
            toReturn = readFloat(prompt);
            
            if ( toReturn >= min && toReturn <= max ) {
                isValid = true;
            }
        }
        
        return toReturn;
    }

    @Override
    public String editString(String prompt, String oldString) {
        String newString = oldString;
        
        String userInput = readString(prompt);
        if(!userInput.isEmpty()) {
            newString = userInput;
        }
            
            return newString;
    }
    


    @Override
    public int editInt(String prompt, int oldValue) {
        int toReturn = Integer.MIN_VALUE;
       
        boolean isValid = false;
        
        while(!isValid){
        
            String userInput = readString(prompt);
            
            if(userInput.isEmpty()){
                toReturn = oldValue;
                isValid = true;
            }else{
                try{
                    toReturn = Integer.parseInt(userInput);
                    isValid = true;
                }catch(NumberFormatException e){
                    
                    
                }
            }
            
        }
        return toReturn;
    }    

    
}       
    
    
    



