package melody.cards;

import base.Board;
import base.Card;
import base.Circle;
import melody.choosers.MipuChooser;
import melody.choosers.OuroraChooser;

import java.util.ArrayList;

public class Ourora extends Card {
    public Ourora(){
        this.grade = 1;
    }
    @Override
    public void onPlace(Circle c, Board b) {
        b.hand.add(b.deck.draw(1).get(0));
        int discardChoice = OuroraChooser.choose(b.hand, b);
        if(discardChoice == -1) throw new RuntimeException("Ourora did not choose a card to discard. Update your discard priority list.");
        //System.out.println("Ourora discarding: " + b.hand.get(discardChoice).toString());
        b.drop.add(b.hand.get(discardChoice));
        b.hand.remove(discardChoice);
    }

    @Override
    public void act(int i, Board b) {

    }
}
