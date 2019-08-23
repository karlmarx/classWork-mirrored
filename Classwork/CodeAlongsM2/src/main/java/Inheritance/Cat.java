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
public class Cat extends Pet 
        implements Snuggleable {
    String favFood;
    public void meow(){
        System.out.println("Meow");
        
        }
    public Cat(String favFood, String name, String owner){
            super(name, owner);
            this.favFood = favFood;
    }

    @Override
    public void beSnuggled() {
        this.meow(); //To change body of generated methods, choose Tools | Templates.
    }
  
}
