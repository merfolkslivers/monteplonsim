package melody.choosers;

import base.Board;
import base.Card;
import base.ChooserCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SednaChooser {

    public static int[] choose(ArrayList<Card> cards, Board b) {
        int[] pickedCard = new int[2]; //First is the card to get, second is the card to discard from board.hand

        pickedCard[0] = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.repeatPriority);
        if(pickedCard[0] == -1) {
            pickedCard[1] = -1; //Give up and don't discard, just say you whiffed.
        } else {
            pickedCard[1] = ChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.discardPriority);
            if(pickedCard[1] == -1){
                System.out.println("Sedna didn't pick a card to discard.");
                System.out.println("Vanguard: " + b.vg.unit.toString());
                System.out.println("Soul: " + b.vg.soul.toString());
                System.out.println("Hand: " + b.hand.toString());
                System.out.println("Drop: " + b.drop.toString());
                throw new RuntimeException("Sedna failed to pick a card to discard for her effect.");
            }
        }

        return pickedCard;
    }

}
