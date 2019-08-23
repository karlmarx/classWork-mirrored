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
public class Dog extends Pet
implements Snuggleable {
        private String breed;

    public Dog(String breed, String name, String owner) {
        super(name, owner);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }




        
        public void bark(){
            System.out.println("bark");
        }

    @Override
    public void beSnuggled() {
       this.bark();//To change body of generated methods, choose Tools | Templates.
        System.out.println("snugglesnugglesnuggle");
    }
                
}
