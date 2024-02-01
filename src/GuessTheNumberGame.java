import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private Random random;
    private int targetNumber;
    private Player currentPlayer;
    private Player otherPlayer;

    public int lowerBound = 1;
    public int upperBound = 100;

    public GuessTheNumberGame() {
        random = new Random();
        targetNumber = random.nextInt(100) + 1;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void play() {
        System.out.println("**************************************");
        System.out.println("🎮 Welcome to Guess The Number Game! 🎮");
        System.out.println("**************************************");

        // Prompt the user to enter their name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        String playerName = scanner.nextLine();

        // Create instances of HumanPlayer and ComputerPlayer with the provided names
        HumanPlayer humanPlayer = new HumanPlayer(playerName);
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer", this);

        // Assign players to currentPlayer and otherPlayer
        currentPlayer = humanPlayer;
        otherPlayer = computerPlayer;

        while (true) {
            int guess = currentPlayer.makeGuess();
            checkGuess(guess);

            if (guess == targetNumber) {
                displayWinner();
                break;
            }

            // Switch players for the next turn
            Player temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        }
    }

    private void checkGuess(int guess) {
        if (guess < targetNumber) {
            System.out.println(currentPlayer.getName() + "'s guess is too low.↘️");
        } else if (guess > targetNumber) {
            System.out.println(currentPlayer.getName() + "'s guess is too high.↗️");
        } else {
            System.out.println(currentPlayer.getName() + " guessed the correct number!");
        }
        System.out.println("----------------------------------------------");
    }

    private void displayWinner() {
        System.out.println(currentPlayer.getName() + " 🎉 is the winner! 😁");
        System.out.println("Guesses: " + currentPlayer.getGuesses());
    }
}
