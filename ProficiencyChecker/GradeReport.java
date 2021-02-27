import java.io.*;
import java.util.Scanner;

public class GradeReport {
    public static File myFile;
    static Scanner inFile, inFileTwo;
    static Scanner keyboardInput;

    public static void main(String[] args) throws IOException {
        myFile = new File("/Users/tirth/Documents/Java/ProficiencyChecker/grades.txt");
        if (!myFile.exists()) {
            System.err.println("File Not Found!");
            System.exit(0);
        }
        inFile = new Scanner(myFile);
        inFile.nextLine();

        switch (menuCard()) {
            case 1:
                System.out.println("You chose Class Average");
                classAverage();
                break;
            case 2:
                System.out.println("You chose Student Average");
                studentAverage();
                break;
            case 3:
                System.out.println("You chose Standard Deviation");
                calculateSD();
                break;
            case 4:
                System.out.println("You chose Letter Grades");
                letterGrades();
                break;
            case 5:
                System.out.println("You chose Minimum/Maximum");
                maxMinGrade();
                break;
            case 6:
                System.out.println("You chose to Locate a student's grades");
                locateIndividual();
                break;
            case 7:
                System.out.println(
                        "You chose to Locate All students where the difference between exam1 and exam2 is greater than 15");
                locatePercentageBase();
                break;
            case 8:
                System.out.println("You chose Histogram");
                histogram();
                break;
            case 9:
                System.out.println("You chose to Update data");
                updateData();
                break;
            case 10:
                System.err.println("You chose to Quit!");
                System.exit(0);
                break;
            default:
                System.err.println("There is something wrong with you! You are advised to go check with a Doc...");
                System.exit(0);
                break;
        }

        inFile.close();
    }

    private static int menuCard() {
        int inputUserChoice;

        System.out.println("1. Class Average\n" + "2. Student Average\n" + "3. Standard Deviation\n"
                + "4. Letter grades\n" + "5. Minimum/Maximum\n" + "6. Locate a student's grades\n"
                + "7. Locate All students where the difference between exam1 and exam2 is greater than 15%\n"
                + "8. Histogram\n" + "9. Update data (enter name, new test scores)\n" + "10. Quit");
        System.out.println("\nEnter your choice..");
        keyboardInput = new Scanner(System.in);
        try {
            inputUserChoice = keyboardInput.nextInt();
        } catch (java.util.InputMismatchException e) {
            inputUserChoice = 0;
        }
        return inputUserChoice;
    }

    private static void updateData() {
        String inputStudentName, studentName;
        float studentGradeExamOne, studentGradeExamTwo, inputStudentExamOneGrade, inputStudentExamTwoGrade;
        int totalLineCount, falseConditionCount;

        System.out.println("Enter student name to update his/her grades...");
        keyboardInput = new Scanner(System.in);
        inputStudentName = keyboardInput.nextLine();

        studentName = "";
        totalLineCount = 0;
        falseConditionCount = 0;
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }

            if (!((inputStudentName.replaceAll("\\s", "")).equalsIgnoreCase(studentName.replaceAll("\\s", "")))) {
                falseConditionCount++;
            }

            inFile.nextFloat();
            inFile.nextFloat();
            studentName = "";
            totalLineCount++;
        }
        if (totalLineCount == falseConditionCount) {
            System.err.println("No Students Matched with your given name!!");
            System.exit(0);
        }

        try {
            inFile = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inFile.nextLine();
        System.out.println("Enter " + inputStudentName + "'s grade of exam-1...");
        inputStudentExamOneGrade = keyboardInput.nextFloat();
        if (inputStudentExamOneGrade > 100) {
            System.err.println("Grade Range is in-between 1 and 100");
            System.exit(0);
        }
        System.out.println("Enter " + inputStudentName + "'s grade of exam-2...");
        inputStudentExamTwoGrade = keyboardInput.nextFloat();
        if (inputStudentExamTwoGrade > 100) {
            System.err.println("Grade Range is in-between 1 and 100");
            System.exit(0);
        }

        System.out.println("Grades Updated Successfully!!!");
        outputTableHeader();

        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }
            studentGradeExamOne = inFile.nextFloat();
            studentGradeExamTwo = inFile.nextFloat();

            if ((inputStudentName.replaceAll("\\s", "")).equalsIgnoreCase(studentName.replaceAll("\\s", ""))) {
                studentGradeExamOne = inputStudentExamOneGrade;
                studentGradeExamTwo = inputStudentExamTwoGrade;
            }
            while (studentName.length() < 30) {
                studentName += " ";
            }
            System.out.println(studentName + String.format("%7s", studentGradeExamOne)
                    + String.format("%20s", studentGradeExamTwo));

            studentName = "";
        }
        outputTableFooter();
    }

    private static void histogram() {
        int inputHistogramChoice;

        System.out.println("1. Vertical Histogram\n2. Horizontal Histogram\n");
        System.out.println("Which type of histogram do you want to print?");
        keyboardInput = new Scanner(System.in);
        inputHistogramChoice = keyboardInput.nextInt();

        switch (inputHistogramChoice) {
            case 1:
                verticalHistogram();
                break;
            case 2:
                horizontalHistogram();
                break;
            default:
                System.out.println("You've entered wrong number!!");
                System.exit(0);
                break;
        }
    }

    private static void horizontalHistogram() {
        String studentName;
        float studentGradeOne, studentGradeTwo;
        int starCounter, formatCounter;

        // Horizontal Histogram of Exam-1
        System.out.println("\n\n******************** Horizontal Histogram of Exam-1 ********************\n");
        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }
            studentGradeOne = inFile.nextFloat();
            inFile.nextFloat();

            while (studentName.length() < 26) {
                studentName += " ";
            }

            // Exam One
            System.out.print(studentName);
            for (starCounter = 10; starCounter <= studentGradeOne; starCounter += 10) {
                System.out.print("[][]");
            }

            formatCounter = starCounter;
            while (formatCounter <= 100) {
                System.out.print("    ");
                formatCounter += 10;
            }

            System.out.printf("%10.2f%4s%d%s", studentGradeOne, "[>=", (starCounter - 10), "]");
            System.out.println("\n");

            studentName = "";
        }

        try {
            inFile = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inFile.nextLine();

        // Horizontal Histogram of Exam-2
        System.out.println("\n\n******************** Horizontal Histogram of Exam-2 ********************\n");
        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }
            inFile.nextFloat();
            studentGradeTwo = inFile.nextFloat();

            while (studentName.length() < 26) {
                studentName += " ";
            }

            // Exam Two
            System.out.print(studentName);
            for (starCounter = 10; starCounter <= studentGradeTwo; starCounter += 10) {
                System.out.print("[][]");
            }

            formatCounter = starCounter;
            while (formatCounter <= 100) {
                System.out.print("    ");
                formatCounter += 10;
            }

            System.out.printf("%10.2f%4s%d%s", studentGradeTwo, "[>=", (starCounter - 10), "]");
            System.out.println("\n");

            studentName = "";
        }
    }

    private static void verticalHistogram() {
        float studentGradeOne, studentGradeTwo;
        int starCounter, formatCounter;

        // Vertical Histogram of Exam-1
        System.out.println(
                "\n\n---------------------------------------------------------- Vertical Histogram of Exam-1 ----------------------------------------------------------\n");
        starCounter = 110;
        while (starCounter > 10) {
            try {
                inFile = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            inFile.nextLine();
            System.out.printf("%s%d%s", "[>=", (starCounter - 10), "]  ");
            while (inFile.hasNext()) {
                while (!inFile.hasNextFloat()) {
                    inFile.next();
                }
                studentGradeOne = inFile.nextFloat();
                inFile.nextFloat();

                if ((studentGradeOne >= starCounter - 10) && (studentGradeOne <= starCounter)) {
                    System.out.print(" |||| ");
                } else if (studentGradeOne >= starCounter) {
                    System.out.print(" |||| ");
                } else {
                    System.out.print("      ");
                }
            }
            System.out.print("\n");

            starCounter -= 10;
        }

        // Vertical Histogram of Exam-2
        System.out.println(
                "\n\n---------------------------------------------------------- Vertical Histogram of Exam-2 ----------------------------------------------------------\n");
        starCounter = 110;
        while (starCounter > 10) {
            try {
                inFile = new Scanner(myFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            inFile.nextLine();
            System.out.printf("%s%d%s", "[>=", (starCounter - 10), "]  ");
            while (inFile.hasNext()) {
                while (!inFile.hasNextFloat()) {
                    inFile.next();
                }
                inFile.nextFloat();
                studentGradeTwo = inFile.nextFloat();

                if ((studentGradeTwo >= starCounter - 10) && (studentGradeTwo <= starCounter)) {
                    System.out.print(" |||| ");
                } else if (studentGradeTwo >= starCounter) {
                    System.out.print(" |||| ");
                } else {
                    System.out.print("      ");
                }
            }
            System.out.print("\n");

            starCounter -= 10;
        }
    }

    private static void locatePercentageBase() {
        String studentName;
        float studentGradeExamOne, studentGradeExamTwo;
        int totalLineCount, falseConditionCount;

        outputTableHeader();

        totalLineCount = 0;
        falseConditionCount = 0;
        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }
            studentGradeExamOne = inFile.nextFloat();
            studentGradeExamTwo = inFile.nextFloat();
            if (Math.abs((studentGradeExamOne - studentGradeExamTwo)) > 15) {
                while (studentName.length() < 30) {
                    studentName += " ";
                }

                System.out.println(studentName + String.format("%7s", studentGradeExamOne)
                        + String.format("%16s", studentGradeExamTwo) + String.format("%s%.2f%s", " -- [",
                                Math.abs((studentGradeExamOne - studentGradeExamTwo)), "]"));
            } else {
                falseConditionCount++;
            }

            totalLineCount++;
            studentName = "";
        }

        if (totalLineCount == falseConditionCount) {
            System.err.println("No Student Found having a difference of >15% in his/her grades!!!");
            System.exit(0);
        } else {
            outputTableFooter();
        }
    }

    private static void locateIndividual() {
        String inputStudentName, studentName;
        float studentGradeExamOne, studentGradeExamTwo;
        int totalLineCount, falseConditionCount;

        System.out.println("Enter student name to know his/her grades...");
        keyboardInput = new Scanner(System.in);
        inputStudentName = keyboardInput.nextLine();

        outputTableHeader();

        studentName = "";
        totalLineCount = 0;
        falseConditionCount = 0;

        // **********************************************
        // #2 Count controlled while loop
        // **********************************************
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }
            studentGradeExamOne = inFile.nextFloat();
            studentGradeExamTwo = inFile.nextFloat();

            if ((inputStudentName.replaceAll("\\s", "")).equalsIgnoreCase(studentName.replaceAll("\\s", ""))) {
                while (studentName.length() < 30) {
                    studentName += " ";
                }

                System.out.println(studentName + String.format("%7s", studentGradeExamOne)
                        + String.format("%20s", studentGradeExamTwo));
            } else {
                falseConditionCount++;
            }

            studentName = "";
            totalLineCount++;
        }

        if (totalLineCount == falseConditionCount) {
            System.err.println("No Students Matched with your given name!!");
            System.exit(0);
        } else {
            outputTableFooter();
        }
    }

    private static void maxMinGrade() {
        String studentName;
        // Exam One
        float minGradeOne, maxGradeOne;
        String minGradeStudentNameOne, maxGradeStudentNameOne;
        // Exam Two
        float minGradeTwo, maxGradeTwo;
        String minGradeStudentNameTwo, maxGradeStudentNameTwo;

        studentName = "";
        // ExamOne
        minGradeOne = 0;
        maxGradeOne = 0;
        minGradeStudentNameOne = "";
        maxGradeStudentNameOne = "";

        // ExamTwo
        minGradeTwo = 0;
        maxGradeTwo = 0;
        minGradeStudentNameTwo = "";
        maxGradeStudentNameTwo = "";

        while (inFile.hasNext()) {
            float studentGrade;

            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }

            // Exam One
            studentGrade = inFile.nextFloat();
            if (minGradeOne == 0) {
                minGradeOne = studentGrade;
            } else if (studentGrade < minGradeOne) {
                minGradeOne = studentGrade;
                minGradeStudentNameOne = studentName;
            } else if (studentGrade == minGradeOne) {
                System.out.println("Minimum Grade of Exam one is: " + studentName + "--> " + minGradeOne);
            }

            if (maxGradeOne == 0) {
                maxGradeOne = studentGrade;
            } else if (studentGrade > maxGradeOne) {
                maxGradeOne = studentGrade;
                maxGradeStudentNameOne = studentName;
            } else if (studentGrade == maxGradeOne) {
                System.out.println("Maximun Grade of Exam one is: " + studentName + "--> " + maxGradeOne);
            }

            // Exam Two
            studentGrade = inFile.nextFloat();
            if (minGradeTwo == 0) {
                minGradeTwo = studentGrade;
            } else if (studentGrade < minGradeTwo) {
                minGradeTwo = studentGrade;
                minGradeStudentNameTwo = studentName;
            } else if (studentGrade == minGradeTwo) {
                System.out.println("Minimum Grade of Exam two is: " + studentName + "--> " + minGradeTwo);
            }

            if (maxGradeTwo == 0) {
                maxGradeTwo = studentGrade;
            } else if (studentGrade > maxGradeTwo) {
                maxGradeTwo = studentGrade;
                maxGradeStudentNameTwo = studentName;
            } else if (studentGrade == maxGradeTwo) {
                System.out.println("Maximun Grade of Exam two is: " + studentName + "--> " + maxGradeTwo);
            }

            studentName = "";
        }
        System.out.println("Minimum Grade of Exam one is: " + minGradeStudentNameOne + "--> " + minGradeOne);
        System.out.println("Maximum Grade of Exam one is: " + maxGradeStudentNameOne + "--> " + maxGradeOne);
        System.out.println("\nMinimum Grade of Exam two is: " + minGradeStudentNameTwo + "--> " + minGradeTwo);
        System.out.println("Maximum Grade of Exam two is: " + maxGradeStudentNameTwo + "--> " + maxGradeTwo);
    }

    private static void letterGrades() {
        String studentName;

        outputTableHeader();

        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }

            while (studentName.length() < 30) {
                studentName += " ";
            }

            System.out.println(studentName + String.format("%5s", letterGradeCalc(inFile.nextFloat()))
                    + String.format("%20s", letterGradeCalc(inFile.nextFloat())));
            studentName = "";
        }
        outputTableFooter();
    }

    public static char letterGradeCalc(float numberGrade) {
        char letterGrade;
        if (numberGrade >= 90) {
            letterGrade = 'A';
        } else if (numberGrade >= 80) {
            letterGrade = 'B';
        } else if (numberGrade >= 70) {
            letterGrade = 'C';
        } else if (numberGrade >= 60) {
            letterGrade = 'D';
        } else if (numberGrade >= 50) {
            letterGrade = 'E';
        } else {
            letterGrade = 'F';
        }
        return letterGrade;
    }

    private static void calculateSD() {
        int totalNumberCount;
        // Exam One
        float meanOne, sumOfNumbersOne, sumOfNumbersSquareOne, SumOfXSquareOne, SDOne;
        // Exam Two
        float meanTwo, sumOfNumbersTwo, sumOfNumbersSquareTwo, SumOfXSquareTwo, SDTwo;

        sumOfNumbersOne = 0;
        sumOfNumbersSquareOne = 0;
        sumOfNumbersTwo = 0;
        sumOfNumbersSquareTwo = 0;

        // **********************************************
        // #3 for loop calculation
        // **********************************************
        for (totalNumberCount = 0; inFile.hasNext(); totalNumberCount++) {
            float gradeExamOne, gradeExamTwo;

            while (!inFile.hasNextFloat()) {
                inFile.next();
            }
            gradeExamOne = inFile.nextFloat();
            gradeExamTwo = inFile.nextFloat();

            sumOfNumbersOne += gradeExamOne;
            sumOfNumbersTwo += gradeExamTwo;

            sumOfNumbersSquareOne += (gradeExamOne * gradeExamOne);
            sumOfNumbersSquareTwo += (gradeExamTwo * gradeExamTwo);
        }

        meanOne = sumOfNumbersOne / totalNumberCount;
        meanTwo = sumOfNumbersTwo / totalNumberCount;

        SumOfXSquareOne = sumOfNumbersSquareOne / totalNumberCount;
        SumOfXSquareTwo = sumOfNumbersSquareTwo / totalNumberCount;

        SDOne = (float) Math.sqrt((SumOfXSquareOne - meanOne));
        SDTwo = (float) Math.sqrt((SumOfXSquareTwo - meanTwo));

        System.out.println("Standard Deviation of exam-1 is: " + String.format("%.2f", SDOne));
        System.out.println("Standard Deviation of exam-2 is: " + String.format("%.2f", SDTwo));
    }

    private static void studentAverage() {
        float studentAverage;
        String studentName;

        System.out.println("*********************************************");
        System.out.printf("%s%40s\n", "Name", "Average Grade");
        System.out.println("*********************************************");

        studentAverage = 0;
        studentName = "";
        while (inFile.hasNext()) {
            while (!inFile.hasNextFloat()) {
                studentName += inFile.next() + " ";
            }

            while (studentName.length() < 30) {
                studentName += " ";
            }

            studentAverage = (inFile.nextFloat() + inFile.nextFloat()) / 2;
            System.out.println(studentName + String.format("%10.2f", studentAverage));
            studentAverage = 0;
            studentName = "";
        }
        System.out.println("*********************************************");
    }

    private static void classAverage() {
        float examOneClassAverage, examTwoClassAverage;
        int totalStudentCount;

        examOneClassAverage = 0;
        examTwoClassAverage = 0;
        totalStudentCount = 0;

        // **********************************************
        // #1 Event controlled loop
        // **********************************************
        do {
            while (!inFile.hasNextFloat()) {
                inFile.next();
            }
            examOneClassAverage += inFile.nextFloat();
            examTwoClassAverage += inFile.nextFloat();

            totalStudentCount++;
        } while (inFile.hasNext());
        System.out.printf("%s%.2f\n", "Class Average of exam-1 is: ", examOneClassAverage / totalStudentCount);
        System.out.printf("%s%.2f\n", "Class Average of exam-2 is: ", examTwoClassAverage / totalStudentCount);
    }

    private static void outputTableHeader() {
        System.out.println("***********************************************************");
        System.out.printf("%s%34s%20s\n", "Name", "exam 1", "exam 2");
        System.out.println("***********************************************************");
    }

    private static void outputTableFooter() {
        System.out.println("***********************************************************");
    }
}