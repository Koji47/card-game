package snap;

public class Card {
    private final String suit;
    private final String value;
    private final String symbol;

    public Card(String suit, int value, String symbol) {
        this.suit = suit;
        this.value = String.valueOf(value);
        this.symbol = symbol;
    }



    public String getSuit() {
        return suit;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        String stringColour = null;
        if (suit.equals(CardSuits.SPADE.getSuit()) || suit.equals(CardSuits.CLUB.getSuit())){
            stringColour = "\u001B[30m";
        }
        if (suit.equals(CardSuits.HEART.getSuit()) || suit.equals(CardSuits.DIAMOND.getSuit())){
            stringColour = "\u001B[31m";
        }

        return stringColour + suit + " " + symbol + "\u001B[0m";
    }
}


