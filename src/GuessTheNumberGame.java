import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GuessTheNumberGame {
    Random random;
    protected int targetNumber;
    protected Player currentPlayer;
    protected Player otherPlayer;

    protected int lowerBound = 1;
    protected int upperBound = 100;

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

        String playerName = getPlayerName();

        // Create instances of HumanPlayer and ComputerPlayer with the provided names
        HumanPlayer humanPlayer = new HumanPlayer(playerName);
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer", this);

        // Assign players to currentPlayer and otherPlayer
        currentPlayer = humanPlayer;
        otherPlayer = computerPlayer;

        while (true) {

            if (checkGuess(currentPlayer)) {
                displayWinner();
                break;
            }

            // Switch players for the next turn
            Player temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        }
    }

    public String getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        return scanner.nextLine();
    }

    /**
     * Verifies the guess made by the current player.
     * @param - object representing the current player making the guess.
     * Also,the parameter (Player player) indicates that the method expects an object of type "Player"
     * @return true if the guess is correct, false otherwise.
     */
     public boolean checkGuess(Player player) {
        int guess = player.makeGuess();
        boolean result = false;

        if (guess == targetNumber) {
            System.out.println(currentPlayer.getName() + " guessed the correct number!");
            result = true;
        } else if (guess < targetNumber) {
            System.out.println(currentPlayer.getName() + "'s guess is too low.↘️");
        } else {
            System.out.println(currentPlayer.getName() + "'s guess is too high.↗️");
        }
        System.out.println("----------------------------------------------");
        return result;
    }


    public void displayWinner() {
        System.out.println(currentPlayer.getName() + " 🎉 is the winner! 😁");
        String guessesAsString = Arrays.stream(currentPlayer.getGuesses())
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("Guesses: " + guessesAsString);
    }
}
