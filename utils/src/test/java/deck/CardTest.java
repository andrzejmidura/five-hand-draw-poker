package deck;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void cardInitialization() {
        Card card = new Card(Card.Suit.HEART, Card.Rank.ACE);
        assertNotNull(card);
        assertEquals(card.suit, Card.Suit.HEART);
        assertEquals(card.rank, Card.Rank.ACE);
    }
}
