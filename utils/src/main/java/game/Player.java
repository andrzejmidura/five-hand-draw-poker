package game;

import deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public enum Action {
        NONE    (0),
        BET     (1),
        FOLD    (2),
        CHECK   (3),
        CALL    (4),
        RAISE   (5),
        ALLIN   (6),
        EXIT    (7);

        Action(int v) {
            this.value = v;
        }
        public final int value;
    }

    protected String name;
    protected int pocket;
    protected int stake = 0;
    protected Hand hand;
    protected Action currentAction = Action.NONE;
    protected List<Card> cards = new ArrayList<>(5);

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getPocket() {
        return pocket;
    }

    public void setPocket(int pocket) {
        this.pocket = pocket;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public Action getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(Action currentAction) {
        this.currentAction = currentAction;
    }



    public Player() {}
    public Player(String n) {
        this.name = n;
    }

    public void addCard(Card c) {
        if (this.cards.size()==5) // if it is new deal
            this.cards.clear();
        this.cards.add(c);
    }
}
