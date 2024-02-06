import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;

    @BeforeEach
    void setUp() {
        // Create a HumanPlayer instance with a mocked Scanner
        humanPlayer = new HumanPlayer("TestPlayer");
        humanPlayer.scanner = mock(Scanner.class);
    }

    @Test
    void testMakeGuess() {
        // Prepare simulated input
        provideMockedUserInput("42\n");

        // Set up the mocked Scanner to return simulated input
        when(humanPlayer.scanner.nextInt()).thenReturn(42);

        // Call the makeGuess method
        int guess = humanPlayer.makeGuess();

        // Verify that the guess is correct
        assertEquals(42, guess);
    }

    // Helper method to mock user input
    private void provideMockedUserInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
