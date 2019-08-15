/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karlmarx
 */

import static java.lang.Float.parseFloat;
import java.util.Scanner;

public class CtoFFtoC {
        public static void main(String[] args){
                Scanner inputReader = new Scanner(System.in);
            
                float userInputTemp;
                
                String tempScaleCode; 
                String isAnother;
                
                
                System.out.println("User: What temperature would you like to convert? (in degrees)");
                userInputTemp = parseFloat(inputReader.nextLine()); 
                System.out.println("Thank you, User! Which temperature scale would you like to convert from? Enter C for Celsius, F for Fahrenheit");
                tempScaleCode = inputReader.nextLine();
                
                if  (tempScaleCode.equalsIgnoreCase("C")) {
                    System.out.println(userInputTemp + " degrees Celsius is equivalent to " + ((userInputTemp *1.8) + 32) + " degrees Fahrenheit.");
                } else if (tempScaleCode.equalsIgnoreCase("F")) {
                    System.out.println(userInputTemp + " degrees Fahrenheit is equivalent to " + ((userInputTemp -32) * 0.556) + " degrees Celsius.");
                } else {
                    System.out.println("Invalid input. Please follow the instructions");
                }
                
                 System.out.println("Would you like to complete another conversion? Y for Yes or N for No");
                 isAnother = inputReader.nextLine();
                 
                 if (isAnother.equalsIgnoreCase("Y"))
                         {
                             
                                     float userInputTemp2;
              
                                    String tempScaleCode2; 
                                    String isAnother2;


                                    System.out.println("User: What temperature would you like to convert? (in degrees)");
                                    userInputTemp = parseFloat(inputReader.nextLine()); 
                                    System.out.println("Thank you, User! Which temperature scale would you like to convert from? Enter C for Celsius, F for Fahrenheit");
                                    tempScaleCode = inputReader.nextLine();

                                    if  (tempScaleCode.equalsIgnoreCase("C")) {
                                        System.out.println(userInputTemp + " degrees Celsius is equivalent to " + ((userInputTemp *1.8) + 32) + " degrees Fahrenheit.");
                                    } else if (tempScaleCode.equalsIgnoreCase("F")) {
                                        System.out.println(userInputTemp + " degrees Fahrenheit is equivalent to " + ((userInputTemp -32) * 0.556) + " degrees Celsius.");
                                    } else {
                                        System.out.println("Invalid input. Please follow the instructions");
                                    }
                         } else {
                     System.out.println("Goodbye!");
                 }
                
                
                
               
       
                        
                
                
                
        };
    
}
