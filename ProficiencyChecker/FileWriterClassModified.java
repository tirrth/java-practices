//Rutvi Shah
//Lab 4
//Fruit Report

import java.io.*;
import java.util.Scanner;

public class FileWriterClassModified {
    public static void main(String[] args) throws IOException {
        String title;
        Integer i;
        Double grandTotal = 0.00, total = 0.00;

        File myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Input.txt");
        PrintWriter outFile = new PrintWriter("/Users/tirth/Documents/Java/ProficiencyChecker/Output.txt");
        Scanner inFile = new Scanner(myFile);
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the Title: ");
        title = kb.nextLine();

        System.out.printf("\n\n%40s\n", title);
        System.out.println("**********************************************************");
        System.out.printf("%-4s%16s%15s%9s%13s\n", "Items", "Grade", "Quantity", "Price", "Total");
        System.out.println("**********************************************************");
        outFile.printf("\n\n%40s\n", title);
        outFile.println("**********************************************************");
        outFile.printf("%-4s%16s%15s%9s%13s\n", "Items", "Grade", "Quantity", "Price", "Total");
        outFile.println("**********************************************************");

        while (inFile.hasNextLine()) {
            for (i = 0; i < 4 && inFile.hasNextLine(); i++) {
                String itemName = inFile.nextLine();
                String grades = inFile.nextLine();
                Integer quantity = inFile.nextInt();
                double price = inFile.nextDouble();
                total = total + (price * quantity);
                System.out.printf("%-6s%13s%13d%13.2f%13.2f\n", itemName, grades, quantity, price, total.floatValue());
                outFile.printf("%-6s%13s%13d%13.2f%13.2f\n", itemName, grades, quantity, price, total);
                inFile.nextLine();
            }
            grandTotal = grandTotal + total;
            System.out.println("**********************************************************");
            outFile.println("**********************************************************");
            System.out.printf("%50s%8.2f\n", "Grand Total:", grandTotal);
            outFile.printf("%50s%8.2f\n", "Grand Total:", grandTotal);
        }

        inFile.close();
        outFile.close();
        kb.close();
    }
}