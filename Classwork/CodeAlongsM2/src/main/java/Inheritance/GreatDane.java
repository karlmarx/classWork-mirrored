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
public class GreatDane extends Dog {
    private String favCouch;
    
    public GreatDane(String aName, String anOwner, String favCouch){
        super("Great Dane", aName, anOwner);
                this.favCouch = favCouch;
    }
    
    public void beRegal(){
        System.out.println("Regal");
    }
    @Override
    public void bark(){
        System.out.println("BARK BARK BARK BARK ");
    }
}
