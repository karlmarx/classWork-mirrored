/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.ui;

import com.karlmarxindustries.vending.dto.Change;
import com.karlmarxindustries.vending.dto.ChangeAndOutcome;
import com.karlmarxindustries.vending.dto.Coins;
import static com.karlmarxindustries.vending.dto.Coins.QUARTER;
import com.karlmarxindustries.vending.dto.Snack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class VendingViewCoinByCoin {
    private UserIO io;
    public VendingViewCoinByCoin(UserIO io) {
        this.io = io;
    }    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("[1] Insert Coins");
        io.print("[2] Buy Something");
        io.print("[3] Exit");
        return io.readInt("Please select from the above choices.", 1, 3);
    }
    public boolean confirmPurchasing(){
       String selection = io.readString("Would you like to purchase another Snack? (Enter 'Y' or 'N')");
       boolean continueAdding = selection.equalsIgnoreCase("Y");
       return continueAdding;
    }
    public void displayPurchaseOutcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void insertCoin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void displayMoneyInside(BigDecimal change) {
        io.print("Current balance: $" + change);
    }
    public BigDecimal displayAddMoneyBannerGetMoney() {
        io.print("=== Add Funds ===");
        int quartersIn = io.readInt("How many quarters would you like to insert?", 0, 2147483647);
        int dimesIn = io.readInt("How many dimes would you like to insert?", 0, 2147483647); 
        int nickelsIn = io.readInt("How many nickels would you like to insert?", 0, 2147483647);
        int penniesIn = io.readInt("How many pennies would you like to insert?", 0, 2147483647);
        BigDecimal moneyInserted = Coins.QUARTER.value.multiply(new BigDecimal(quartersIn)); 
        moneyInserted = moneyInserted.add(Coins.DIME.value.multiply(new BigDecimal(dimesIn)));
        moneyInserted = moneyInserted.add(Coins.NICKEL.value.multiply(new BigDecimal(nickelsIn)));
        moneyInserted = moneyInserted.add(Coins.PENNY.value.multiply(new BigDecimal(penniesIn)));
        BigDecimal moneyInScale = moneyInserted.setScale(2, RoundingMode.HALF_UP); //am I scaling at right time?
        return moneyInScale;
    }
    public void displayCurrentBalance(BigDecimal amount) {
        io.print("Current Balance: $" + amount);
    }
   public void displayAllSnacks(List<Snack> snackList) {
        int libraryCount = 0;
        for (Snack currentSnack : snackList) {
            io.print("========= Slot: " + currentSnack.getSlot() + " =========");
            io.print("Name: " + currentSnack.getName());
            io.print("Price: $" + currentSnack.getPrice() + " | Quantity:" + currentSnack.getQuantity());//maybe comcin lines
            io.print("============================");
            libraryCount++;
            }   
        io.print("Total snacks in THE MACHINE: " + libraryCount);
    }
    public void displayDisplayAllBanner() {
        io.print("==== Display All Snacks ==+=");
    }
    public String getSlotChoice() {
        String selection =  io.readString("Please enter the snack slot.(A1-A6)").toUpperCase();
        boolean blankEntry = true;
        while (blankEntry) {
            if (!(selection.equals(""))){
                blankEntry = false;
            } else {
                selection = io.readString("You must select a slot to continue:");
            } 
        }
        return selection;
    }
    public void viewSnack(Snack snack) {
        if (snack != null) {
            io.print("Slot: " + snack.getSlot());
            io.print("Name: " + snack.getName());
            io.print("Price: $" + snack.getPrice());
            io.print("Quantity: " + snack.getQuantity());
           
        } else {
            io.print("There is no snack at this slot.");
            return;
        }
    }
    public void displayRemoveSnackBanner () {
        io.print("=== Remove Snack ===");
    }
    public void purchaseSnackBanner () {
        io.print("=== Purchase Snack ===");
    }
    public void displayExitBanner() {
        io.print(" ##### #    # ######    #    #   ##    ####  #    # # #    # ###### ");
        io.print("   #   #    # #         ##  ##  #  #  #    # #    # # ##   # #      ");
        io.print("   #   ###### #####     # ## # #    # #      ###### # # #  # #####  ");
        io.print("   #   #    # #         #    # ###### #      #    # # #  # # #      ");
        io.print("   #   #    # #         #    # #    # #    # #    # # #   ## #      ");
        io.print("   #   #    # ######    #    # #    #  ####  #    # # #    # ###### ");
        io.print("Thank you for using and being used by THE MACHINE!!!");
    }
    public void displayInvalidInput() {
        io.print("Invalid Input! Try Again");
    }
    public boolean confirmCorrectSelection(){
       String selection = io.readString("Is this the correct snack? (Enter 'Y' or 'N')");
       boolean correctSelection = selection.equalsIgnoreCase("Y");
       return correctSelection;
     }
    public void displayErrorMessage(String errorMsg){
            io.print("--- ERROR ---");
            io.print(errorMsg);
     }
    public void displayWelcomeBanner(){
            io.print(" ##### #    # ######    #    #   ##    ####  #    # # #    # ###### ");
            io.print("   #   #    # #         ##  ##  #  #  #    # #    # # ##   # #      ");
            io.print("   #   ###### #####     # ## # #    # #      ###### # # #  # #####  ");
            io.print("   #   #    # #         #    # ###### #      #    # # #  # # #      ");
            io.print("   #   #    # #         #    # #    # #    # #    # # #   ## #      ");
            io.print("   #   #    # ######    #    # #    #  ####  #    # # #    # ###### ");
            io.print("Welcome to THE MACHINE!");
    }
    public boolean confirmContinueAdding(){
       String selection = io.readString("Would you like to add more funds? (Enter 'Y' or 'N')");
       boolean continueAdding = selection.equalsIgnoreCase("Y");
       return continueAdding;
    }
    public void displayChangeBack(Change changeBack) {
        System.out.println("Don't forget to take your change!");
        for (int i = 0; i < changeBack.getNumQuarters(); i++) {
            System.out.print("Q");
        }
        io.print(" ");
        for (int i = 0; i < changeBack.getNumDimes(); i++) {
            System.out.print("D");
        }
        io.print(" ");
        for (int i = 0; i < changeBack.getNumQuarters(); i++) {
            System.out.print("N");
        }
        io.print(" ");
        for (int i = 0; i < changeBack.getNumPennies(); i++) {
            System.out.print("P");
        }
        io.print(" ");
        io.print(changeBack.getNumQuarters() + " Quarters");
        io.print(changeBack.getNumDimes() + " Dimes");
        io.print(changeBack.getNumNickels() + " Nickels");
        io.print(changeBack.getNumPennies() + " Pennies"); 
    }
}


