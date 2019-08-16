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
public class InterestCalculator {
        public static void main(String[] args) {
         

            Scanner scanner = new Scanner(System.in);
            System.out.println("How many years would you like to invest over?");
            int numberOfYears = scanner.nextInt();
            System.out.println("What is the annual interest rate? (enter percentage in whole numbers)");
            int annualInterestRate = scanner.nextInt(); 
            System.out.println("What is the principal investment?");
            double InputPrincipal = scanner.nextDouble();
            
            double[] yearlyTotals = new double[numberOfYears];
             yearlyTotals[0] = InputPrincipal;
            for (int i = 1; i < numberOfYears; i++){
                yearlyTotals[i] =  annualCompoundIt(yearlyTotals[i-1], annualInterestRate);
                
             ;
            }
            
            for (int i = 0; i < numberOfYears; i++){
                     System.out.println("The  amount at the beginning of year " + (i+1) + " is $" + yearlyTotals[i] + ".");
                            System.out.println("The  amount at the end of year " + (i+2) + " is $" + yearlyTotals[i+1] + ".");
                             System.out.println("The amount of interest earned during year " + (i+1) + " is $" + (yearlyTotals[i+1] - yearlyTotals[i]) + ".");
                    }            
        }
            
        public static double annualCompoundIt(double preInterestAmount, int annualInterestRate) 
        {
            
            return preInterestAmount * Math.pow((1+(annualInterestRate/100)/4),4); 
        }
               
       
        
          
            }
    

