/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author board
 */
public class ConsoleIO implements UserIO {

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        LocalDate parsedDate = null;
        boolean isValid = false;
        print(prompt);
        Scanner sc = new Scanner(System.in);
        while (!isValid) {           
            String input = sc.nextLine();
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                parsedDate = LocalDate.parse(input, formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                print(prompt);
            }
        }
        return parsedDate;
    }

    @Override
    public LocalDate readLocalDate(String prompt, LocalDate min, LocalDate max) {
        LocalDate toReturn = null;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readLocalDate(prompt);

            if (toReturn.compareTo(min) > 0 && toReturn.compareTo(max) < 0) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
   
        return userInput.trim();
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            try {
                toReturn = Integer.parseInt(userInput);
                isValid = true;
            } catch (NumberFormatException e) {
            }
        }

        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readInt(prompt);

            if (toReturn >= min && toReturn <= max) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            try {
                toReturn = Double.parseDouble(userInput);
                isValid = true;
            } catch (NumberFormatException e) {

            }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Double toReturn = Double.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readDouble(prompt);

            if (toReturn >= min && toReturn <= max) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            try {
                toReturn = Long.parseLong(userInput);
                isValid = true;
            } catch (NumberFormatException e) {

            }
        }

        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Long toReturn = Long.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readLong(prompt);

            if (toReturn >= min && toReturn <= max) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            try {
                toReturn = Float.parseFloat(userInput);
                isValid = true;
            } catch (NumberFormatException e) {

            }
        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readFloat(prompt);

            if (toReturn >= min && toReturn <= max) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {

        BigDecimal toReturn = null;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            try {
                toReturn = new BigDecimal(userInput);
                isValid = true;
            } catch (NumberFormatException e) {

            }
        }

        return toReturn;

    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal toReturn = null;

        boolean isValid = false;

        while (!isValid) {

            toReturn = readBigDecimal(prompt);

            if (toReturn.compareTo(min) >= 0 && toReturn.compareTo(max) <= 0) {
                isValid = true;
            }
        }

        return toReturn;
    }

    @Override
    public String editString(String prompt, String oldString) {
        String newString = oldString;

        String userInput = readString(prompt);
        if (!userInput.isEmpty()) {
            newString = userInput;
        }

        return newString;
    }
    
    @Override
    public BigDecimal editBigDecimal(String prompt, BigDecimal oldBigDecimal) {
        BigDecimal toReturn = oldBigDecimal;

        String userInput = readString(prompt);
        if (!userInput.isEmpty()) {
            toReturn = new BigDecimal(userInput);
        }

        return toReturn;
    }

    @Override
    public int editInt(String prompt, int oldValue) {
        int toReturn = Integer.MIN_VALUE;

        boolean isValid = false;

        while (!isValid) {

            String userInput = readString(prompt);

            if (userInput.isEmpty()) {
                toReturn = oldValue;
                isValid = true;
            } else {
                try {
                    toReturn = Integer.parseInt(userInput);
                    isValid = true;
                } catch (NumberFormatException e) {

                }
            }

        }
        return toReturn;
    }

}
