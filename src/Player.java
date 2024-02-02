import java.util.Arrays;

public abstract class Player {

    // Attributes
    private String name;          // Name of the player
    protected int[] guesses;      // Array to store guesses
    protected int guessCount;     // Counter to track number of guesses

    // Constructor
    public Player(String name) {
        this.name = name;
        this.guesses = new int[100];  // Initialize the guesses array with a size of 100
        this.guessCount = 0;          // Initialize guess count to 0
    }

    // Abstract method to be implemented by subclasses
    public abstract int makeGuess();

    // Getter method for the player's name
    public String getName() {
        return name;
    }

    // Getter method for the player's guesses
    // Returns a copy of the guesses array containing only the actual guesses made
    public int[] getGuesses() {
        return Arrays.copyOf(guesses, guessCount);
    }
}
