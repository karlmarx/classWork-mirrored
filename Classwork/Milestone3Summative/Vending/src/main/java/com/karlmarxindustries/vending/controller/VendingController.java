/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.vending.controller;

import com.karlmarxindustries.vending.exception.FilePersistenceException;
import com.karlmarxindustries.vending.dao.VendingDaoFileImpl;
import com.karlmarxindustries.vending.dto.DVD;
import com.karlmarxindustries.ui.VendingView;
import com.karlmarxindustries.ui.UserIO;
import com.karlmarxindustries.ui.UserIOConsoleImpl;
import java.util.List;
import com.karlmarxindustries.vending.dao.VendingDao;
import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.Snack;
import com.karlmarxindustries.vending.exception.InsufficientFundsException;
import com.karlmarxindustries.vending.exception.ItemSoldOutException;
import com.karlmarxindustries.vending.service.ServiceLayer;

/**
 *
 * @author karlmarx
 */
public class VendingController {
    VendingView view;
    VendingDao dao;
    ServiceLayer service;
    
    public VendingController(VendingDao dao, VendingView view, ServiceLayer service) {
        this.dao = dao;
        this.view = view;
        this.service = service;
    }

    public void run() throws FilePersistenceException, InsufficientFundsException, ItemSoldOutException {
        boolean keepGoing = true;
        int menuSelection = 0;
        welcomeMessage();
        dao.loadInventory();
       // try{
        while (keepGoing) {
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    insertCoin();
                    break;
                case 2:
                    buySomething();
                    break;
                case 7:
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
        List<Snack> snackList = service.getAllSnacksInMachine();
        view.displayAllSnacks(snackList);
    }
   
    
    
    
   private void buySomething() throws InsufficientFundsException, ItemSoldOutException, FilePersistenceException {
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
                        view.insertCoin();
                        dao.updateMoneyInside();
                        slotWanted = view.getTitleChoice();
                        snack = service.getOneItem(slotWanted); 
                        view.viewSnack(snack); 
                        correctSelection = view.confirmCorrectSelection();
                        Change change = service.purchaseItem(slotWanted, snack.getPrice()); //HOW TO LOOP IF SOLD OUT OR INSUFFICIENT FUNDS??
                        view.displayPurchaseOutcome();
                        
                        if(snack == null) {
                            throw new ItemSoldOutException("asd");
                        } else if (change == null) { //this is just a placeholder and will not actually work 
                           throw new InsufficientFundsException("asdasd");
                        }
                        soldOut = false;
                        insuffFunds = false;
                    } catch (ItemSoldOutException e ){
                        continue;
                    } catch (InsufficientFundsException f ){
                        view.displayMoneyInside();
                        continue;
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
        dao.writeInventory();
        view.displayExitBanner();
    }
    private void welcomeMessage(){
        view.displayWelcomeBanner();
    }

    private void insertCoin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    
    
}
