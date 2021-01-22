package base;

import java.util.HashMap;

public abstract class Card {
    public int grade = -1;
    public String name;
    public int power = 0;
    public int additionalPower = 0;
    public boolean hoptActivated;
    public boolean isMarker = false;
    public int markerType = 0; //0 - Force, 1 - Accel, 2 - Protect
    public int markerVariant = 0; // 1 or 2

    public void onPlace(Circle c, Board b) {

    }
    public void act(int i, Board b) {

    }

    protected void retire(Circle c, Board b) {
        b.drop.add(this);
        c.unit = null;
        onRetire(c, b);
    }

    public void onRetire(Circle c, Board b) {

    }
}
