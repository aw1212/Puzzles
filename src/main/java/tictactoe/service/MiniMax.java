package tictactoe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Game;
import tictactoe.data.Move;

@Component
public class MiniMax {

    @Autowired
    private GameService gameService;
    @Autowired
    private BoardService boardService;

    public Map<Move, Integer> maximize(Board board, int depth, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = boardService.getEmptySlots(board);
        int bestScore = Integer.MIN_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameService.gameIsOver(board, game) || depth == 0) {
            bestScore = gameService.getScore(board, game);
        } else {
            for (Move move : possibleMoves) {
                cells[move.getRow()][move.getColumn()].setCell(game.getAiPlayerPiece());
                Map.Entry<Move, Integer> entry = minimize(board, depth - 1, game).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestRow = move.getRow();
                    bestCol = move.getColumn();
                }
                cells[move.getRow()][move.getColumn()].setCell("[ ]");
            }
        }
        Move move = new Move(bestRow, bestCol);
        Map<Move, Integer> moveAndScore = new HashMap<>();
        moveAndScore.put(move, bestScore);

        return moveAndScore;
    }

    private Map<Move, Integer> minimize(Board board, int depth, Game game) {
        Cell[][] cells = board.getBoard().clone();
        List<Move> possibleMoves = boardService.getEmptySlots(board);
        int bestScore = Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (gameService.gameIsOver(board, game) || depth == 0) {
            bestScore = gameService.getScore(board, game);
        } else {
            for (Move move : possibleMoves) {
                cells[move.getRow()][move.getColumn()].setCell(game.getOpponentPiece());
                Map.Entry<Move, Integer> entry = maximize(board, depth - 1, game).entrySet().iterator().next();
                currentScore = entry.getValue();
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    bestRow = move.getRow();
                    bestCol = move.getColumn();
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
