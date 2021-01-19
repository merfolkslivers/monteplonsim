package melody.choosers;

import base.Card;
import base.ChooserCommon;
import melody.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MelodyChooserCommon extends ChooserCommon {
    public static ArrayList<Class> grade1Priority = new ArrayList<Class>(Arrays.asList(
            Mipu.class,
            Cier.class
    ));
    public static ArrayList<Class> grade2Priority = new ArrayList<Class>(Arrays.asList(
            Sonata2.class,
            Fina2.class));
    public static ArrayList<Class> grade3Priority = new ArrayList<Class>(Arrays.asList(
            Plon.class,
            Sonata3.class,
            Caro3.class,
            Fina3.class,
            Canon3.class
            ));
    public static ArrayList<Class> fullPriority = new ArrayList<Class>(Arrays.asList(
            Sonata3.class,
            Sonata2.class,
            Plon.class,
            Caro3.class,
            Fina3.class,
            Fina2.class,
            Canon3.class
    ));
}
