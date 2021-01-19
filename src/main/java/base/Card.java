package base;

public abstract class Card {
    public int grade;
    public int name;

    public abstract void onPlace(Circle c, Board b);
    public abstract void act(int i, Board b);
}
