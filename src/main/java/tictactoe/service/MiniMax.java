package tictactoe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Move;

@Component
public class MiniMax {

    @Autowired
    private GameService gameService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private Opponent opponent;
    @Autowired
    private AI ai;

    public Map<Move, Integer> maximize(Board board, int depth) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = boardService.getEmptySlots(board);
        int bestScore = Integer.MIN_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameService.gameIsOver(board) || depth == 0) {
            bestScore = gameService.getScore(board);
        } else {
            for (Move move : possibleMoves) {
                int row = move.getRow();
                int column = move.getColumn();
                cells[row][column].setCell(ai.getPiece());
                Map.Entry<Move, Integer> entry = minimize(board, depth - 1).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestRow = row;
                    bestCol = column;
                }
                cells[move.getRow()][move.getColumn()].setCell("[ ]");
            }
        }
        Move move = new Move(bestRow, bestCol);
        Map<Move, Integer> moveAndScore = new HashMap<>();
        moveAndScore.put(move, bestScore);

        return moveAndScore;
    }

    private Map<Move, Integer> minimize(Board board, int depth) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = boardService.getEmptySlots(board);
        int bestScore = Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameService.gameIsOver(board) || depth == 0) {
            bestScore = gameService.getScore(board);
        } else {
            for (Move move : possibleMoves) {
                int row = move.getRow();
                int column = move.getColumn();
                cells[row][column].setCell(opponent.getPiece());
                Map.Entry<Move, Integer> entry = maximize(board, depth - 1).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    bestRow = row;
                    bestCol = column;
                }
                cells[move.getRow()][move.getColumn()].setCell("[ ]");
            }
        }
        Move move = new Move(bestRow, bestCol);
        Map<Move, Integer> moveAndScore = new HashMap<>();
        moveAndScore.put(move, bestScore);

        return moveAndScore;
    }

}
