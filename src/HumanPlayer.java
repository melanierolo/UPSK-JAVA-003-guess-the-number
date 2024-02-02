import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(String name) {
        super(name);
        scanner = new Scanner(System.in);
    }

    @Override
    public int makeGuess() {
        System.out.println(getName() + ", enter your guess between 1 and 100:");
        int guess = scanner.nextInt();
        guesses[guessCount++] = guess;
        return guess;
    }
}
