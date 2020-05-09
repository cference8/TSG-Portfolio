/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.ProductInfo;
import java.util.List;

/**
 *
 * @author board
 */
public interface FlooringProductDao {
    //Dao is read only
    
    public List<ProductInfo> getAllProductInfo() throws FlooringDaoException;
    
        
}
