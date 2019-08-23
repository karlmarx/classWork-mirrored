/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.milestone1summative;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author karlmarxA
 */
public class DogGenetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomizer = new Random();
        int breedOne = 0;
        int breedTwo = 0;
        int breedThree = 0;
        int breedFour = 0;
        int breedFive = 0;
        
        while (breedFive <= 0 || breedOne == 100 ||  breedOne - breedTwo == 100 || breedOne - breedTwo - breedThree == 100) {
                breedOne = randomizer.nextInt(96)+1;
                breedTwo = randomizer.nextInt(100 - breedOne)+1;
                breedThree = randomizer.nextInt(100 - breedOne - breedTwo)+1;
                breedFour = randomizer.nextInt(100 - breedOne - breedTwo - breedThree)+1;
                breedFive = (100 - breedOne - breedTwo - breedThree - breedFour);
        }  
        /* I changed this to a while loop to prevent 0% for any breed.  breedOne - breedFour cannot be zero 
        because they are all random integer +1.  breedFive could be 0 or less, and if so the while loop will 
        repeat until breedFive is more than 0. This also prevents randomizer from using a negative 
        parameter.*/
        
        System.out.println("What is your dog's name?");
        String inputDogName = scanner.nextLine();
        System.out.println("Well then, I have this highly reliable report on" + inputDogName + " "
                + "'s prestigious background right here.");
        System.out.println("\n" + inputDogName + " is:");
        System.out.println(breedOne + "% St Bernard.");
        System.out.println(breedTwo + "% Communist Dachshund.");
        System.out.println(breedThree + "% Korean Jindo (진돗개).");
        System.out.println(breedFour + "% Ibizan Hound.");
        System.out.println(breedFive + "% Finnish Lapphund.");
        System.out.println("\n Wow, that's quite the dog!");
    }
}
