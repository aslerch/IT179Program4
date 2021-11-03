package ilstu.edu;

import java.util.*;

import static ilstu.edu.Main.stars;

/** A class that represents a card game */
public class Game {

    /** Fields */
    private static LinkedList<Card> unshuffledDeck = createDeck();
    private static Stack<Card> drawPile = new Stack<>();
    private static Stack<Card> discardPile = new Stack<>();
    private Queue<Player> playerPool = new ArrayDeque<>();

    /** Constructors */
    /**
     * Default constructor
     */
    public Game() {}

    /**
     * Instantiates a new Game object. Shuffles the cards and creates the player objects
     * @param players The players of the game
     */
    public Game(LinkedList<String> players) {
        shuffle();
         for (int i = 0; i < players.size(); i++) {
            this.playerPool.add(new Player(players.get(i)));
        }
    }

    /** Public Methods */
    /**
     * Draws the first card of the draw pile
     * @return A card
     */
    public static Card drawCard() {
        return drawPile.pop();
    }

    /**
     * simulates the game
     * @return player who won
     */
    public Player playGame() {
        Player playerWhoWon = null;
        while (playerWhoWon == null) {
            Player currentPlayer = playerPool.poll();
            System.out.println(currentPlayer.getName() + "'s turn");
            if (discardPile.isEmpty())
                System.out.println("\nDiscard Pile: Empty");
            else
                System.out.println("\nDiscard Pile: " + discardPile.peek());
            System.out.println("\n" + currentPlayer.getName() + "'s current hand of cards: ");
            if (currentPlayer.takeTurn()) // true if the player has won
                playerWhoWon = currentPlayer;
            System.out.println("\n" + currentPlayer.getName() + "'s new hand: ");
            currentPlayer.displayHand();
            playerPool.offer(currentPlayer);
            stars();
        }
        return playerWhoWon;
    }

    /** Private Methods */
    /**
     * Shuffles the deck of cards
     */
    private static void shuffle() {
        Random randomNumberGenerator = new Random();
        while ( ! unshuffledDeck.isEmpty() ) {
            int indexOfCardToAdd = randomNumberGenerator.nextInt(unshuffledDeck.size());
            drawPile.push(unshuffledDeck.get(indexOfCardToAdd));
            unshuffledDeck.remove(indexOfCardToAdd);
        }
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

    /**
     * gets the draw pile
     * @return the draw pile
     */
    public static Stack<Card> getDrawPile() {
        return drawPile;
    }

    /**
     * gets the discard pile
     * @return the discard pile
     */
    public static Stack<Card> getDiscardPile() {
        return discardPile;
    }
}
