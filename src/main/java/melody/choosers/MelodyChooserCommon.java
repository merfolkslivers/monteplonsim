package melody.choosers;

import base.*;
import melody.cards.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MelodyChooserCommon extends ChooserCommon {
    public static ArrayList<Class> grade1Priority = new ArrayList<Class>(Arrays.asList(
            Mipu.class,
            Ourora.class,
            Sedna.class,
            Coral1.class,
            FiveTogether.class,
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
    public static ArrayList<Class> repeatPriority = new ArrayList<Class>(Arrays.asList(
            Plon.class,
            Sonata3.class,
            Caro3.class,
            Fina3.class,
            Canon3.class
    ));
    public static ArrayList<Class> fullPriority = new ArrayList<Class>(Arrays.asList(
            Plon.class,
            Sonata3.class,
            Sonata2.class,
            Caro3.class,
            Fina3.class,
            Fina2.class,
            Canon3.class,
            FiveTogether.class
    ));

    public static ArrayList<Class> soulPriority = new ArrayList<Class>(Arrays.asList(
            Sonata3.class,
            Sonata2.class,
            Caro3.class,
            Canon3.class,
            Fina3.class,
            Fina2.class,
            FiveTogether.class
    ));
    public static ArrayList<Class> discardPriority = new ArrayList<Class>(Arrays.asList(
            Starter.class,
            DrawTrigger.class,
            CritTrigger.class,
            HealTrigger.class,
            Ourora.class,
            Coral1.class,
            FiveTogether.class,
            Fina2.class,
            Fina3.class,
            Canon3.class,
            Caro3.class,
            Sedna.class,
            Sonata2.class,
            Sonata3.class,
            Plon.class,
            Cier.class,
            Mipu.class
    ));

    public static ArrayList<Class> coral1Priority = new ArrayList<Class>(Arrays.asList(
            Sonata3.class,
            Sonata2.class,
            Caro3.class,
            Canon3.class,
            Fina3.class,
            Fina2.class,
            FiveTogether.class,
            DrawTrigger.class,
            CritTrigger.class,
            HealTrigger.class,
            Ourora.class,
            Coral1.class,
            Sedna.class,
            Plon.class,
            Cier.class,
            Mipu.class
    ));
}
