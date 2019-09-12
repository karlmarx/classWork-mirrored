/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.dao;

import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.service.FlooringServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class FlooringOrderDaoImplTest {
    FlooringOrderDaoImpl testODao;
    public FlooringOrderDaoImplTest() {
         ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("flooringTestApplicationContext.xml");
        testODao = ctx.getBean("testODao", FlooringOrderDaoImpl.class);
    }

    @Test
    public void testGetFilenames() {
        String[] filenames = testODao.getOrderFileNames("Orders/");
        
        Assertions.assertNotNull(filenames, "should not be null, there are files in dir");
        Assertions.assertEquals(2, filenames.length, "there should be two files in dir");
        
    }
    @Test
    public void testCreateOrder() throws FilePersistenceException {
        int orderNumber1 = 21;
        Order orderToAdd = new Order(LocalDate.parse("2020-12-12"), "Bill", "TX", new BigDecimal("101.0"), "TILE");
        
       
        
        Order gotBack = testODao.createOrder(orderNumber1, orderToAdd);
        Order gottenOrder = testODao.getOrder(orderNumber1);
        
        Assertions.assertEquals(gottenOrder.getDate(), orderToAdd.getDate(), "order dates should match");
        Assertions.assertEquals(gottenOrder.getOrderNumber(), orderToAdd.getOrderNumber(), "order numbers should match");
        Assertions.assertEquals(gottenOrder.getCustomerName(), orderToAdd.getCustomerName(), "customer names should match");
        Assertions.assertEquals(gottenOrder.getState(), orderToAdd.getState(), "states should match");
        Assertions.assertEquals(gottenOrder.getArea(), orderToAdd.getArea(), "areas should match");
        Assertions.assertEquals(gottenOrder.getProductType(), orderToAdd.getProductType(), "order numbers should match");
        
        
        Assertions.assertNull(gotBack, "map should have been  empty");    
    }
    @Test
    public void testGetAllOrders() throws FilePersistenceException {
        int orderNumber1 = 25;
        Order orderToAdd1 = new Order(LocalDate.parse("9999-1-1"), "Jim", "CA", new BigDecimal("123.0"), "WOOD");
        
        int orderNumber2 = 223;
        Order orderToAdd2 = new Order(LocalDate.parse("2040-1-1"), "Ted", "KY", new BigDecimal("121.0"), "LAMINATE");
        
        Order gotBackFirst = testODao.createOrder(orderNumber1, orderToAdd1);
        Order gotBackSecond = testODao.createOrder(orderNumber2, orderToAdd2);
        
                List<Order> allDaOrders = testODao.getAllOrders();

        
       Assertions.assertNotNull(allDaOrders, "Our Order list should not be null");
        Assertions.assertEquals(2, allDaOrders.size(), "there should be 2 orders in the list");
        Assertions.assertTrue(allDaOrders.contains(orderToAdd1), "orders list should have the first order  stored");
        Assertions.assertTrue(allDaOrders.contains(orderToAdd2), "orders list shoud have the second order stored");
        
        Assertions.assertNull(gotBackFirst, "There shouldn't be a order with that key stored in empty dao.");
        Assertions.assertNull(gotBackSecond, "There shouldn't be a order with that key stored in empty dao.");
    }
    @Test
    public void createRemoveOrderTest() throws FilePersistenceException{
        int orderNumber = 1231;
        Order toStore = new Order(LocalDate.parse("2020-01-01"), "Karl", "KY", new BigDecimal("1324.0"), "Carpet");
         testODao.createOrder(orderNumber, toStore);
        Order removed = testODao.removeOrder(orderNumber);
        Order shouldBeNullBcItWasRemoved = testODao.getOrder(orderNumber);
        
        Assertions.assertEquals(toStore, removed, "stored and removed order should be same");
        Assertions.assertNull(shouldBeNullBcItWasRemoved," order should no longer be in dao");
    }
    @Test
     public void updateOrderTest() throws FilePersistenceException{
        //arrange
        int orderNumber = 1231546465;
        Order toStore = new  Order(LocalDate.parse("2100-01-01"), "Karl", "KY", new BigDecimal("1324.0"), "Carpet");

        Order toStore2 =new Order(LocalDate.parse("2040-01-01"), "Ted", "KY", new BigDecimal("121.0"), "LAMINATE");
        
       
        testODao.createOrder(orderNumber, toStore);
        testODao.editOrder(orderNumber, toStore2);

        Order retrieved = testODao.getOrder(orderNumber);
        List<Order> allDaOrders = testODao.getAllOrders();

        //assert
        Assertions.assertEquals(toStore2, retrieved, "order shoulda been replaced by second");
        Assertions.assertEquals(1, allDaOrders.size(), "there should be 1 order only");
        Assertions.assertTrue(allDaOrders.contains(toStore2));
    }
     @Test
     public void testDeleteFileIfEmpty() {
         
     }
     @Test
     public void updateOrderWithoutAddingTest() throws FilePersistenceException{
         Order emptyOrder = new Order();
        int orderNumber = 123123;
        testODao.editOrder(orderNumber, emptyOrder);
        List<Order> shouldBeEmpty = testODao.getAllOrders();
        Assertions.assertTrue(shouldBeEmpty.size() == 0, "editing with empty order should not have added an order.");
     }
    
     @Test
     public void testMarshallUnmarshallNameHasCommas() throws FilePersistenceException{
         
     }
//    @Test
//     public void testMarshallUnmarshallNameNull() throws FilePersistenceException{
//         
//     }
     //test marshall?
     
    
}
