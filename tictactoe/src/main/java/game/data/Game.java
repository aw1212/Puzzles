package game.data;

public class Game {

    private boolean isWon = false;
    private boolean isATie = false;
    private int difficultyLevel;

    public boolean isWon() {
        return isWon;
    }

    public void setIsWon(boolean isWon) {
        this.isWon = isWon;
    }

    public boolean isATie() {
        return isATie;
    }

    public void setIsATie(boolean isATie) {
        this.isATie = isATie;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
