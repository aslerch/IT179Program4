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

    public String chooseCardPile() {

    }

    /**
     * Checks to see if a new card would allow the player to win the game
     * @param newCard new card to be considered in comparison to the player's hand
     * @return true if the player could win by adding the new card to their hand, false otherwise
     */
    private boolean doesNewCardWinGame(Card newCard) {
        // creates a temporary hand of five cards including the new card
        LinkedList<Card> fiveCards = new LinkedList<>();
        fiveCards.add(newCard);
        for (Card card : handOfCards) {
            fiveCards.add(card);
        }
        // calculates the total of the four highest cards
        int total = calculateTotalOfFourHighestCards(fiveCards);
        // counts how many of each suit is present in the temporary hand of five cards
        int numOfHearts = 0;
        int numOfSpades = 0;
        int numOfClubs = 0;
        int numOfDiamonds = 0;
        for (Card card : fiveCards) {
            if (card.getSuit().equalsIgnoreCase("hearts"))
                numOfHearts++;
            if (card.getSuit().equalsIgnoreCase("spades"))
                numOfSpades++;
            if (card.getSuit().equalsIgnoreCase("clubs"))
                numOfClubs++;
            if (card.getSuit().equalsIgnoreCase("diamonds"))
                numOfDiamonds++;
        }
        // checks win conditions with the new card accounted for
        if (total >= 20)
            return true;
        if (numOfHearts >= 4 || numOfSpades >= 4 || numOfClubs >= 4 || numOfDiamonds >= 4)
            return true;
        else
            return false;
    }

    /**
     * Calculates the summation of all values for five cards
     * @param fiveCards The five cards whose values should be added
     * @return The total value of all five cards
     */
    private int calculateTotalOfFourHighestCards(LinkedList<Card> fiveCards) {
        int totalOfFive = 0;
        for (Card card : fiveCards) {
            totalOfFive += card.getValue();
        }
        int totalOfFourHighestCards = totalOfFive - getLowestValueCard(fiveCards).getValue();
        return totalOfFourHighestCards;
    }

    /**
     * Finds the lowest value card in a list of cards
     * @param fiveCards The list of cards
     * @return The Card object which has the lowest value
     */
    private Card getLowestValueCard(LinkedList<Card> fiveCards) {
        Card lowestValueCard = fiveCards.get(0);
        for (Card card : fiveCards) {
            if (card.getValue() < lowestValueCard.getValue())
                lowestValueCard = card;
        }
        return lowestValueCard;
    }


}
