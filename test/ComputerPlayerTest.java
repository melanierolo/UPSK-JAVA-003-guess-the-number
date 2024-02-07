import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ComputerPlayerTest {
    private GuessTheNumberGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        game = mock(GuessTheNumberGame.class);

        // Mock a player
        player = mock(Player.class);
        when(player.getName()).thenReturn("Elena");
        game.currentPlayer = player;
        game.otherPlayer = mock(Player.class);
    }

    @Test
    void testMakeGuess_TargetNumberInLowerHalf() {
        when(game.getTargetNumber()).thenReturn(35);
        when(game.getLowerBound()).thenReturn(1); // Set lowerBound
        when(game.getUpperBound()).thenReturn(50); // Set upperBound

        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        int guess = computerPlayer.makeGuess();

        assertEquals(25, guess);
        // Verify lowerBound change
        verify(game, times(1)).setLowerBound(26);

    }

    @Test
    void testMakeGuess_TargetNumberInUpperHalf() {
        when(game.getTargetNumber()).thenReturn(70);
        when(game.getLowerBound()).thenReturn(50); // Set lowerBound
        when(game.getUpperBound()).thenReturn(100); // Set upperBound

        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        int guess = computerPlayer.makeGuess();

        assertEquals(75, guess);
        // Verify upperBound change
        verify(game, times(1)).setUpperBound(74);
    }

    @Test
    void testMakeGuess_CorrectGuess() {
        when(game.getTargetNumber()).thenReturn(50);
        when(game.getLowerBound()).thenReturn(1); // Set lowerBound
        when(game.getUpperBound()).thenReturn(100); // Set upperBound

        ComputerPlayer computerPlayer = new ComputerPlayer("TestComputer", game);

        int guess = computerPlayer.makeGuess();

        assertEquals(50, guess);
    }
}
