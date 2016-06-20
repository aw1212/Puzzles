package game.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.data.Board;
import game.data.Cell;
import game.data.Game;
import game.data.Move;

@Component
public class AI extends Player {

    @Autowired
    private MiniMax miniMax;

    @Override
    public void makeMove(Board board, Game game) throws IOException {
        System.out.println("AI's move:");
        Map<Move, Integer> moveAndScore = miniMax.maximize(board, game.getDifficultyLevel());
        Map.Entry<Move, Integer> coordinatesAndScore = moveAndScore.entrySet().iterator().next();
        int row = coordinatesAndScore.getKey().getRow();
        int column = coordinatesAndScore.getKey().getColumn();

        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[row][column].getCell().equals("[ ]")) {
            makeMove(board, game);
        } else {
            currentBoard[row][column].setCell(getPiece());
            board.setBoard(currentBoard);
            System.out.println(board);
        }
    }
}

