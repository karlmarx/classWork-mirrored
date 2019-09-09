/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.birthdaycalendar.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author karlmarx
 */
public class main {
    public static void main(String[] args) throws ParseException {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
         Scanner io = new Scanner(System.in);
        String dateIn = readString("What's your birthday? (MM/DD/YYYY format)").trim();
        LocalDate dobDate = LocalDate.parse(dateIn, formatter);
        System.out.println("That means you were born on a " + dobDate.getDayOfWeek() + ".");
      
        
        int currentYear = LocalDate.now().getYear();
        LocalDate thisYear = dobDate.withYear(currentYear);
        System.out.println("This year it's on a "  + thisYear.getDayOfWeek() +  ".");
       
        LocalDate nextYear = dobDate.withYear(currentYear +1 );
        int compare = thisYear.compareTo(LocalDate.now());
        int daysTilNext = 1000;
        int nextBirthyear = 1000;
        if (compare >  0 ) {
            daysTilNext = Math.toIntExact(ChronoUnit.DAYS.between(thisYear, LocalDate.now()));
            nextBirthyear = thisYear.getYear();
            
        } else if (compare > 0) {
            daysTilNext = Math.toIntExact(ChronoUnit.DAYS.between(nextYear, LocalDate.now()));
            nextBirthyear = nextYear.getYear();
        } else if (compare == 0) {
            daysTilNext = 0;
            nextBirthyear = thisYear.getYear();
            
        }
        LocalDate nextBday = dobDate.withYear(nextBirthyear);
        System.out.println("Since today is " + LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ", it is only " + Math.abs(daysTilNext) + " days until your next birthday!");
        int ageAtNextBirthday = nextBirthyear - dobDate.getYear();
        System.out.println("Bet you're SUPER excited to be turning " + ageAtNextBirthday + " on " + nextBday.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ".");
        
        
        
        

    }
//    public static String stringToDayOfWeek(String input) throws ParseException {
//        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
//        Date dt1=format1.parse(input);
//        DateFormat format2=new SimpleDateFormat("EEEE"); 
//        String finalDay=format2.format(dt1);
//        return finalDay;
//    }
//    public static Date stringToDate(String input) throws ParseException {
//        
//        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
//        Date dt1=format1.parse(input);
//        return dt1;
//    }
    public static String readString(String prompt) {
         Scanner scanner = new Scanner(System.in);
        String result = "(blank)";
        System.out.println(prompt);
        result = scanner.nextLine();
        return result;
    }
}
