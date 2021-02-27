//Rutvi Shah
//Carpet file IO

import java.util.Scanner;
import java.io.*;

public class Carpentry {
    public static void main(String[] args) throws IOException {
        // variables
        String item1, item2, item3;
        Float length1, length2, length3;
        float width1, width2, width3;
        float price1, price2, price3;
        float cost1, cost2, cost3;
        float total;

        File myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Carpet.txt");
        Scanner inFile = new Scanner(myFile);
        System.out.println(inFile);
        Scanner kb = new Scanner(System.in);

        PrintWriter outFile = new PrintWriter("/Users/tirth/Documents/Java/ProficiencyChecker/CarpetFile.txt");

        // input
        item1 = inFile.next();
        item2 = inFile.next();
        item3 = inFile.next();

        inFile.nextLine();

        length1 = inFile.nextFloat();
        length2 = inFile.nextFloat();
        length3 = inFile.nextFloat();

        inFile.nextLine();

        width1 = inFile.nextFloat();
        width2 = inFile.nextFloat();
        width3 = inFile.nextFloat();

        inFile.nextLine();

        price1 = inFile.nextFloat();
        price2 = inFile.nextFloat();
        price3 = inFile.nextFloat();

        // calculations
        cost1 = length1 * width1 * price1;
        cost2 = length2 * width2 * price3;
        cost3 = length3 * width3 * price3;
        total = cost1 + cost2 + cost3;

        // output
        System.out.println("************************************************************************************");
        System.out.printf("%30s%20s%20s\n", "Room 1", "Room 2", "Room 3");
        System.out.println("************************************************************************************");
        System.out.printf("%-8s%20.2f%20.2f%20.2f\n", item1, length1, length2, length3);
        System.out.printf("%-8s%20.2f%20.2f%20.2f\n", item2, width1, width2, width3);
        System.out.printf("%-8s%20.2f%20.2f%20.2f\n", item3, price1, price2, price3);
        System.out.println("************************************************************************************");
        System.out.printf("%-8s%20.2f%20.2f%20.2f\n", "Cost", cost1, cost2, cost3);
        System.out.println("************************************************************************************");
        System.out.printf("%-8s%40.2f\n", "Total", total);

        inFile.close();
        outFile.close();
        kb.close();
    }
}
