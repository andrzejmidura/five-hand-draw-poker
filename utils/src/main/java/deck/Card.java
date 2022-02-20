package deck;

public class Card {
    public enum Suit {
        HEART(4),
        DIAMOND(3),
        CLUB(2),
        SPADE(1);

        Suit(int v) {
            this.value = v;
        }
        private int value;
    }
    public enum Rank {
        ACE(14),
        KING(13),
        QUEEN(12),
        JACK(11),
        TEN(10),
        NINE(9),
        EIGHT(8),
        SEVEN(7),
        SIX(6),
        FIVE(5),
        FOUR(4),
        THREE(3),
        TWO(2);

        Rank(int v) {
            this.value = v;
        }
        private int value;
    }

    public Card(Suit s, Rank r) {
        this.suit = s;
        this.rank = r;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (this.suit != ((Card) obj).suit || this.rank != ((Card) obj).rank)
            return false;
        return true;
    }

    protected Suit suit;
    protected Rank rank;
}
