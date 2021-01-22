package melody.cards;

import base.Board;
import base.Card;
import base.Circle;

public class Plon extends Card {
    public Plon(){
        this.grade = 3;
    }
    @Override
    public void onPlace(Circle c, Board b) {
        if(c.isVanguard == true) {
            //System.out.println("Plon ridden");
            b.hand.addAll(b.deck.draw(2));
        }
    }

    @Override
    public void act(int i, Board b) {

    }
}
