package melody;

import base.Board;
import base.Card;
import base.ChooserCommon;
import melody.cards.*;
import melody.choosers.MelodyChooserCommon;

import java.util.ArrayList;
import java.util.Arrays;

public class testbed {

    static int numDraw = 4;
    static int numHeal = 4;
    static int numCrit = 8;
    static int numCier = 4;
    static int numMipu = 4;
    static int numOuro = 0;
    static int numFive = 0;
    static int numSedn = 4;
    static int numSon2 = 4;
    static int numFin2 = 6;
    static int numPlon = 4;
    static int numSon3 = 4;
    static int numCar3 = 0;
    static int numFin3 = 0;
    static int numCan3 = 3;

    public static void main(String[] args) {
        int count1st = 0;
        int count2nd = 0;
        int count3rd = 0;
        int count4th = 0;
        int count5th = 0;
        int cardsInHand = 0;
        int numSims = 1000000;
        for(int i = 0; i < numSims; i++) {
            boolean canDrive = i % 2 == 0;
            Board b = new Board();
            int result1 = simulateFirstRide(b);
            if(result1 != 1) {
                count1st++;
                continue;
            }
            simulateTurn(b, canDrive);
            int result2 = simulateSecondRide(b);
            if(result2 != 1){
                count2nd++;
                continue;
            }
            simulateTurn(b, true);
            int result3 = simulateSecondRide(b); //Third Ride
            if(result3 != 1){
                count3rd++;
                continue;
            }
            simulateTurn(b, true);
            int result4 = simulateSecondRide(b); // Fourth Ride
            if(b.vg.unit.getClass() != Plon.class) {
                count4th++;
                continue;
            }
            count5th++;
            cardsInHand += b.hand.size();
            //System.out.println(b.vg.unit);
            System.out.println(b.vg.soul);
        }

        System.out.println(divide(count1st, numSims));
        System.out.println(divide(count2nd, numSims));
        System.out.println(divide(count3rd, numSims));
        System.out.println(divide(count4th, numSims));
        System.out.println(divide((count1st + count2nd + count3rd + count4th), numSims));
        System.out.println(divide(count5th, numSims));
        System.out.println(cardsInHand / (count5th));
    }

    public static double divide(int top, int bottom){
        Double topi = (double) top;
        Double boti = (double) bottom;
        return ((Double) topi / boti);
    }

    public static int simulateFirstRide(Board board) {
        board.deck.botdeck(prepDeck());
        board.deck.shuffle();
        board.vg.unit = new Starter();
        board.hand.addAll(board.deck.draw(5));
        ArrayList<Card> newHand = new ArrayList<Card>();
        if(MelodyChooserCommon.getFirstMatchingInPriorityList(board.hand, MelodyChooserCommon.grade1Priority) == -1) {
            //Mulligan until we get a Grade 1.
            board.deck.botdeck(board.hand);
            board.hand = new ArrayList<Card>();
            board.hand.addAll(board.deck.draw(5));
        } else if(MelodyChooserCommon.getFirstMatchingInPriorityList(board.hand, MelodyChooserCommon.grade2Priority) == -1) {
            //Mulligan until we get a Grade 2.
            for(Card c : board.hand) {
                if(c.grade != 1 || c.grade == 0) {
                    board.deck.botdeck(new ArrayList<>(Arrays.asList(c)));
                } else {
                    newHand.add(c);
                }
            }
            board.hand = newHand;
            board.hand.addAll(board.deck.draw(5-board.hand.size()));
        } else if(MelodyChooserCommon.getFirstMatchingInPriorityList(board.hand, MelodyChooserCommon.grade3Priority) == -1) {
            //Mulligan until we get a Grade 3.
            for(Card c : board.hand) {
                if(c.grade == 0) {
                    board.deck.botdeck(new ArrayList<>(Arrays.asList(c)));
                } else {
                    newHand.add(c);
                }
            }
            board.hand = newHand;
            board.hand.addAll(board.deck.draw(5-board.hand.size()));
        }

        board.deck.shuffle();
        board.hand.add(board.deck.draw(1).get(0)); //Turn 1 draw a card.
        int rideTarget = getRideTarget(board);
        if(rideTarget == -1) {
            return -1;
        }
        board.ride(board.hand.get(rideTarget));
        return 1;
    }

    public static int simulateSecondRide(Board board) {
        board.hand.add(board.deck.draw(1).get(0)); //Turn 2 draw a card.
        int rideTarget = getRideTarget(board);
        if(rideTarget == -1) {
            return -1;
        }
        board.ride(board.hand.get(rideTarget));
        return 1;
    }

    public static int simulateTurn(Board board, boolean canDriveCheck) {
        boolean cardPlayedThisLoop = false;
        do {
            cardPlayedThisLoop = false;
            int idx = MelodyChooserCommon.getFirstMatchingInPriorityList(board.hand, MelodyChooserCommon.grade1Priority);
            Card c = null;
            if(idx != -1){
                c = board.hand.get(idx);
            }
            else {
                break;
            }
            if(c.getClass() == Mipu.class) {
                if(board.damageZone.size() >= 1 && board.vg.unit.grade >= 2) {
                    board.drop.add(board.damageZone.get(0));
                    board.damageZone.remove(0);
                    board.call(c, board.rightFront);
                    cardPlayedThisLoop = true;
                } else if(board.vg.soul.size() >= 1) {
                    board.drop.add(board.vg.soul.get(0));
                    board.vg.soul.remove(0);
                    board.call(c, board.rightFront);
                    cardPlayedThisLoop = true;
                }
            } else if(c.getClass() == Cier.class) {
                if (board.vg.soul.size() >= 1) {
                    board.drop.add(board.vg.soul.get(0));
                    board.vg.soul.remove(0);
                    board.call(c, board.rightFront);
                    cardPlayedThisLoop = true;
                }
            } else if(c.getClass() == Sedna.class) {
                board.call(c, board.rightFront);
                cardPlayedThisLoop = true;
            } else if(c.getClass() == Ourora.class) {
                board.call(c, board.rightFront);
                cardPlayedThisLoop = true;
            }
        } while(cardPlayedThisLoop == true);
        for(int drive = 0; drive < (board.vg.unit.grade == 3 ? 2 : 1); drive++) {
            if(canDriveCheck) {
                Card driveCheck = board.deck.draw(1).get(0);
                board.hand.add(driveCheck);
                if(driveCheck.getClass() == DrawTrigger.class) board.hand.add(board.deck.draw(1).get(0));
            }
        }
        for(int drive = 0; drive < (board.vg.unit.grade == 3 ? 2 : 1); drive++) {
            Card damageCheck = board.deck.draw(1).get(0);
            board.damageZone.add(damageCheck);
            if(damageCheck.getClass() == DrawTrigger.class) board.hand.add(board.deck.draw(1).get(0));
        }
        return 1;
    }

    public static int getRideTarget(Board b) {
        switch(b.vg.unit.grade) {
            case 0:
                return MelodyChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.grade1Priority);
            case 1:
                return MelodyChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.grade2Priority);
            case 2:
                return MelodyChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.grade3Priority);
            case 3:
                return MelodyChooserCommon.getFirstMatchingInPriorityList(b.hand, MelodyChooserCommon.repeatPriority);
        }
        return -1;
    }

    public static ArrayList<Card> prepDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        addToDeck(DrawTrigger.class, deck, numDraw);
        addToDeck(HealTrigger.class, deck, numHeal);
        addToDeck(CritTrigger.class, deck, numCrit);
        addToDeck(Cier.class, deck, numCier);
        addToDeck(Mipu.class, deck, numMipu);
        addToDeck(Sonata2.class, deck, numSon2);
        addToDeck(Fina2.class, deck, numFin2);
        addToDeck(Plon.class, deck, numPlon);
        addToDeck(Sonata3.class, deck, numSon3);
        addToDeck(Caro3.class, deck, numCar3);
        addToDeck(Fina3.class, deck, numFin3);
        addToDeck(Canon3.class, deck, numCan3);
        addToDeck(Ourora.class, deck, numOuro);
        addToDeck(FiveTogether.class, deck, numFive);
        addToDeck(Sedna.class, deck, numSedn);
        if(deck.size() != 49) throw new RuntimeException("Deck size incorrect: " + deck.size());
        return deck;
    }

    public static void addToDeck(Class c, ArrayList<Card> deck, int numCards) {
        for(int i = 0; i < numCards; i++) {
            try {
                deck.add((Card)c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
