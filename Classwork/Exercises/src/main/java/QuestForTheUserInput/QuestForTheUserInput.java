/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestForTheUserInput;

/**
 *
 * @author karlmarx
 */
import java.util.Scanner;

public class QuestForTheUserInput {
public static void main(String[]args){
    Scanner inputReader = new Scanner(System.in);
    
    String yourName;
    String yourQuest;
    double velocityOfSwallow;
    
    System.out.print("What is your name?");
    yourName = inputReader.nextLine();
    
    System.out.print("What is your quest?");
    yourQuest = inputReader.nextLine();
    
    System.out.print("What is the airpeed velocity of an unladen swallow?");
    velocityOfSwallow = inputReader.nextDouble();
    
    System.out.println("How do you know " + velocityOfSwallow + "is correct," + yourName + ",");
    System.out.println("when you didn't even know if the swallow was African or European?");
    System.out.println("Maybe skip answering things about birds and instead go " + yourQuest + ".");
    
    }   

}
