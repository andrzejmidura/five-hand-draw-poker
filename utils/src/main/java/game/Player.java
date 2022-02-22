package game;

import deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public enum Action {
        BET     (1),
        FOLD    (2),
        CHECK   (3),
        CALL    (4),
        RAISE   (5),
        ALLIN   (6);

        Action(int v) {
            this.value = v;
        }
        private int value;
    }

    private int id;
    private int pocket = 200;
    private int stake = 0;
    private Action currentAction;
    private List<Card> cards = new ArrayList<>(5);

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
    public Player(int id) {
        this.id = id;
    }

    public void addCard(Card c) {
        if (this.cards.size()==5) // if it is new deal
            this.cards.clear();
        this.cards.add(c);
    }
}
