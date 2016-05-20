package tictactoe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Game;
import tictactoe.service.BoardService;
import tictactoe.service.WinChecker;

@RunWith(MockitoJUnitRunner.class)
public class WinCheckerTest {

    @Spy
    private BoardService boardService;

    @InjectMocks
    private WinChecker winChecker = new WinChecker();

    @Test
    public void givenEmptyBoard_whenPlayingGame_thenNoWinners() {
        Board board = boardService.getNewBoard();
        Game game = new Game();
        game.setAiPlayerPiece("[X]");
        game.setOpponentPiece("[O]");

        assertFalse(winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
        assertFalse(winChecker.isAWinForOpponent(board, game.getOpponentPiece()));
    }

    @Test
    public void givenDiagonalX_whenAIisX_thenAIisWinner() {
        Board board = boardService.getNewBoard();
        Cell[][] cells = board.getBoard();
        cells[0][0].setCell("[X]");
        cells[1][1].setCell("[X]");
        cells[2][2].setCell("[X]");
        board.setBoard(cells);
        Game game = new Game();
        game.setAiPlayerPiece("[X]");
        game.setOpponentPiece("[O]");

        assertTrue(winChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
        assertFalse(winChecker.isAWinForOpponent(board, game.getOpponentPiece()));
    }

}
