import org.junit.jupiter.api.AfterEach; // Import the AfterEach annotation
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GuessTheNumberGameTest {

    private GuessTheNumberGame game;

    @BeforeEach
    void setUp() {
        game = new GuessTheNumberGame();

        // Mock a player and set it as the currentPlayer using reflection
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getName()).thenReturn("Elena");
        setPrivateField(game, "currentPlayer", currentPlayer);
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
    public void testGetPlayerName() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Prepare
        String playerName = "John";

        // Act
        String actualName = invokeGetPlayerNameWithInput(playerName);

        // Assert
        assertEquals(playerName, actualName);
    }

    // Helper method to invoke getPlayerName method with given input
    private String invokeGetPlayerNameWithInput(String playerName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (InputStream mockedInput = new ByteArrayInputStream(playerName.getBytes())) {
            // Redirect System.in to the mockedInput stream
            System.setIn(mockedInput);

            // Obtain the method getPlayerName via reflection
            Method getPlayerNameMethod = GuessTheNumberGame.class.getDeclaredMethod("getPlayerName");
            getPlayerNameMethod.setAccessible(true);

            // Create a new instance of GuessTheNumberGame
            GuessTheNumberGame game = new GuessTheNumberGame();

            // Invoke the getPlayerName method
            return (String) getPlayerNameMethod.invoke(game);
        } catch (IOException e) {
            // Suppress IOException since ByteArrayInputStream's close method rarely throws an exception
            return null;
        } finally {
            // Restore the original System.in
            System.setIn(System.in);
        }
    }

    @Test
    void testCheckGuessWithReflection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        // Access the private targetNumber field using reflection
        Field targetNumberField = GuessTheNumberGame.class.getDeclaredField("targetNumber");
        targetNumberField.setAccessible(true);

        // Set the value of the targetNumber field
        targetNumberField.setInt(game, 42);

        // Create a mock player
        Player player = new Player("Elena") {
            @Override
            public int makeGuess() {
                return 42; // Mock guess is 42
            }
        };

        // Access the private checkGuess method using reflection
        Method checkGuessMethod = GuessTheNumberGame.class.getDeclaredMethod("checkGuess", Player.class);
        checkGuessMethod.setAccessible(true);

        // Call the private method with the mock player
        boolean result = (boolean) checkGuessMethod.invoke(game, player);

        // Assert the result
        assertTrue(result);
    }

    // Helper method to set the value of a private field using reflection
    private void setPrivateField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Handle exception properly in your code
        }
    }

    // @AfterEach method to clean up after each test
    @AfterEach
    void tearDown() {
        // Restore System.in to its original state
        System.setIn(System.in);
    }
}
