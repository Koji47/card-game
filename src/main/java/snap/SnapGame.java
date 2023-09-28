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
        System.out.println("\u001B[31m" +"Welcome to Snap!!!" + "\u001B[0m");
        System.out.println("\u001B[30m" +"Player 1 press Enter to take your turn"+ "\u001B[0m");
        System.out.println("When you see 2 cards that have matching values type " + "\u001B[31m" + "'snap'" + "\u001B[0m");

        ScheduledFuture<?> scheduledTask = null;
        while (!deckOfCards.isEmpty()) {
            String input = scanner.nextLine();
            Card currentCard = dealCard();
            System.out.println(currentPlayer.getName() + " drew: " + currentCard);

            if (canSnap()) {
                System.out.println("Snap opportunity ~(‾▿‾)~ type 'snap' within 2 seconds to win.");
                scheduledTask = snapExecutor.schedule(() -> {
                    System.out.println(currentPlayer.getName() + " missed the snap. GAME OVER");
                    previousCard = null;
                    System.exit(0);
                }, 2, TimeUnit.SECONDS);
            } else {
                previousCard = currentCard;
                currentPlayer = getOpponent();
            }

            if (input.equalsIgnoreCase("snap") && canSnap()) {
                if (scheduledTask != null) {
                    scheduledTask.cancel(true);
                }
                snapExecutor.shutdown();
                System.out.println("Snap! (∩╹□╹∩) " + currentPlayer.getName() + " wins \\m/ (>.<) \\m/");
                break;
            }

            if (input.equalsIgnoreCase("V>P")) {
                System.out.println("༼つಠ益ಠ༽つ ─=≡ΣO)) hadouken");
            }
        }

        if (deckOfCards.isEmpty()){
            System.out.println(" (╭ರ_•́) you seem to be very unlucky, there were no snap opportunities");
            System.out.println(deckOfCards.size());
        }
        scanner.close();
        snapExecutor.shutdown();

    }

    private Player getOpponent() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    private boolean canSnap() {
        return previousCard != null && previousCard.getValue() == currentCard.getValue();
    }
}