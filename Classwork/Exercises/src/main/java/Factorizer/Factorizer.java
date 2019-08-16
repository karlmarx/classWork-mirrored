/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizer;

import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class Factorizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which number would you like to factor?");
        int input = scanner.nextInt();  
        int factorCounter = 0;
        int factorSum = 0;
        System.out.println("You have selected " + input + ".");
        int[] toFactor = new int[input];
        for (int i = 0; i < input; i++) {
            toFactor[i] = i + 1;
        }
       System.out.println("The factors of " + input + " are as follows: ");
        
        for (int i=0; i < input; i++) {
            if ((input % toFactor[i] ) == 0) {
                System.out.println(toFactor[i] + "   ");
                factorCounter += 1;
                factorSum += toFactor[i];
                        }
        }
            
            if (factorSum - input == input) {
                System.out.println("Also," + input + "is a perfect number.");
            }
                
            if (factorCounter == 2) {
                System.out.println("Also," + input + "is a prime number.");
            }
       
        
    }
}
