/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Random;

/**
 *
 * @author karlmarx
 */
public class Opinionator {
    public static void main(String[] args) {
        Random rzer = new Random();
        System.out.println("I can't decide what animal I like the best.");
        System.out.println("I know! Random can decide for me.");
         int x = rzer.nextInt(5);
         
         System.out.println("The number we chose was " + x);
         
         if (x == 0) {
             System.out.println("Llamas are the best!");
         } else if (x == 1) {
             System.out.println("Dodos are the best!");
         } else if (x == 2) {
             System.out.println("Wooly Mammoths are best!");
         } else if (x == 3){
             System.out.println("Sharks are best!");
         } else if (x == 4) {
             System.out.println("cockatoos are best");
         }else if (x == 5) {
             System.out.println("have you met a mole rat");
         }
         System.out.println("thanks, random! you're the best.");
    }
}
