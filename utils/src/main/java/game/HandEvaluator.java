package game;

import deck.Card;

import java.util.ArrayList;

public class HandEvaluator {
    protected Hand hand = Hand.HIGH_CARD;
    protected int[] ranks = new int[14];
    protected int sameCards=1, sameCards2=1;
    protected int sameCardsSuit, sameCards2Suit;
    protected boolean isStraight=false, isFlush=true;

    public HandEvaluator() {
        for (int i=0; i<14; i++) {
            this.ranks[i] = 0;
        }
    }

    public String evaluate(ArrayList<Card> cards) {
        String s;

        for (Card c : cards) {
            this.ranks[c.getRank().getValue()] += 1;
        }
        for (int i=1; i<14 ; i++) {
            if (ranks[i]>sameCards) {
                if (sameCards != 1) {
                    sameCards2 = sameCards;
                    sameCards2Suit = sameCardsSuit;
                }
                sameCards = ranks[i];
                sameCardsSuit = i;
            } else if (ranks[i]>sameCards2) {
                sameCards2 = ranks[i];
                sameCards2Suit = i;
            }
        }
        this.checkForStraight();
        this.checkForViolatingFlush(cards);

        if (this.sameCards==2) {
            if (sameCards2!=2)
                this.hand = Hand.PAIR;
            else {
                this.hand = Hand.TWO_PAIR;
            }
        }
        if (this.sameCards==3) {
                this.hand = Hand.THREE_OF_A_KIND;
        }
        if (this.isStraight) {
            hand = Hand.STRAIGHT;
        }
        if (this.isFlush) {
            hand = Hand.FLUSH;
        }
        if (this.sameCards==3 && this.sameCards2==2) {
            hand = Hand.FULL_HOUSE;
        }
        if (this.sameCards==4) {
            hand = Hand.FOUR_OF_A_KIND;
        }
        if (this.isFlush && this.isStraight) {
            hand = Hand.STRAIGHT_FLUSH;
        }
        // TODO: ROYAL FLUSH EVALUATION

        s = this.hand.toString();
        this.reset();
        return s;
    }

    protected void checkForStraight() {
        int start = 0, range = 0;
        for (int i=1; i<14; i++) {
            if (this.ranks[i]==1) {
                if (start==0)
                    start = i;
            } else if (start!=0) {
                if (range<i-start) {
                    range = i-start;
                }
                start = 0;
            }
        }
        if (range==5)
            this.isStraight = true;
        else if (start==9) // it provides check for the Nine to King situation, because then range won't be calculated and start will be 9
            this.isStraight = true;
        else if (this.ranks[1]==1 && start==10) // same as above but for Ten to Ace situation
            this.isStraight = true;
    }

    protected void checkForViolatingFlush(ArrayList<Card> cards) {
        for (int i=0; i<4; i++) {
            if (cards.get(i).getSuit()!=cards.get(i+1).getSuit())
                this.isFlush = false;
        }
    }

    protected void reset() {
        for (int i=0; i<14; i++) {
            this.ranks[i] = 0;
        }
        this.hand = Hand.HIGH_CARD;
        this.sameCards = this.sameCards2 = 1;
        this.isStraight = false;
        this.isFlush = true;
    }
}
