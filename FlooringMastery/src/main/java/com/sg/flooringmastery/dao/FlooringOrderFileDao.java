/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author board
 */
public class FlooringOrderFileDao implements FlooringOrderDao {

    String orderPath;

    public FlooringOrderFileDao(String orderPath) {
        this.orderPath = orderPath;
    }

    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) throws FlooringDaoException {
        if (date == null) {
            throw new FlooringDaoException("Got Null date in getAllOrdersByDate()");
        }

        List<Order> allOrders = new ArrayList<>();
        FileReader reader = null;
        String filePath = getOrderPath(date);
        File toCheck = new File(filePath);

        if (toCheck.isFile()) {

            try {
                reader = new FileReader(filePath);
                Scanner sc = new Scanner(reader);

                sc.nextLine(); //consumes the header

                while (sc.hasNextLine()) {

                    String line = sc.nextLine();
                    Order toAdd = parseOrder(line);
                    toAdd.setLocalDate(date);
                    allOrders.add(toAdd);
                }
            } catch (FileNotFoundException ex) {
                throw new FlooringDaoException("File not found. ", ex);
            }

            try {
                reader.close();
            } catch (IOException ex) {
                throw new FlooringDaoException("could not close reader", ex);
            }

            return allOrders;
        } else {
            return allOrders;
        }
    }

    @Override
    public Order createOrder(Order orderToAdd) throws FlooringDaoException {

        if (orderToAdd == null || orderToAdd.getLocalDate() == null || orderToAdd.getArea() == null
                || orderToAdd.getState() == null || orderToAdd.getProductType() == null
                || orderToAdd.getTaxRate() == null || orderToAdd.getMatUnitCost() == null
                || orderToAdd.getLaborUnitCost() == null) {
            throw new FlooringDaoException("hit null in createOrder()");
        }

        LocalDate date = orderToAdd.getLocalDate();
        //get all orders in a file that have the same date as the new order object.
        List<Order> allOrders = getAllOrdersByDate(date);
        //add order to the file with a unique order number
        int orderNum = computeNewOrderNum(allOrders);
        //set that order number to the new order object
        orderToAdd.setOrderNumber(orderNum);
        //add that new order object to the list.
        allOrders.add(orderToAdd);
        //write it to the file.
        writeFile(allOrders, date);
        //return new order object.
        return orderToAdd;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) throws FlooringDaoException {

        Order currentOrder = null;

        List<Order> allOrders = getAllOrdersByDate(date);

        //get the order in the file by the unique order number..
        for (Order order : allOrders) {
            if (orderNum == order.getOrderNumber()) {
                currentOrder = order;
                currentOrder.setLocalDate(date);
            }
        }
        if (currentOrder == null) {
            throw new FlooringDaoException("hit null in getOrder()");
        }
        //return the located order
        return currentOrder;
    }

    @Override
    public void editOrder(Order edited) throws
            InvalidOrderNumber, FlooringDaoException {

        if (edited.getLocalDate() == null || edited.getArea() == null
                || edited.getState() == null || edited.getProductType() == null
                || edited.getTaxRate() == null || edited.getMatUnitCost() == null
                || edited.getLaborUnitCost() == null) {
            throw new FlooringDaoException("null date in editOrder()");
        }

        LocalDate date = edited.getLocalDate();

        //get all orders in a file that have the same date as the order object to edit.
        List<Order> allOrders = getAllOrdersByDate(date);

        int index = -1;

        // use for loop to go through the whole file to find 
        // that specific order number to edit.
        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);

            //at that file index get order number == edit order number
            if (toCheck.getOrderNumber() == edited.getOrderNumber()) {
                index = i;
                break;
            }
        }
        //when trying to edit 06/02/2013 the index comes back as -1
        if (index == -1) {
            throw new InvalidOrderNumber("Could not find Order Number: "
                    + edited.getOrderNumber() + "in editOrder()");
        }
        //once located set the order object to edit that the location of the index
        allOrders.set(index, edited);

        //write to the file with the edited information
        writeFile(allOrders, date);
    }

    @Override
    public void deleteOrder(Order orderToDelete) throws FlooringDaoException {

        LocalDate date = orderToDelete.getLocalDate();
        List<Order> currentOrder = getAllOrdersByDate(date); //order in list
        int orderNum = orderToDelete.getOrderNumber(); //object order number

        Order deleteOrder = currentOrder
                .stream()
                .filter(o -> o.getOrderNumber() == orderNum)
                .findFirst()
                .orElse(null);

        if (deleteOrder != null) {
            currentOrder.remove(deleteOrder);
            writeFile(currentOrder, orderToDelete.getLocalDate());
        }
    }

    private String getOrderPath(LocalDate date) {
        String datePath;
        String dateString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        dateString = date.format(formatter);

        Path toDateFile = Path.of(orderPath, "Orders_" + dateString + ".txt");

        datePath = toDateFile.toString();

        return datePath;

    }

    private void writeFile(List<Order> order, LocalDate date) throws FlooringDaoException {
        //finallize the order to the designated file location
        PrintWriter writer = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

            File file = new File("Orders", "Orders_" + formatter.format(date) + ".txt");

            writer = new PrintWriter(new FileWriter(file));

            writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                    + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

            for (Order toWrite : order) {
                String line = convertToLine(toWrite);
                writer.println(line);

                writer.flush();
            }

            writer.close();
        } catch (IOException ex) {
            throw new FlooringDaoException("Could not open file for writing: " + orderPath, ex);
        }
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

    private Order parseOrder(String line) {
        //used in getAllOrdersByDate();
        String[] cells = line.split(",");
        Order parsed = new Order();
        parsed.setOrderNumber(Integer.parseInt(cells[0]));
        parsed.setCustomerName(cells[1]);
        parsed.setState(cells[2]);
        parsed.setTaxRate(new BigDecimal(cells[3]));
        parsed.setProductType(cells[4]);
        parsed.setArea(new BigDecimal(cells[5]));
        parsed.setMatUnitCost(new BigDecimal(cells[6]));
        parsed.setLaborUnitCost(new BigDecimal(cells[7]));
        parsed.calcMatCost().add(new BigDecimal(cells[8]));
        parsed.calcLaborCost().add(new BigDecimal(cells[9]));
        parsed.calcTaxes().add(new BigDecimal(cells[10]));
        parsed.calcTotal().add(new BigDecimal(cells[11]));

        return parsed;
    }

    private String convertToLine(Order toWrite) {
        //used in writeFile();
        String line
                = toWrite.getOrderNumber() + ","
                + toWrite.getCustomerName() + ","
                + toWrite.getState() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getMatUnitCost() + ","
                + toWrite.getLaborUnitCost() + ","
                + toWrite.calcMatCost() + ","
                + toWrite.calcLaborCost() + ","
                + toWrite.calcTaxes() + ","
                + toWrite.calcTotal();

        return line;
    }

    @Override
    public void exportAll() throws FlooringDaoException {
        //1. build a master list of allOrders
        //1a.get a list of all the files in the order directory
        //1b.loop through the list of all files and get a list of orders from each file
        //1c. add the list of files to the master list
        //2. loop through the list and write out each one.

        List<Order> allOrdersMaster = new ArrayList<>();
        File[] allFiles = getAllOrderFiles();
        for (int i = 0; i < allFiles.length; i++) {
            File orderFile = allFiles[i];
            LocalDate orderDate = converFileToDate(orderFile.getName());
            List<Order> allOrdersDate = getAllOrdersByDate(orderDate);
            allOrdersMaster.addAll(allOrdersDate);
        }
        writeExportFile(allOrdersMaster);
    }

    private File[] getAllOrderFiles() {

        File dir = new File(orderPath);

        return dir.listFiles();
    }

    private LocalDate converFileToDate(String name) {
        String dateString = name.substring(7, 15);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        return LocalDate.parse(dateString, formatter);
    }

    private void writeExportFile(List<Order> allOrdersMaster) throws FlooringDaoException {
        PrintWriter writer = null;

        String path = Paths.get("Backup", "DataExport.txt").toString();

        try {

            File file = new File(path);

            writer = new PrintWriter(new FileWriter(file));

            writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                    + "CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

            for (Order toWrite : allOrdersMaster) {
                String line = convertToLine(toWrite) + "," + toWrite.getLocalDate();
                writer.println(line);

                writer.flush();
            }

            writer.close();
        } catch (IOException ex) {
            throw new FlooringDaoException("hit exception in writExportFile");
        }
    }

}
