/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArrayExercises;

/**
 *
 * @author karlmarx
 */
public class ApplesAndOranges {
    public static void main(String[] args) {
        String[] fruit = {
            "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"
            };
        
        int orangesum = 0;
        int applesum = 0;
        
        for (String fruitInd : fruit) if (fruitInd.equals("Orange")) {
            orangesum += 1;
         }  else  {
            applesum += 1;
         }  
        
        String [] appleArray = new  String[applesum];
        String [] orangeArray = new String[orangesum];
        
        for (int i=0; i < applesum; i++) {
           appleArray[i] = "Apple";
                   }
        
        for (int i=0; i < orangesum;i++) {
            orangeArray[i] = "Orange";
        }
        
         
        printArray(appleArray);
        printArray(orangeArray);        
        
        
        System.out.println("\nTotal : " + orangesum + " oranges");
        System.out.println("Total : " + applesum + " apples");
         
         
        }
            
    
        public static void printArray (String[] arrayName){
            System.out.println("The elements of this array are as follows: ");
            for (int i = 0; i < arrayName.length; i++){
                System.out.print(" -" + arrayName[i] + "- ");
           
           // unneeded line     System.out.println("\n");
            }
            
            
        }
}


    

                
                