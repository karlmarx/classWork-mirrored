/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factorizer;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author karlmarx
 */
public class luckySevens {
        public static void main(String[] args) {
        Random randomizer = new Random();
        Scanner scanner = new Scanner(System.in); 
        
            int rollTally = 0;
            int currentMaximum = 0; 
            int rollAtMax = 0;
        
            System.out.println("How many dollars do you have?");
            int userDollars = scanner.nextInt();
       
        
            while(userDollars > 0){
            int dieRoll = randomizer.nextInt(6)+randomizer.nextInt(6)+2;
            if (dieRoll == 7) {
                userDollars += 4; 
                if (userDollars > currentMaximum){
                    currentMaximum = userDollars;
                    rollAtMax = rollTally;
                }
            } else {    
                userDollars -= 1;
            }
            rollTally ++;
        }
            System.out.println("You are broke after " + rollTally + " rolls.");  
            System.out.println("You should have quit after " + rollAtMax + " rolls when you had $" + currentMaximum + ".");
        
    }
}
