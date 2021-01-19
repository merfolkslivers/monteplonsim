package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    public Stack<Card> deck;
    public void shuffle() {
        Collections.shuffle(deck);
    }
    public ArrayList<Card> draw(int noCards) {
        ArrayList<Card> cards = new ArrayList<Card>();
        if(noCards >= deck.size()) {
            throw new RuntimeException("Ran out of cards in deck somehow, so we decked out.");
        }
        for(int i = 0; i < noCards; i++) {
            cards.add(deck.pop());
        }
        return cards;
    }
    public void botdeck(ArrayList<Card> cards) {
        for(Card card : cards) {
            deck.add(0, card);
        }
    }

    public Deck(Stack<Card> cards) {
        this.deck = cards;
    }
}
