package ilstu.edu;

import java.util.LinkedList;

public class Main {

    public static void main(String [] args) {

        displayStartScreen();

    }

    private static void displayStartScreen() {
        System.out.println("""
                *****************************************
                Welcome to the card game
                Please enter 1 to start""");
    }

    private static void stars() {
        System.out.println("*****************************************");
    }

}
