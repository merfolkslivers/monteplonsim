package luard;

import base.*;
import luard.cards.*;

import java.util.ArrayList;

public class testbed {
    static int numDraw = 8;
    static int numHeal = 4;
    static int numCrit = 4;
    static int numAOwl = 2;
    static int numApoB = 2;
    static int numChar = 2;
    static int numBran = 3;
    static int numKnie = 4;
    static int numPint = 2;
    static int numLlua = 2;
    static int numMach = 2;
    static int numLiaf = 4;
    static int numMorf = 3;
    static int numDriv = 3;
    static int numHart = 4;

    public static void main(String[] args) {
        prepBoard();
    }

    public static void prepBoard() {
        Board board = new Board();
        TestbedCommon.prepareStartingHand(board, prepDeck());
    }

    public static ArrayList<Card> prepDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        TestbedCommon.addToDeck(DrawTrigger.class, deck, numDraw);
        TestbedCommon.addToDeck(HealTrigger.class, deck, numHeal);
        TestbedCommon.addToDeck(CritTrigger.class, deck, numCrit);
        TestbedCommon.addToDeck(Owl.class, deck, numAOwl);
        TestbedCommon.addToDeck(Bat.class, deck, numApoB);
        TestbedCommon.addToDeck(Charon.class, deck, numChar);
        TestbedCommon.addToDeck(Branwen.class, deck, numBran);
        TestbedCommon.addToDeck(Knies.class, deck, numKnie);
        TestbedCommon.addToDeck(Painter.class, deck, numPint);
        TestbedCommon.addToDeck(Lluails.class, deck, numLlua);
        TestbedCommon.addToDeck(Macha.class, deck, numMach);
        TestbedCommon.addToDeck(Liafail.class, deck, numLiaf);
        TestbedCommon.addToDeck(Morfessa.class, deck, numMorf);
        TestbedCommon.addToDeck(DriverLuard.class, deck, numDriv);
        TestbedCommon.addToDeck(HeartLuard.class, deck, numHart);
        if(deck.size() != 49) throw new RuntimeException("Deck size not 49:"  + deck.size());
        return deck;
    }

}
