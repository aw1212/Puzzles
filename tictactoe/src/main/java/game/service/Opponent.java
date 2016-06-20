package game.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import game.data.Board;
import game.data.Cell;
import game.data.Game;
import game.data.Move;

@Component
public class Opponent extends Player {

    private boolean isStarter;

    public boolean isStarter() {
        return isStarter;
    }

    public void setStarter(boolean starter) {
        isStarter = starter;
    }

    @Override
    public void makeMove(Board board, Game game) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Position as row|number " +
                "(eg the first cell is 00, the one to it's right is 01, the one below it is 10: ");
        try {
            String input = br.readLine();
            Move move = new Move(Integer.parseInt(input.substring(0, 1)), Integer.parseInt(input.substring(1, 2)));
            makeMoveIfEmptySpot(board, move, game);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("That is not a valid location. Try again\n");
            makeMove(board, game);
        }
    }

    private void makeMoveIfEmptySpot(Board board, Move move, Game game) throws IOException {
        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[move.getRow()][move.getColumn()].getCell().equals("[ ]")) {
            System.out.println("Already a piece there. Try again\n");
            makeMove(board, game);
        } else {
            currentBoard[move.getRow()][move.getColumn()].setCell(getPiece());
            board.setBoard(currentBoard);
            System.out.println(board);
        }
    }
}