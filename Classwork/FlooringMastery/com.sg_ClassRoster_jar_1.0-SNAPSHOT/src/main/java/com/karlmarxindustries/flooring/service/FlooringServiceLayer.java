/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.service;

import com.karlmarxindustries.flooring.dao.FilePersistenceException;
import com.karlmarxindustries.flooring.dao.TestingModeException;
import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.dto.Product;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public interface FlooringServiceLayer {
    
    public Order calculateAndOrderNumber(Order student) throws
            FlooringDuplicateIdException,
            FlooringDataValidationException,
            FilePersistenceException;
 
//    public List<Order> getAllStudents() throws
//            FilePersistenceException;
// 
//    public Order getStudent(String studentId) throws
//            FilePersistenceException;
// 
//    public Order removeStudent(String studentId) throws
//            FilePersistenceException;

    public void initialLoadProductTaxInfo() throws FilePersistenceException;
    public String firstLetterCapRestLower (String string); 
    public List<String> loadValidStates() throws
            FilePersistenceException;

    public List<Product> loadProducts() throws
            FilePersistenceException;

    public Order addOrder(Order toAdd) throws FilePersistenceException;
    

    public List<Order> getAllOrders();

    public List<Order> getOrdersForDate(LocalDate searchDate) throws NoOrdersOnDateException, FilePersistenceException;

    public Order getMatchingOrderForDate(LocalDate searchDate, int searchOrderNumber) throws FilePersistenceException,  NoMatchingOrdersException;

    public void removeOrder(Order toRemove) throws FilePersistenceException;

    public void saveWorks() throws FilePersistenceException, TestingModeException ;

    public Order calculateCostsTaxesTotal(Order edited) throws FlooringDuplicateIdException,
            FlooringDataValidationException,
            FilePersistenceException;

    public void editOrder(Order editedAndCalculated) throws FilePersistenceException;

    public void loadOrderData() throws FilePersistenceException;
}
