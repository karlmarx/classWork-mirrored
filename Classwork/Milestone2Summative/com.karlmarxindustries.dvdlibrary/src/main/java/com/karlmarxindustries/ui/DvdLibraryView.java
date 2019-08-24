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
        io.print("1. List DVDs");
        io.print("2. Add a DVD");
        io.print("3. Search for a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit Existing DVD");
        //add search function
        //add edit function
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
   public DVD getNewDvdInfo(){
        String title = io.readString("Please enter DVD title: ");
        int releaseDate = io.readInt("Please input release date in format MMDDYYYY: "); //make sure to validate this input
        String rating = io.readString("Please enter MPAA rating: ");
        String director = io.readString("Please enter director's name: ");
        String studio = io.readString("Please enter studio: ");
        String userRatingOrNote = io.readString("Please enter personal rating or other note: ");
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
        for (DVD currentDvd : dvdList) {
            io.print(currentDvd.getTitle() + ": "
                + currentDvd.getRating() + " "
                + currentDvd.getDirector()); //ADD IN THE OTHER STUFF
            }   
        io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD Banner ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }
    //edit this to be a search

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getRating() + " " + dvd.getDirector());
            io.print(dvd.getStudio());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }
    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }
    public void displayRemoveSuccessBanner(){
        io.readString("DVD successfully removed.  Please hit enter to continue.");
    }
     public void displayEditSuccessBanner(){
        io.readString("DVD successfully edited.  Please hit enter to continue.");
    }
    public void displayExitBanner() {
        io.print("Goodbye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public boolean confirmCorrectSelection(){
       String selection = ("Is this the correct DVD? (Enter 'Y' or 'N'");
       boolean correctSelection = selection.equalsIgnoreCase("Y");
       return correctSelection;
     }
}
