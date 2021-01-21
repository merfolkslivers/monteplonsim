package melody.cards;

import base.Board;
import base.Card;
import base.ChooserCommon;
import base.Circle;
import melody.choosers.CierChooser;
import melody.choosers.MelodyChooserCommon;

import java.util.ArrayList;

public class Coral1 extends Card {
    public Coral1(){
        this.grade = 1;
    }

    @Override
    public void onPlace(Circle c, Board b) {
        if (c.isVanguard) {
            b.hand.add(b.deck.draw(1).get(0));
            int soulChargeChoice = ChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.coral1Priority);
            if(soulChargeChoice == -1) {
                throw new RuntimeException("Coral1 failed to choose a card to go to soul.");
            }
            Card card = b.hand.get(soulChargeChoice);
            b.hand.remove(card);
            b.vg.soul.add(card);
        }

        if (c.isVanguard == false) {
            Card card = b.deck.draw(1).get(0);
            b.vg.soul.add(card);
            if(b.vg.unit.getClass() == Coral1.class) {
                card = b.deck.draw(1).get(0);
                b.vg.soul.add(card);
            }
        }
    }

    @Override
    public void act(int i, Board b) {

    }
}
