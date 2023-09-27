package snap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardGame {
    private List<Card> deckOfCards;
    private Card previousCard;
    private Card currentCard;

    public Card getCurrentCard() {
        return currentCard;
    }

    public CardGame() {
        this.deckOfCards = new ArrayList<>();
        makeDeck();
        this.previousCard = null;
        this.currentCard = null;
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public void makeDeck() {
        for (CardSuits suit : CardSuits.values()) {
            for (CardValues value : CardValues.values()) {
                deckOfCards.add(new Card(suit.getSuit(), value.getValue(), value.getSymbol()));
            }
        }
    }

    public void displayDeck() {
        System.out.println("This is your deck: ");
        for (Card card : deckOfCards) {
            System.out.println(card);
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
    }

    public Card dealCard() {
        Card currentCard = null;
        if (!deckOfCards.isEmpty()) {
            currentCard = deckOfCards.get(0);
            deckOfCards.remove(currentCard);
//            System.out.println(currentCard);
        } else {
            System.out.println("The deck is empty. try again");
        }
        return currentCard;
    }

    public ArrayList<Card> sortDeckInNumberOrder() {
        deckOfCards = (ArrayList<Card>) deckOfCards
                .stream()
                .sorted((c1, c2) -> Integer.valueOf(c1.getValue()) - Integer.valueOf(c2.getValue()))
                .collect(Collectors.toList());
        return (ArrayList<Card>) deckOfCards;
    }

    public ArrayList<Card> sortDeckIntoSuits (){
        deckOfCards = (ArrayList<Card>) deckOfCards
                .stream()
                .sorted((c1, c2) -> Integer.valueOf(c1.getValue()) - Integer.valueOf(c2.getValue()))
                .sorted((c1,c2) -> c1.getSuit().compareTo(c2.getSuit())).collect(Collectors.toList());
        return (ArrayList<Card>) deckOfCards;
    }



}
