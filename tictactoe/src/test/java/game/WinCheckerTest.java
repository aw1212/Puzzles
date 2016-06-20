package game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import game.data.Board;
import game.data.Cell;
import game.service.BoardService;
import game.service.WinChecker;

@RunWith(MockitoJUnitRunner.class)
public class WinCheckerTest {

    @Spy
    private BoardService boardService;

    @InjectMocks
    private WinChecker winChecker = new WinChecker();

    @Test
    public void givenEmptyBoard_whenCheckingWin_thenNoWinners() {
        Board board = boardService.getNewBoard();

        assertFalse(winChecker.isAWinForX(board));
        assertFalse(winChecker.isAWinForO(board));
    }

    @Test
    public void givenDiagonalX_whenCheckingWin_thenXisWinner() {
        Board board = boardService.getNewBoard();
        Cell[][] cells = board.getBoard();
        cells[0][0].setCell("[X]");
        cells[1][1].setCell("[X]");
        cells[2][2].setCell("[X]");
        board.setBoard(cells);

        assertTrue(winChecker.isAWinForX(board));
        assertFalse(winChecker.isAWinForO(board));
    }

}
