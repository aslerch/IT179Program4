package ilstu.edu;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** A class that represents a card game */
public class Game {

    /** Fields */
    private LinkedList<Card> unshuffledDeck = createDeck();
    private Stack<Card> drawPile;
    private Stack<Card> discardPile;
    private Queue<Card> playerPool;

    /** Constructors */
    public Game() {}
    public Game(LinkedList<String> playerPool) {
        shuffle();
    }

    /** Methods */
    /**
     * Shuffles the deck of cards
     */
    private static void shuffle() {

    }

    /**
     * Creates the deck of cards
     * @return The deck of cards
     */
    private static LinkedList<Card> createDeck() {
        LinkedList<Card> deck = new LinkedList<Card>();
        for (int i = 1; i <= 10; i++) {
            deck.add(new Card(i, "hearts"));
            deck.add(new Card(i, "spades"));
            deck.add(new Card(i, "clubs"));
            deck.add(new Card(i, "diamonds"));
        }
        return deck;
    }

}
