/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author board
 */
public class FlooringView {
    private UserIO io;
    
    public FlooringView(UserIO io){
        this.io = io;
    }
    

    public int printMainMenuGetMenuChoice() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * \n");
        io.print("*  <<Flooring Program>>\n");
        io.print("* 1. Display Orders\n");
        io.print("* 2. Add an Order\n");
        io.print("* 3. Edit an Order\n");
        io.print("* 4. Remove an Order\n");
        io.print("* 5. Export All Orders\n");
        io.print("* 6. Quit\n");
        io.print("*\n");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * \n");
        return io.readInt("Please select from the above choices.\n", 1, 6);
        
    }

    public void displayAllOrdersByDate(List<Order> orderList) { 
        for (Order cOrder : orderList) {
            String orderInfo = String.format(
                    "Order Number: %s\n"
                            + "Customer Name: %s\n"
                            + "State: %s\n"
                            + "Tax Rate: %s\n"
                            + "Product Type: %s\n"
                            + "Area: %s\n"
                            + "Material Cost: %s\n"
                            + "Labor Cost: %s\n"
                            + "Material Cost: %s\n"
                            + "Labor Cost: %s\n"
                            + "Tax: %s\n"
                            + "Total Cost: %s\n"
                    ,cOrder.getOrderNumber()
                    ,cOrder.getCustomerName()
                    ,cOrder.getState()
                    ,cOrder.getTaxRate()
                    ,cOrder.getProductType()
                    ,cOrder.getArea()
                    ,cOrder.getMatUnitCost()
                    ,cOrder.getLaborUnitCost()
                    ,cOrder.calcMatCost()
                    ,cOrder.calcLaborCost()
                    ,cOrder.calcTaxes()
                    ,cOrder.calcTotal());
            io.print(orderInfo);
        }
      
    }

    public LocalDate askUserForDate() {
        
        LocalDate toReturn = io.readLocalDate("Please enter valid date mm/dd/yyyy.");
        
        return toReturn;
    }

    public Order getNewOrderInfo() throws FlooringViewException {
        Order newOrder = new Order();
        //Order Date
        LocalDate orderDate = io.readLocalDate("Please enter a future date: ", LocalDate.now(), LocalDate.MAX);
        //Customer Name
        String customerName = io.readString("Please enter your name: ");
        //State
        String state = io.readString("Please enter your State Abbreviation: ");           
        //Product Type
        String productType = io.readString("Please enter product type: ");
        //Area
        BigDecimal area = io.readBigDecimal("Please enter floor area: ", new BigDecimal("100.00"), new BigDecimal("10000000.00"));
        
        //set all above fields
        newOrder.setCustomerName(customerName);
        newOrder.setState(state.toUpperCase());
        newOrder.setProductType(productType.substring(0, 1).toUpperCase() + productType.substring(1).toLowerCase());
        newOrder.setArea(area);
        newOrder.setLocalDate(orderDate);
        
        return newOrder;
                              
    }

    public void displayOrderEnteredSuccessfully() {
        io.print("Order entered Successfully\n");
    }

    public Order editOrder(Order toEdit) {
        Order edited = new Order();
        
        //customerName
        String customerName = io.editString("Enter customer name (or press enter to keep " 
                + toEdit.getCustomerName() + "): ", toEdit.getCustomerName());
        //State
        String state = io.editString("Enter state (or press enter to keep "
                + toEdit.getState() + "): ", toEdit.getState());
        //ProductType
        String productType = io.editString("Enter product type (or press enter to keep " 
                + toEdit.getProductType() + "): ", toEdit.getProductType());
        //Area
        BigDecimal area = io.editBigDecimal("Enter Area (or press enter to keep "
                + toEdit.getArea() + "): ", toEdit.getArea());
        
        //set all above fields
        edited.setLocalDate(toEdit.getLocalDate()); //keep date in object
        edited.setOrderNumber(toEdit.getOrderNumber()); //keep order num in object
        edited.setCustomerName(customerName);
        edited.setState(state);
        edited.setProductType(productType);
        edited.setArea(area);
                
        return edited;            
    }

    public Order displayOrderToRemove(Order toRemove) {
       
        io.print("=== Selected order to remove ===\n");
        io.print("Order Number: " + toRemove.getOrderNumber() + "\n");
        io.print("Customer Name: " + toRemove.getCustomerName() + "\n");
        io.print("State: " + toRemove.getState() + "\n");
        io.print("Product Type: " + toRemove.getProductType() + "\n");
        
        String toDelete = io.readString("Are you sure you want to delete?(Y/N)");
        
        if(toDelete.substring(0).toUpperCase().equals("Y")){
            io.print("Entered '" + toDelete + "' to successfully delete\n");
            return toRemove;
        } else{
            return null;
        }
        
        
        
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public void summaryOfOrder(Order order) {
        io.print("=== Summary of your Order ===\n");
        io.print("Material Cost Per Sq Ft: " + order.getMatUnitCost() + "\n");
        io.print("Labor Cost per Sq Ft: " + order.getLaborUnitCost() + "\n");
        io.print("Total Material Cost: " + order.calcMatCost() + "\n");
        io.print("Total Labor Cost: " + order.calcLaborCost() + "\n");
        io.print("Total Tax cost: " + order.calcTaxes() + "\n");
        io.print("Total Cost: " + order.calcTotal() + "\n");
        io.print("Order recieved on " + order.getLocalDate() + "\n");                  
    }

    public void unknownCommand() {
        io.print("Invalid entry! try again.\n");
    }

    public LocalDate getOrderByDate() {
        return io.readLocalDate("Please enter a date: ", LocalDate.MIN, LocalDate.MAX);
    }

    public int getOrderByOrderNumber() {
        return io.readInt("Please enter your order number: ");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void couldNotFindOrder() {
        io.print("Orders do not exist on that date. try again.\n");
    }

    public void displayExportAllSuccess() {
        io.print("Export Successful!!\n");
    }

    
}
