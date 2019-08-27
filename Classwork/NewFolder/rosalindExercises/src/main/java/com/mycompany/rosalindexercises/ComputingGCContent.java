/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rosalindexercises;

import java.security.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author karlmarx
 */
public class ComputingGCContent {
    public static void main(String[] args) {
        String number6404 = "CCTGCGGAAGATCGGCACTAGAATAGCCAGAACCGTTTCTCTGAGGCTTCCGGCCT"+
                "TCCCTCCCACTAATAATTCTGAGG";
        String number5959 = "CCATCGGTAGCGCATCCTTAGTCCAATTAAGTCCCTATCCAGGCGCTCCGCCGAAGGTCT\n" +
"ATATCCATTTGTCAGCAGACACGC";
        String number0808 = "CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGAC\n" +
"TGGGAACCTGCGGGCAGTAGGTGGAAT";
        HashMap<Integer, String> map = new HashMap<>();
        map.put(6404, number6404);
        map.put(5959, number5959);
        map.put(808, number0808);
        HashMap<Integer, Double> contentMapped = getContent(map);
                Integer maxKey = Collections.max(contentMapped.entrySet(), Map.Entry.comparingByValue()).getKey();
                Double maxSequence = contentMapped.get(maxKey);
                System.out.println(maxKey + " , " + maxSequence);
        //do a hash map?
      
        
    }
    public static HashMap<Integer, Double> getContent(HashMap<Integer, String> sequenceMap){
            char temp;
            double gcContent = 0;
            int gcCount = 0;
            HashMap<Integer, Double> contents = new HashMap<>();
            for (int keys : sequenceMap.keySet()){
                String sequence = sequenceMap.get(keys);
             gcCount = 0;
                for (int i = 0; i < sequence.length(); i++) {
                    
                 temp = sequence.charAt(i);
                 if (temp == 'C' || temp == 'G'){
                   gcCount++;
                }
               gcContent = gcCount / sequence.length();
                    System.out.println(gcContent + " " + );
               contents.put(keys, gcContent);
            }
            }
            
            return contents;
    }
   
}
