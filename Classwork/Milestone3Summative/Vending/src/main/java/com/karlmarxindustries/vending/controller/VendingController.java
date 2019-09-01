/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.controller;

import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.ui.VendingView;
import com.karlmarxindustries.vending.dao.AuditDao;
import java.util.List;
import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import com.karlmarxindustries.vending.service.ServiceLayer;
import java.math.BigDecimal;

/**
 *
 * @author karlmarx
 */
public class VendingController {
    VendingView view;
    ServiceLayer service;
    AuditDao audit;
    //CONTROLLER NEEDS TO HANDLE EXCEPTIONS!!!
    
    public VendingController(VendingView view, ServiceLayer service, AuditDao audit) {
        this.view = view;
        this.service = service;
        this.audit = audit;
    }

    public void run() throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException {
        boolean keepGoing = true;
        int menuSelection = 0;
        welcomeMessage();
        service.loadInventory();
        displayInventory();
        
       // try{
        while (keepGoing) {
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    insertMoney();
                    break;
                case 2:
                    buySomething();
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    invalidInput();
            }
        }
//        } catch (FilePersistenceException e){
//            view.displayErrorMessage(e.getMessage());
//        } - WHY??????????
        exitMessage();
    }
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    
    private void displayInventory() throws FilePersistenceException {
        view.displayDisplayAllBanner();
        List<Snack> snackList = service.getAllSnacksInStock();
        view.displayAllSnacks(snackList);
        
    }
   
    
    
    
   private void buySomething() throws InsufficientFundsException, ItemSoldOutException, FilePersistenceException {
            displayInventory();
            view.purchaseSnackBanner();
            Snack snack = null;
            String slotWanted = null;
            boolean correctSelection = false;
            boolean soldOut = true;
            boolean keepPurchasing = true;
            boolean insuffFunds = true;
            while(keepPurchasing){
                while(!correctSelection || soldOut || insuffFunds) {
                    try{                        
                        slotWanted = view.getSlotChoice();
                        snack = service.getOneItem(slotWanted); 
                        view.viewSnack(snack); 
                        if (snack != null){
                            correctSelection = view.confirmCorrectSelection();
                       
                        }
                         ChangeAndOutcome changeBack = service.purchaseItem(slotWanted, snack.getPrice()); //HOW TO LOOP IF SOLD OUT OR INSUFFICIENT FUNDS??
                       
                        view.displayPurchaseOutcome(changeBack); 
                        //what is going on with this getter???? 
                        /// if SUCCESS ONLY! otherwise 
                        if (changeBack.getOutcomeSuccess()) {
                            BigDecimal balanceAfterPurchase = service.deductPriceFromBalance(snack.getPrice());
                            view.displayChangeBack(changeBack.getChange());
                            view.displayCurrentBalance(balanceAfterPurchase);
                            soldOut = false;
                            insuffFunds = false;
                            view.displayChangeBack(changeBack.getChange());
                        } 
                        view.displayCurrentBalance(service.checkCurrentBalance());
                        if(snack.getQuantity() == 0) {
                            throw new ItemSoldOutException("Sold out");
                        } else if (service.checkCurrentBalance().compareTo(snack.getPrice())== -1) { //this is just a placeholder and will not actually work 
                           throw new InsufficientFundsException("Insufficient Funds");
                        }
                        
                    } catch (ItemSoldOutException e ){
                        System.out.println("Sold OUt. Returning to main menu");
                        return;
                    } catch (InsufficientFundsException f ){
                        System.out.println("insufficient funds:");
                        view.displayMoneyInside(service.checkCurrentBalance());
                        System.out.println("Please insert more funds or select a different item.");
                        return;
                    }
                    
                }
                
                keepPurchasing = view.confirmPurchasing();
            }
///display balance?            view.displayEditSuccessBanner(); 
    } 
    private void invalidInput() {
        view.displayInvalidInput();
    }
    private void exitMessage() throws FilePersistenceException {
        List<Snack> allSnacks = service.getAllSnacksInStock();
        service.writeInventory(allSnacks);
        view.displayExitBanner();
    }
    private void welcomeMessage(){
        view.displayWelcomeBanner();
    }

    private void insertMoney() {
        boolean keepAdding = true;
        while (keepAdding){
            BigDecimal moneyInputFromUser = view.displayAddMoneyBannerGetMoney();
            service.updateMoneyInside(moneyInputFromUser);
            view.displayMoneyInside(service.checkCurrentBalance());
            audit.stamp(service.checkCurrentBalance(), "After money was inserted");
            keepAdding = view.confirmContinueAdding();
        }
    }

    
    
}
