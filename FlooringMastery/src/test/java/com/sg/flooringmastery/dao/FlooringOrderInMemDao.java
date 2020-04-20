/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author board
 */
public class FlooringOrderInMemDao implements FlooringOrderDao {

    List<Order> orders = new ArrayList<>();

    public FlooringOrderInMemDao() {

        Order toAdd = new Order();
        toAdd.setLocalDate(LocalDate.of(2013, 06, 02));
        toAdd.setOrderNumber(1);
        toAdd.setCustomerName("John");
        toAdd.setState("CA");
        toAdd.setTaxRate(new BigDecimal("25.00"));
        toAdd.setProductType("Tile");
        toAdd.setArea(new BigDecimal("249.00"));
        toAdd.setMatUnitCost(new BigDecimal("3.50"));
        toAdd.setLaborUnitCost(new BigDecimal("4.15"));
        toAdd.calcMatCost().add(new BigDecimal("871.50"));
        toAdd.calcLaborCost().add(new BigDecimal("1033.35"));
        toAdd.calcTaxes().add(new BigDecimal("476.21"));
        toAdd.calcTotal().add(new BigDecimal("2381.06"));

        Order toAdd2 = new Order();
        toAdd2.setLocalDate(LocalDate.of(2013, 06, 02));
        toAdd2.setOrderNumber(2);
        toAdd2.setCustomerName("Albert EinStein");
        toAdd2.setState("KY");
        toAdd2.setTaxRate(new BigDecimal("6.00"));
        toAdd2.setProductType("Carpet");
        toAdd2.setArea(new BigDecimal("217.00"));
        toAdd2.setMatUnitCost(new BigDecimal("2.25"));
        toAdd2.setLaborUnitCost(new BigDecimal("2.10"));
        toAdd2.calcMatCost().add(new BigDecimal("488.25"));
        toAdd2.calcLaborCost().add(new BigDecimal("455.70"));
        toAdd2.calcTaxes().add(new BigDecimal("56.64"));
        toAdd2.calcTotal().add(new BigDecimal("1000.59"));

        orders.add(toAdd);
        orders.add(toAdd2);

    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) throws FlooringDaoException {
        orders.get(0).getLocalDate();
        return orders;
    }

    @Override
    public Order createOrder(Order orderToAdd) throws FlooringDaoException {
        if (orderToAdd == null) {
            throw new FlooringDaoException("hit null in createOrder()");
        }
        
        int orderNum = computeNewOrderNum(orders);
        orderToAdd.setOrderNumber(orderNum);
        orders.add(orderToAdd);
        return orderToAdd;
    }

    @Override
    public void editOrder(Order edited) throws FlooringDaoException, InvalidOrderNumber {
        int index = -1;

        for (int i = 0; i < orders.size(); i++) {
            Order toCheck = orders.get(i);

            if (toCheck.getOrderNumber() == edited.getOrderNumber()) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new InvalidOrderNumber("Could not find Order Number: "
                    + edited.getOrderNumber() + "in editOrder()");
        }

        orders.set(index, edited);
    }

    @Override
    public void deleteOrder(Order orderToDelete) throws FlooringDaoException {

        int orderNum = orderToDelete.getOrderNumber(); //object order number

        //find the order in mem to delete if null do nothing
        //if not null remove order.
        Order deleteOrder = orders
                .stream()
                .filter(o -> o.getOrderNumber() == orderNum)
                .findFirst()
                .orElse(null);

        if (deleteOrder != null) {
            orders.remove(deleteOrder);
        }
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) throws FlooringDaoException {

        Order currentOrder = null;

        //get the order in the file by the unique order number..
        for (Order order : orders) {
            if (orderNum == order.getOrderNumber()) {
                currentOrder = order;
                currentOrder.setLocalDate(date);
                currentOrder.setOrderNumber(orderNum);
            }
        }

        //return the located order
        return currentOrder;

    }

    private int computeNewOrderNum(List<Order> allOrders) {
        //use this when adding a order to a file.

        //create new order number from the max order number in the list.
        return allOrders
                .stream()
                .mapToInt(o -> o.getOrderNumber())
                .max()
                .orElse(0) + 1;
    }

    @Override
    public void exportAll() throws FlooringDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
