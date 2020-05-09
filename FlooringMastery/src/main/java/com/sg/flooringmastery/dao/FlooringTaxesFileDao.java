/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author board
 */
public class FlooringTaxesFileDao implements FlooringTaxesDao {
    //Dao is read only

    String taxesPath;

    public FlooringTaxesFileDao(String taxesPath) {
        this.taxesPath = taxesPath;
    }

    @Override
    public List<Taxes> getAllTaxes() throws FlooringDaoException {
        List<Taxes> allTaxes = new ArrayList<>();

        try {
            Scanner reader = new Scanner(new BufferedReader(new FileReader(taxesPath)));

            //consume the header row
            reader.nextLine();

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                Taxes parsed = parseTaxes(line);

                allTaxes.add(parsed);

            }

            reader.close();

        } catch (FileNotFoundException ex) {
            throw new FlooringDaoException("Could not open file: " + taxesPath, ex);
        }

        return allTaxes;
    }

    private Taxes parseTaxes(String line) {

        Taxes parsed = new Taxes();

        String[] cells = line.split(",");

        parsed.setState(cells[0]);
        parsed.setStateName(cells[1]);
        parsed.setTaxRate(new BigDecimal(cells[2]));

        return parsed;

    }

}
