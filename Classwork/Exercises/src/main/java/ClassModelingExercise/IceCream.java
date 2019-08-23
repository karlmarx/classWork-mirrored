/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassModelingExercise;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author karlmarx
 */
public class IceCream {
    public static void main(String[] args) {       
    // create a map that maps a country to its population
    HashMap<String, Integer> populations = new HashMap<>();
       
    // add the first country
    populations.put("USA", 313000000);
       
    // add the next country
    populations.put("Canada", 34000000);
       
    // add another country
    populations.put("United Kingdom", 63000000);
       
    // add another country
    populations.put("Japan", 127000000);
       
    // ask the map for its size - it will be 4
    System.out.println("Map size is: " + populations.size());
       
    // get the Collection of values from the Map
    Collection<Integer> popValues = populations.values();
       
    // list all of the population values - how can we tell which 
    // population value goes with each country?
    for (Integer p : popValues) {
        System.out.println(p);
    }       
}
    
}
