/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import com.karlmarxindustries.flooring.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author karlmarx
 */
public class FlooringOrderDaoImpl implements FlooringOrderDao {

    private static final String DELIMITER = ",";
    private static final String MODE_DELIMITER = "::";
    private String pathToDirectory = "Orders/";

    private Map<Integer, Order> orders = new HashMap<>();

    public List<Order> getAllOrders() throws FilePersistenceException {
        return new ArrayList<Order>(orders.values());
    }

    public Order getOrder(String orderNumber) throws FilePersistenceException {
        return orders.get(orderNumber);
    }

    @Override
    public void loadOrders() throws FilePersistenceException {
        String[] filenames = getOrderFileNames(pathToDirectory);
        loadOrderInfoForAllFiles(filenames);
    }

    @Override
    public List<Order> getAllOrdersForDate(LocalDate date) throws FilePersistenceException {
        List<Order> allOrders = getAllOrders();
        List<Order> matchingOrders = new ArrayList<>();
        Iterator<Order> itr = allOrders.iterator();
        for (Order order : allOrders) {
            if (order.getDate().compareTo(date) == 0) {
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    }

    public void loadOrderInfoByFile(String filename) throws FilePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(pathToDirectory + filename)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Uh-oh! Could not load order data into memory. Shutting down.", e);
        }
        String currentLine;
        Order currentOrder;
        String dateFromFile = filename.substring(7, 15);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateFromFile, formatter);
        String headerLine = scanner.nextLine(); //to skip the first line
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            currentOrder.setDate(date);
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }

    public String[] getOrderFileNames(String pathToDirectory) {
        File folder = new File(pathToDirectory);
        String[] files = folder.list();
        return files;
    }

    public void loadOrderInfoForAllFiles(String[] files) throws FilePersistenceException {
        for (String filename : files) {
            loadOrderInfoByFile(filename);
        }
    }

    public Order unmarshallOrder(String orderAsText) throws FilePersistenceException {
        String[] orderTokens = orderAsText.split(DELIMITER);
        String orderNumber = orderTokens[0];
        Order orderFromFile = new Order();
        orderFromFile.setOrderNumber(Integer.valueOf(orderNumber));
        String commaSubstitution = orderTokens[1].replace("::",","); //in case of coma in name
        orderFromFile.setCustomerName(commaSubstitution);
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));
        return orderFromFile;

    }

    private String marshallOrder(Order anOrder) {
        String orderAsText = anOrder.getOrderNumber() + DELIMITER;
        String commaSubstitution = anOrder.getCustomerName().replace(",", "::");
        orderAsText += commaSubstitution + DELIMITER;
        orderAsText += anOrder.getState() + DELIMITER;
        orderAsText += String.valueOf(anOrder.getTaxRate()) + DELIMITER;
        orderAsText += anOrder.getProductType() + DELIMITER;
        orderAsText += String.valueOf(anOrder.getArea()) + DELIMITER;
        orderAsText += String.valueOf(anOrder.getCostPerSquareFoot()) + DELIMITER;
        orderAsText += String.valueOf(anOrder.getLaborCostPerSquareFoot() + DELIMITER);
        orderAsText += String.valueOf(anOrder.getMaterialCost() + DELIMITER);
        orderAsText += String.valueOf(anOrder.getLaborCost() + DELIMITER);
        orderAsText += String.valueOf(anOrder.getTax() + DELIMITER);
        orderAsText += String.valueOf(anOrder.getTotal() + DELIMITER);

        return orderAsText;
    }

    private void saveOrderInfoByDate(LocalDate date) throws FilePersistenceException {
        //different paths for new files vs edit???? - must create one
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String dateInString = date.format(formatter);
        String filename = ("Orders_" + dateInString + ".txt");
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(pathToDirectory + File.separator + filename));
        } catch (IOException e) {
            throw new FilePersistenceException("Could not save order data", e); //add more info
        }
        String orderAsText;
        List<Order> ordersForDate = getAllOrdersForDate(date);
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        for (Order currentOrder : ordersForDate) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();

    }

    @Override
    public void saveAllOrders() throws FilePersistenceException, TestingModeException {
        boolean isModeTesting = this.isModeTesting();
        if (isModeTesting) {
            throw new TestingModeException("Data cannot be saved while in testing mode. Returning to main menu.");  //catch in controller
        } else {
            Collection<Order> allOrders = orders.values();
            Set<LocalDate> allDates = new HashSet<>();
            for (Order order : allOrders) {
                allDates.add(order.getDate());
            }
            String[] fileNames = getOrderFileNames(pathToDirectory);
            for (String filename : fileNames) {
                String dateFromFile = filename.substring(7, 15);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
                LocalDate date = LocalDate.parse(dateFromFile, formatter);
                allDates.add(date);
            }
            for (LocalDate each : allDates) {
                saveOrderInfoByDate(each);
                deleteFileIfEmpty(each);
            }
        }

        //this addresses if a date has been removed completely and the file should be deleted.
    }

//    @Override
//    public Order createOrder(Integer orderNumber, Order order) throws FilePersistenceException {
//        Order newOrder = orders.put(orderNumber, order);
//        return newOrder;
//    }
    @Override
    public void editOrder(Integer orderNumber, Order order) throws FilePersistenceException {
        orders.replace(orderNumber, order);
    }

    @Override
    public Order removeOrder(Integer orderNumber) throws FilePersistenceException {
        Order removedOrder = orders.remove(orderNumber);
        return removedOrder;
    }

    @Override
    public void loadOrderInfo(String pathToDirectory) throws FilePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(Integer orderNumber) throws FilePersistenceException {
        return orders.get(orderNumber);
    }

    @Override
    public Order createOrder(Integer orderNumber, Order toAdd) {
        Order newOrder = orders.put(toAdd.getOrderNumber(), toAdd);
        return newOrder;
    }

    @Override
    public boolean isModeTesting() throws FilePersistenceException {
        boolean isTesting = false;
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("Mode.txt")));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException("Uh-oh! Could not load mode data into memory. Shutting down.", e);
        }
        String line = scanner.nextLine();
        String[] modeTokens = line.split(MODE_DELIMITER);
        if (modeTokens[1].trim().equalsIgnoreCase("Training")) {
            isTesting = true;
        }
        scanner.close();
        return isTesting;
    }

//    @Override
//    public void createOrder(Order toAdd) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    private void deleteFileIfEmpty(LocalDate each) throws FilePersistenceException {

        List<Order> orders = this.getAllOrdersForDate(each);
        if (orders.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
            String dateInString = each.format(formatter);
            String filename = ("Orders_" + dateInString + ".txt");
            File file = new File(pathToDirectory + File.separator + filename);
            file.delete();
        }

    } ///test this

   

    
}
