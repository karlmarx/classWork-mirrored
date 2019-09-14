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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author karlmarx
 */
public class FlooringViewDeutsch {

    private UserIO io;

    public FlooringViewDeutsch(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("<<BodenMeister 1.0>>");
        io.print("[1] Bestellungen Anzeigen");
        io.print("[2] Bestellungen Anlegen");
        io.print("[3] Bestellungen Bearbeiten");
        io.print("[4] Bestellungen Löschen");
        io.print("[5] Fortschritt Speichern");
        io.print("[6] Englisch");
        return io.readInt("Bitte wählen Sie aus den obigen Optionen.", 1, 6);
    }

    public Order getNewOrderInfo(List<String> stateList, List<Product> productList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
                io.print("Sie haben ein Datum in der Vergangenheit eingegeben. Bitte geben Sie ein gültiges zukünftiges Datum ein. TT.MM.JJJJ");
            } else {
                validDate = true;
            }
        }
        while (!validName) {
            customerName = io.readString("Bitte geben Sie den Kundennamen ein.").trim();
            if (!isAlphaPeriodComma(customerName)) {
                io.print("Ungültiges Format! Zulässige Zeichen: Zahlen, Buchstaben, Kommas und Punkte.");
            } else {
                validName = true;
            }
        }
        //figure out way to get state list.  maybe as a METHOD PARAMATER
        while (!validState) {
            inputState = io.readString("Biite geben Sie den Staat ein: (Nur aus zwei Buchstaben bestehende Abkürzungen.) ");
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState)) {
                io.print("Fußböden können nicht in " + upperState + " verkauft werden.  Bitte geben Sie einen anderen Staat ein.");
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString("Bitte geben Sie das Produkt aus der obigen Auswahl ein: (Verwenden Sie nur den vollständigen Namen)");
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product)) {
                io.print("Dieses Produkt ist nicht verfügbar. Bitte wählen Sie aus den verfügbaren Optionen.");
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDouble("Bitte geben Sie den Bereich in Quadratfuß ein. (mindestens 100)", 100.0d, Double.MAX_VALUE);
        area = new BigDecimal(String.valueOf(doubleArea));
        Order orderToValidate = new Order(date, customerName, inputState, area, inputProduct);
        return orderToValidate;
        //send to service layer for calculations and to validate state.
    }

    public void displayAddOrderBanner() {
        io.print("=== Bestellungen Anlegen ===");
    }

    public void displayAddOrderSuccessBanner() {
        io.readString("Bestellung erfolgreich erstellt. Bitte drücken Sie die Eingabetaste, um fortzufahren.");
    }

    public void displayOrdersForDate(List<Order> orderList) {
        io.print("+++++++++++++++++++++++");
        for (Order order : orderList) {
            this.displayOrder(order);
            io.print("+++++++++++++++++++++++");
        }
        io.readString("Bitte drücken Sie die Eingabetaste, um fortzufahren.");
    }

    public void displayEditOrderBanner() {
        io.print("=== Bestellungen Löschen ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Die Bestellung wurde erfolgreich gelöscht. Bitte drücken Sie die Eingabetaste, um fortzufahren.");
    }

    public void displayExitBanner() {
        io.print("Auf wiedersehen!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unbekannte Anweisung!!!");
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
        io.print("===Verfügbare Produkte:===");
        for (Product currentProduct : productTypeList) {
            io.print(counter + ". [" + currentProduct.getProductType() + "] - Kosten: $"
                    + currentProduct.getCostPerSquareFoot() + " - Arbeitskosten: $"
                    + currentProduct.getLaborCostPerSquareFoot());
            io.print("==========================");
            counter++;
        }
    }

    public boolean displayConfirmOrderToAdd(Order toAdd) {
        boolean confirmed = false;
        io.print("Bitte überprüfen Sie diese Bestellung, um sicherzustellen, dass sie korrekt ist:");
        displayOrder(toAdd);
        String confirmation = io.readString("Möchten Sie diesen Auftrag erstellen? (J/N)");
        if (confirmation.equalsIgnoreCase("J")) {
            confirmed = true;
            io.print("Die Bestellung wurde angelegt und mit der Bestellnummer #" + toAdd.getOrderNumber() + "versehen."); //split this up?
        } else {
            io.print("Die Bestellung wurde nicht angelegt.");
        }
        return confirmed;
    }

    public void displayDisplayOrdersBanner() {
        io.print("=== Bestellungen Anzeigen ===");
    }

    public LocalDate getDesiredDate() {

        boolean validDate = false;
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (!validDate) {
            try {
                String dateAsText = io.readString("Bitte geben Sie das Datum ein (TT.MM.JJJJ): ").trim();

                date = LocalDate.parse(dateAsText, formatter);
                validDate = true;
            } catch (DateTimeException e) {
                io.print("Das Datumsformat ist ungültig. Bitte versuche es erneut!");

            }
        }
        return date;
    }

    public int getOrderNumber() {
        int orderNumber = io.readInt("Bitte geben Sie die Bestellnummer ein!", 1, Integer.MAX_VALUE);
        return orderNumber;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("--- FEHLER ---");
        io.print(errorMsg);
    }

    public boolean displayConfirmOrderToRemove(Order toRemove) {
        io.print("Bitte überprüfen Sie die Bestellung, um sicherzustellen, dass es sich um die richtige Bestellung handelt.");
        io.print("Bestellung #" + toRemove.getOrderNumber());
        displayOrder(toRemove);
        return confirmSomething("Möchten Sie hinzufügen, entfernen Sie diese Bestellung? (J/N)");
    }

    public boolean confirmSomething(String toConfirm) {
        boolean confirmed = false;
        String confirmation = io.readString(toConfirm);
        if (confirmation.equalsIgnoreCase("J")) {
            confirmed = true;
        }
        return confirmed;
    }

    public boolean displayConfirmSave() {
        return confirmSomething("Sind Sie sicher, dass Sie Ihre Arbeit aus dieser Sitzung speichern möchten? (J/N)");
    }

    public void displaySaveSuccess() {
        io.print("Die in dieser Sitzung vorgenommenen Änderungen wurden erfolgreich gespeichert.");
    }

    public boolean displayConfirmEditing(Order editedAndCalculated) {
        displayOrder(editedAndCalculated);
        return confirmSomething("Möchten Sie diese Änderungen speichern? (J/N)");
    }

    public void displayEditSuccess() {
        io.print("Die Änderungen wurden gespeichert. Stellen Sie sicher, dass Sie im Hauptmenü auf \"Fortschritt Speichern\" klicken, um Ihre Änderungen zu übernehmen.");
    }

    public void displayNoChangesMade() {
        io.print("Es wurden keine Änderungen vorgenommen.");
    }

    public void displayOrder(Order order) {
        if (order.getOrderNumber() != 0) {
            io.print("Bestellung nummer: " + order.getOrderNumber());
        } else {
            io.print("Bestellnummer wurde nicht vergeben.");
        }
        io.print("Bestelldatum: " + order.getDate());
        io.print("Kundenname: " + order.getCustomerName());
        io.print("Staat: " + order.getState());
        io.print("Steuersatz: " + order.getTaxRate());
        io.print("Produkttyp:" + order.getProductType());
        io.print("Größe: " + order.getArea() + "square feet");
        io.print("Kosten pro Quadratfuß: $" + order.getCostPerSquareFoot());
        io.print("Arbeitsosten pro Quadratfuß: $" + order.getLabourCostPerSquareFoot());
        io.print("Materialkosten: $" + order.getMaterialCost());
        io.print("Arbeitskosten: $" + order.getLabourCost());
        io.print("Steuer: $" + order.getTax());
        io.print("Gesamtsumme: $" + order.getTotal());
    }

    public void displayNotSaved() {
        io.print("Ihre Änderungen wurden nicht gespeichert.");
    }

    public Order displayCurrentGetEdits(List<String> stateList, List<Product> productList, Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
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
            customerName = io.readString("Bitte geben Sie den Kundennamen ein[" + order.getCustomerName() + "]: ").trim();
            if (!isAlphaPeriodComma(customerName) && !customerName.equals("")) {
                io.print("Ungültiges Format! Zulässige Zeichen: Zahlen, Buchstaben, Kommas und Punkte.");
            } else {
                validName = true;
            }

        }
        while (!validState) {
            inputState = io.readString("Biite geben Sie den Staat ein[" + order.getState().toUpperCase() + "]: (Nur aus zwei Buchstaben bestehende Abkürzungen.) ");
            String upperState = inputState.toUpperCase();
            if (!stateList.contains(upperState) && !inputState.equals("")) {
                io.print("Fußböden können nicht in " + upperState + " verkauft werden.  Bitte geben Sie einen anderen Staat ein.");
            } else {
                validState = true;
            }
        }
        while (!validProduct) {
            this.displayAllProducts(productList);
            inputProduct = io.readString("Bitte geben Sie das Produkt aus der obigen Auswahl ein[" + order.getProductType().toUpperCase() + "]:  ");
            String product = inputProduct.trim().toUpperCase();
            if (!productTypeList.contains(product) && !inputProduct.equals("")) {
                io.print("Dieses Produkt ist nicht verfügbar. Bitte wählen Sie aus den verfügbaren Optionen.");
            } else {
                validProduct = true;
            }
        }
        double doubleArea = io.readDoubleAllowBlank("Bitte geben Sie den Bereich in Quadratfuß ein[" + order.getArea() + "sq ft]. (mindestens 100)", 100.0d, Double.MAX_VALUE);
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
        io.print(" $$$$$$$\\                            $$$$$$$\\                  $$\\                     $$\\      $$\\           $$\\             $$\\                                 $$\\       $$$$$$\\  \n"
                + "$$  __$$\\                           $$  __$$\\                 $$ |                    $$$\\    $$$ |          \\__|            $$ |                              $$$$ |     $$$ __$$\\ \n"
                + "$$ |  $$ | $$$$$$\\   $$$$$$\\        $$ |  $$ | $$$$$$\\   $$$$$$$ | $$$$$$\\  $$$$$$$\\  $$$$\\  $$$$ | $$$$$$\\  $$\\  $$$$$$$\\ $$$$$$\\    $$$$$$\\   $$$$$$\\        \\_$$ |     $$$$\\ $$ |\n"
                + "$$ |  $$ |$$  __$$\\ $$  __$$\\       $$$$$$$\\ |$$  __$$\\ $$  __$$ |$$  __$$\\ $$  __$$\\ $$\\$$\\$$ $$ |$$  __$$\\ $$ |$$  _____|\\_$$  _|  $$  __$$\\ $$  __$$\\         $$ |     $$\\$$\\$$ |\n"
                + "$$ |  $$ |$$$$$$$$ |$$ |  \\__|      $$  __$$\\ $$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  $$ |$$ \\$$$  $$ |$$$$$$$$ |$$ |\\$$$$$$\\    $$ |    $$$$$$$$ |$$ |  \\__|        $$ |     $$ \\$$$$ |\n"
                + "$$ |  $$ |$$   ____|$$ |            $$ |  $$ |$$ |  $$ |$$ |  $$ |$$   ____|$$ |  $$ |$$ |\\$  /$$ |$$   ____|$$ | \\____$$\\   $$ |$$\\ $$   ____|$$ |              $$ |     $$ |\\$$$ |\n"
                + "$$$$$$$  |\\$$$$$$$\\ $$ |            $$$$$$$  |\\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ |  $$ |$$ | \\_/ $$ |\\$$$$$$$\\ $$ |$$$$$$$  |  \\$$$$  |\\$$$$$$$\\ $$ |            $$$$$$\\ $$\\\\$$$$$$  /\n"
                + "\\_______/  \\_______|\\__|            \\_______/  \\______/  \\_______| \\_______|\\__|  \\__|\\__|     \\__| \\_______|\\__|\\_______/    \\____/  \\_______|\\__|            \\______|\\__|\\______/ \n"
                + "                                                                                                                                                                                    \n"
                + "                                                                                                                                                                                    \n"
                + "                                                                                                                                                                                    ");
    }
}
