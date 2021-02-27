import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class MostPopularProductsAlgo {
    public static File inputFile;
    static Scanner inputFileScanner;
    static Scanner keyboardInput;
    static ArrayList<ArrayList<Object>> prodArrayList = new ArrayList<>();
    static int counter = 0;
    static float largest_number = 0;
    static float[] finalResultPercentages;
    static String[] finalResultProductName;
    static float[] factorsContribution;

    public static void main(String[] args) throws IOException {
        fileScanner();
        dataManager(menuCard());
        logicManager();
    }

    private static void fileScanner() throws FileNotFoundException {
        inputFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/product_data.txt");
        if (!inputFile.exists()) {
            System.err.println("File Not Found!");
            System.exit(0);
        }
        inputFileScanner = new Scanner(inputFile);
        inputFileScanner.nextLine();
        inputFileScanner.nextLine();
        inputFileScanner.nextLine();
    }

    private static String menuCard() {
        String inputUserChoice;

        System.out.println("\n\n1. Product Price\n" + "2. Quantity\n" + "3. Product Ratings\n" + "4. Total User Count\n"
                + "5. Discount");
        System.out.println(
                "\nType 'A', If you want to include all of the above factors to fetch the Most Popular Products!!");
        System.out.println(
                "\nType Factor key followed by a space, If you want to exclude one or more factors to fetch the Most Popular Products!!");
        keyboardInput = new Scanner(System.in);
        try {
            inputUserChoice = keyboardInput.nextLine();
            if (inputUserChoice.equalsIgnoreCase("A")) {
                inputUserChoice = "";
            }
        } catch (java.util.InputMismatchException e) {
            inputUserChoice = "";
        }

        return inputUserChoice;
    }

    private static void dataManager(String excluded_factors) {
        Boolean price_factor_included = true, qty_factor_included = true, ratings_factor_included = true,
                total_rating_user_count_factor_included = true, discount_factor_included = true;
        String[] excluded_factors_arr = excluded_factors.split(" ");

        for (String excluded_factor : excluded_factors_arr) {
            if (excluded_factor.isBlank() == false && (Character.isLetter(excluded_factor.charAt(0)) == false)) {
                switch (Integer.parseInt(excluded_factor)) {
                    case 1:
                        price_factor_included = false;
                        break;
                    case 2:
                        qty_factor_included = false;
                        break;
                    case 3:
                        ratings_factor_included = false;
                        break;
                    case 4:
                        total_rating_user_count_factor_included = false;
                        break;
                    case 5:
                        discount_factor_included = false;
                        break;
                    default:
                        System.err.println(
                                "There is something wrong with you! You are advised to go check with a Doc...");
                        System.exit(0);
                        break;
                }
            } else if (excluded_factor.isBlank() == false) {
                System.err.println("There is something wrong with you! You are advised to go check with a Doc...");
                System.exit(0);
            }
        }

        counter = 0;
        while (inputFileScanner.hasNext()) {
            prodArrayList.add(new ArrayList<Object>());

            if (price_factor_included) {
                prodArrayList.get(counter).add(inputFileScanner.nextFloat());
            } else {
                inputFileScanner.nextFloat();
            }

            if (qty_factor_included) {
                prodArrayList.get(counter).add((float) inputFileScanner.nextInt());
            } else {
                inputFileScanner.nextInt();
            }

            if (ratings_factor_included) {
                prodArrayList.get(counter).add(inputFileScanner.nextFloat());
            } else {
                inputFileScanner.nextFloat();
            }

            if (total_rating_user_count_factor_included) {
                prodArrayList.get(counter).add((float) inputFileScanner.nextInt());
            } else {
                inputFileScanner.nextInt();
            }

            if (discount_factor_included) {
                prodArrayList.get(counter).add((float) inputFileScanner.nextInt());
            } else {
                inputFileScanner.nextInt();
            }

            prodArrayList.get(counter).add(inputFileScanner.nextLine().trim());

            counter++;
        }

        System.out.println(
                "Press 'Y' If you want to change the contribution of each factors? If not, Press any other key & it'll be same ["
                        + (100 / (prodArrayList.get(0).size() - 1)) + "%] for each and every factors!!");
        keyboardInput = new Scanner(System.in);
        String inputUserChoice = keyboardInput.nextLine();
        if (inputUserChoice.equalsIgnoreCase("Y")) {
            factorsContribution = new float[prodArrayList.get(0).size() - 1];

            for (int i = 0; i < prodArrayList.get(0).size() - 1; i++) {
                System.out.println("Enter your choice of contribution(%) of factor - " + (i + 1) + "....");
                keyboardInput = new Scanner(System.in);
                float inputUserChoiceOfContribution = keyboardInput.nextFloat();
                factorsContribution[i] = inputUserChoiceOfContribution;
            }
        } else {
            factorsContribution = new float[0];
        }

        if (factorsContribution.length != 0) {
            float factorsContributionSum = 0;
            for (int i = 0; i < factorsContribution.length; i++) {
                factorsContributionSum += factorsContribution[i];
            }

            if (factorsContributionSum != 100) {
                System.err.println("The sum of all factors' contribution has to be 100!! Whereas your sum is ==> "
                        + factorsContributionSum);
                System.exit(0);
            }
        }

        System.out.println(
                "------------------------------------------------------------- Before applying the algorithm -------------------------------------------------------------");
        for (int i = 0; i < prodArrayList.size(); i++) {
            System.out.println("  " + (i + 1) + " --> " + prodArrayList.get(i));
        }
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    private static void logicManager() {
        finalResultPercentages = new float[prodArrayList.size()];
        finalResultProductName = new String[prodArrayList.size()];

        System.out.println(
                "\n\n------------------------------------------------------------- After applying the algorithm -------------------------------------------------------------");

        int i = 0, j = 0;
        for (j = 0; j < (prodArrayList.get(0).size() - 1); j++) {
            for (i = 0; i < prodArrayList.size(); i++) {
                if (largest_number == 0) {
                    largest_number = (float) prodArrayList.get(i).get(j);
                } else if (largest_number <= (float) prodArrayList.get(i).get(j)) {
                    largest_number = (float) prodArrayList.get(i).get(j);
                }
            }

            for (i = 0; i < prodArrayList.size(); i++) {

                if (factorsContribution.length == 0) {
                    prodArrayList.get(i).set(j,
                            (((float) prodArrayList.get(i).get(j) * (100 / (prodArrayList.get(0).size() - 1)))
                                    / largest_number));
                } else {
                    prodArrayList.get(i).set(j,
                            (((float) prodArrayList.get(i).get(j) * (factorsContribution[j])) / largest_number));
                }
            }

            largest_number = 0;
        }

        // for(i = 0; i < prodArrayList.size(); i++){
        // System.out.println(" "+(i+1) + " --> " + prodArrayList.get(i));
        // }

        // System.out.println("\n");

        float percentageAdder = 0;
        String productName = "";
        for (i = 0; i < prodArrayList.size(); i++) {
            for (j = 0; j < prodArrayList.get(i).size(); j++) {
                if (j == (prodArrayList.get(i).size() - 1)) {
                    productName = (String) prodArrayList.get(i).get(j);
                } else {
                    percentageAdder += (float) prodArrayList.get(i).get(j);
                }
            }

            finalResultPercentages[i] = percentageAdder;
            finalResultProductName[i] = productName;

            percentageAdder = 0;
        }

        quickSort(finalResultPercentages, 0, prodArrayList.size() - 1);

        for (i = 0; i < finalResultPercentages.length; i++) {
            System.out.println(" " + Math.round(finalResultPercentages[i]) + "% --> " + finalResultProductName[i]);
        }

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private static int partition(float arr[], int low, int high) {
        float pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] > pivot) {
                i++;

                // swap arr[i] and arr[j]
                float temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                String temp_string = finalResultProductName[i];
                finalResultProductName[i] = finalResultProductName[j];
                finalResultProductName[j] = temp_string;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        float temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        String temp_string = finalResultProductName[i + 1];
        finalResultProductName[i + 1] = finalResultProductName[high];
        finalResultProductName[high] = temp_string;

        return i + 1;
    }

    private static void quickSort(float arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);  // Before pi
            quickSort(arr, pi + 1, high); // After pi
        }
    }
}
