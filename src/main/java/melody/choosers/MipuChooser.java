package melody.choosers;

import base.Board;
import base.Card;
import base.ChooserCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MipuChooser {

    //First to soul, second to hand
    public static int[] choose(ArrayList<Card> cards, Board b) {
        int[] results = new int[2];
        results[0] = -1;
        results[1] = -1;
        int currentGrade = b.vg.unit.grade;
        List<Class> priorityList;
        List<Card> rideTargets = b.hand.stream().filter(f -> f.grade == b.vg.unit.grade + 1).collect(Collectors.toList());
        if(rideTargets.size() == 0) { // We don't have a ride target for the next grade.
            priorityList = choosePriorityList(b.vg.unit.grade);
            List<Card> ridables = cards.stream().filter(f -> f.grade == b.vg.unit.grade + 1).collect(Collectors.toList());
            if(ridables.size() > 0) {
                results[1] = cards.indexOf(ridables.get(ChooserCommon.getFirstMatchingInPriorityList(ridables, priorityList)));
            } else { //We can't find a ride target. Just thin the deck, I guess.
                    results[1] = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
        } else { //We have a ride target, can we solidify our ride chain?
            if(b.vg.unit.grade == 2) { // If we're at Grade 2 but we have a Grade 3, go with the full priority list
                results[1] = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
            if (b.vg.unit.grade == 1) { // Ditto, find a grade 2?
                results[1] = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.grade2Priority);
            }
            if(results[1] == -1) { // We found no one, just find anything
                results[1] = ChooserCommon.getFirstMatchingInPriorityList(cards, MelodyChooserCommon.fullPriority);
            }
        }
        results[0] = chooseSoulTarget(cards, results[1], b);
        return results;
    }

    public static List<Class> choosePriorityList(int grade) {
        switch(grade) {
            case 1:
                return MelodyChooserCommon.grade2Priority;
            case 2:
                return MelodyChooserCommon.grade3Priority;
            default:
                return MelodyChooserCommon.fullPriority;
        }
    }

    public static int chooseSoulTarget(ArrayList<Card> cards, int handtarget, Board b) {
        ArrayList<Card> soulTargets = (ArrayList<Card>) cards.clone();
        if(handtarget != -1) {
            soulTargets.remove(handtarget);
        }
        int soulTarget = MelodyChooserCommon.getFirstMatchingInPriorityList(soulTargets, MelodyChooserCommon.soulPriority);
        if (soulTarget != -1)
        {
            return cards.indexOf(soulTargets.get(soulTarget));
        }
        return -1;
    }



}
