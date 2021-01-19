package melody.cards;

import base.Board;
import base.Card;
import base.Circle;
import melody.choosers.MipuChooser;

import java.util.ArrayList;

public class Mipu extends Card {
    public Mipu(){
        this.grade = 1;
    }
    @Override
    public void onPlace(Circle c, Board b) {
        ArrayList<Card> peekedCards = b.deck.draw(7);
        int[] chosenCards = MipuChooser.choose(peekedCards, b);
        Card chosenToSoul = null;
        Card chosenToHand = null;
        if(chosenCards[0] != -1) {
            chosenToSoul = peekedCards.get(chosenCards[0]);
            b.vg.soul.add(chosenToSoul);

        }
        if(chosenCards[1] != -1) {
            chosenToHand = peekedCards.get(chosenCards[1]);
            b.hand.add(chosenToHand);
        }
        if(chosenToSoul != null) peekedCards.remove(chosenToSoul);
        if(chosenToHand != null) peekedCards.remove(chosenToHand);
        b.deck.botdeck(peekedCards);
        b.deck.shuffle();
    }

    @Override
    public void act(int i, Board b) {

    }
}
