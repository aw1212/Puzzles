package tictactoe.service;

import tictactoe.data.Board;
import tictactoe.data.Cell;

public class TieChecker {

    public static boolean isATie(Board board) {
        Cell[][] cells = board.getBoard();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                if (cells[row][column].getCell().equals("[ ]")) {
                    return false;
                }
            }
        }
        return true;
    }

}