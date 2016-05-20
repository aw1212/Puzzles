package tictactoe.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Game;
import tictactoe.data.Move;

@Component
public class AI extends Player {

    @Autowired
    private MiniMax miniMax;

    @Override
    public void makeMove(Board board, Game game) throws IOException {
        System.out.println("AI's move:");
        Map<Move, Integer> moveAndScore = miniMax.maximize(board, game.getDifficultyLevel(), game);
        Map.Entry<Move, Integer> coordinatesAndScore = moveAndScore.entrySet().iterator().next();
        int row = coordinatesAndScore.getKey().getRow();
        int column = coordinatesAndScore.getKey().getColumn();

        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[row][column].getCell().equals("[ ]")) {
            makeMove(board, game);
        } else {
            currentBoard[row][column].setCell(game.getAiPlayerPiece());
            board.setBoard(currentBoard);
            System.out.println(board);
        }
    }
}

