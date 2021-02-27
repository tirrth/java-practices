import java.util.Scanner;
import java.io.*;

public class TheSameThingAgainAndAgain {
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {
        // defining types
        String userName, fileUserName, formattedString, previousUserName, smallestTotalUserName;
        Integer userSaleQuantity, userTotalSaleQuantity;
        Double userSaleAmount, userTotalSaleAmount, previousUserTotalSaleAmount, smallestTotal;
        File myFile = null;
        PrintWriter outFile = null;
        Scanner inFile = null, inFileTwo = null;

        try {
            myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Sales.txt");
            outFile = new PrintWriter("/Users/tirth/Documents/Java/ProficiencyChecker/SaleRpt.txt");
            inFile = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("\nFile Not Found!!\n");
            System.exit(0);
        }

        // calling the keyboardInput Method to go further
        Scanner inputRead = new Scanner(System.in);
        System.out.println("\nEnter your sweet name..." + ANSI_RED_BACKGROUND + ANSI_WHITE);
        userName = inputRead.nextLine();

        // printing consmetic stuff in the output file
        formattedString = String.format(ANSI_RESET + "\n\n%9s%35s%16s%20s\n", "Person", "Quantity", "Sale", "Total");
        System.out.printf(formattedString);
        System.out.println("***********************************************************************************");
        outFile.printf("\n%9s%35s%16s%20s\n", "Person", "Quantity", "Sale", "Total");
        outFile.println("***********************************************************************************");

        // file data processing
        userTotalSaleQuantity = 0;
        userTotalSaleAmount = 0.00;
        inFile.nextLine();
        // long startTimeNano = System.nanoTime();
        long startTimeMilli = System.currentTimeMillis();
        while (inFile.hasNext()) {
            userSaleQuantity = inFile.nextInt();
            userSaleAmount = inFile.nextDouble();
            fileUserName = inFile.nextLine().trim();

            if (fileUserName.equalsIgnoreCase(userName)) {
                userTotalSaleQuantity = userTotalSaleQuantity + userSaleQuantity;
                userTotalSaleAmount = userTotalSaleAmount + (userSaleQuantity * userSaleAmount);

                // printing input file data in the output file
                System.out.printf("%20s%20d%20.2f%20.2f\n", fileUserName, userSaleQuantity, userSaleAmount,
                        (userSaleQuantity * userSaleAmount));
                outFile.printf("%20s%20d%20.2f%20.2f\n", fileUserName, userSaleQuantity, userSaleAmount,
                        (userSaleQuantity * userSaleAmount));
            }
        }

        // printing input file data in the output file
        System.out.println("***********************************************************************************");
        System.out.printf("%40d%40.2f\n", userTotalSaleQuantity, userTotalSaleAmount);
        System.out.println("***********************************************************************************");
        outFile.println("***********************************************************************************");
        outFile.printf("%40d%40.2f\n", userTotalSaleQuantity, userTotalSaleAmount);
        outFile.println("***********************************************************************************");

        inFile = new Scanner(myFile);
        userTotalSaleQuantity = 0;
        userTotalSaleAmount = 0.00;
        previousUserTotalSaleAmount = 0.00;
        previousUserName = "";
        smallestTotalUserName = ""; // Initialised this here just to make my debugger happy :)
        smallestTotal = 0.00; // Initialised this here just to make my debugger happy :)
        inFile.nextLine();

        while (inFile.hasNext()) {
            userSaleQuantity = inFile.nextInt();
            userSaleAmount = inFile.nextDouble();
            fileUserName = inFile.nextLine().trim();
            previousUserName = fileUserName;

            inFileTwo = new Scanner(myFile);
            inFileTwo.nextLine();
            userTotalSaleAmount = 0.00;
            while (inFileTwo.hasNext()) {
                userSaleQuantity = inFileTwo.nextInt();
                userSaleAmount = inFileTwo.nextDouble();
                fileUserName = inFileTwo.nextLine().trim();

                if (fileUserName.equalsIgnoreCase(previousUserName)) {
                    userTotalSaleAmount = userTotalSaleAmount + (userSaleQuantity * userSaleAmount);
                    previousUserTotalSaleAmount = userTotalSaleAmount;
                    previousUserName = fileUserName;
                }
            }

            if (smallestTotal == 0.00 || smallestTotal > previousUserTotalSaleAmount) {
                smallestTotal = previousUserTotalSaleAmount;
                smallestTotalUserName = previousUserName;
            }
        }
        long endTimeMilli = System.currentTimeMillis();

        System.out.printf("%s%.2f%s%s\n\n",
                ANSI_GREEN_BACKGROUND + ANSI_WHITE + "The person with the smallest total ($ ", smallestTotal, ") was: ",
                smallestTotalUserName + ANSI_RESET);
        outFile.printf("%s%.2f%s%s", "The person with the smallest total ($ ", smallestTotal, ") was: ",
                smallestTotalUserName);
        System.out.println("Time taken in the execution of the program --> " + ANSI_RED_BACKGROUND
                + (endTimeMilli - startTimeMilli) / 1000.00 + "s" + ANSI_RESET);

        inFile.close();
        inFileTwo.close();
        inputRead.close();
        outFile.close();
    }
}