import java.util.Scanner;
import java.io.*;

public class TryingToLearnSomethingNew {
    public static void main(String[] args) throws IOException {
        // defining types
        Boolean fileFound;
        File myFile = null;
        Scanner inFile = null;

        // file data fetching
        fileFound = false;
        while (fileFound == false) {
            try {
                myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Sales.txt");
                inFile = new Scanner(myFile);
                fileFound = true;
            } catch (FileNotFoundException e) {
                System.out.println("\ninvalid file path, please enter the correct one!!\n");
                System.exit(0);
            }
        }

        inFile.nextLine();
        while (inFile.hasNext()) {
            System.out.println(inFile.nextInt() * inFile.nextFloat());
            System.out.println(inFile.nextLine());
        }

        // closing all the opened scanners
        inFile.close();
    }
}
