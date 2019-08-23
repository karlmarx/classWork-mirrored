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
public class Chihuahua extends Dog {
    private String favPillow;

    public Chihuahua(String favPillow, String breed, String name, String owner) {
        super(breed, name, owner);
        this.favPillow = favPillow;
    }
    
    public String getName(){
        return "The great " + super.getName() + "the XXXX";
    }
    
    public void beYippy(){
        System.out.println("YIPYIPYIP");
    }
    
}
