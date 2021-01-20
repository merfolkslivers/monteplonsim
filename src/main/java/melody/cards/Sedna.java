package melody.cards;

import base.Board;
import base.Card;
import base.Circle;
import melody.choosers.CierChooser;
import melody.choosers.SednaChooser;

import java.util.ArrayList;

public class Sedna extends Card {
    public Sedna(){
        this.grade = 1;
    }

    @Override
    public void onPlace(Circle c, Board b) {
        //System.out.println("Looking through top 7...");
        ArrayList<Card> cards = b.deck.draw(5);
        int[] choice = SednaChooser.choose(cards, b);
        if(choice[0] != -1) {
            Card card = cards.get(choice[0]);
            Card discard = b.hand.get(choice[1]);
            cards.remove(card);
            b.hand.remove(discard);
            b.hand.add(card);
            b.drop.add(discard);
            b.deck.deck.addAll(cards);
            b.deck.shuffle();

        }
    }

    @Override
    public void act(int i, Board b) {

    }
}
