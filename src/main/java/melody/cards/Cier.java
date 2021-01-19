package melody.cards;

import base.Board;
import base.Card;
import base.Circle;
import melody.choosers.CierChooser;

import java.util.ArrayList;

public class Cier extends base.Card {
    public Cier(){
        this.grade = 1;
    }

    @Override
    public void onPlace(Circle c, Board b) {
        if(c.isVanguard){
            return;
        }
        //System.out.println("Looking through top 7...");
        ArrayList<Card> cards = b.deck.draw(7);
        int choice = CierChooser.choose(cards, b);
        if(choice != -1) {
            Card card = cards.get(choice);
            cards.remove(card);
            b.hand.add(card);
            b.deck.deck.addAll(cards);
            b.deck.shuffle();
        }
    }

    @Override
    public void act(int i, Board b) {

    }
}
