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

    public boolean gameIsOver(Board board, Game game) {
        return winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())
                || winChecker.isAWinForOpponent(board, game.getOpponentPiece())
                || tieChecker.isATie(board);
    }

    public int getScore(Board board, Game game) {
        int score = 0;
        if (winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())) {
            score = 100;
        } else if (winChecker.isAWinForOpponent(board, game.getOpponentPiece())) {
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
        if (winChecker.isAWinForOpponent(board, game.getOpponentPiece())) {
            System.out.println("YOU WIN!");
        } else if (winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())) {
            System.out.println("YOU LOSE!");
        } else {
            System.out.println("IT'S A TIE");
        }
    }

    private void playWithOpponentFirst(Board board, Game game) throws IOException {
        do {
            opponent.makeMove(board, game);
            game.setIsWon(winChecker.isAWinForOpponent(board, game.getOpponentPiece()));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
            ai.makeMove(board, game);
            game.setIsWon(winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
        } while (!game.isATie() && !game.isWon());
    }

    private void playWithAIFirst(Board board, Game game) throws IOException {
        do {
            ai.makeMove(board, game);
            game.setIsWon(winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
            opponent.makeMove(board, game);
            game.setIsWon(winChecker.isAWinForOpponent(board, game.getOpponentPiece()));
            game.setIsATie(tieChecker.isATie(board));
            if (game.isATie() || game.isWon()) {
                break;
            }
        } while (!game.isATie() && !game.isWon());
    }

}
