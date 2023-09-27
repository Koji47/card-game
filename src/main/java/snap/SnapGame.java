package snap;

import java.util.Scanner;
import java.util.concurrent.*;

public class SnapGame extends CardGame {
    private Scanner scanner;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private ScheduledExecutorService snapExecutor;

    public SnapGame() {
        this.scanner = new Scanner(System.in);
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        this.currentPlayer = player1;
        this.snapExecutor = Executors.newSingleThreadScheduledExecutor();

    }

    public void playSnapGame() {
        shuffleDeck();
//        sortDeckInNumberOrder();
        System.out.println("Player 1 press Enter to take your turn");

        boolean snap = false;
        ScheduledFuture<?> scheduledTask = null;
        while (!snap) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("V>P")) {
                System.out.println("༼つಠ益ಠ༽つ ─=≡ΣO)) hadouken");
            } else if (canSnap()) {
                scheduledTask = snapExecutor.schedule(()->{ // scheduled task after 2 seconds
                    System.out.println(currentPlayer.getName() + " missed the snap");
                }, 2, TimeUnit.SECONDS);
                System.out.println("Snap opportunity ~(‾▿‾)~ type 'snap' within 2 seconds to win.");

            } else if (input.equalsIgnoreCase("snap")) {
                if (scheduledTask != null) {
                    scheduledTask.cancel(true);
                }
                snapExecutor.shutdown(); // cancels the scheduled task
                System.out.println("0Snap! (∩╹□╹∩) " + currentPlayer.getName() + " wins \\m/ (>.<) \\m/ ");
                break;
            } else if (canSnap()){
                snapExecutor.shutdown(); // cancels the scheduled task
                System.out.println(currentPlayer.getName() + " missed the snap");
                break;
            } else {
                Card currentCard = dealCard();
                System.out.println(currentPlayer.getName() + " drew: " + currentCard);
                currentPlayer = getOpponent();
            }
        }
        scanner.close();
        snapExecutor.shutdown();
    }

    private Player getOpponent() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    private boolean canSnap() {
        Card previousCard = getPreviousCard();
        return previousCard != null && previousCard.getSymbol().equals(getCurrentCard().getSymbol());
    }


}