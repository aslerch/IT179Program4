package ilstu.edu;

import java.util.ArrayList;
import java.util.LinkedList;

/** A class that represents a player in a game */
public class Player {

    /** Fields */
    private LinkedList<Card> handOfCards = new LinkedList<>();
    private String name;

    /** Constructors */
    /**
     * Instantiates a Player object with their name and draws four cards
     * @param name The name of the player
     */
    public Player(String name) {
        this.name = name;
        for (int i = 1; i <= 4; i++)
            drawCard();
    }

    /** Methods */
    /**
     * Draws a card from the top of the deck
     */
    public void drawCard() {
        handOfCards.add(Game.drawCard());
    }

    /**
     * Displays the hand of the player
     */
    public void displayHand() {
        for (int i = 0; i < handOfCards.size(); i++) {
            System.out.println(handOfCards.get(i).toString());
        }
    }


}
