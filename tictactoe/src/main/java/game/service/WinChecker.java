package game.service;

import org.springframework.stereotype.Component;

import game.data.Board;
import game.data.Cell;

@Component
public class WinChecker {

    public boolean isAWinForX(Board board) {
        return isAWin(board, "[X]");
    }

    public boolean isAWinForO(Board board) {
        return isAWin(board, "[O]");
    }

    private boolean isAWin(Board board, String player) {
        Cell[][] cells = board.getBoard();

        if (cells[0][0].getCell().equals(player)
                && cells[1][0].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[0][1].getCell().equals(player)
                && cells[0][2].getCell().equals(player)) {
            return true;
        }
        if (cells[1][0].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[1][2].getCell().equals(player)) {
            return true;
        }
        if (cells[2][0].getCell().equals(player)
                && cells[2][1].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[1][0].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        if (cells[0][1].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][1].getCell().equals(player)) {
            return true;
        }
        if (cells[0][2].getCell().equals(player)
                && cells[1][2].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][0].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][2].getCell().equals(player)) {
            return true;
        }
        if (cells[0][2].getCell().equals(player)
                && cells[1][1].getCell().equals(player)
                && cells[2][0].getCell().equals(player)) {
            return true;
        }
        return false;
    }

}