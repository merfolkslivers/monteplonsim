package luard.cards;

import base.Board;
import base.Card;
import base.Circle;

public class DriverLuard extends Card {
    public DriverLuard() {
        this.grade = 3;
        this.power = 13000;
        this.name = "Dragdriver, Luard";
    }
    @Override
    public void onPlace(Circle c, Board b) {

    }

    @Override
    public void act(int i, Board b) {

    }
}
