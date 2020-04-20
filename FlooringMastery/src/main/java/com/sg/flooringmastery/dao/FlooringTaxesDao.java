/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Taxes;
import java.util.List;

/**
 *
 * @author board
 */
public interface FlooringTaxesDao {
    //DO NOT HARDCODE
    //Dao is read only
    
    public List<Taxes> getAllTaxes() throws FlooringDaoException; 
    
    
}
