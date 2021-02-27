
import java.io.*;
import java.util.Scanner;

public class ScannerOutput {
    public static void main(String[] args) {
        final String storeName = "ACME STORE";
        final String barcodeValue = "22349999991019203150";
        String buyersChoice;
        String department = "";
        String discountCard = "";
        Boolean cardFlag = false;
        Double total = 0.00;

        Integer deptCode = Integer.parseInt(barcodeValue.substring(0, 4));
        Integer partNumber = Integer.parseInt(barcodeValue.substring(4, 10));
        String month = barcodeValue.substring(10, 12);
        Integer date = Integer.parseInt(barcodeValue.substring(12, 14));
        Integer year = Integer.parseInt(barcodeValue.substring(14, 16));
        Double price = Double.parseDouble(barcodeValue.substring(16, 18) + "." + barcodeValue.substring(18, 20));

        switch (month) {
            case "1":
                month = "Jan";
                break;
            case "2":
                month = "Feb";
                break;
            case "3":
                month = "March";
                break;
            case "4":
                month = "April";
                break;
            case "5":
                month = "May";
                break;
            case "6":
                month = "June";
                break;
            case "7":
                month = "July";
                break;
            case "8":
                month = "Aug";
                break;
            case "9":
                month = "Sept";
                break;
            case "10":
                month = "Oct";
                break;
            case "11":
                month = "Nov";
                break;
            case "12":
                month = "Dec";
                break;
        }

        if (deptCode >= 1000 && deptCode <= 1999) {
            department = "Clothing";
        } else if (deptCode >= 2000 && deptCode <= 2999) {
            department = "Hardware";
        } else if (deptCode >= 3000 && deptCode <= 3999) {
            department = "Food";
        } else if (deptCode >= 4000 && deptCode <= 4999) {
            department = "Toys";
        } else if (deptCode >= 5000 && deptCode <= 5999) {
            department = "Sporting Goods";
        } else if (deptCode >= 6000 && deptCode <= 9999) {
            department = "Misc.";
        }

        System.out.println("\nSelect your choice of platform to print your reciept:");
        System.out.println("1. Print to Screen\n2. Print to Data File\n3. both");

        Scanner choiceScanner = new Scanner(System.in);
        buyersChoice = choiceScanner.nextLine();

        System.out.println("\nDo you have a discount card to get 10% off on your purchases? ");
        System.out.println("Press 'Y', if you have it\nPress 'N', if you don't have it");
        discountCard = choiceScanner.nextLine();

        switch (discountCard) {
            case "Y":
                cardFlag = true;
                break;
            case "y":
                cardFlag = true;
                break;
            case "N":
                cardFlag = false;
                break;
            case "n":
                cardFlag = false;
                break;
            default:
                cardFlag = false;
        }

        if (cardFlag == false && (department == "Clothing" || department == "Food")) {
            total = price;
        } else if (cardFlag == true && (department == "Clothing" || department == "Food")) {
            total = price - (price / 10);
        } else if (cardFlag == false && (department != "Clothing" && department != "Food")) {
            total = price - ((price * 6.625) / 100);
        } else if (cardFlag == true && (department != "Clothing" && department != "Food")) {
            total = price - ((price * 16.625) / 100);
        }

        switch (Integer.parseInt(buyersChoice)) {
            case 1:
                recieptGenerator(storeName, month, date, year, department, partNumber, price, total, cardFlag, "Print");
                break;
            case 2:
                recieptGenerator(storeName, month, date, year, department, partNumber, price, total, cardFlag, "Write");
                break;
            case 3:
                recieptGenerator(storeName, month, date, year, department, partNumber, price, total, cardFlag, "Print");
                recieptGenerator(storeName, month, date, year, department, partNumber, price, total, cardFlag, "Write");
                break;
            default:
                System.out.println("You've entered wrong digit!!");
        }
        choiceScanner.close();
    }

    static void recieptGenerator(String storeName, String month, Integer date, Integer year, String department,
            Integer partNumber, Double price, Double total, Boolean cardFlag, String buyersChoice) {

        // if (buyerChoice == "Write") {
        // File outFile = new
        // File("Users/tirth/Documents/Java/ProficiencyChecker/DataFile.txt");
        // // Instantiating the PrintStream class
        // PrintStream outputFile = new PrintStream(outFile);
        // System.setOut(outputFile);
        // } else if (buyerChoice == "Print") {
        // // Do nothing, Just Netflix and Chill!!
        // }

        System.out.println("\n\n******************************");
        System.out.printf("%s%10s%4d%s%3d%d\n", storeName, month, date, ",", 20, year);
        System.out.println("******************************");
        System.out.println(department + " Department");
        System.out.println("Item" + " # " + partNumber);
        System.out.printf("\n%s%20s%.2f\n", "Price", "$", price);
        if (cardFlag == true) {
            System.out.printf("%s%12s%.2f\n", "Discount (10%)", "$", (price / 10));
        }
        if (department != "Clothing" && department != "Food") {
            System.out.printf("%s%12s%.2f\n", "Tax   (6.625%)", "$", ((price * 6.625) / 100));
        }
        System.out.println("******************************");
        System.out.printf("%s%20s%.2f\n", "Total", "$", total);
        System.out.println("******************************");

        // if (buyerChoice == "Write") {
        // outputFile.close();
        // }
    }
}