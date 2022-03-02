package game;

import deck.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandEvaluatorTest {
    private final HandEvaluator he = new HandEvaluator();
    private final ArrayList<Card> cards = new ArrayList<>(5);

    @Test
    void PairEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals("PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals("PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TEN));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals("PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.THREE));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.THREE));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TWO));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TEN));
        assertEquals("PAIR", he.evaluate(cards));
    }

    @Test
    void TwoPairEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.JACK));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.JACK));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.TEN));
        assertEquals("TWO_PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TEN));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.TEN));
        assertEquals("TWO_PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.JACK));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.JACK));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.NINE));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.NINE));
        assertEquals("TWO_PAIR", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.JACK));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.JACK));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.FOUR));
        assertEquals("TWO_PAIR", he.evaluate(cards));
    }

    @Test
    void ThreeOfKindEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.THREE));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TWO));
        assertEquals("THREE_OF_A_KIND", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals("THREE_OF_A_KIND", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.TEN));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals("THREE_OF_A_KIND", he.evaluate(cards));
    }

    @Test
    void StraightEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.FIVE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.SIX));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.THREE));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.TWO));
        assertEquals("STRAIGHT", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.NINE));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.FIVE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.SIX));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.EIGHT));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.SEVEN));
        assertEquals("STRAIGHT", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.KING));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.JACK));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.TEN));
        assertEquals("STRAIGHT", he.evaluate(cards));
    }

    @Test
    void FlushEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Rank.SIX));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND, Card.Rank.TWO));
        assertEquals("FLUSH", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.CLUB, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.CLUB, Card.Rank.SIX));
        cards.add(new Card(Card.Suit.CLUB, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB, Card.Rank.TWO));
        assertEquals("FLUSH", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.SIX));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEART, Card.Rank.TWO));
        assertEquals("FLUSH", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.SIX));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TWO));
        assertEquals("FLUSH", he.evaluate(cards));
    }

    @Test
    void FullHouseEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.TWO));
        assertEquals("FULL_HOUSE", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.FOUR));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.ACE));
        assertEquals("FULL_HOUSE", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART, Card.Rank.TEN));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TEN));
        cards.add(new Card(Card.Suit.CLUB,  Card.Rank.TEN));
        cards.add(new Card(Card.Suit.SPADE, Card.Rank.TWO));
        assertEquals("FULL_HOUSE", he.evaluate(cards));
    }

    @Test
    void FourOfKindEvaluationTest() {
        cards.clear();
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.ACE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.TWO));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.TWO));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.TWO));
        assertEquals("FOUR_OF_A_KIND", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.NINE));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.NINE));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.NINE));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.NINE));
        assertEquals("FOUR_OF_A_KIND", he.evaluate(cards));

        cards.clear();
        cards.add(new Card(Card.Suit.HEART,     Card.Rank.ACE));
        cards.add(new Card(Card.Suit.CLUB,      Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.QUEEN));
        cards.add(new Card(Card.Suit.DIAMOND,   Card.Rank.ACE));
        cards.add(new Card(Card.Suit.SPADE,     Card.Rank.ACE));
        assertEquals("FOUR_OF_A_KIND", he.evaluate(cards));
    }
}
