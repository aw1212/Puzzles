package tictactoe.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tictactoe.data.Board;
import tictactoe.data.Game;

@Service
public class GameService {

    @Autowired
    private AI ai;
    @Autowired
    private Opponent opponent;
    @Autowired
    private WinChecker winChecker;
    @Autowired
    private TieChecker tieChecker;

    public boolean gameIsOver(Board board) {
        return winChecker.isAWinForX(board)
                || winChecker.isAWinForO(board)
                || tieChecker.isATie(board);
    }

    public int getScore(Board board) {
        int score = 0;
        if (aiIsWinner(board)) {
            score = 100;
        } else if (opponentIsWinner(board)) {
            score = -100;
        } else if (tieChecker.isATie(board)) {
            score = 0;
        }
        return score;
    }

    public void playGame(Board board, Game game) throws IOException {
        if (opponent.isStarter()) {
            playWithOpponentFirst(board, game);
        } else {
            playWithAIFirst(board, game);
        }
        if (opponentIsWinner(board)) {
            System.out.println("YOU WIN!");
        } else if (aiIsWinner(board)) {
            System.out.println("YOU LOSE!");
        } else {
            System.out.println("IT'S A TIE");
        }
    }

    private void playWithOpponentFirst(Board board, Game game) throws IOException {
        do {
            opponent.makeMove(board, game);
            game.setIsWon(opponentIsWinner(board));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
            ai.makeMove(board, game);
            game.setIsWon(aiIsWinner(board));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
        } while (!game.isATie() && !game.isWon());
    }

    private void playWithAIFirst(Board board, Game game) throws IOException {
        do {
            ai.makeMove(board, game);
            game.setIsWon(aiIsWinner(board));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
            opponent.makeMove(board, game);
            game.setIsWon(opponentIsWinner(board));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
        } while (!game.isATie() && !game.isWon());
    }

    private boolean aiIsWinner(Board board) {
        return ai.getPiece().equals("[X]") && winChecker.isAWinForX(board)
                || ai.getPiece().equals("[O]") && winChecker.isAWinForO(board);
    }

    private boolean opponentIsWinner(Board board) {
        return opponent.getPiece().equals("[O]") && winChecker.isAWinForO(board)
                || opponent.getPiece().equals("[X]") && winChecker.isAWinForX(board);
    }
}
