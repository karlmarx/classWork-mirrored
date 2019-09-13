/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.service;

import com.karlmarxindustries.flooring.dao.FilePersistenceException;
import com.karlmarxindustries.flooring.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author karlmarx
 */
public class FlooringServiceLayerImplTest {
    private FlooringServiceLayerImpl service;
    private FlooringODaoStubImpl oDao;
     private FlooringPDaoStubImpl pDao;
    private FlooringTDaoStubImpl tDao;
    Order testOrderOne; //order#1 
    Order testOrderTwo;             //order #2
    Order testOrderThree;
    public FlooringServiceLayerImplTest()  {
         ApplicationContext ctx = 
            new ClassPathXmlApplicationContext("flooringTestApplicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringServiceLayerImpl.class);
        
     
//         Order orderOne = oDao.firstOrderDate1; 1
//         Order orderTwo = oDao.secondOrderDate1;
//         Order orderThree = oDao.thirdOrderDate2;
         
    }
    @Before
    public void loadOrdersForTests() throws FilePersistenceException {
//        testOrderOne = this.oDao.getOrder(1); //order#1 
//        testOrderTwo = this.oDao.getOrder(2);             //order #2
//        testOrderThree = this.oDao.getOrder(7);
   //     service.initialLoadProductTaxInfo();
    }
    @Test
    public void testCalculateCostsTaxesTotal() throws FilePersistenceException, FlooringDuplicateIdException, FlooringDataValidationException {
        Order order = new Order(LocalDate.parse("2019-10-01"), "mister mister", "KY", new BigDecimal("20.00"), "Tile");
        Order calculatedOrder = service.calculateCostsTaxesTotal(order);
        BigDecimal shouldBeArea = new BigDecimal("20.00");
        BigDecimal shouldBeCostPSFTile = new BigDecimal("1.01");
        BigDecimal shouldBeLabourCostPSFTile = new BigDecimal("11.01");
        BigDecimal shouldBeKYTax = new BigDecimal("12.02").divide(new BigDecimal("100.00"));
        BigDecimal shouldBeMaterialCost = shouldBeArea.multiply(shouldBeCostPSFTile).setScale(2, RoundingMode.HALF_UP);
        BigDecimal shouldBeLabourCost = shouldBeArea.multiply(shouldBeLabourCostPSFTile).setScale(2, RoundingMode.HALF_UP);
        BigDecimal shouldBeTax = (shouldBeMaterialCost.add(shouldBeLabourCost)).multiply(shouldBeKYTax).setScale(2, RoundingMode.HALF_UP);
        BigDecimal shouldBeTotal = shouldBeMaterialCost.add(shouldBeLabourCost).add(shouldBeTax).setScale(2, RoundingMode.HALF_UP);
        
        Assertions.assertEquals(shouldBeMaterialCost, order.getMaterialCost(), "Material cost should match calculation");
         Assertions.assertEquals(shouldBeLabourCost, order.getLabourCost(), "Labour cost should match calculation");
          Assertions.assertEquals(shouldBeTax, order.getTax(), "Tax should match calculation");
           Assertions.assertEquals(shouldBeTotal, order.getTotal(), "Total should match calculation");
        
        

    }
    @Test
    public void testGetMatchingOrderCorrectInfo() throws FilePersistenceException  {
        try{
            Order matchingOrder = service.getMatchingOrderForDate(LocalDate.parse("2020-12-20"), 1);
            Assertions.assertEquals("Jon Hodgman", matchingOrder.getCustomerName(), "Customer name should be Jon Hodgman" );
            Assertions.assertEquals(LocalDate.parse("2020-12-20"), matchingOrder.getDate(), "Date should be 2020-12-20" );
            Assertions.assertEquals(1, matchingOrder.getOrderNumber(), "Order number should be 1" );
            Assertions.assertEquals("KY", matchingOrder.getState(), "State should be KY" );
            Assertions.assertEquals(new BigDecimal(123.45).setScale(2, RoundingMode.HALF_UP), matchingOrder.getArea(), "Area should be 123.45");
            Assertions.assertEquals("Tile", matchingOrder.getProductType(), "Area should be 123.45");
           
        } catch (NoMatchingOrdersException e) {
            Assertions.fail("Exception [No Matching Orders] was thrown, but should not have been.");
        } 
        
    }
     @Test
    public void testGetMatchingOrderWrongDate() throws FilePersistenceException {
        Order matchingOrder = null;
        try{
             matchingOrder = service.getMatchingOrderForDate(LocalDate.parse("2020-12-31"), 2);
            Assertions.fail("Exception [No Matching Orders] should have been thrown, but wasn't");
        } catch (NoMatchingOrdersException e) {
           
        } 
        Assertions.assertNull(matchingOrder, "MatchingOrder should be null.");
    }
     @Test
    public void testGetMatchingOrderWrongOrderNumber() throws FilePersistenceException {
         Order matchingOrder = null;
        try{
             matchingOrder = service.getMatchingOrderForDate(LocalDate.parse("2020-12-20"), 3);
            Assertions.fail("Exception [No Matching Orders] should have been thrown, but wasn't");
        } catch (NoMatchingOrdersException e) {
           
        } 
        Assertions.assertNull(matchingOrder, "MatchingOrder should still be null.");
    }
     @Test
    public void testFirstLetterCapRestLower() {
        String toTest = "lowercase";
        String toTest2 = "UPPERCASE";
        String toTest3 = "mIxEDcase2394";
        String toTest4 = "bACKWARDS";
        Assertions.assertEquals("Lowercase", service.firstLetterCapRestLower(toTest), "Result should be Lowercase");
        Assertions.assertEquals("Uppercase", service.firstLetterCapRestLower(toTest2), "Result should be Uppercase");
        Assertions.assertEquals("Mixedcase2394", service.firstLetterCapRestLower(toTest3), "Result should be Mixedcase2394");
        Assertions.assertEquals("Backwards", service.firstLetterCapRestLower(toTest4), "Result should be Backwards");
    }
     @Test
    public void testCreatewwOrder() {
    }
 
}
