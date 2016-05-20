package tictactoe.data;

import org.springframework.stereotype.Component;

@Component
public class Game {

    private boolean isWon = false;
    private boolean isATie = false;
    private String opponentPiece;
    private String aiPlayerPiece;
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

    public String getOpponentPiece() {
        return opponentPiece;
    }

    public void setOpponentPiece(String opponentPiece) {
        this.opponentPiece = opponentPiece;
    }

    public String getAiPlayerPiece() {
        return aiPlayerPiece;
    }

    public void setAiPlayerPiece(String aiPlayerPiece) {
        this.aiPlayerPiece = aiPlayerPiece;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
