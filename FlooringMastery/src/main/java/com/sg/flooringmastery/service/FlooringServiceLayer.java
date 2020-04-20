/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringDaoException;
import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringTaxesDao;
import com.sg.flooringmastery.dao.InvalidOrderNumber;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.ProductInfo;
import com.sg.flooringmastery.model.Taxes;
import com.sg.flooringmastery.view.FlooringViewException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author board
 */
public class FlooringServiceLayer {

    FlooringOrderDao orderDao;
    FlooringProductDao productDao;
    FlooringTaxesDao taxesDao;

    public FlooringServiceLayer(FlooringOrderDao orderDao, FlooringProductDao productDao, FlooringTaxesDao taxesDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxesDao = taxesDao;
    }

    public List<Order> getAllOrdersByDate(LocalDate date) throws FlooringDaoException {
        return orderDao.getAllOrdersByDate(date);
    }

    public Order createOrder(Order order) throws FlooringDaoException, FlooringProductTypeBadType {

        BigDecimal min = new BigDecimal("100.00");
        BigDecimal max = new BigDecimal("1000000.00");
        BigDecimal taxRate = new BigDecimal("0.0");
        BigDecimal area = order.getArea();
        List<ProductInfo> products = productDao.getAllProductInfo();
         String orderProduct = order.getProductType();
            boolean containProduct = false;
            
            for (ProductInfo pi : products) {
                if (pi.getProductType().equals(orderProduct)) {
                    containProduct = true;
                }
            }
            if (!containProduct) {
                throw new FlooringProductTypeBadType("Product type does not exist");
            }
        
        List<Taxes> tax = taxesDao.getAllTaxes();

        if (area.compareTo(min) >= 0 && area.compareTo(max) <= 0) {
            for (Taxes t : tax) {
                if (t.getState().equals(order.getState())) {
                    taxRate = t.getTaxRate();
                }
            }
            for (ProductInfo pi : products) {
                if (pi.getProductType().equals(order.getProductType())) {
                    BigDecimal matCostSqFt = (pi.getMaterialCostPerSquareFoot());
                    BigDecimal laborCostSqFt = (pi.getLaborCostPerSquareFoot());
                    order.setTaxRate(taxRate.setScale(2, RoundingMode.HALF_UP));
                    order.setMatUnitCost(matCostSqFt.setScale(2, RoundingMode.HALF_UP));
                    order.setLaborUnitCost(laborCostSqFt.setScale(2, RoundingMode.HALF_UP));
                }
            }
        }

        orderDao.createOrder(order);

        return order;
    }

    public Order getOrder(LocalDate date, int orderNum) throws FlooringDaoException {
        return orderDao.getOrder(date, orderNum);
    }

    public void editOrder(Order edited) throws FlooringDaoException, InvalidOrderNumber {
        BigDecimal taxRate = new BigDecimal("0.0");
        BigDecimal area = edited.getArea();
        BigDecimal min = new BigDecimal("100.00");
        BigDecimal max = new BigDecimal("1000000.00");
        List<ProductInfo> products = productDao.getAllProductInfo();
        List<Taxes> tax = taxesDao.getAllTaxes();

        if (area.compareTo(min) >= 0 && area.compareTo(max) <= 0) {
            //advanced forloop to get the taxes in the Taxes.txt file = order object state
            for (Taxes t : tax) {
                if (t.getState().equals(edited.getState())) {
                    taxRate = t.getTaxRate();
                }
            }
            //advanced forloop to get the productType in the product.txt file = order object productType
            for (ProductInfo pi : products) {
                if (pi.getProductType().equals(edited.getProductType())) {
                    BigDecimal matCostSqFt = (pi.getMaterialCostPerSquareFoot());
                    BigDecimal laborCostSqFt = (pi.getLaborCostPerSquareFoot());
                    edited.setTaxRate(taxRate.setScale(2, RoundingMode.HALF_UP));
                    edited.setMatUnitCost(matCostSqFt.setScale(2, RoundingMode.HALF_UP));
                    edited.setLaborUnitCost(laborCostSqFt.setScale(2, RoundingMode.HALF_UP));
                }
            }
        }

        orderDao.editOrder(edited);
    }

    public void deleteOrder(Order order) throws FlooringDaoException {
        orderDao.deleteOrder(order);
    }

    public void exportAllData() throws FlooringDaoException {
        orderDao.exportAll();
        
    }
}
