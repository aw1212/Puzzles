package game.service;

import org.springframework.stereotype.Component;

import game.data.Board;
import game.data.Cell;

@Component
public class TieChecker {

    public boolean isATie(Board board) {
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