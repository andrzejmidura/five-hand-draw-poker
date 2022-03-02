package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game g = new TwoPlayerGame();
    private final Player p1 = new Player("p1");
    private final Player p2 = new Player("p2");

    @Test
    void gameProperInitialization() {
        g = new TwoPlayerGame();
        assertEquals(2, g.maxPlayers);
        g = new ThreePlayerGame();
        assertEquals(3, g.maxPlayers);
        g = new FourPlayerGame();
        assertEquals(4, g.maxPlayers);
        assertFalse(g.isRunning());
    }

    @Test
    void addingSamePlayerMultipleTimes() {
        Game g = new TwoPlayerGame();
        g.addPlayer(p1);
        g.addPlayer(p1);
        assertEquals(1, g.players.size());
    }

    @Test
    void gameIsRunningTest() {
        g = new TwoPlayerGame();
        g.addPlayer(p1);
        g.addPlayer(p2);
        assertTrue(g.isRunning());
    }

    @Test
    void getRespondTest() {
        assertArrayEquals(  ("1;p2;NONE;"+Game.ENTRY_PRICE+";p1;"+Game.ENTRY_PRICE+";;0;"+g.wins).toCharArray(),
                            g.getRespond(p1, -1).toCharArray());
    }
}
