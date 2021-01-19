package melody.choosers;

import base.Board;
import base.Card;
import base.ChooserCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CierChooser {

    public static int choose(ArrayList<Card> cards, Board b) {
        int currentGrade = b.vg.unit.grade;
        int pickedCard = -1;
        List<Class> priorityList;

        List<Card> rideTargets = b.hand.stream().filter(f -> f.grade == b.vg.unit.grade + 1).collect(Collectors.toList());

        if(rideTargets.size() == 0) { // We don't have a ride target for the next grade.
            switch(currentGrade) {
                case 1:
                    priorityList = MelodyChooserCommon.grade2Priority;
                    break;
                case 2:
                    priorityList = MelodyChooserCommon.grade3Priority;
                    break;
                default:
                    priorityList = MelodyChooserCommon.fullPriority;
                    break;
            }
            List<Card> ridables = cards.stream().filter(f -> f.grade == b.vg.unit.grade + 1).collect(Collectors.toList());
            if(ridables.size() > 0) {
                pickedCard = cards.indexOf(ridables.get(MelodyChooserCommon.getFirstMatchingInPriorityList(ridables, priorityList)));
            }
            else {
                pickedCard = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
        }
        else { //We have a ride target, can we solidify our ride chain?
            if(b.vg.unit.grade == 2) { // If we're at Grade 2 but we have a Grade 3, go with the full priority list
                pickedCard = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
            if (b.vg.unit.grade == 1) { // Ditto, find a grade 2?
                pickedCard = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.grade2Priority);
            }
            if(pickedCard == -1) { // We found no one, just find anything
                pickedCard = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
        }
        //If we got here, none of the cards were Melody AND in the priority list. Make sure to update
        //the priority list if you add new cards to the deck.
        return pickedCard;
    }

}
