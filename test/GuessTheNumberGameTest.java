import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessTheNumberGameTest {

    @Test
    public void testInitialBounds() {
        GuessTheNumberGame game = new GuessTheNumberGame();
        assertEquals(1, game.lowerBound);
        assertEquals(100, game.upperBound);
    }

    @Test
    public void testTargetNumberInRange() {
        GuessTheNumberGame game = new GuessTheNumberGame();
        assertTrue(game.getTargetNumber() >= game.lowerBound && game.getTargetNumber() <= game.upperBound);
    }

}

