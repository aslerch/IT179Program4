package ilstu.edu;

/** A class that represents a playing card */
public class Card {

    /** Fields */
    private int value;
    private String suit;

    /** Constructors */
    /**
     * Default Constructor
     */
    public Card() {}

    /**
     * Instantiates a Card object with all fields specified
     * @param value The value of the card
     * @param suit The suit type of the card
     */
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    /** Methods */
    /**
     * Gets the value of the card
     * @return Value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the card
     * @param value Desired value of the card
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the suit of the card
     * @return Suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Sets the suit of the card
     * @param suit Desired suit of the card
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
