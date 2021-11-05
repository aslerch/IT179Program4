package ilstu.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

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
            drawCard(Game.getDrawPile());
    }

    /** Public Methods */
    /**
     * The player takes a full turn and gives notice if they've won
     * @return true if the player has won, false otherwise
     */
    public boolean takeTurn() {
        displayHand();
        boolean hasPlayerWon = chooseCardPileAndDraw();
        // displays card just drawn
        System.out.println("\nCard Added:\n" + handOfCards.getLast().toString());
        if (hasPlayerWon == true) {
            discardCard();
            return true;
        }
        else { // player has not won and must discard a card
            discardCard();
            return false;
        }

    }

    /**
     * Displays the hand of the player
     */
    public void displayHand() {
        for (int i = 0; i < handOfCards.size(); i++) {
            System.out.println(handOfCards.get(i).toString());
        }
    }

    /**
     * gets the name of the player
     * @return name of the player
     */
    public String getName() {
        return this.name;
    }

    /** Private Methods */
    /**
     * Selects which card to pick and draws it into the player's hand
     * @return true if the player has won, false if the player has not won
     */
    private boolean chooseCardPileAndDraw() {
        if (Game.getDiscardPile().size() == 0) {
            return drawCard(Game.getDrawPile());
        }
        else {
            Card topCardOfDiscardPile = Game.getDiscardPile().peek();
            if (doesNewCardWinGame(topCardOfDiscardPile) == true)
                return drawCard(Game.getDiscardPile());
            else
                return drawCard(Game.getDrawPile());
        }
    }

    /**
     * Adds a card from the top of a stack of cards to the player's hand
     * @param cardPile the stack of cards that should be drawn from
     */
    private boolean drawCard(Stack<Card> cardPile) {
        Card newCard = cardPile.pop();
        handOfCards.add(newCard);
        return doesHandOfCardsWinGame();
    }

    /**
     * checks to see if the hand of cards would win the game
     * @return true if the hand would allow the player to win, false otherwise
     */
    private boolean doesHandOfCardsWinGame() {
        // creates a temporary hand of five cards including the new card
        LinkedList<Card> copyOfHandOfCards = new LinkedList<Card>();
        for (Card card : handOfCards) {
            copyOfHandOfCards.add(new Card(card.getValue(), card.getSuit()));
        }
        // calculates the total of the four highest cards
        int total = calculateTotalOfCardsWithMostCommonSuit(copyOfHandOfCards);
        // checks win conditions with the new card accounted for
        if (total >= 20)
            return true;
        if (hasFourOfOneSuit(copyOfHandOfCards))
            return true;
        else
            return false;
    }

    /**
     * Checks to see if a new card would allow the player to win the game
     * @param newCard new card to be considered in comparison to the player's hand
     * @return true if the player could win by adding the new card to their hand, false otherwise
     */
    private boolean doesNewCardWinGame(Card newCard) {
        // creates a temporary hand of five cards including the new card
        LinkedList<Card> copyOfHandOfCards = new LinkedList<Card>();
        for (Card card : handOfCards) {
            copyOfHandOfCards.add(new Card(card.getValue(), card.getSuit()));
        }
        copyOfHandOfCards.add(new Card(newCard.getValue(), newCard.getSuit()));
        // calculates the total of the four highest cards
        int total = calculateTotalOfCardsWithMostCommonSuit(copyOfHandOfCards);
        // checks win conditions with the new card accounted for
        if (total >= 20)
            return true;
        if (hasFourOfOneSuit(copyOfHandOfCards))
            return true;
        else
            return false;
    }

    /**
     * Calculates the summation of all values for five cards
     * @param cards The five cards whose values should be added
     * @return The total value of all five cards
     */
    private int calculateTotalOfCardsWithMostCommonSuit(LinkedList<Card> cards) {
        int totalOfCardsWithMostCommonSuit = 0;
        String mostCommonSuit = mostCommonSuit(cards);
        for (Card card : cards) {
            if (card.getSuit().equalsIgnoreCase(mostCommonSuit))
                totalOfCardsWithMostCommonSuit += card.getValue();
        }
        return totalOfCardsWithMostCommonSuit;
    }

    /**
     * Finds the lowest value card in a list of cards
     * @param cards The list of cards
     * @return The Card object which has the lowest value
     */
    private Card getLowestValueCard(LinkedList<Card> cards) {
        Card lowestValueCard = cards.get(0);
        for (Card card : cards) {
            if (card.getValue() < lowestValueCard.getValue())
                lowestValueCard = card;
        }
        return lowestValueCard;
    }

    /**
     * determines if there is at least four of one suit present in the list of cards
     * @param cards cards to be analyzed
     * @return true if there is at least four of any one suit, false otherwise
     */
    private boolean hasFourOfOneSuit(LinkedList<Card> cards) {
        int numOfHearts = 0;
        int numOfSpades = 0;
        int numOfClubs = 0;
        int numOfDiamonds = 0;
        for (Card card : cards) {
            if (card.getSuit().equalsIgnoreCase("hearts"))
                numOfHearts++;
            if (card.getSuit().equalsIgnoreCase("spades"))
                numOfSpades++;
            if (card.getSuit().equalsIgnoreCase("clubs"))
                numOfClubs++;
            if (card.getSuit().equalsIgnoreCase("diamonds"))
                numOfDiamonds++;
        }
        if (numOfHearts >= 4 || numOfSpades >= 4 || numOfClubs >= 4 || numOfDiamonds >= 4)
            return true;
        else
            return false;
    }

    /**
     * removes a card from the player's hand and pushes it to the discard pile
     */
    private void discardCard() {
        // finds the most common suit in the player's hand
        String mostCommonSuit = mostCommonSuit(handOfCards);
        // gets a copy of the lowest value card in the hand
        LinkedList<Card> copyOfHandOfCards = new LinkedList<Card>();
        for (Card card : handOfCards) {
            copyOfHandOfCards.add(new Card(card.getValue(), card.getSuit()));
        }
        for (int i = 0; i <= 10; i++) {
            boolean flag = discardCardIfValuesAreEqualAndNotOfSameSuit(i, mostCommonSuit);
            if (flag == true)
                break;
        }
    }

    /**
     * finds the most common suit among a set of cards
     * @param cards the cards to be analyzed
     * @return the card suit that is most common
     */
    private String mostCommonSuit(LinkedList<Card> cards) {
        Card numOfHearts = new Card(0, "hearts");
        Card numOfSpades = new Card(0, "spades");;
        Card numOfClubs = new Card(0, "clubs");;
        Card numOfDiamonds = new Card(0, "diamonds");;
        for (Card card : cards) {
            if (card.getSuit().equalsIgnoreCase("hearts"))
                numOfHearts.setValue(numOfHearts.getValue() + 1);
            if (card.getSuit().equalsIgnoreCase("spades"))
                numOfSpades.setValue(numOfSpades.getValue() + 1);
            if (card.getSuit().equalsIgnoreCase("clubs"))
                numOfClubs.setValue(numOfClubs.getValue() + 1);
            if (card.getSuit().equalsIgnoreCase("diamonds"))
                numOfDiamonds.setValue(numOfDiamonds.getValue() + 1);
        }
        Card [] numOfCardsPerSuit = {numOfHearts, numOfSpades, numOfClubs, numOfDiamonds};
        Card mostCommonSuit = numOfCardsPerSuit[0];
        for (Card suit : numOfCardsPerSuit) {
            if (suit.getValue() > mostCommonSuit.getValue())
                mostCommonSuit = suit;
        }
        return mostCommonSuit.getSuit();
    }

    /**
     * Cycles through the player's hand and discards a card if it is equal to the value parameter and is not of the same suit as the parameter
     * @param value comparative value
     * @param mostCommonSuit The most common suit in the player's hand
     * @return true if a card was discarded, false otherwise
     */
    private boolean discardCardIfValuesAreEqualAndNotOfSameSuit(int value, String mostCommonSuit) {
        for (Card card : handOfCards) {
            if ( ! card.getSuit().equalsIgnoreCase(mostCommonSuit) && card.getValue() == value) {
                Card cardToBeDiscarded = card;
                handOfCards.remove(card);
                Game.getDiscardPile().push(cardToBeDiscarded);
                return true;
            }
        }
        return false;
    }


}
