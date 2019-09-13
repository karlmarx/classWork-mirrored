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
import com.karlmarxindustries.flooring.ui.FlooringViewDeutsch;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author karlmarx
 */
public class FlooringControllerBilingual {

    FlooringView view;
    private FlooringServiceLayer service;
    //idea : secret method to switch between test and production
    FlooringViewDeutsch viewDE;
    Locale aLocale = new Locale("en", "US");
    Locale deLocale = new Locale("de", "DE");
  
    ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", aLocale);
    ResourceBundle messagesDE = ResourceBundle.getBundle("MessagesBundle", deLocale);

    public FlooringControllerBilingual(FlooringServiceLayer service, FlooringView view, FlooringViewDeutsch viewDE) {
        this.viewDE = viewDE;
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
            view.displayErrorMessage(messages.getString(e.getMessage()));
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
                case 7:
                    menuDeutsch();
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

    private int getMenuSelectionDE() {
        return viewDE.printMenuAndGetSelection();
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
                view.displayErrorMessage(messages.getString(e.getMessage()));
            }
        } while (hasErrors);
    }
    private void addOrderDE() throws FilePersistenceException {
        viewDE.displayAddOrderBanner();

        List<String> states = service.loadValidStates();
        List<Product> products = service.loadProducts();
        boolean hasErrors = false;
        do { //maybe get rid of this????
            Order currentOrder = viewDE.getNewOrderInfo(states, products);
            try {
                Order toAdd = service.calculateAndOrderNumber(currentOrder);
                boolean isConfirmed = viewDE.displayConfirmOrderToAdd(toAdd);
                if (isConfirmed) {
                    service.addOrder(toAdd);
                }
                hasErrors = false;
            } catch (FlooringDuplicateIdException | FlooringDataValidationException e) {
                hasErrors = true;
                viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));
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
            view.displayErrorMessage(messages.getString(e.getMessage()));
        }
    }

    private void displayOrdersDE() throws FilePersistenceException, NoOrdersOnDateException {
        viewDE.displayDisplayOrdersBanner();
        LocalDate searchDate = viewDE.getDesiredDate();
        try {
            List<Order> ordersForDate = service.getOrdersForDate(searchDate);
            viewDE.displayOrdersForDate(ordersForDate);
        } catch (NoOrdersOnDateException e) {
                            viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));

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
                          view.displayErrorMessage(messages.getString(e.getMessage()));

        }

    }

    private void removeOrderDE() throws FilePersistenceException, NoMatchingOrdersException {
        viewDE.displayEditOrderBanner();
        int searchOrderNumber = viewDE.getOrderNumber();
        LocalDate searchDate = viewDE.getDesiredDate();

        //try catch for no such order
        try {
            Order toRemove = service.getMatchingOrderForDate(searchDate, searchOrderNumber);
            boolean isConfirmed = viewDE.displayConfirmOrderToRemove(toRemove);
            if (isConfirmed) {
                service.removeOrder(toRemove);
                viewDE.displayRemoveSuccessBanner(); //redundant
            } else {
                viewDE.displayNoChangesMade();
            }
        } catch (NoMatchingOrdersException e) {
                           viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));

        }

    }

    private void unknownCommandDE() {
        viewDE.displayUnknownCommandBanner();
    }

    private void exitMessageDE() {
        viewDE.displayExitBanner();
    }

    private void editOrderDE() throws FlooringDuplicateIdException, FlooringDataValidationException, FilePersistenceException, NoMatchingOrdersException {
        viewDE.displayEditOrderBanner();
        int searchOrderNumber = viewDE.getOrderNumber();
        LocalDate searchDate = viewDE.getDesiredDate();

        try {
            Order toEdit = service.getMatchingOrderForDate(searchDate, searchOrderNumber);
            List<String> states = service.loadValidStates();
            List<Product> products = service.loadProducts();
            Order edited = viewDE.displayCurrentGetEdits(states, products, toEdit);
            Order editedAndCalculated = service.calculateCostsTaxesTotal(edited);
            boolean isConfirmed = viewDE.displayConfirmEditing(editedAndCalculated);
            if (isConfirmed) {
                viewDE.displayEditSuccess();
                service.editOrder(editedAndCalculated);
            } else {
                viewDE.displayNoChangesMade();
            }
        } catch (NoMatchingOrdersException e) {
                viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));
        }
    }
private void saveCurrentWork() throws FilePersistenceException {
        boolean isConfirmed = view.displayConfirmSave();
        if (isConfirmed) {
            try {
                service.saveWorks();
                view.displaySaveSuccess();///try catch for persistence exception
            } catch (TestingModeException e) {
                view.displayErrorMessage(messages.getString(e.getMessage()));
            }
        } else {
            view.displayNotSaved();
        }

    }

    private void saveCurrentWorkDE() throws FilePersistenceException {
        boolean isConfirmed = viewDE.displayConfirmSave();
        if (isConfirmed) {
            try {
                service.saveWorks();
                viewDE.displaySaveSuccess();///try catch for persistence exception
            } catch (TestingModeException e) {
                viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));
            }
        } else {
            viewDE.displayNotSaved();
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
                view.displayErrorMessage(messages.getString(e.getMessage()));
        }
    }

    private void menuDeutsch() throws FilePersistenceException, NoOrdersOnDateException, NoMatchingOrdersException, FlooringDuplicateIdException, FlooringDataValidationException {
        boolean keepGoingDE = true;
        int menuSelectionDE = 0;
        try {
            service.loadOrderData();
            service.initialLoadProductTaxInfo();
        } catch (FilePersistenceException e) {
                viewDE.displayErrorMessage(messagesDE.getString(e.getMessage()));
            keepGoingDE = false;
        }
        while (keepGoingDE) {

            menuSelectionDE = getMenuSelectionDE();

            switch (menuSelectionDE) {
                case 1:
                    displayOrdersDE();
                    break;
                case 2:
                    addOrderDE();
                    break;
                case 3:
                    editOrderDE();
                    break;
                case 4:
                    removeOrderDE();
                    break;
                case 5:
                    saveCurrentWorkDE();
                    break;
                case 6:
                    keepGoingDE = false;
                    break;
                default:
                    unknownCommandDE();
            }

        }
        exitMessageDE();
    }

}
