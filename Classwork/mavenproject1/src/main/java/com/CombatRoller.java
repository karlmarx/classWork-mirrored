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
public class CombatRoller {
        public static void main(String[] args){
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
                        System.out.println("POW! The enemy now has " + enemyHitPoints + " hit points!");
                } else {
                    System.out.println("Whoosh!");
                }
                        
                        
                 }
            
                    System.out.println("You killed it in " + numberStrikes + " strikes!");
            }
        }

