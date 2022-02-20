package deck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>(52);

    public Deck() {
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                this.cards.add(new Card(s, r));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void sort() {
        this.cards.clear();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                this.cards.add(new Card(s, r));
            }
        }
    }

    public Card getFromTop() {
        Card c = cards.get(cards.size()-1);
        cards.remove(cards.size()-1);
        return c;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (this.cards.size() != ((Deck) obj).cards.size())
            return false;

        for (int i=0; i<this.cards.size()-1; i++) {
            if (!this.cards.get(i).equals(((Deck) obj).cards.get(i)))
                return false;
        }
        return true;
    }
}
