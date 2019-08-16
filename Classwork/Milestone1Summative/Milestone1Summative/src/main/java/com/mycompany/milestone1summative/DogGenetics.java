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
 * @author karlmarx
 */
public class DogGenetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomizer = new Random();
        int breedOne = randomizer.nextInt(100);
        int breedTwo = randomizer.nextInt(100 - breedOne);
        int breedThree = randomizer.nextInt(100 - breedOne - breedTwo);
        int breedFour = randomizer.nextInt(100 - breedOne - breedTwo - breedThree);
        int breedFive = (100 - breedOne - breedTwo - breedThree - breedFour);
        
        ///add in some line breaks maybe?
        System.out.println("What is your dog's name?");
        String inputDogName = scanner.nextLine();
        System.out.println("Well then, I have this highly reliable report on" + inputDogName + "'s prestigious background right here.");
        System.out.println("\n" + inputDogName + " is:");
        System.out.println(breedOne + "% St Bernard.");
        System.out.println(breedTwo + "% Communist Dachshund.");
        System.out.println(breedThree + "% Korean Jindo (진돗개).");
        System.out.println(breedFour + "% Ibizan Hound.");
        System.out.println(breedFive + "% Finnish Lapphund.");
        System.out.println("\n Wow, that's quite the dog!");
    }
}
