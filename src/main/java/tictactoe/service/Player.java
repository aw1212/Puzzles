package tictactoe.service;

import java.io.IOException;

import tictactoe.data.Board;
import tictactoe.data.Game;

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