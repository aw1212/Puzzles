package tictactoe.service;

import tictactoe.data.Board;
import tictactoe.data.Cell;

public class WinChecker {

    public static boolean isAWinForAIPlayer(Board board, String player) {
        return isAWin(board, player);
    }

    public static boolean isAWinForOpponent(Board board, String opponent) {
        return isAWin(board, opponent);
    }

    private static boolean isAWin(Board board, String player) {
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