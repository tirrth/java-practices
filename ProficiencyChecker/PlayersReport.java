import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class PlayersReport {
    static File myFile;
    static Scanner inFile;
    static Scanner keyboardInput;
    static int vertexCount = 5;
    Players<> players = new Players<>();
    static ArrayList<> playerDetails = new ArrayList();
    static int counter;
    // static ArrayList<Integer> playerId;
    // static ArrayList<String> playerName;
    // static ArrayList<Integer> yearInducted;
    // static ArrayList<String> teamName;
    // static ArrayList<Integer> yearsPlayed;
    // static ArrayList<Float> battingAverage;

    public static void main(String[] args) throws IOException {
        myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/Baseball.dat");
        if (!myFile.exists()) {
            System.err.println("File Not Found!");
            System.exit(0);
        }
        inFile = new Scanner(myFile);

        counter = 0;
        while (inFile.hasNext()) {
            playerDetails.add(new ArrayList<>());
            playerDetails.get(counter).add(inFile.nextInt());
            inFile.nextLine();
            playerDetails.get(counter).add(inFile.nextLine());
            playerDetails.get(counter).add(inFile.nextInt());
            inFile.nextLine();
            playerDetails.get(counter).add(inFile.nextLine());
            playerDetails.get(counter).add(inFile.nextInt());
            playerDetails.get(counter).add(inFile.nextFloat());
            counter++;
        }

        switch (menuCard()) {
            case 1:
                System.out.println("You chose to find average years played!!");
                averageYearsCalc();
                break;
            case 2:
                System.out.println("ds");
                sortedByTeam();
                break;
            case 3:
                System.out.println("re");
                break;
            case 4:
                System.out.println("bv");
                break;
            case 5:
                System.out.println("as");
                break;
            case 6:
                System.err.println("You chose to Quit!");
                System.exit(0);
                break;
            default:
                System.err.println("There is something wrong with you! You are advised to go check with a Doc...");
                System.exit(0);
                break;
        }

        // // int playerId[];
        // playerId = new ArrayList<Integer>();
        // playerName = new ArrayList<String>();
        // yearInducted = new ArrayList<Integer>();
        // teamName = new ArrayList<String>();
        // yearsPlayed = new ArrayList<Integer>();
        // battingAverage = new ArrayList<Float>();

        // System.out.println(playerDetails);

        inFile.close();
    }

    static int menuCard() {
        int inputUserChoice;

        System.out.println("1. Average years played\n" + "2. Print table Sorted by Team\n"
                + "3. Print table Sorted by Batting average (descending)\n" + "4. Search for Name\n"
                + "5. Search for Id\n" + "6. Quit");
        System.out.println("\nEnter your choice..");
        keyboardInput = new Scanner(System.in);
        try {
            inputUserChoice = keyboardInput.nextInt();
        } catch (java.util.InputMismatchException e) {
            inputUserChoice = 0;
        }
        return inputUserChoice;
    }

    static void averageYearsCalc() {
        // System.out.println(playerDetails);
        Integer yearsPlayed;
        Integer averageYearsPlayed;
        // yearsPlayed = new ArrayList<Integer>();

        // while (inFile.hasNext()) {
        // inFile.nextLine();
        // inFile.nextLine();
        // inFile.nextLine();
        // inFile.nextLine();
        // yearsPlayed.add(inFile.nextInt());
        // inFile.nextLine();
        // }

        averageYearsPlayed = 0;
        for (int i = 0; i < playerDetails.size(); i++) {
            // System.out.println(playerDetails.get(i).get(4));
            yearsPlayed = playerDetails.get(i).get(4);
            // System.out.println(playerDetails.get(i).get(4).getClass().getName());
            // System.out.println(playerDetails.get(i).get(4));
            averageYearsPlayed += yearsPlayed;
        }
        System.out.println("Aerage years played: " + averageYearsPlayed);
    }

    static void sortedByTeam() {
        // playerId = new ArrayList<Integer>();
        // playerName = new ArrayList<String>();
        // yearInducted = new ArrayList<Integer>();
        // teamName = new ArrayList<String>();
        // yearsPlayed = new ArrayList<Integer>();
        // battingAverage = new ArrayList<Float>();

        // while (inFile.hasNext()) {
        // playerId.add(inFile.nextInt());
        // inFile.nextLine();
        // playerName.add(inFile.nextLine());
        // yearInducted.add(inFile.nextInt());
        // inFile.nextLine();
        // teamName.add(inFile.nextLine());
        // yearsPlayed.add(inFile.nextInt());
        // battingAverage.add(inFile.nextFloat());
        // }
        // System.out.println(playerId);
        // System.out.println(playerName);
        // System.out.println(yearInducted);
        // System.out.println(teamName);
        // System.out.println(yearsPlayed);
        // System.out.println(battingAverage);
    }
}