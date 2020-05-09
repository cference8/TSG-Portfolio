/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringDaoException;
import com.sg.flooringmastery.dao.InvalidOrderNumber;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.service.FlooringProductTypeBadType;
import com.sg.flooringmastery.service.FlooringServiceLayer;
import com.sg.flooringmastery.view.FlooringView;
import com.sg.flooringmastery.view.FlooringViewException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author board
 */
public class FlooringController {

    FlooringServiceLayer service;
    FlooringView view;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        boolean done = false;
        int menuSelection = 0;

        while (!done) {
            try {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportAll();
                        break;
                    case 6:
                        done = true;
                        break;
                    default:
                        unknownCommand();

                }
            } catch (FlooringDaoException | InvalidOrderNumber
                    | FlooringViewException | FlooringProductTypeBadType ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
        exitMessage();

    }

    private void displayOrders() throws FlooringDaoException {
        //1. Ask user for Date 
        //2. Get all orders by date based on the users input
        //  if no orders exist for that date display error and 
        // return user to the main menu
        //3. display all orders by date

        LocalDate userInput = view.askUserForDate();
        List<Order> orderList = service.getAllOrdersByDate(userInput);
        if (orderList.isEmpty()) {
            view.couldNotFindOrder();
        }
        view.displayAllOrdersByDate(orderList);
    }

    private void addOrder() throws FlooringDaoException, FlooringViewException, FlooringProductTypeBadType {

        // 1. Ask user to input Order info
        // 2. send to the service layer to finish order
        // 3. show summary to user and ask if they would like to submit (Y/N)
        // if no back to main menu without adding inputs to the file.
        // 4. if yes send it off to be added to a file.
        // 5. order entered successfully
        Order order = view.getNewOrderInfo();

        Order finishedOrder = service.createOrder(order);

        view.summaryOfOrder(finishedOrder);
        view.displayOrderEnteredSuccessfully();
    }

    private void editOrder() throws FlooringDaoException, InvalidOrderNumber {

        // 1. get order by date
        // 2. get order by order number
        // 3. get that file by date.
        // 4. get the order in that file
        // 5. Step user through specific fields to edit or not
        //  ask user ARE YOU SURE (Y/N) if no back to main menu with no changes saved.
        // 6. if yes write to file
        LocalDate date = view.getOrderByDate();
        int orderNum = view.getOrderByOrderNumber();
        Order toEdit = service.getOrder(date, orderNum);
        Order edited = view.editOrder(toEdit);
        service.editOrder(edited);
        view.displayOrderEnteredSuccessfully();

    }

    private void removeOrder() throws FlooringDaoException, InvalidOrderNumber, FlooringViewException {
        //1. Ask user for a date
        //2. Ask user for a orderNum
        //3. Get file by date
        //4. get the order in that file
        //5. display order to remove and ask user ARE YOU SURE (Y\N).
        //6. if yes remove the order.

        LocalDate date = view.getOrderByDate(); //by date
        int orderNum = view.getOrderByOrderNumber(); //by Order Number
        Order toDelete = service.getOrder(date, orderNum);
        Order toRemove = view.displayOrderToRemove(toDelete);
        if(toRemove == null){
            throw new FlooringViewException("---Order not removed---\n");
        }
        service.deleteOrder(toRemove); //delete if yes

    }

    private void exportAll() throws FlooringDaoException {
        service.exportAllData();
        view.displayExportAllSuccess();
    }

    private void unknownCommand() {
        view.unknownCommand();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        return view.printMainMenuGetMenuChoice();
    }

}
