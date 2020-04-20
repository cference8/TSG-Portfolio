/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author board
 */
public class Order {
    
    private int orderNumber;   
    
    private LocalDate date;
    private String customerName; 
   
    private String state;
    private BigDecimal taxRate;  
    private String productType; 
    private BigDecimal area;
    
    private BigDecimal matUnitCost;     //material cost per square foot
    private BigDecimal laborUnitCost;   //labor cost per sqaure foot
    
     
    public BigDecimal calcMatCost(){
        return matUnitCost.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal calcLaborCost(){
        return laborUnitCost.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }
    
    public BigDecimal calcTaxes(){
        return calcMatCost()
                .add(calcLaborCost())
                .multiply(taxRate)
                .divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
    }
    
    public BigDecimal calcTotal(){
        return calcMatCost()
                .add(calcLaborCost())
                .add(calcTaxes()).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the date
     */
    public LocalDate getLocalDate() {
        return date;
    }


    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the productName
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productName to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the matUnitCost
     */
    public BigDecimal getMatUnitCost() {
        return matUnitCost;
    }

    /**
     * @param matUnitCost the matUnitCost to set
     */
    public void setMatUnitCost(BigDecimal matUnitCost) {
        this.matUnitCost = matUnitCost;
    }

    /**
     * @return the laborUnitCost
     */
    public BigDecimal getLaborUnitCost() {
        return laborUnitCost;
    }

    /**
     * @param laborUnitCost the laborUnitCost to set
     */
    public void setLaborUnitCost(BigDecimal laborUnitCost) {
        this.laborUnitCost = laborUnitCost;
    }
    
}
