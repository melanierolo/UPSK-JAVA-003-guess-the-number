import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ComputerPlayerTest {
    private GuessTheNumberGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        // Setting up the mocks before each test
        game = mock(GuessTheNumberGame.class);

        // Mocking a player
        player = mock(Player.class);
        when(player.getName()).thenReturn("Elena");
        game.currentPlayer = player;
        game.otherPlayer = mock(Player.class);
    }

    @Test
    void testMakeGuess_TargetNumberInLowerHalf_ShouldGuessCorrectlyAndAdjustLowerBound() {
        // Setting up the mocked values for the test scenario
        when(game.getTargetNumber()).thenReturn(35);
        when(game.getLowerBound()).thenReturn(1); // Set lowerBound
        when(game.getUpperBound()).thenReturn(50); // Set upperBound

        // Creating an instance of ComputerPlayer to test
        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        // Making the guess
        int guess = computerPlayer.makeGuess();

        // Verifying the guess is correct and lowerBound is adjusted
        assertEquals(25, guess);
        verify(game, times(1)).setLowerBound(26);
    }

    @Test
    void testMakeGuess_TargetNumberInUpperHalf_ShouldGuessCorrectlyAndAdjustUpperBound() {
        // Setting up the mocked values for the test scenario
        when(game.getTargetNumber()).thenReturn(70);
        when(game.getLowerBound()).thenReturn(50); // Set lowerBound
        when(game.getUpperBound()).thenReturn(100); // Set upperBound

        // Creating an instance of ComputerPlayer to test
        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        // Making the guess
        int guess = computerPlayer.makeGuess();

        // Verifying the guess is correct and upperBound is adjusted
        assertEquals(75, guess);
        verify(game, times(1)).setUpperBound(74);
    }

    @Test
    void testMakeGuess_CorrectGuess_ShouldReturnCorrectNumber() {
        // Setting up the mocked values for the test scenario
        when(game.getTargetNumber()).thenReturn(50);
        when(game.getLowerBound()).thenReturn(1); // Set lowerBound
        when(game.getUpperBound()).thenReturn(100); // Set upperBound

        // Creating an instance of ComputerPlayer to test
        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        // Making the guess
        int guess = computerPlayer.makeGuess();

        // Verifying the guess is correct
        assertEquals(50, guess);
    }
}
