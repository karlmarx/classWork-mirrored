/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.ui;

import com.karlmarxindustries.dvdlibrary.dto.DVD;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author karlmarx
 */
public class DvdLibraryView {

    private UserIO io;
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }    

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("[1] List DVDs");
        io.print("[2] Add a DVD");
        io.print("[3] Search for a DVD");
        io.print("[4] Remove a DVD");
        io.print("[5] Edit Existing DVD");
        io.print("[6] View a DVD listing");
        io.print("[7] Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
   public DVD getNewDvdInfo(){
        boolean titleBlank = true;
        String title = io.readString("Please enter DVD title: ").toUpperCase().trim();
        while (titleBlank) {
            if (!(title.equals(""))){
            titleBlank = false;
            } else {
                title = io.readString("Title is a required field. Please enter DVD title:");
            } 
        }
        int releaseDate = io.readInt("Please input release date in format MMDDYYYY: "); // USE SPLIT TO MAKE IT LOOK NICER
        String rating = io.readString("Please enter MPAA rating: ").toUpperCase();
        String director = io.readString("Please enter director's name: ").toUpperCase();
        String studio = io.readString("Please enter studio: ").toUpperCase();
        String userRatingOrNote = io.readString("Please enter personal rating or other note: ").toUpperCase();
        DVD currentDVD = new DVD(title);
        currentDVD.setRating(rating);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserRatingOrNote(userRatingOrNote);
        return currentDVD;
    }
   
    public DVD updateDvdInfo(DVD dvd){
        boolean keepGoing = true;
        while (keepGoing){
        System.out.println("What would you like to update?");
       int choice = io.readInt("Enter 1 for Release Date, 2 for MPAA rating, 3 for Director, 4 for Studio, or 5 for Note/Rating", 1, 6);
        switch (choice){
            case 1:
                int newReleaseDate = io.readInt("Please input new release date in format MMDDYYYY: "); //reset scanner
                dvd.setReleaseDate(newReleaseDate);
                break;
            case 2:
                 String newRating = io.readString("Please enter new MPAA rating: ");
                 dvd.setRating(newRating);
                 break;
            case 3:
                String newDirector = io.readString("Please enter new director's name: ");
                dvd.setDirector(newDirector);
                break;
            case 4:
                String newStudio = io.readString("Please enter new studio: ");
                dvd.setStudio(newStudio);
                break;
            case 5:
                String newUserRatingOrNote = io.readString("Please enter new personal rating or other note: ");    
                dvd.setUserRatingOrNote(newUserRatingOrNote);
                break;
            default:
                System.out.println("Please enter a valid option");
                ///put while loop to repeat if error OR multiple field edits
        } if (choice >0 && choice <6) {
            String continueEditing = io.readString("Would you like to update another field? (Y or n)");
            if (continueEditing.equalsIgnoreCase("N")) {
                keepGoing=false;
                
                //WHAT TO DO NEXT?
            }
        }
        }
         //make sure to validate this input
        return dvd;
        //add step showing updated entry
    
    }
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
   public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
   public void displayDvdList(List<DVD> dvdList) {
        int libraryCount = 0;
        for (DVD currentDvd : dvdList) {
            io.print("Title: " + currentDvd.getTitle());
            io.print("Release date: " + currentDvd.getReleaseDate());
            io.print("Rating: " + currentDvd.getRating());
            io.print("Director: " + currentDvd.getDirector());
            io.print("Studio: " + currentDvd.getStudio());
            io.print("User Rating/Notes: " + currentDvd.getUserRatingOrNote());
            io.print("======================");
            libraryCount++;
            }   
        io.print("Total DVDs in library: " + libraryCount);
        io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    public void displayDisplayDvdBanner () {
        io.print("=== Search DVD Banner ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD title.").toUpperCase();
    }
    
    public String getTitleChoiceExact() {
        return io.readString("Please enter the exact DVD title. (If unsure, please use search or list functions instead.").toUpperCase();
    }
    
       public void getTitleChoiceAndSearch(List<DVD> dvdList) {
        boolean titleBlank = true;
        String search = io.readString("Please enter all or part of the title you are looking for.").toUpperCase();
        while (titleBlank) {
            if (!(search.equals(""))){
            titleBlank = false;
            } else {
                search = io.readString("Title is a required field. Please enter DVD title:");
            } 
        }
         int searchResults = 0;
                 for (DVD currentDvd : dvdList) {
                     if(currentDvd.getTitle().contains(search)){
                            System.out.println("===Matching Title#" + (searchResults+1) + " ===");

                            io.print("Title: " + currentDvd.getTitle());
                            io.print("Release date: " + currentDvd.getReleaseDate());
                            io.print("Rating: " + currentDvd.getRating());
                            io.print("Director: " + currentDvd.getDirector());
                            io.print("Studio: " + currentDvd.getStudio());
                            io.print("User Rating/Notes: " + currentDvd.getUserRatingOrNote());
                            io.print("======================");
                            searchResults++;
                     } 
                     if (searchResults == 0) {
                         System.out.println("No results found.  Please try again or choose 'List DVDs' to view entire library.");
                     }
                }   
                
        io.readString("Please hit enter to continue.");
      
        }
    //edit this to be a search

    
    //edit can be search, is this correct?, yes or no to edit
    public void viewDvd(DVD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release date: " + dvd.getReleaseDate());
            io.print("Rating: " + dvd.getRating());
            io.print("Director: " + dvd.getDirector());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating/Notes: " + dvd.getUserRatingOrNote());
            io.print("");
        } else {
            io.print("No such DVD. Please use 'Search' or 'List' to confirm title.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }
    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }
    public void displayViewDvdBanner () {
        io.print("=== View DVD ===");
       
                
    }
    public void displayRemoveSuccessBanner(){
        io.readString("DVD successfully removed.  Please hit enter to continue.");
    }
     public void displayEditSuccessBanner(){
        io.readString("DVD successfully edited.  Please hit enter to continue.");
    }
    public void displayExitBanner() {
        io.print("Thank you for using DVD Archive!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public boolean confirmCorrectSelection(){
       String selection = io.readString("Is this the correct DVD? (Enter 'Y' or 'N'");
       boolean correctSelection = selection.equalsIgnoreCase("Y");
       return correctSelection;
     }
    public void displayErrorMessage(String errorMsg){
            io.print("--- ERROR ---");
            io.print(errorMsg);
     }
    public void displayWelcomeBanner(){
            io.print("Welcome to DVD Archive!");
    }
}
