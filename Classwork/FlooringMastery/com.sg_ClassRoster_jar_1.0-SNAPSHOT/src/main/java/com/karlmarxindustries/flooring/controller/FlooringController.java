/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.controller;

import com.karlmarxindustries.flooring.service.NoOrdersOnDateException;
import com.karlmarxindustries.flooring.dao.FilePersistenceException;
import com.karlmarxindustries.flooring.dao.TestingModeException;
import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.dto.Product;
import com.karlmarxindustries.flooring.service.FlooringDataValidationException;
import com.karlmarxindustries.flooring.service.FlooringDuplicateIdException;
import com.karlmarxindustries.flooring.ui.FlooringView;
import java.util.List;
import com.karlmarxindustries.flooring.service.FlooringServiceLayer;
import com.karlmarxindustries.flooring.service.NoMatchingOrdersException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karlmarx
 */
public class FlooringController {

    FlooringView view;
    private FlooringServiceLayer service;
    //idea : secret method to switch between test and production

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws FilePersistenceException, NoOrdersOnDateException, FlooringDuplicateIdException, FlooringDataValidationException, NoMatchingOrdersException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            service.loadOrderData();
            service.initialLoadProductTaxInfo();
        } catch (FilePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            keepGoing = false;
        }
        while (keepGoing) {

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
                    saveCurrentWork();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addOrder() throws FilePersistenceException {
        view.displayAddOrderBanner();

        List<String> states = service.loadValidStates();
        List<Product> products = service.loadProducts();
        boolean hasErrors = false;
        do { //maybe get rid of this????
            Order currentOrder = view.getNewOrderInfo(states, products);
            try {
                Order toAdd = service.calculateAndOrderNumber(currentOrder);
                boolean isConfirmed = view.displayConfirmOrderToAdd(toAdd);
                if (isConfirmed) {
                    service.addOrder(toAdd);
                }
                hasErrors = false;
            } catch (FlooringDuplicateIdException | FlooringDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void displayOrders() throws FilePersistenceException, NoOrdersOnDateException {
        view.displayDisplayOrdersBanner();
        LocalDate searchDate = view.getDesiredDate();
        try {
            List<Order> ordersForDate = service.getOrdersForDate(searchDate);
            view.displayOrdersForDate(ordersForDate);
        } catch (NoOrdersOnDateException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder() throws FilePersistenceException, NoMatchingOrdersException {
        view.displayEditOrderBanner();
        int searchOrderNumber = view.getOrderNumber();
        LocalDate searchDate = view.getDesiredDate();

        //try catch for no such order
        try {
            Order toRemove = service.getMatchingOrderForDate(searchDate, searchOrderNumber);
            boolean isConfirmed = view.displayConfirmOrderToRemove(toRemove);
            if (isConfirmed) {
                service.removeOrder(toRemove);
                view.displayRemoveSuccessBanner(); //redundant
            } else {
                view.displayNoChangesMade();
            }
        } catch (NoMatchingOrdersException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void editOrder() throws FlooringDuplicateIdException, FlooringDataValidationException, FilePersistenceException, NoMatchingOrdersException {
        view.displayEditOrderBanner();
        int searchOrderNumber = view.getOrderNumber();
        LocalDate searchDate = view.getDesiredDate();

        try {
            Order toEdit = service.getMatchingOrderForDate(searchDate, searchOrderNumber);
            List<String> states = service.loadValidStates();
            List<Product> products = service.loadProducts();
            Order edited = view.displayCurrentGetEdits(states, products, toEdit);
            Order editedAndCalculated = service.calculateCostsTaxesTotal(edited);
            boolean isConfirmed = view.displayConfirmEditing(editedAndCalculated);
            if (isConfirmed) {
                view.displayEditSuccess();
                service.editOrder(editedAndCalculated);
            } else {
                view.displayNoChangesMade();
            }
        } catch (NoMatchingOrdersException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void saveCurrentWork() throws FilePersistenceException {
        boolean isConfirmed = view.displayConfirmSave();
        if (isConfirmed) {
            try {
                service.saveWorks();
                view.displaySaveSuccess();///try catch for persistence exception
            } catch (TestingModeException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } else {
            view.displayNotSaved();
        }

    }

}
