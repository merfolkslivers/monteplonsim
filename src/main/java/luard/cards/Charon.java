package luard.cards;

import base.Board;
import base.Card;
import base.Circle;

public class Charon extends Card {
    public Charon(){
        this.grade = 1;
        this.power = 8000;
        this.name = "Black Sage, Charon";
    }
    @Override
    public void onPlace(Circle c, Board b) {

    }

    @Override
    public void act(int i, Board b) {

    }
}
