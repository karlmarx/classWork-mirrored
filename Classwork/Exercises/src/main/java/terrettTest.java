
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karlmarx
 */
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

/**
 *
 * @author karlmarx
 */

public class terrettTest{

    public static void main(String[]args){
    Scanner inputReader = new Scanner(System.in);
    
    String yourName;
    String yourColour;
    String yourFruit;
    String yourBand;
    
    
            
    System.out.print("What is your name?");
    yourName = inputReader.nextLine();
   
   if (yourName.equalsIgnoreCase("terrett")) { 
       System.out.println("שלום חמודי!  אתה חתיך!  (I can't get Hebrew to appear in correct direction in this compiler...yet.");
               } else {
       System.out.println("Access Denied!");
   }
    
   if (yourName.equalsIgnoreCase("terrett")) {
    
   System.out.println("What kind of fruit should we take on our mountain voyage?");
    yourFruit = inputReader.nextLine();
    
    System.out.println("Really? " + yourFruit + "? I hope it doesn't attract too many (non-human) bears.  Who should be the featured band or artist on our roadtrip mix?");
    yourBand = inputReader.nextLine();
    
    System.out.println(yourBand + " is a good choice. We have to have some Dolly Parton, too.  I heard she loves  " + yourFruit + " too.");
       System.out.println("Maybe, we should be prepared for potential bear attacks.  Let's try throwing " + yourFruit + " at the attacking bear.");
       
                 Random rng = new Random();
            
            
            //set up an enemy
            int enemyHitPoints = 10;
            //set up an attack
            int attackHitChance = 50;
            int attackDamage = 2;
            
            int numberStrikes = 0;
            
            while (enemyHitPoints > 0) {
                numberStrikes++;
                int hit = rng.nextInt();
                if (hit < attackHitChance) {
                        enemyHitPoints -= attackDamage;
                        System.out.println("POW! The bear now has " + enemyHitPoints + " hit points!");
                } else {
                    System.out.println("Darn, you missed! Better luck next time!");
                }
                
                
                 }
            
                    System.out.println("You disabled the bear in " + numberStrikes + " strikes!  Let's celebrate by making out under the stars whilst listening to some " + yourBand + "!");
   }
}
}