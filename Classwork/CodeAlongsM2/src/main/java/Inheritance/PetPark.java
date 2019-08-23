/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inheritance;

/**
 *
 * @author karlmarx
 */
public class PetPark {
    public static void main(String[] args) {
        System.out.println("Welcome to the pet park!");
        GreatDane beethoven = new GreatDane("d", "d", "d");
        Pet mister = new Pet();
        Cat frannie = new Cat("x","xx","xxx");
        Chihuahua charlie = new Chihuahua("the red one", "x","Chihuahua","ben");
        Iguana iggy = new Iguana("karl", "ben");        
        Dog karlmarx = new Dog("commie", "karlmarx", "me");
        
        Pet[] pets = new Pet[6];
        pets[0] = beethoven;
        pets[1] = frannie;
        pets[2] = mister; 
        pets[3] = charlie;
        pets[4] = iggy;
        pets[5] = karlmarx;
            
        for (Pet aPet : pets){
            System.out.println(aPet.getName() + " is owned by " + aPet.getOwner());
        }
        
        System.out.println("Going to snuggle beethoven");
        PetPark.snugglePet(beethoven);
        
        }
    public static void snugglePet(Snuggleable toSnuggle) {
            toSnuggle.beSnuggled();
            
    }
}
