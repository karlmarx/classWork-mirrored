/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.controller;

import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.ui.VendingView;
import java.util.List;
import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import java.math.BigDecimal;
import com.karlmarxindustries.vending.service.VendingServiceLayer;
import com.karlmarxindustries.vending.dao.VendingAuditDao;

/**
 *
 * @author karlmarx
 */
public class VendingController {
    VendingView view;
    VendingServiceLayer service;
    //CONTROLLER NEEDS TO HANDLE EXCEPTIONS!!!
    
    public VendingController(VendingView view, VendingServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException {
        boolean keepGoing = true;
        int menuSelection = 0;
        welcomeMessage();
        service.loadInventory();
        displayInventory();
        
        try{
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
        } catch (FilePersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
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
        Snack snack;
        String slotWanted = null;
//        boolean soldOut = true;
//        boolean insuffFunds = true;

        displayInventory();
        view.purchaseSnackBanner();
        boolean correctSelection = false;
        while(!correctSelection) {
            try{                        
                slotWanted = view.getSlotChoice();
                snack = service.getOneItem(slotWanted); 
                view.viewSnack(snack);
                if (snack != null){
                    correctSelection = view.confirmCorrectSelection();
                    view.displayBeginningBalance(service.checkCurrentBalance());
                    ChangeAndOutcome changeBack = service.purchaseItem(slotWanted, snack.getPrice()); 
                    if (changeBack.getOutcomeSuccess()) {
                        BigDecimal balanceAfterPurchase = service.deductPriceFromBalance(snack.getPrice());
                        service.updateMoneyInside(balanceAfterPurchase);
                        view.displaySuccessBanner(snack);
                        view.displayChangeBack(changeBack.getChange());
                        service.updateMoneyInside(new BigDecimal("0.00")); 
                    //redundant    view.displayCurrentBalance(balanceAfterPurchase);
//                        soldOut = false;
//                        insuffFunds = false;
                    } 
                    
                } else {
                    return;
                }
            }
            catch (ItemSoldOutException e) {
                view.displayErrorMessage(e.getMessage());
                return;
            } catch (InsufficientFundsException f) {
                view.displayErrorMessage(f.getMessage());
                view.displayMoneyInside(service.checkCurrentBalance());
                return;
            }
        }
    } 
    private void invalidInput() {
        view.displayInvalidInput();
    }
    private void exitMessage() throws FilePersistenceException {
        List<Snack> allSnacks = service.getAllSnacksInStock();
        service.writeInventoryOnExit(allSnacks);
        view.displayExitBanner();
    }
    private void welcomeMessage(){
        view.displayWelcomeBanner();
    }
    private void insertMoney() throws FilePersistenceException {
        boolean keepAdding = true;
        while (keepAdding){
            BigDecimal moneyInputFromUser = view.displayAddMoneyBannerGetMoney();
            service.addToMoneyInside(moneyInputFromUser);
            view.displayMoneyInside(service.checkCurrentBalance());
            keepAdding = view.confirmContinueAdding();
        }
    }
}
