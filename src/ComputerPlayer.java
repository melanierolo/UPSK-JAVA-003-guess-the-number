import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random;
    private GuessTheNumberGame game;

    public ComputerPlayer(String name, GuessTheNumberGame game) {
        super(name);
        random = new Random();
        this.game = game;
    }

    @Override
    public int makeGuess() {
        int guess = divideAndConquer(game.lowerBound, game.upperBound);
        System.out.println(getName() + " guessed: " + guess);
        guesses[guessCount++] = guess;
        return guess;
    }

    private int divideAndConquer(int lower, int upper) {
        int mid = (lower + upper) / 2;
        if (mid == game.getTargetNumber()) {
            System.out.println("Correct number! ✅");
        } else if (mid < game.getTargetNumber()) {
            game.lowerBound = mid - 1;
        } else if (mid > game.getTargetNumber()) {
            game.upperBound = mid + 1;
        }

        return mid;
    }
}
