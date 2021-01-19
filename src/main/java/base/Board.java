package base;

import java.util.ArrayList;
import java.util.Stack;

public class Board {
    public Circle leftFront = new Circle(0, 0, false);
    public Circle vg = new Circle(1, 0, true);
    public Circle rightFront = new Circle(2,0, false);
    public Circle leftRear = new Circle(0,1, false);
    public Circle centerrear = new Circle(1, 1, false);
    public Circle rightRear = new Circle(2, 1, false);
    public Circle triggerZone = new Circle(-1, 0, false, false);
    public Deck deck = new Deck(new Stack<Card>());
    public ArrayList<Card> damageZone = new ArrayList<Card>();
    public ArrayList<Card> hand = new ArrayList<Card>();
    public ArrayList<Card> drop = new ArrayList<Card>();

    public void ride(Card card){
        if(card.grade != this.vg.unit.grade +1) {
            throw new RuntimeException("Something tried to ride a card at the wrong grade: " + card.toString() + " on top of " + this.vg.unit.toString());
        }
        if(vg.unit.grade == 1) {
            this.hand.add(this.deck.draw(1).get(0)); //starter vanguard draw effect
        }
        vg.soul.add(vg.unit);
        vg.unit = card;
        this.hand.remove(card);
        vg.unit.onPlace(vg, this);
    }

    public void call(Card card, Circle circle) {
        if(card.grade > this.vg.unit.grade) {
            throw new RuntimeException("Something tried to call a card at the wrong grade: " + card.toString() + " on top of " + this.vg.unit.toString());
        }
        if(circle.unit != null){ //Move unit being called over to drop, if one is there.
            drop.add(circle.unit);
        }
        circle.unit = card;
        this.hand.remove(card);
        circle.unit.onPlace(circle, this);
    }
}
