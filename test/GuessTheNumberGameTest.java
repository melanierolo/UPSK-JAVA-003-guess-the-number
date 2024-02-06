import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class GuessTheNumberGameTest {

    private GuessTheNumberGame game;

    @Mock
    private Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        game = new GuessTheNumberGame();

        // Mock a player
        when(player.getName()).thenReturn("Elena");
        game.currentPlayer = player;
        game.otherPlayer = mock(Player.class);
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
        // Arrange
        // Stub the makeGuess() method of the player to return the target number
        when(player.makeGuess()).thenReturn(game.getTargetNumber());

        // Act and Assert
        assertTrue(game.checkGuess(player)); // Expecting the guess to be correct
    }

    @Test
    void testCheckGuessHigh() {
        // Arrange
        int targetNumber = 42; // Choose a specific target number for the test
        game.targetNumber = targetNumber; // Set the target number in the game

        // Stub the makeGuess() method of the player to return a guess higher than the target number
        when(player.makeGuess()).thenReturn(targetNumber + 10);

        // Act and Assert
        assertFalse(game.checkGuess(player)); // Check if the guess is too high
    }

    @Test
    void testCheckGuessLow() {
        // Arrange
        int targetNumber = 42; // Choose a specific target number for the test
        game.targetNumber = targetNumber; // Set the target number in the game

        // Stub the makeGuess() method of the player to return a guess lower than the target number
        when(player.makeGuess()).thenReturn(targetNumber - 10);

        // Act and Assert
        assertFalse(game.checkGuess(player)); // Check if the guess is too low
    }

    // @AfterEach method to clean up after each test
    @AfterEach
    void tearDown() {
        // Restore System.in to its original state
        System.setIn(System.in);
    }
}
