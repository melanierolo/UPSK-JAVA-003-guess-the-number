import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuessTheNumberGameTest {

    private GuessTheNumberGame game;

    @BeforeEach
    void setUp() {
        game = new GuessTheNumberGame();

        // Mock a player
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getName()).thenReturn("Elena");
        game.currentPlayer = currentPlayer; // Assign currentPlayer directly
    }

    @Test
    public void testInitialBounds() {
        assertEquals(1, game.lowerBound);
        assertEquals(100, game.upperBound);
    }

    @Test
    public void testTargetNumberInRange() {
        assertTrue(game.getTargetNumber() >= game.lowerBound && game.getTargetNumber() <= game.upperBound);
    }

    @Test
    public void testGetPlayerName() {
        // Prepare
        String playerName = "John";
        // Mock user input
        provideMockedUserInput(playerName);

        // Act
        String actualName = game.getPlayerName();

        // Assert
        assertEquals(playerName, actualName);
    }

    // Helper method to mock user input
    private void provideMockedUserInput(String playerName) {
        InputStream mockedInput = new ByteArrayInputStream(playerName.getBytes());
        System.setIn(mockedInput);
    }

    @Test
    void testCheckGuess() {
        // Create a mock player
        Player player = new Player("Elena") {
            @Override
            public int makeGuess() {
                return game.getTargetNumber(); // Mock guess is the target number
            }
        };
        assertTrue(game.checkGuess(player));
    }

    // @AfterEach method to clean up after each test
    @AfterEach
    void tearDown() {
        // Restore System.in to its original state
        System.setIn(System.in);
    }
}
