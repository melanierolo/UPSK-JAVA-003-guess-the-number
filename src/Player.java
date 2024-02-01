public abstract class Player {

    protected String name;
    protected int guess;
    protected int[] guesses;
    protected int guessCount;

    public Player(String name) {
        this.name = name;
        this.guesses = new int[100];
        this.guessCount = 0;
    }

    public int makeGuess(){
        return guess;
    };

    public String getName() {
        return name;
    }

    public String getGuesses() {
        StringBuilder history = new StringBuilder();
        for (int i = 0; i < guessCount; i++) {
            history.append(guesses[i]);
            if (i < guessCount - 1) {
                history.append(", ");
            }
        }
        return history.toString();
    }
}
