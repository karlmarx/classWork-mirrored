/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karlmarxindustries.flooring.ui;

import com.karlmarxindustries.flooring.dto.Order;
import com.karlmarxindustries.flooring.dto.Product;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author karlmarx
 */
public class FlooringView {

    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    Locale aLocale = new Locale("en", "US");
    Locale deLocale = new Locale("de", "DE");
    Locale heLocale = new Locale("iw", "IL");
    Locale nlLocale = new Locale("nl", "NL");
    Locale twLocale = new Locale("zh", "TW");
    Locale koLocale = new Locale("ko", "KO");
    
    
    boolean isGerman = false;
    boolean isHebrew = false;
        private Language currentLanguage = Language.ENGLISH;

    //idea: have the language chosen persist to the next session

    ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", aLocale);

    private UserIO io;

    public void displayTooSmall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
    public enum Language {
        ENGLISH, GERMAN, HEBREW, CHINESE, KOREAN, DUTCH
    }
      public int displayLanguageGetChoice() {
   
        io.print("EN/DE/HE"); //change to something better 
        io.print(BLACK_BACKGROUND + RED_BOLD + "[1] Deutsch");
        io.print(BLUE_BOLD  +"[2] עברית");
        io.print(YELLOW_BOLD + "[3] English");
        io.print(BLACK_BACKGROUND + RED_BOLD + "[4] Nederlands");
        io.print(BLUE_BOLD  +"[5] 繁體字");
        io.print(YELLOW_BOLD + "[6] 한국말");
        io.print(GREEN_BOLD + "[7] " + messages.getString("Quit"));
        return io.readInt(messages.getString("8"), 1, 7);
    
    }

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void switchDeutsch() {
        messages = ResourceBundle.getBundle("MessagesBundle", deLocale);
        currentLanguage = Language.GERMAN;
    }
    public void switchHebrew() {
        messages = ResourceBundle.getBundle("MessagesBundle", heLocale);
        currentLanguage = Language.HEBREW;
    }

    public void switchEnglisch() {
        messages = ResourceBundle.getBundle("MessagesBundle", aLocale);
            currentLanguage = Language.ENGLISH;
    }
    public void switchChinese() {
        messages = ResourceBundle.getBundle("MessagesBundle", twLocale);
        currentLanguage = Language.CHINESE;
    }
    public void switchDutch() {
        messages = ResourceBundle.getBundle("MessagesBundle", nlLocale);
        currentLanguage = Language.DUTCH;
    }

    public void switchKorean() {
        messages = ResourceBundle.getBundle("MessagesBundle", koLocale);
            currentLanguage = Language.KOREAN;
    }
    public boolean isDeutsch (){
    if (currentLanguage.equals(Language.GERMAN)) {
        return true;
                } else {
        return false;
    }
 
}

    public int printMenuAndGetSelection() {
        io.print(messages.getString("0"));
        io.print(BLACK_BACKGROUND + RED_BOLD + messages.getString("1"));
        io.print(GREEN_BOLD + messages.getString("2"));
        io.print(YELLOW_BOLD + messages.getString("3"));
        io.print(WHITE_BRIGHT + messages.getString("4"));
        io.print(CYAN_BOLD + messages.getString("5"));
        io.print(BLUE_BOLD + "[6] DE/EN/한국말/中文/עברית"); // change htis
        io.print(WHITE_BRIGHT + messages.getString("7"));
        return io.readInt(messages.getString("8"), 1, 7);
    }

    public Order getNewOrderInfo(List<String> stateList, List<Product> productList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        boolean validDate = false;
        boolean validName = false;
        boolean validProduct = false;
        boolean validState = false;
        String customerName = null;
        String dateAsText;
        LocalDate date = null;
        String inputState = null;
        String inputProduct = null;
        List<String> productTypeList = new ArrayList<>();
        for (Product each : productList) {
            productTypeList.add(each.getProductType());
        }
        BigDecimal area;
        while (!validDate) {
            date = getDesiredDate();
            if (date.compareTo(LocalDate.now()) < 0) {
                io.print(messages.getString("9"));
            } else {
                validDate = true;
            }
        }
        while (!validName) {
            customerName = io.readString(messages.getString("10")).trim();
            if (!isAlphaPeriodComma(customerName)) {
                io.print(messages.getString("11"));
            } else {
                validName = true;
            }
        }
        while (!validState) {
            inputState = io.readString(messages.getString("12") + "\n" + displayCommafiedStateList(stateList));
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState)) {
                io.print(messages.getString("13") + upperState + messages.getString("13a"));
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString(messages.getString("14"));
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product)) {
                io.print(messages.getString("15"));
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDouble(messages.getString("16"), 0.0d, Double.MAX_VALUE);
        area = new BigDecimal(String.valueOf(doubleArea));
        Order orderToValidate = new Order(date, customerName, inputState, area, inputProduct);
        return orderToValidate;
        //send to service layer for calculations and to validate state.
    }

    public void displayAddOrderBanner() {
        io.print(messages.getString("17"));
    }

    public void displayAddOrderSuccessBanner() {
        io.readString(messages.getString("18"));
    }

    public void displayOrdersForDate(List<Order> orderList) {
        io.print("+++++++++++++++++++++++");
        for (Order order : orderList) {
            this.displayOrder(order);
            io.print("+++++++++++++++++++++++");
        }
        io.readString(messages.getString("19"));
    }

    public void displayEditOrderBanner() {
        io.print(messages.getString("20"));
    }
    public void displayRemoveOrderBanner() {
        io.print(messages.getString("20a"));
    }

    public void displayRemoveSuccessBanner() {
        io.readString(messages.getString("21"));
    }

    public void displayExitBanner() {
        io.print(messages.getString("22"));
    }

    public void displayUnknownCommandBanner() {
        io.print(messages.getString("23"));
    }

    public boolean isAlphaPeriodComma(String stringToCheck) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9 .,]*$");
// Pattern pattern = Pattern.compile("^[\\w .,]+$");
        Matcher m = pattern.matcher(stringToCheck);
        boolean doesItMatch = m.matches();
        return doesItMatch;
    }

    private void displayAllProducts(List<Product> productTypeList) {
        int counter = 1;
        io.print(messages.getString("24"));
        for (Product currentProduct : productTypeList) {
            io.print(counter + ". [" + currentProduct.getProductType() + messages.getString("25")
                    + currentProduct.getCostPerSquareFoot() + messages.getString("26")
                    + currentProduct.getLaborCostPerSquareFoot());
            io.print("==========================");
            counter++;
        }
    }

    public boolean displayConfirmOrderToAdd(Order toAdd) {
        boolean confirmed = false;
        io.print(messages.getString("27"));
        displayOrder(toAdd);
        String confirmation = io.readString(messages.getString("28"));
        if (confirmation.equalsIgnoreCase("Y") || confirmation.equalsIgnoreCase("J")) {
            confirmed = true;
            io.print(messages.getString("29") + toAdd.getOrderNumber() + "."); //split this up?
        } else {
            io.print(messages.getString("30")); //put this logic in controller probably
        }
        return confirmed;
    }

    public void displayDisplayOrdersBanner() {
        io.print(messages.getString("31"));
    }

    public LocalDate getDesiredDate() {

        boolean validDate = false;
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(messages.getString("dateFormat"));
//        if (currentLanguage.equals(Language.GERMAN) || currentLanguage.equals(Language.HEBREW)) {
//            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        }
        while (!validDate) {
            try {
                String dateAsText = io.readString(messages.getString("31.5")).trim();

                date = LocalDate.parse(dateAsText, formatter);
                validDate = true;
            } catch (DateTimeException e) {
                io.print(messages.getString("32"));

            }
        }
        return date;
    }

    public int getOrderNumber() {
        int orderNumber = io.readInt(messages.getString("33"), 1, Integer.MAX_VALUE);
        return orderNumber;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print(RED_BACKGROUND + messages.getString("34"));
        io.print(errorMsg);
    }

    public boolean displayConfirmOrderToRemove(Order toRemove) {
        io.print(messages.getString("35"));
        io.print(messages.getString("36") + toRemove.getOrderNumber());
        displayOrder(toRemove);
        return confirmSomething(messages.getString("37"));
    }

    public boolean confirmSomething(String toConfirm) {
        boolean confirmed = false;
        String confirmation = io.readString(toConfirm);
        if (confirmation.equalsIgnoreCase("Y")||confirmation.equalsIgnoreCase("J")) {
            confirmed = true;
        }
        return confirmed;
    }

    public boolean displayConfirmSave() {
        return confirmSomething(messages.getString("38"));
    }

    public void displaySaveSuccess() {
        io.print(messages.getString("39"));
    }

    public boolean displayConfirmEditing(Order editedAndCalculated) {
        displayOrder(editedAndCalculated);
        return confirmSomething(messages.getString("40"));
    }

    public void displayEditSuccess() {
        io.print(messages.getString("41"));
    }

    public void displayNoChangesMade() {
        io.print(messages.getString("42"));
    }

    public void displayOrder(Order order) {
        if (order.getOrderNumber() != 0) {
            io.print(messages.getString("43")+ order.getOrderNumber());
        } else {
            io.print(messages.getString("44"));
        }
        io.print(messages.getString("45") + order.getDate());
        io.print(messages.getString("46") + order.getCustomerName());
        io.print(messages.getString("47") + order.getState());
        io.print(messages.getString("48") + order.getTaxRate());
        io.print(messages.getString("49") + order.getProductType());
        io.print(messages.getString("50")+ order.getArea() + messages.getString("50a"));
        io.print(messages.getString("51") + order.getCostPerSquareFoot());
        io.print(messages.getString("52")+ order.getLabourCostPerSquareFoot());
        io.print(messages.getString("53") + order.getMaterialCost());
        io.print(messages.getString("54") + order.getLabourCost());
        io.print(messages.getString("55")+ order.getTax());
        io.print(messages.getString("56") + order.getTotal());
    }

    public void displayNotSaved() {
        io.print(messages.getString("57"));
    }

    public Order displayCurrentGetEdits(List<String> stateList, List<Product> productList, Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(messages.getString("dateFormat"));
        
        boolean validName = false;
        boolean validProduct = false;
        boolean validState = false;
        String customerName = null;
        String inputState = null;
        String inputProduct = null;
        List<String> productTypeList = new ArrayList<>();
        for (Product each : productList) {
            productTypeList.add(each.getProductType());
        }
        BigDecimal area;
        while (!validName) {
            customerName = io.readString(messages.getString("58") + order.getCustomerName() + "]: ").trim();
            if (!isAlphaPeriodComma(customerName) && !customerName.equals("")) {
                io.print(messages.getString("59"));
            } else {
                validName = true;
            }

        }
        while (!validState) {
            inputState = io.readString(messages.getString("60")+ order.getState().toUpperCase() + messages.getString("60a")+  "\n" + displayCommafiedStateList(stateList));
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState) && !inputState.equals("")) {
                io.print(messages.getString("61") + upperState + messages.getString("62"));
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString(messages.getString("63") + order.getProductType().toUpperCase() + "]:  ");
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product) && !inputProduct.equals("")) {
                io.print(messages.getString("64"));
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDoubleAllowBlank(messages.getString("65") + order.getArea() + messages.getString("65a"), 0.0d, Double.MAX_VALUE);
        area = new BigDecimal(String.valueOf(doubleArea));
        if (!customerName.equals("")) {
            order.setCustomerName(customerName);
        }
        if (!inputState.equals("")) {
            order.setState(inputState);
        }
        if (doubleArea != 0) {
            order.setArea(area);
        }
        if (!inputProduct.equals("")) {
            order.setProductType(inputProduct);
        }

        return order;

    }

    public void displayTitleBanner() {
        io.print("");
        io.print("$$$$$$$$\\ $$\\                               $$\\      $$\\                       $$\\                                 $$\\       $$$$$$\\  \n"
                + "$$  _____|$$ |                              $$$\\    $$$ |                      $$ |                              $$$$ |     $$$ __$$\\ \n"
                + "$$ |      $$ | $$$$$$\\   $$$$$$\\   $$$$$$\\  $$$$\\  $$$$ | $$$$$$\\   $$$$$$$\\ $$$$$$\\    $$$$$$\\   $$$$$$\\        \\_$$ |     $$$$\\ $$ |\n"
                + "$$$$$\\    $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$\\$$\\$$ $$ | \\____$$\\ $$  _____|\\_$$  _|  $$  __$$\\ $$  __$$\\         $$ |     $$\\$$\\$$ |\n"
                + "$$  __|   $$ |$$ /  $$ |$$ /  $$ |$$ |  \\__|$$ \\$$$  $$ | $$$$$$$ |\\$$$$$$\\    $$ |    $$$$$$$$ |$$ |  \\__|        $$ |     $$ \\$$$$ |\n"
                + "$$ |      $$ |$$ |  $$ |$$ |  $$ |$$ |      $$ |\\$  /$$ |$$  __$$ | \\____$$\\   $$ |$$\\ $$   ____|$$ |              $$ |     $$ |\\$$$ |\n"
                + "$$ |      $$ |\\$$$$$$  |\\$$$$$$  |$$ |      $$ | \\_/ $$ |\\$$$$$$$ |$$$$$$$  |  \\$$$$  |\\$$$$$$$\\ $$ |            $$$$$$\\ $$\\\\$$$$$$  /\n"
                + "\\__|      \\__| \\______/  \\______/ \\__|      \\__|     \\__| \\_______|\\_______/    \\____/  \\_______|\\__|            \\______|\\__|\\______/ \n"
                + "                                                                                                                                      ");
    }
        public String displayCommafiedStateList(List<String> stateList) {
            String listAbbr = "[";
            for (String abbr : stateList) {
                listAbbr += abbr;
                listAbbr += ", ";
            }
            String trimmedList = listAbbr.substring(0, listAbbr.length()-2);
            trimmedList += "]";
            return trimmedList;
        }
}
