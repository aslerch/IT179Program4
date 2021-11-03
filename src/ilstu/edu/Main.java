package ilstu.edu;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static Scanner keyboard = new Scanner(System.in);
    private static final int START = 1, MIN_NUM_OF_PLAYERS = 2, MAX_NUM_OF_PLAYERS = 5;

    public static void main(String [] args) {

        startScreen();
        LinkedList<String> players = getPlayersFromUser();

    }

    /** private methods */
    /**
     * asks the user to input players information
     * @return a linked list containing the players' names
     */
    private static LinkedList<String> getPlayersFromUser() {
        System.out.println("How many players?");
        int numOfPlayers = askForIntBetween(MIN_NUM_OF_PLAYERS, MAX_NUM_OF_PLAYERS);
        stars();
        LinkedList<String> players = new LinkedList<String>();
        keyboard.nextLine(); // used for input stream formatting purposes
        for (int i = 1; i <= numOfPlayers; i++) {
            players.add(askForStringWithinLength("Please enter the name of player " + i + ": ", 30));
        }
        stars();
        return players;
    }

    /**
     * displays the start screen and asks the user for a starting input
     */
    private static void startScreen() {
        displayStartScreen();
        int selection = askForIntBetween(START, START);
        stars();
    }

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
                System.out.print("Your selection (" + num1 + " - " + num2 + "): ");
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

    /**
     * Asks for a String that is within a certain length
     * @param informationRequestMessage The request message
     * @param lengthUpperBound The upper bound for length
     * @return A String within a length determined by the upper bound
     */
    private static String askForStringWithinLength(String informationRequestMessage, int lengthUpperBound) {
        boolean isWithinCorrectLength = false;
        String output = "";
        while (isWithinCorrectLength == false) {
            System.out.print(informationRequestMessage);
            output = keyboard.nextLine();
            if (output.length() <= lengthUpperBound)
                isWithinCorrectLength = true;
            if (output.length() > lengthUpperBound)
                System.out.println("input not of valid length " + "(" + lengthUpperBound + ")");
        }
        return output;
    }

}
