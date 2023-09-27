package snap;

import java.util.Scanner;

public class Snap extends CardGame {
    private Scanner scanner;

    public Snap() {
        scanner = new Scanner(System.in);
    }

    public void playSnap() {
        shuffleDeck();
        System.out.println("Press Enter to take your turn");

        Card previousCard = null;
        Card currentCard;
        boolean snap = false;

        while (!snap) {
            scanner.nextLine();
            currentCard = dealCard();

            if (currentCard == null) {
                System.out.println("There are no more cards in the deck. Game Over (╯°□°）╯︵ ┻━┻");
                break;
            }
            System.out.println("You drew: " + currentCard);

            if (previousCard != null && currentCard.getSymbol().equals(previousCard.getSymbol())) {
                System.out.println("Snap! (∩╹□╹∩) (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ You won! ");
                snap = true;
            }
            previousCard = currentCard;
        }
        scanner.close();
    }

}


