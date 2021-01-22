package luard.cards;

import base.Board;
import base.Card;
import base.Circle;

public class Owl extends Card {
    public Owl(){
        this.grade = 1;
        this.power = 5000;
        this.name = "Abyssal Owl";
    }
    @Override
    public void onPlace(Circle c, Board b) {

    }

    @Override
    public void act(int i, Board b) {

    }
}
