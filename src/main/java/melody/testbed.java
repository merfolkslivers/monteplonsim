package melody;

import base.*;
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
    static int numSedn = 0;
    static int numCor1 = 0;
    static int numSon2 = 4;
    static int numFin2 = 4;
    static int numPlon = 4;
    static int numSon3 = 4;
    static int numCar3 = 3;
    static int numFin3 = 4;
    static int numCan3 = 2;

    public static void main(String[] args) {
        int count1st = 0;
        int count2nd = 0;
        int count3rd = 0;
        int count4th = 0;
        int count5th = 0;
        int countPlon1st = 0;
        int count6th = 0;
        double noSonatas = 0;
        double noCaros = 0;
        int cardsInHand = 0;
        int numSims = 1000000;
        for(int i = 0; i < numSims; i++) {
            System.out.println("Beginning game " + i);
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
            if(b.vg.unit.getClass() == Plon.class) {
                countPlon1st++;
            }
            simulateTurn(b, true);
            count6th++;
            int result4 = simulateSecondRide(b); // Fourth Ride
            if(b.vg.unit.getClass() != Plon.class) {
                count4th++;
                continue;
            }
            simulateTurn(b, true);
            count5th++;
            cardsInHand += b.hand.size();
            for(Card c : b.vg.soul) {
                if(c.getClass() == Sonata2.class || c.getClass() == Sonata3.class || c.getClass() == Caro3.class)
                    noSonatas++;
                if(c.getClass() == Caro3.class) noCaros++;
            }
            System.out.println("End of game " + i);
            //System.out.println(b.vg.unit);
            //System.out.println(b.vg.soul);
        }

        System.out.println("Missed ride 1: " + TestbedCommon.divide(count1st, numSims));
        System.out.println("Missed ride 2: " + TestbedCommon.divide(count2nd, numSims));
        System.out.println("Missed ride 3: " + TestbedCommon.divide(count3rd, numSims));
        System.out.println("Plon not 4th ride: " + TestbedCommon.divide(count4th, numSims));
        System.out.println("Percent failed: " + TestbedCommon.divide((count1st + count2nd + count3rd + count4th), numSims));
        System.out.println("Percent success: " + TestbedCommon.divide(count5th, numSims));
        System.out.println("Average cards in hand: " + (cardsInHand / (count5th)));
        System.out.println("Average number of Sonatas in soul: " + (noSonatas / count5th));
        System.out.println("Average number of Caros in soul: " + (noCaros / count5th));
        System.out.println("Percentage of times 1st ride was Plon: " + TestbedCommon.divide(countPlon1st, count6th));
    }

    public static int simulateFirstRide(Board board) {
        TestbedCommon.prepareStartingHand(board, prepDeck());
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
        //RETRO: Make a priority list of cards to play, and place their call-or-ride functionality elsewhere
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
                    int choice = MelodyChooserCommon.getFirstMatchingInPriorityList(board.vg.soul, MelodyChooserCommon.discardPriority);
                    if(choice == -1) {
                        throw new RuntimeException("We somehow failed to choose something to soulblast for Miep's cost.");
                    }
                    Card soulblast = board.vg.soul.get(choice);
                    board.drop.add(soulblast);
                    board.vg.soul.remove(soulblast);
                    board.call(c, board.rightFront);
                    cardPlayedThisLoop = true;
                }
            } else if(c.getClass() == Cier.class) {
                int grade3Check = MelodyChooserCommon.getFirstMatchingInPriorityList(board.hand, MelodyChooserCommon.grade3Priority);
                if (board.vg.soul.size() >= 1 && grade3Check != -1) {
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
        //RETRO: Somewhere else?
        for(int drive = 0; drive < (board.vg.unit.grade == 3 ? 2 : 1); drive++) {
            //System.out.println(board.vg.unit.toString() + " is drive checking " + (board.vg.unit.grade == 3 ? 2 : 1) + " times");
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
        TestbedCommon.addToDeck(DrawTrigger.class, deck, numDraw);
        TestbedCommon.addToDeck(HealTrigger.class, deck, numHeal);
        TestbedCommon.addToDeck(CritTrigger.class, deck, numCrit);
        TestbedCommon.addToDeck(Cier.class, deck, numCier);
        TestbedCommon.addToDeck(Mipu.class, deck, numMipu);
        TestbedCommon.addToDeck(Sonata2.class, deck, numSon2);
        TestbedCommon.addToDeck(Fina2.class, deck, numFin2);
        TestbedCommon.addToDeck(Plon.class, deck, numPlon);
        TestbedCommon.addToDeck(Sonata3.class, deck, numSon3);
        TestbedCommon.addToDeck(Caro3.class, deck, numCar3);
        TestbedCommon.addToDeck(Fina3.class, deck, numFin3);
        TestbedCommon.addToDeck(Canon3.class, deck, numCan3);
        TestbedCommon.addToDeck(Ourora.class, deck, numOuro);
        TestbedCommon.addToDeck(FiveTogether.class, deck, numFive);
        TestbedCommon.addToDeck(Sedna.class, deck, numSedn);
        TestbedCommon.addToDeck(Coral1.class, deck, numCor1);
        if(deck.size() != 49) throw new RuntimeException("Deck size incorrect: " + deck.size());
        return deck;
    }


}
