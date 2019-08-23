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
public class Iguana extends Pet {
    public void blink(){
        System.out.println("Blink");
    }
    public Iguana(String name, String owner) {
        super(name, owner);
        
    }
}
