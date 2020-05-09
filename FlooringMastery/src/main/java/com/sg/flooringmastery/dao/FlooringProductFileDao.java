/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.ProductInfo;
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
public class FlooringProductFileDao implements FlooringProductDao {
    //Dao is read only
    String productPath;
    
    public FlooringProductFileDao(String productPath) {
        this.productPath = productPath;
    }


    @Override
    public List<ProductInfo> getAllProductInfo() throws FlooringDaoException{
        List<ProductInfo> allProducts = new ArrayList<>();

        try {
            Scanner reader = new Scanner(new BufferedReader(new FileReader(productPath)));

            //consume the header row
            reader.nextLine();

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                ProductInfo parsed = parseProduct(line);

                allProducts.add(parsed);

            }

            reader.close();

        } catch (FileNotFoundException ex) {
            throw new FlooringDaoException("Could not open file: " + productPath, ex);
        }

        return allProducts;
    }

    private ProductInfo parseProduct(String line) {

        ProductInfo parsed = new ProductInfo();

        String[] cells = line.split(",");

        parsed.setProductType(cells[0]);
        parsed.setMaterialCostPerSquareFoot(new BigDecimal(cells[1]));
        parsed.setLaborCostPerSquareFoot(new BigDecimal(cells[2]));

        return parsed;

    }

}
