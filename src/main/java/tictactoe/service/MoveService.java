package tictactoe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Game;
import tictactoe.data.Move;

@Service
public class MoveService {

    @Autowired
    private MiniMax aiPlayer;

    public void getMove(Board board, Game game) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Position as row|number " +
                "(eg the first cell is 00, the one to it's right is 01, the one below it is 10: ");
        try {
            String input = br.readLine();
            Move move = new Move(Integer.parseInt(input.substring(0, 1)), Integer.parseInt(input.substring(1, 2)));
            Cell[][] currentBoard = board.getBoard();
            if (!currentBoard[move.getRow()][move.getColumn()].getCell().equals("[ ]")) {
                System.out.println("Already a piece there. Try again\n");
                getMove(board, game);
            } else {
                currentBoard[move.getRow()][move.getColumn()].setCell(game.getOpponentPiece());
                board.setBoard(currentBoard);
                System.out.println(board);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("That is not a valid location. Try again\n");
            getMove(board, game);
        }
    }

    public void makeMove(Board board, Game game) {
        System.out.println("AI's move:");
        Map<Move, Integer> moveAndScore = aiPlayer.maximize(board, game.getDifficultyLevel(), game);
        Map.Entry<Move, Integer> moveAndScoreEntry = moveAndScore.entrySet().iterator().next();
        Move move = moveAndScoreEntry.getKey();

        Cell[][] currentBoard = board.getBoard();
        if (!currentBoard[move.getRow()][move.getColumn()].getCell().equals("[ ]")) {
            makeMove(board, game);
        } else {
            currentBoard[move.getRow()][move.getColumn()].setCell(game.getAiPlayerPiece());
            board.setBoard(currentBoard);
            System.out.println(board);
        }
    }

}
