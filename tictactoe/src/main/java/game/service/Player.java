package game.service;

import java.io.IOException;

import game.data.Board;
import game.data.Game;

public abstract class Player {

    private String piece;

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public abstract void makeMove(Board board, Game game) throws IOException;

}