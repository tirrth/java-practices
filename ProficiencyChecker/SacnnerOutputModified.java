//Rutvi Shah
//Lab 5
//Selection

import java.io.*;
import java.util.Scanner;

public class SacnnerOutputModified {
    public static void main(String[] args) throws IOException {
        String barcode, str, deptName, monthName, cardReply, userChoice;
        int deptCode, partNum, month, date, year;
        double price, discountAmt, taxRate, taxAmt, totalPrice;
        boolean discountCard = false, tax;

        File myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Barcode.txt");
        Scanner inFile = new Scanner(myFile);
        PrintWriter outFile = new PrintWriter("/Users/tirth/Documents/Java/ProficiencyChecker/BarcodeFile.txt");

        // assignments
        taxRate = 6.625f;

        // inputs
        barcode = inFile.nextLine();

        str = barcode.substring(0, 4);
        deptCode = Integer.parseInt(str);

        str = barcode.substring(4, 10);
        partNum = Integer.parseInt(str);

        str = barcode.substring(10, 12);
        month = Integer.parseInt(str);

        str = barcode.substring(12, 14);
        date = Integer.parseInt(str);

        str = barcode.substring(14, 16);
        year = Integer.parseInt(str);

        str = barcode.substring(16, 20);
        price = Integer.parseInt(str) / 100.00;

        // inputs for department names if statements
        deptName = "";
        tax = true;

        if (deptCode >= 1000 && deptCode <= 1999) {
            deptName = "Clothing";
            tax = false;
        }

        else if (deptCode >= 2000 && deptCode <= 2999) {
            deptName = "Hardware";
            tax = true;
        }

        else if (deptCode >= 3000 && deptCode <= 3999) {
            deptName = "Food";
            tax = false;
        }

        else if (deptCode >= 4000 && deptCode <= 4999) {
            deptName = "Toys";
            tax = true;
        }

        else if (deptCode >= 5000 && deptCode <= 5999) {
            deptName = "Sporting Goods";
            tax = true;
        }

        else if (deptCode > 6000 && deptCode < 6999) {
            deptName = "Misc";
            tax = false;
        }

        // using switch statements for decoding month
        monthName = "";
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }

        Scanner kb = new Scanner(System.in);

        // ask user where to print
        System.out.println("\nSelect where you want to print your receipt");
        System.out.println("1. Print to Screen\n2. Print to Data File\n3. Both");
        userChoice = kb.nextLine();

        // ask user if he/she have a discount card
        System.out.println("\nDo you have a discount card? 'Y' for yes. 'N' for no...");
        cardReply = kb.nextLine().toUpperCase();

        if (cardReply.charAt(0) == 'Y') {
            discountCard = true;
        } else if (cardReply.charAt(0) == 'N') {
            discountCard = false;
        }

        // switch (cardReply) {
        // case "Y":
        // discountCard = true;
        // break;
        // case "y":
        // discountCard = true;
        // break;
        // case "N":
        // discountCard = false;
        // break;
        // case "n":
        // discountCard = false;
        // break;
        // default:
        // discountCard = false;
        // }

        if (discountCard && tax) {
            taxAmt = price * taxRate / 100;
            discountAmt = price * 0.10f;
            totalPrice = price + taxAmt - discountAmt;
        } else if (!discountCard && tax) {
            discountAmt = 0;
            taxAmt = price * taxRate / 100;
            totalPrice = price + taxAmt - discountAmt;
        } else if (discountCard && !tax) {
            discountAmt = price * 0.10f;
            taxAmt = 0;
            totalPrice = price + taxAmt - discountAmt;
        } else {
            discountAmt = 0;
            taxAmt = 0;
            totalPrice = price;
        }

        if (userChoice.charAt(0) == '1' || userChoice.charAt(0) == '3') {
            System.out.println("\n\n******************************");
            System.out.printf("%s%10s%4d%s%3d%d\n", "Acme Store", monthName, date, ",", 20, year);
            System.out.println("******************************");
            System.out.println(deptName + " Department");
            System.out.println("Item" + " # " + partNum);
            System.out.printf("\n%s%20s%.2f\n", "Price", "$", price);
            if (discountCard) {
                System.out.printf("%s%12s%.2f\n", "Discount (10%)", "$", (price / 10));
            }
            if (tax) {
                System.out.printf("%s%12s%.2f\n", "Tax   (6.625%)", "$", ((price * taxRate) / (100)));
            }
            System.out.println("******************************");
            System.out.printf("%s%20s%.2f\n", "Total", "$", totalPrice);
            System.out.println("******************************");
        }

        if (userChoice.charAt(0) == '2' || userChoice.charAt(0) == '3') {
            outFile.println("\n\n******************************");
            outFile.printf("%s%10s%4d%s%3d%d\n", "Acme Store", monthName, date, ",", 20, year);
            outFile.println("******************************");
            outFile.println(deptName + " Department");
            outFile.println("Item" + " # " + partNum);
            outFile.printf("\n%s%20s%.2f\n", "Price", "$", price);
            if (discountCard) {
                outFile.printf("%s%12s%.2f\n", "Discount (10%)", "$", (price / 10));
            }
            if (tax) {
                outFile.printf("%s%12s%.2f\n", "Tax   (6.625%)", "$", ((price * taxRate) / (100)));
            }
            outFile.println("******************************");
            outFile.printf("%s%20s%.2f\n", "Total", "$", totalPrice);
            outFile.println("******************************");
        }

        kb.close();
        inFile.close();
        outFile.close();
    }

}