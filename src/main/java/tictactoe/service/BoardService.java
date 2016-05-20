package tictactoe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Move;

@Service
public class BoardService {

    public Board getNewBoard() {
        Board board = new Board();
        Cell[][] cells = new Cell[Board.getColumnSize()][Board.getRowSize()];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                Cell cell = new Cell("[ ]");
                cells[row][column] = cell;
            }
        }
        board.setBoard(cells);
        return board;
    }

    public List<Move> getEmptySlots(Board board){
        List<Move> possibleMoves = new ArrayList<>();
        Cell[][] cells = board.getBoard();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells.length; column++) {
                if(cells[row][column].getCell().equals("[ ]")) {
                    Move move = new Move(row, column);
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }

}
