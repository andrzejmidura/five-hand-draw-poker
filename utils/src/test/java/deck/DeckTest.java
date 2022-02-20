package deck;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void deckProperInitialization() {
        Deck deck = new Deck();
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.TWO));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.THREE));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.FOUR));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.FIVE));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.SIX));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.SEVEN));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.EIGHT));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.NINE));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.TEN));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.JACK));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.QUEEN));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.KING));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.SPADE, Card.Rank.ACE));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.CLUB, Card.Rank.TWO));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.CLUB, Card.Rank.THREE));
        assertEquals(deck.getFromTop(), new Card(Card.Suit.CLUB, Card.Rank.FOUR));
    }

    @Test
    void deckShuffle() {
        Deck deckToTest = new Deck();
        Deck deckToCompare = new Deck();
        deckToTest.shuffle();
        assertNotEquals(deckToTest, deckToCompare);
    }

    @Test
    void deckSorting() {
        Deck deckToTest = new Deck();
        Deck deckToCompare = new Deck();
        deckToTest.shuffle();
        deckToTest.getFromTop();
        deckToTest.getFromTop();
        deckToTest.sort();
        assertEquals(deckToTest, deckToCompare);
    }
}
