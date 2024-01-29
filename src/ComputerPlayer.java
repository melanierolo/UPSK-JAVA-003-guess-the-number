package src;

import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer(String name) {
        super(name);
        random = new Random();
    }

    @Override
    public int makeGuess() {
        int guess = random.nextInt(100) + 1;
        System.out.println(name + " guessed: " + guess);
        guesses[guessCount++] = guess;
        return guess;
    }

    public int getGuessCount() {
        return guessCount;
    }
}
