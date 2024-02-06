import java.util.Scanner;

public class HumanPlayer extends Player {
    protected Scanner scanner; // Scanner object to read user input

    // Constructor for HumanPlayer class, taking the player's name as input
    public HumanPlayer(String name) {
        super(name); // Call the constructor of the superclass (Player) with the provided name
        scanner = new Scanner(System.in); // Initialize the Scanner to read input from the console
    }

    // Override the makeGuess method from the Player class
    @Override
    public int makeGuess() {
        // Prompt the player to enter their guess
        System.out.println(getName() + ", enter your guess between 1 and 100:");

        // Read the player's input (guess) from the console
        int guess = scanner.nextInt();

        // Store the guess in the array of guesses maintained by the Player class
        guesses[guessCount++] = guess;

        // Return the guess entered by the player
        return guess;
    }
}
