package base;

import java.util.ArrayList;

public class TestbedCommon {
    public static double divide(int top, int bottom){
        Double topi = (double) top;
        Double boti = (double) bottom;
        return ((Double) topi / boti);
    }

    public static void addToDeck(Class c, ArrayList<Card> deck, int numCards) {
        for(int i = 0; i < numCards; i++) {
            try {
                deck.add((Card)c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void prepareStartingHand(Board board, ArrayList cards) {
        board.deck.botdeck(cards);
        board.deck.shuffle();
        board.vg.unit = new Starter();
        board.hand.addAll(board.deck.draw(5));
    }
}
