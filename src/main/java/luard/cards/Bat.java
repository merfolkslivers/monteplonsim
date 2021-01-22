package luard.cards;

import base.Board;
import base.Card;
import base.Circle;

public class Bat extends Card {
    public Bat(){
        this.grade = 1;
        this.power = 7000;
        this.name = "Apocalypse Bat";
    }
    @Override
    public void onPlace(Circle c, Board b) {

    }

    @Override
    public void act(int i, Board b) {

    }
}
