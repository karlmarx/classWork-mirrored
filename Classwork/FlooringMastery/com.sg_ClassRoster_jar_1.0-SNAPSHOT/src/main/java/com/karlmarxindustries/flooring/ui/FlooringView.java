/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.ui;

import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.dto.Product;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author karlmarx
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo(List<String> stateList, List<Product> productList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        boolean validDate = false;
        boolean validName = false;
        boolean validProduct = false;
        boolean validState = false;
        String customerName = null;
        String dateAsText;
        LocalDate date = null;
        String inputState = null;
        String inputProduct = null;
        List<String> productTypeList = new ArrayList<>();
        for (Product each : productList) {
            productTypeList.add(each.getProductType());
        }
        BigDecimal area;
        while (!validDate) {
            date = getDesiredDate();
            if (date.compareTo(LocalDate.now()) < 0) {
                io.print("You have entered a date in the past.  Please enter a valid future date.");
            } else {
                validDate = true;
            }
        }
        while (!validName) {
            customerName = io.readString("Please enter customer name: ").trim();
            if (!isAlphaPeriodComma(customerName)||customerName.equalsIgnoreCase("null")) {
                io.print("Invalid Format!  Allowed characters: numbers, letters, commas, and periods. Name cannot be 'null'.");
            } else {
                validName = true;
            }
        }
        //figure out way to get state list.  maybe as a METHOD PARAMATER
        while (!validState) {
            inputState = io.readString("Please enter state: (using 2 letter abbreviation) ");
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState)) {
                io.print("Floors cannot be sold in " + upperState + " currently.  Please try again.");
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString("Please enter product from above:  (use full name of product)");
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product)) {
                io.print("That product is not available.  Please select from the available options.");
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDouble("Please enter area in sq feet. (minimum 100)", 100.0d, Double.MAX_VALUE);
        area = new BigDecimal(String.valueOf(doubleArea));
        Order orderToValidate = new Order(date, customerName, inputState, area, inputProduct);
        return orderToValidate;
        //send to service layer for calculations and to validate state.
    }

    public void displayAddOrderBanner() {
        io.print("=== Add Order ===");
    }

    public void displayAddOrderSuccessBanner() {
        io.readString("Order successfully created.  Please hit enter to continue");
    }

    public void displayOrdersForDate(List<Order> orderList) {
        for (Order order  : orderList) {
            this.displayOrder(order);
            io.print("+++++++++++++++++++++++");
        }
        io.readString("Please hit enter to continue.");
    }



    public void displayEditOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Order successfully removed.  Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Goodbye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

  

    public boolean isAlphaPeriodComma(String stringToCheck) {
         Pattern pattern = Pattern.compile("^[A-Za-z0-9 .,]*$");
// Pattern pattern = Pattern.compile("^[\\w .,]+$");
        Matcher m = pattern.matcher(stringToCheck);
        boolean doesItMatch = m.matches();
        return doesItMatch;
    }

    private void displayAllProducts(List<Product> productTypeList) {
        int counter = 1;
        io.print("===Available Products:===");
        for (Product currentProduct : productTypeList) {
            io.print(counter + ". [" + currentProduct.getProductType() + "] - Cost: $"
                    + currentProduct.getCostPerSquareFoot() + " - Labor Cost: $"
                    + currentProduct.getLaborCostPerSquareFoot());
                     io.print("++++++++++++++++++++++++++");
            counter++;
        }
        io.print("==========================");
        //io.readString("Please hit enter to continue.");
    }

    public boolean displayConfirmOrderToAdd(Order toAdd) {
        boolean confirmed = false;
        io.print("Please review the order to make sure it is correct:");
        displayOrder(toAdd);
        String confirmation = io.readString("Would you like to add this order? (Y/N)");
        if (confirmation.equalsIgnoreCase("Y")) {
            confirmed = true;
            io.print("The order has been created and assigned order id#" + toAdd.getOrderNumber() + "."); //split this up?
        } else {
            io.print("The order has not been created."); //put this logic in controller probably
        }
        return confirmed;
    }

    public void displayDisplayOrdersBanner() {
               io.print("=== Display Order ===");
    }

    public LocalDate getDesiredDate() {
        
        boolean validDate = false;
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        while(!validDate) {
            try {
                String dateAsText = io.readString("Please enter date (MM/DD/YYYY): ").trim();
                
                date = LocalDate.parse(dateAsText, formatter);
                validDate = true;
            } catch (DateTimeException e) { //this okay to handle here?
                io.print("The date format was invalid.  Please try again!");
                
            }
        }
         return date;   
    }
    public int getOrderNumber() {
        int orderNumber = io.readInt("Please enter order number", 1, Integer.MAX_VALUE);
        return orderNumber;
    }
    public void displayErrorMessage(String errorMsg){
            io.print("--- ERROR ---");
            io.print(errorMsg);
    }

    public boolean displayConfirmOrderToRemove(Order toRemove) {
        io.print("Please review the order to make sure it is the correct order");
        io.print("Order #" + toRemove.getOrderNumber());
        displayOrder(toRemove);
        return confirmSomething("Would you like to add remove this order? (Y/N)");
    }
    public boolean confirmSomething(String toConfirm) {
        boolean confirmed = false;
        String confirmation = io.readString(toConfirm);
        if (confirmation.equalsIgnoreCase("Y")) {
            confirmed = true;
        } 
        return confirmed;
    }
    public boolean displayConfirmSave() {
        return confirmSomething("Are you sure you would like to save your work from this session? (Y/N)");
    }

    public void displaySaveSuccess() {
        io.print("The changes made in this session have been saved successfully.");
    }


    public boolean displayConfirmEditing(Order editedAndCalculated) {
        displayOrder(editedAndCalculated);
        return confirmSomething("Would you like to save these changes? (Y/N)");
    }

    public void displayEditSuccess() {
        io.print("The changes have been saved.  Make sure to 'Save Current Work' from the main menu to finalize your changes.");
    }

    public void displayNoChangesMade() {
        io.print("No changes have been made.");
    }
    public void displayOrder(Order order) {
        if (order.getOrderNumber() != 0) {
            io.print("Order number: " + order.getOrderNumber());
        } else {
            io.print("- Order # has yet to be assigned.");
        }
        io.print("Order date: " +order.getDate());
        io.print("Customer name: " + order.getCustomerName());
        io.print("State: " + order.getState());
        io.print("Tax rate: " + order.getTaxRate());
        io.print("Product type:" + order.getProductType());
        io.print("Area: " + order.getArea() + "square feet");
        io.print("Cost per square foot: $" + order.getCostPerSquareFoot());
        io.print("Labor cost per square foot: $" + order.getLaborCostPerSquareFoot());
        io.print("Material cost: $" + order.getMaterialCost());
        io.print("Labor cost: $" + order.getLaborCost());
        io.print("Tax: $" + order.getTax());
        io.print("Total: $" + order.getTotal());
    }

    public void displayNotSaved() {
        io.print("Your changes have not been saved.");
    }
    
    public Order displayCurrentGetEdits(List<String> stateList, List<Product> productList, Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        boolean validName = false;
        boolean validProduct = false;
        boolean validState = false;
        String customerName = null;
        String oldName = order.getCustomerName();
                    String oldState = order.getState();
                                String oldProduct = order.getProductType();
                                BigDecimal oldArea = order.getArea();


        LocalDate date = null;
        String inputState = null;
        String inputProduct = null;
        List<String> productTypeList = new ArrayList<>();
        for (Product each : productList) {
            productTypeList.add(each.getProductType());
        }
        BigDecimal area;
        while (!validName) {
            customerName = io.readString("Please enter customer name[" + order.getCustomerName() + "]: ").trim();
            if (!isAlphaPeriodComma(customerName)||customerName.equalsIgnoreCase("null") && !customerName.equals("")) {
                io.print("Invalid Format!  Allowed characters: numbers, letters, commas, and periods. Name cannot be 'null'.");
            } else {
                validName = true;
            }
            
        }
        while (!validState) {
            inputState = io.readString("Please enter state[" + order.getState().toUpperCase() + "]: (using 2 letter abbreviation) ");
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState) && !inputState.equals("")) {
                io.print("Floors cannot be sold in " + inputState + "currently.  Please try again.");
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString("Please enter product type from list above[" + order.getProductType().toUpperCase() + "]:  ");
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product) && !inputProduct.equals("")) {
                io.print("That product is not available.  Please select from the available options.");
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDoubleAllowBlank("Please enter area in sq feet[" + order.getArea() + "sq ft]. (minimum 100)", 100.0d, Double.MAX_VALUE);
        area = new BigDecimal(String.valueOf(doubleArea));
        if (!customerName.equals("")) order.setCustomerName(customerName);
        if(!inputState.equals("")) order.setState(inputState);
        if(doubleArea != 0) order.setArea(area); //does this work?
        if(!inputProduct.equals("")) order.setProductType(inputProduct);
       

        return order;
        //send to service layer for calculations and to validate state.
    }
}
