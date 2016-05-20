package tictactoe.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tictactoe.data.Board;
import tictactoe.data.Game;

@Service
public class GameService {

    @Autowired
    private MoveService moveService;

    public boolean gameIsOver(Board board, Game game) {
        return WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())
                || WinChecker.isAWinForOpponent(board, game.getOpponentPiece())
                || TieChecker.isATie(board);
    }

    public int getScore(Board board, Game game) {
        int score = 0;
        if (WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())) {
            score = 100;
        } else if (WinChecker.isAWinForOpponent(board, game.getOpponentPiece())) {
            score = -100;
        } else if (TieChecker.isATie(board)) {
            score = 0;
        }
        return score;
    }

    public void playGame(boolean opponentStarts, Board board, Game game) throws IOException {
        if (opponentStarts) {
            do {
                moveService.getMove(board, game);
                game.setIsWon(WinChecker.isAWinForOpponent(board, game.getOpponentPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                moveService.makeMove(board, game);
                game.setIsWon(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
            } while (!game.isATie() && !game.isWon());
        } else {
            do {
                moveService.makeMove(board, game);
                game.setIsWon(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                moveService.getMove(board, game);
                game.setIsWon(WinChecker.isAWinForOpponent(board, game.getOpponentPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
            } while (!game.isATie() && !game.isWon());
        }
        if (WinChecker.isAWinForOpponent(board, game.getOpponentPiece())) {
            System.out.println("YOU WIN!");
        } else if (WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece())) {
            System.out.println("YOU LOSE!");
        } else {
            System.out.println("IT'S A TIE");
        }
    }

}
