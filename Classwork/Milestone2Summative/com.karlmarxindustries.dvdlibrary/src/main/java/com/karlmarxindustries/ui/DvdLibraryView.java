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
        io.print("3. View a Student");
        io.print("4. Remove a DVD");
        //add search function
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
   public DVD getNewStudentInfo(){
        String title = io.readString("Please enter DVD title: ");
        int releaseDate = io.readInt("Please input release date in format MMDDYYYY: "); //make sure to validate this input
        String rating = io.readString("Please enter MPAA rating: ");
        String director = io.readString("Please enter director's name: ");
        String studio = io.readString("Please enter studio: ");
        String userRatingOrNote = io.readString("Please enter personal rating or other note: ");
        DVD currentDVD = new DVD(title);
        currentDVD.setRating(rating);
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setUserRatingOrNote(userRatingOrNote);
        return currentDVD;
    }
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }
   public void displayCreateSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }
   public void displayStudentList(List<DVD> studentList) {
        for (DVD currentStudent : studentList) {
            io.print(currentStudent.getTitle() + ": "
                + currentStudent.getRating() + " "
                + currentStudent.getDirector());
            }   
        io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }
    public void displayDisplayStudentBanner () {
        io.print("=== Display Student ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the Student ID.");
    }

    public void displayStudent(DVD student) {
        if (student != null) {
            io.print(student.getTitle());
            io.print(student.getRating() + " " + student.getDirector());
            io.print(student.getStudio());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }
    public void displayRemoveSuccessBanner(){
        io.readString("Student successfully removed.  Please hit enter to continue.");
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
}
