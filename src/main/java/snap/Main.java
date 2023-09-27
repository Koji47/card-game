package snap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        SnapGame snap = new SnapGame();
//        Snap snapSinglePlayer = new Snap();
//        snapSinglePlayer.playSnap();
        snap.playSnapGame();
    }
}