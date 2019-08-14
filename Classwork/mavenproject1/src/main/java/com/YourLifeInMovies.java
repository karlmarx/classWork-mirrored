/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class YourLifeInMovies {
    public static void main(String[] args) {
        String userName;
        int birthYear;
        
        Scanner scr = new Scanner(System.in);

        System.out.println("Hey, let's play a game! What's your name?");
        userName = scr.nextLine();
        
        System.out.println("Ok, " +  userName + ", when were you born?");
        birthYear = scr.nextInt();
        
        System.out.println("Well, " + userName + "...");
        if (birthYear < 2005) {
            System.out.println("Pixar's 'Up' came out half a decade ago.");
        }
        
        if (birthYear < 1995) {
            System.out.println("The first Harry Potter movie came out over 15 years ago.");
        }
        
        if (birthYear < 1985){
            System.out.println("Space Jam came out more than 2 decades ago.");
        }
        
        if (birthYear < 1975){
            System.out.println("Jurassic Park is closer to the date of the first lunar landing than it is to today.");
        }
        
        if (birthYear < 1965){
            System.out.println("MASH has been around for almost half a century.");
        }
        
        
    }
}
