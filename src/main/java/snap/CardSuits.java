package snap;

public enum CardSuits {
    HEART("♥"), SPADE("♣"), DIAMOND ("♦"), CLUB("♠");

    private final String suit;

    CardSuits(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }


}
