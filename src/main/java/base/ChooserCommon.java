package base;

import java.util.List;
import java.util.stream.Collectors;

public class ChooserCommon {
    public static int getFirstMatchingInPriorityList(List<Card> cards, List<Class> priorityList) {
        for(int i = 0; i < priorityList.size(); i++) {
            Class currentPriority = priorityList.get(i);
            for(int j = 0; j < cards.size(); j++) {
                if(cards.get(j).getClass() == priorityList.get(i)) {
                    return j;
                }
            }
        }
        return -1;
    }
}
