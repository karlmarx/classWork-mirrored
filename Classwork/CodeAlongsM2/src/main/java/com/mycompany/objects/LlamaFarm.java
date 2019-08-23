/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.objects;

/**
 *
 * @author karlmarx
 */
public class LlamaFarm {
    public static void main(String[] args) {
        System.out.println("Welcome to the llama farm!");
        Llama floyd = new Llama("Floyd", "grey", "curly", 2, 8);
       
        System.out.println("This is floyd, tell us your name Floyd...");
        System.out.println(floyd.getName());
        
        Llama[] llamaHerd = new Llama[5];
        llamaHerd[0] = floyd;
        llamaHerd[1] = new Llama("BeeBop", "brown", "super curly", 15, 58.9);
        llamaHerd[2] = new Llama("tom", "brown", "super curly", 10, 58.9);
        llamaHerd[3] = new Llama("yo", "brown", "super curly", 1, 58.9);
        llamaHerd[4] = new Llama("karl", "brown", "super curly", 2, 58.9);
        
        for (Llama theLlama : llamaHerd) {
            System.out.println(theLlama.getName());
            theLlama.spit();
        }
      
            
        }
        

    }

