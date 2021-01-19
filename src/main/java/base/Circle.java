package base;

import java.util.ArrayList;

public class Circle {
    public boolean isVanguard = false;
    public int column; //left is 0
    public int row; //front is 0
    public ArrayList<Card> soul = new ArrayList<Card>();
    public Card unit;
    public boolean isTriggerZone = false;

    public Circle(int column, int row, boolean isVanguard) {
        this.column = column;
        this.row = row;
        this.isVanguard = isVanguard;
    }

    public Circle(int column, int row, boolean isVanguard, boolean isTriggerZone) {
        this.column = column;
        this.row = row;
        this.isVanguard = isVanguard;
        this.isTriggerZone = isTriggerZone;
    }
}
