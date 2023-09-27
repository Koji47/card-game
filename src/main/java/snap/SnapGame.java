package snap;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class SnapGame extends CardGame {
    private Scanner scanner;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
//    private Timer snapTimer;
//    private ScheduledExecutorService snapExecutor;

    public SnapGame() {
        this.scanner = new Scanner(System.in);
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.currentPlayer = player1;
    }

    public void playSnapGame() {
//        shuffleDeck();
        sortDeckInNumberOrder();
        System.out.println("Player 1 press Enter to take your turn");

        boolean snap = false;
        boolean snapOpportunity = false;
        long snapOpportunityStartTime = 0;

        while (!snap) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("V>P")) {
                System.out.println("༼つಠ益ಠ༽つ ─=≡ΣO)) hadouken");
            } else {
                if (canSnap() && !snapOpportunity()) {
                    snapOpportunity = true;
                    snapOpportunityStartTime = System.currentTimeMillis();
                    System.out.println("Snap opportunity ~(‾▿‾)~ type 'snap' within 2 seconds to win.");
                } else if (snapOpportunity) {
                    long timeDifference = System.currentTimeMillis() - snapOpportunityStartTime;

                    if (input.equalsIgnoreCase("snap") && timeDifference < 2000) {
                        System.out.println("Snap! (∩╹□╹∩) " + currentPlayer.getName() + " wins \\m/ (>.<) \\m/ ");
                        break;
                    } else if (timeDifference > 2000) {
                        System.out.println(currentPlayer.getName() + " missed the snap");
                    }

                } else {
                    Card currentCard = dealCard();
                    System.out.println(currentPlayer.getName() + " drew: " + currentCard);
                    currentPlayer = getOpponent();
                }
            }
        }
        scanner.close();
    }



    private Player getOpponent() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    private boolean canSnap() {
        Card previousCard = getPreviousCard();
        boolean canSnap = previousCard != null && previousCard.getSymbol().equals(getCurrentCard().getSymbol());
        return canSnap;
    }


}


