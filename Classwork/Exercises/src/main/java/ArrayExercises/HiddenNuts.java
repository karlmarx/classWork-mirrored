/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArrayExercises;

import java.util.Random;

/**
 *
 * @author karlmarx
 */
public class HiddenNuts {
    public static void main(String[] args) {
        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        
    for (String i : hidingSpots){
    if (("Nut".equals(hidingSpots))) {
        System.out.println("The nut is located at index " + i +".");
    } 
}
    }
    
    
}
