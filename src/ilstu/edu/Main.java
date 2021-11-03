package ilstu.edu;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String [] args) {

        displayStartScreen();
        int selection = askForIntBetween(1, 1);
        stars();


    }

    /** private methods */
    /**
     * Displays the welcome/start screen
     */
    private static void displayStartScreen() {
        System.out.println("""
                *****************************************
                Welcome to the card game
                Please enter 1 to start""");
    }

    /**
     * Used as a visual delineator between sections/player turns
     */
    private static void stars() {
        System.out.println("*****************************************");
    }

    /**
     * Asks for an int between or equal to lower and upper bound values
     * @param num1 The lower bound
     * @param num2 The upper bound
     * @return A number between or equal to the upper bound and lower bound
     */
    private static int askForIntBetween(int num1, int num2) {
        int selection = -9999;
        boolean isInt = false;
        boolean isBetweenNum1AndNum2 = false;
        while (isInt == false || isBetweenNum1AndNum2 == false) {
            isInt = false;
            isBetweenNum1AndNum2 = false;
            try {
                System.out.print("Your selection: ");
                selection = keyboard.nextInt();
                if (selection >= num1 & selection <= num2)
                    isBetweenNum1AndNum2 = true;
                if (selection < num1 || selection > num2)
                    System.out.println("selection outside of acceptable range");
                isInt = true;
            } catch (InputMismatchException e) {
                keyboard.nextLine();
                System.out.println("Incorrect input: not an integer");
            }
        }
        return selection;
    }

}
