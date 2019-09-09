///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.karlmarxindustries.vending.service;
//
//import com.karlmarxindustries.vending.dao.VendingDao;
//import com.karlmarxindustries.vending.dao.VendingDaoEmptyStubImpl;
//import com.karlmarxindustries.vending.dao.VendingDaoStubImpl;
//import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
//import com.karlmarxindustries.vending.dto.Snack;
//import com.karlmarxindustries.vending.exception.FilePersistenceException;
//import com.karlmarxindustries.vending.exception.InsufficientFundsException;
//import com.karlmarxindustries.vending.exception.ItemSoldOutException;
//import java.math.BigDecimal;
//import java.util.List;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author karlmarx
// */
//
//public class ServiceLayerImplTestWithEmptyDao {
//    private VendingServiceLayer service;
//    private VendingDao dao; //is this okay to use? instead of VendingDao data type
//    
//     
//    public ServiceLayerImplTestWithEmptyDao() {
//
//        ApplicationContext ctx = 
//            new ClassPathXmlApplicationContext("testApplicationContext.xml");
//        service = ctx.getBean("serviceLayerEmptyDAO", VendingServiceLayer.class);
//       
//
//    }
// 
//    @Test 
//    public void testGetAllSnacksInStock() throws FilePersistenceException {
//        List<Snack> initialSnacks =  service.getAllSnacksInStock();
//        List<Snack> snacksToZeroQuantity = initialSnacks;
//        
//        List<Snack> zeroQuantitySnacks = service.getAllSnacksInStock();
//        Assertions.assertEquals(0, initialSnacks.size(), "There should be 0 snacks with non-zero quantity");
//        Assertions.assertEquals(0, zeroQuantitySnacks.size(), "this should be empty because quantities are zero ");
//    }
//    @Test
//    public void testGetOneItem() throws FilePersistenceException {
//        
//        String keyToTest = "anything";
//        
//        Snack shouldBeNull = service.getOneItem("T1");
//        Snack shouldBeNullAlso = service.getOneItem("T2");
//        Snack shouldBeNullAsWell = service.getOneItem("T3");
//
//
//        Assertions.assertNull(shouldBeNull, "this should be null as slot is unused T3"); //are these two verbose
//
//    }
//    
//
//    @Test
//    public void testPurchaseItemQuantity() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
//        //service.loadInventory();
//        service.updateMoneyInside(new BigDecimal("100.00"));
//        int quantityBeforeSale = service.getOneItem("T1").getQuantity();
//        service.purchaseItem("T1", new BigDecimal("20.00"));
//        int quantityAfterSale = service.getOneItem("T1").getQuantity();
//        
//        Assertions.assertEquals(quantityBeforeSale -1, quantityAfterSale, "Quantity should be 1 less after sale.");
//    }
//    @Test
//    public void testPurchaseItemNotEnoughMoney() throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException{
//        service.updateMoneyInside(new BigDecimal("0.50"));
//        try {
//            service.purchaseItem("T1", BigDecimal.ONE);
//            Assertions.fail("this method failed to throw Insufficient funds exception.");
//        } catch (InsufficientFundsException e){
//            
//        } catch (ItemSoldOutException f ) {
//            Assertions.fail("this method threw the wrong exception(Item Sold Out).");
//        }
//        
//    }
//    @Test
//    public void testPurchaseItemQuantityZero() throws InsufficientFundsException, FilePersistenceException, ItemSoldOutException {
//        boolean itemSoldOutExceptionThrown = false;
//        service.updateMoneyInside(new BigDecimal("100.00"));
//        service.getOneItem("T1").setQuantity(0);
//        try {
//            service.purchaseItem("T1", new BigDecimal("1.00"));
//        } catch (ItemSoldOutException e ){
//            itemSoldOutExceptionThrown = true; 
//        } catch (InsufficientFundsException f ) {
//            Assertions.fail("this method threw the wrong exception(Insufficient Funds).");
//        }
//
//        int quantityAfterSale = service.getOneItem("T1").getQuantity();
//        Assertions.assertEquals(0, quantityAfterSale, "Quantity should remain 0 after sale.");
//        Assertions.assertEquals(true, itemSoldOutExceptionThrown, "The Item sold out exception should be thrown when quantity of item is 0.");
//    }
//    
//    
//    
////    @Test 
////    public void testPurchaseItemCorrectChangeGivenFourQuarters() throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException {
////        service.updateMoneyInside(new BigDecimal("4.00"));
////        ChangeAndOutcome change =  service.purchaseItem("T2", BigDecimal.ONE);
////        
////        Assertions.assertEquals(4, change.getChange().getNumQuarters(), "Change should include 4 quarters.");
////        Assertions.assertEquals(0, change.getChange().getNumDimes(), "Change should include 0 dimes.");
////        Assertions.assertEquals(0, change.getChange().getNumNickels(), "Change should include 0 nickels.");
////        Assertions.assertEquals(0, change.getChange().getNumPennies(), "Change should include 0 pennies.");
////        Assertions.assertTrue(change.getOutcomeSuccess(), "Outcome(success) should be true");
////    } 
//    @Test
//    public void testAddToMoney() throws FilePersistenceException {
//        BigDecimal beginningBalance = service.checkCurrentBalance();
//        service.addToMoneyInside(new BigDecimal("1.00"));
//        BigDecimal endBalance = service.checkCurrentBalance();
//        int scaleOfEndBalance = endBalance.scale();
//        Assertions.assertEquals(endBalance.subtract(beginningBalance), new BigDecimal("1.00"), "difference b/w beginning and end balance should be $1.00"); 
//        Assertions.assertEquals(2,scaleOfEndBalance, "scale should be 2");
//    }
//    @Test
//    public void testDeductPriceFromBalance() throws FilePersistenceException {
//        service.updateMoneyInside(new BigDecimal("100.00"));
//        BigDecimal beginningBalance = service.checkCurrentBalance();
//        service.deductPriceFromBalance(BigDecimal.ONE);
//        BigDecimal balanceAfter = service.checkCurrentBalance();
//        Assertions.assertEquals(new BigDecimal("100.00"), beginningBalance, "ending balance should be $100.00");
//        Assertions.assertEquals(new BigDecimal("99.00"), balanceAfter, "ending balance should be $99.00");
//    }
//    @Test
//    public void testUpdateMoneyInside() throws FilePersistenceException{
//        service.updateMoneyInside(new BigDecimal("1.00"));
//        BigDecimal beginningBalance = service.checkCurrentBalance();
//        service.updateMoneyInside(new BigDecimal("1000.00"));
//        BigDecimal endBalance = service.checkCurrentBalance();
//        
//        Assertions.assertEquals(new BigDecimal("1.00"), beginningBalance, "balance should be 1.00 after updating");
//        Assertions.assertEquals(new BigDecimal("1000.00"), endBalance, "balance should be 1000.00 after updating");
//    }
//    
//  
//}
