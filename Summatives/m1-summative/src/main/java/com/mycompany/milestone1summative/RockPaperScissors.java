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
      
        int compWinTally = 0;
        int userWinTally = 0;
        int tieTally = 0;
        boolean playing = true;
        
        
        Scanner scanner = new Scanner(System.in);
        while (playing) {
                int currentRound = 1;
                System.out.println("Rock, paper, scissors time! How many rounds would you like to play? (max=10, min=1)");
                int totalRounds = Integer.valueOf(scanner.nextLine()); 
                
                if (totalRounds > 10 || totalRounds < 1){
                    System.out.println("Error! You have not followed the instructions. Goodbye");
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
                        } else if (gameOutcome.equals("tie")){
                            tieTally ++;
                        }
                        System.out.println("Computer wins: " + compWinTally+ ". Your wins: " + userWinTally + ". Ties: " + tieTally + ".");
                        currentRound ++;
                        }   
                System.out.println("Would you like to play again? Enter 'Y' or 'N'");
                String answer = scanner.nextLine();
                playing = answer.equalsIgnoreCase("y");
                compWinTally = 0;
                userWinTally = 0;
                tieTally = 0;
                if (playing){
                    continue;
               } else {
                        System.out.println("Thanks for playing!");
                        return;
                } 
        }
        
        } 
    
    public static void displayOutcome(String gameOutcome){
       
        
        if (gameOutcome.equals("tie")) {
                System.out.println("It's a tie!");
            
        } else if (gameOutcome.equals("compWin")) {
            System.out.println("The computer wins!");
           
        } else if (gameOutcome.equals("userWin")) {
            System.out.println("You win!");
        } else if (gameOutcome.equals("error")){
            System.exit(0);
        }
       
    }
    
    
    public static String playGame() {
        Scanner scanner = new Scanner(System.in);
        Random randomizer = new Random();
        String gameOutcome = "ERROR";
        System.out.println("Enter 0 for ROCK, 1 for PAPER, 2 for SCISSORS.");
        int userChoice = Integer.valueOf(scanner.nextLine());
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
        } else if (userChoice < 0 || userChoice > 2) {
        System.out.println("Error. You have not followed the instructions.  Shutting down.");
       System.exit(0);
        } 
                       
         switch(compChoice){
                        case 0: 
                            System.out.println("The computer has chosen ROCK.");
                                break;
                        case 1: 
                            System.out.println("The computer has chosen PAPER.");
                            break;
                        case 2: 
                            System.out.println("The computer has chosen SCISSORS.");
                            break;
                    }
                

            return gameOutcome;
    }
    
}
    
    
   
