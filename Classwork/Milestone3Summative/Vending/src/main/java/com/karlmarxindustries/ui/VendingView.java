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
public class VendingView {
    private UserIO io;
    public VendingView(UserIO io) {
        this.io = io;
    }    

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("[1] Insert Coin");
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
        //this was original code for use just dollars and cents as input
//        int centsIn = io.readInt("How many cents would you like to add? 0-99", 0, 99);
//        int dollarsIn = io.readInt("How many dollars would you like to add?");
//        String stringCentsIn = null;
//        if (centsIn < 10) {
//            stringCentsIn = "0" + String.valueOf(centsIn);
//        } else {
//            stringCentsIn = String.valueOf(centsIn);
//        }
        int quartersIn = io.readInt("How many quarters would you like to insert?");
        int dimesIn = io.readInt("How many dimes would you like to insert?"); 
        int nickelsIn = io.readInt("How many nickels would you like to insert?");
        int penniesIn = io.readInt("How many pennies would you like to insert?");
        //Should i move the following to the service Layer?
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
    
//   public Snack getNewSnackInfo(List<Snack> dvdList){
//        boolean titleBlank = true;
//        String title = io.readString("Please enter Snack title: ").toUpperCase().trim();
//        boolean titleDuplicate = true;
//        int duplicateCount =0;
//        while (titleDuplicate){
//            while (titleBlank) {
//                if (!(title.equals(""))){
//                    titleBlank = false;
//                    } else {
//                    title = io.readString("Title is a required field. Please enter Snack title:").toUpperCase().trim();
//                    } 
//                for (Snack eachSnack: dvdList){
//                    if(eachSnack.getTitle().equalsIgnoreCase(title)) {
//                        duplicateCount ++;
//                    }  //this is to make sure unique as to not overwrite iterates through array and counts duplicates
//                } 
//            }
//            if (duplicateCount > 0) {
//                title = io.readString("That title is already in use. Please enter unique Snack title or remove the Snack:").toUpperCase().trim();
//                duplicateCount = 0;
//            } else {
//                titleDuplicate = false;
//            }
//        }
//        int releaseDate = io.readInt("Please input release date in format MMDDYYYY: "); // USE SPLIT TO MAKE IT LOOK NICER
//        String rating = io.readString("Please enter MPAA rating: ").toUpperCase();
//        String director = io.readString("Please enter director's name: ").toUpperCase();
//        String studio = io.readString("Please enter studio: ").toUpperCase();
//        String userRatingOrNote = io.readString("Please enter personal rating or other note: ").toUpperCase();
//        Snack currentSnack = new Snack(title);
//        if (rating.isBlank()){
//            rating = " ";
//        }
//        if (director.isBlank()){
//            director = " ";
//        }  
//        if (studio.isBlank()){
//            studio = " ";
//        }
//        if (userRatingOrNote.isBlank()){
//            userRatingOrNote = " ";
//        }
//        currentSnack.setRating(rating);
//        currentSnack.setReleaseDate(releaseDate);
//        currentSnack.setDirector(director);
//        currentSnack.setStudio(studio);
//        currentSnack.setUserRatingOrNote(userRatingOrNote);
//        return currentSnack;
//    }
//   
//    public Snack updateSnackInfo(Snack dvd){
//        boolean keepGoing = true;
//        while (keepGoing){
//            System.out.println("What would you like to update?");
//            int choice = io.readInt("Enter 1 for Release Date, 2 for MPAA rating, 3 for Director, 4 for Studio, or 5 for Note/Rating", 1, 6);
//            switch (choice){
//                case 1:
//                    int newReleaseDate = io.readInt("Please input new release date in format MMDDYYYY: ");
//                    if (newReleaseDate == 0){
//                        newReleaseDate = 0;
//                    }//THESE ARE TO PREVENT BLANKS IN FILE FOR PERSISTANCE
//                    dvd.setReleaseDate(newReleaseDate);
//                    break;
//                case 2:
//                     String newRating = io.readString("Please enter new MPAA rating: ");
//                     dvd.setRating(newRating);
//                     if (newRating.isBlank()){
//                         newRating = " ";
//                     }
//                     break;
//                case 3:
//                    String newDirector = io.readString("Please enter new director's name: ");
//                    dvd.setDirector(newDirector);
//                    if(newDirector.isBlank()){
//                         newDirector = " ";
//                     }
//                    break;
//                case 4:
//                    String newStudio = io.readString("Please enter new studio: ");
//                    dvd.setStudio(newStudio);
//                    if (newStudio.isBlank()){
//                         newStudio = " ";
//                     }
//                    break;
//                case 5:
//                    String newUserRatingOrNote = io.readString("Please enter new personal rating or other note: ");    
//                    dvd.setUserRatingOrNote(newUserRatingOrNote);
//                    if (newUserRatingOrNote.isBlank()){
//                         newUserRatingOrNote = " ";
//                     }
//                    break;
//                default:
//                    System.out.println("Please enter a valid option");
//                    ///put while loop to repeat if error OR multiple field edits
//            } 
//            if (choice >0 && choice <6) {
//                String continueEditing = io.readString("Would you like to update another field? (Y or N)");
//                if (continueEditing.equalsIgnoreCase("N")) {
//                keepGoing=false;
//                }
//            }
//        }
//        return dvd;
    
//    }
    public void displayAddSnackBanner() {
        io.print("=== Add Snack ===");
    }
   public void displayCreateSuccessBanner() {
        io.readString("Great Success! Snack added.  Please hit enter to continue");
    }
   public void displayAllSnacks(List<Snack> snackList) {
        int libraryCount = 0;
        for (Snack currentSnack : snackList) {
            io.print("=======Slot: " + currentSnack.getSlot() + "=======");
            io.print("Name: " + currentSnack.getName());
            io.print("Price: " + currentSnack.getPrice());
            io.print("Quantity Remaining: " + currentSnack.getQuantity()); //maybe comcin lines
            io.print("======================");
            libraryCount++;
            }   
        io.print("Total snacks in vending machine: " + libraryCount);
   //     io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All Snacks ===");
    }
    public void displayDisplaySnackBanner () {
        io.print("===== Search Snacks ====");
    }

    public String getSlotChoice() {
        String selection =  io.readString("Please enter the snack slot.").toUpperCase();
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
    
    public String getTitleChoiceExact() {
        return io.readString("Please enter the exact Snack title. (If unsure, please use search or list functions instead.)").toUpperCase();
    }
    
//   public void getTitleChoiceAndSearch(List<Snack> dvdList) {
//        boolean titleBlank = true;
//        String search = io.readString("Please enter all or part of the title you are looking for.").toUpperCase();
//        while (titleBlank) {
//            if (!(search.equals(""))){
//            titleBlank = false;
//            } else {
//                search = io.readString("Title is a REQUIRED field. Please enter Snack title:");
//            } 
//        }
//         int searchResults = 0;
//                 for (Snack currentSnack : dvdList) {
//                     if(currentSnack.getTitle().contains(search)){
//                            System.out.println("===Matching Title#" + (searchResults+1) + " ===");
//                            io.print("Title: " + currentSnack.getTitle());
//                            io.print("Release date: " + currentSnack.getReleaseDate());
//                            io.print("Rating: " + currentSnack.getRating());
//                            io.print("======================");
//                            searchResults++; //
//                     } 
//                     if (searchResults == 0) {
//                         System.out.println(":( No results found.  Please try again or choose 'List Snacks' to view entire library.");
//                         return; //this is to make it not confirm selection when there is no search result --> returns to menu
//                     }
//                }   
//        io.readString("Please hit enter to continue.");
//        }
//    //edit this to be a search
//
//    
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
//    public void displayViewSnackBanner () {
//        io.print("=== View Snack ===");
//       
//                
//    }
//    public void displayRemoveSuccessBanner(){
//        io.readString("High 5! Snack has been removed.  Press enter to continue.");
//    }
//     public void displayEditSuccessBanner(){
//        io.readString("Great work! Snack edit is complete.  Press enter to continue.");
//    }
    public void displayExitBanner() {
        io.print(" ____  _____ ____     _____         _   _         ");
        io.print("|    \\|  |  |    \\   |  _  |___ ___| |_|_|_ _ ___ ");
        io.print("|  |  |  |  |  |  |  |     |  _|  _|   | | | | -_|");
        io.print("|____/ \\___/|____/   |__|__|_| |___|_|_|_|\\_/|___|");
        io.print("Thank you for using Snack Machine!!!");
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
            io.print(" ____  _____ ____     _____         _   _         ");
            io.print("|    \\|  |  |    \\   |  _  |___ ___| |_|_|_ _ ___ ");
            io.print("|  |  |  |  |  |  |  |     |  _|  _|   | | | | -_|");
            io.print("|____/ \\___/|____/   |__|__|_| |___|_|_|_|\\_/|___|");
            io.print("Welcome to Snack Archive!");
    }
    public boolean confirmContinueAdding(){
       String selection = io.readString("Would you like to add more funds? (Enter 'Y' or 'N')");
       boolean continueAdding = selection.equalsIgnoreCase("Y");
       return continueAdding;
    }
//    public boolean confirmContinueRemoving(){
//       String selection = io.readString("Would you like to remove another Snack? (Enter 'Y' or 'N')");
//       boolean continueAdding = selection.equalsIgnoreCase("Y");
//       return continueAdding;
//    }

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
        io.print(changeBack.getNumQuarters() + " Quarters");
        io.print(changeBack.getNumDimes() + " Dimes");
        io.print(changeBack.getNumNickels() + " Nickels");
        io.print(changeBack.getNumPennies() + " Pennies"); 
    }

    

}


