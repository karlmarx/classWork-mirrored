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
public class RockPaperScissors {
    public static void main(String[] args) {
        int currentRound = 1; 
        int compWinTally = 0;
        int userWinTally = 0;
        String continueIndicator = "Y";
        boolean playing = true;
        
        
        Scanner scanner = new Scanner(System.in);
        while (playing) {
                System.out.println("Rock, paper, scissors time! How many rounds would you like to play? (max=10)");
                int totalRounds = scanner.nextInt(); 
                if (totalRounds > 10 ){
                    System.out.println("Error! I cannot play that much. Goodbye");
                    return;
                } else {
                    System.out.println("Here we go! " +  totalRounds + " rounds it is!");
                }
        
 
        
                while (currentRound <= totalRounds){
                        String gameOutcome =  playGame();

                        displayOutcome(gameOutcome);


                        if (gameOutcome.equals("compWin")) {
                                compWinTally ++;
                        } else if (gameOutcome.equals("userWin")) {
                                userWinTally ++;
                        }

                        System.out.println("Computer wins: " + compWinTally+ ". Your wins: " + userWinTally + ".");
                        currentRound ++;
                    
                        
                         }   
                System.out.println("Would you like to play again? Enter 'Y' or 'N'");
                String answer = scanner.nextLine();
                playing = answer.equalsIgnoreCase("y");
        }
        
        } 
        
     
    
   
           

       
        
       
         
        
    
    
    public static void displayOutcome(String gameOutcome ){
        if (gameOutcome.equals("tie")) {
                System.out.println("It's a tie!");
            
        } else if (gameOutcome.equals("compWin")) {
            System.out.println("The computer wins!");
           
        } else {
            System.out.println("You win!");
        }
       
    }
    
    
    public static String playGame() {
        Scanner scanner2 = new Scanner(System.in);
        Random randomizer = new Random();
        String gameOutcome = "ERROR";
        System.out.println("Enter 0 for rock, 1 for paper, 2 for scissors");
        int userChoice = scanner2.nextInt();
        int compChoice = randomizer.nextInt(3);
        if (userChoice == compChoice) {
                gameOutcome = "tie";
        } else if ( userChoice == 0 && compChoice == 1) {
                        gameOutcome = "compWin";
                    }else if ( userChoice == 0 && compChoice == 2) {
                        gameOutcome = "userWin";
                    }else if ( userChoice == 1 && compChoice == 2) {
                        gameOutcome = "compWin";
                    }else if ( userChoice == 1 && compChoice == 0) {
                        gameOutcome = "userWin";
                    }else if ( userChoice == 2 && compChoice == 1) {
                        gameOutcome = "userWin";
                    }else if ( userChoice == 2 && compChoice == 0) {
                        gameOutcome = "compWin";
                    }
            return gameOutcome;
    }
    
}
    
    
   
