package melody.choosers;

import base.Board;
import base.Card;
import base.ChooserCommon;

import java.util.ArrayList;

public class OuroraChooser {
    public static int choose(ArrayList<Card> Cards, Board b)
    {
        int choice = ChooserCommon.getFirstMatchingInPriorityList(Cards, MelodyChooserCommon.discardPriority);
        if(choice == -1) throw new RuntimeException("Ourora didn't find anything to discard. Hand:" + Cards.toString());
        return choice;
    }
}
