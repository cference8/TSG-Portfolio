/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author board
 */
public interface FlooringOrderDao {

    //File format is Orders_MMDDYYYY.txt
    
    public List<Order> getAllOrdersByDate(LocalDate date) throws FlooringDaoException;

    public Order createOrder(Order orderToAdd) throws FlooringDaoException;

    public void editOrder(Order edited) throws FlooringDaoException, InvalidOrderNumber;

    public void deleteOrder(Order orderToDelete) throws FlooringDaoException;

    public Order getOrder(LocalDate date, int orderNum) throws FlooringDaoException;

    public void exportAll() throws FlooringDaoException;

}
