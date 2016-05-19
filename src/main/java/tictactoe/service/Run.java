package tictactoe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import tictactoe.data.Board;
import tictactoe.data.Cell;
import tictactoe.data.Game;
import tictactoe.data.Move;

public class Run {

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Board board = BoardService.getNewBoard();

        System.out.println("Welcome to Tic-Tac-Toe. Would you like to be X or O?");
        game.setOpponentPiece(getOpponentPieceChoice());
        game.setAiPlayerPiece(game.getOpponentPiece().equals("[O]") ? "[X]" : "[O]");
        System.out.println("Would you like to go first? Y/N");
        boolean opponentStarts = isOpponentStarter();
        System.out.println("Lastly, what difficulty level? Easy (E) or Impossible to Win (I)?");
        game.setDifficultyLevel(getDepth());
        System.out.println(board);;
        playGame(opponentStarts, board, game);
    }

    private static String getOpponentPieceChoice() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("X") && !input.toUpperCase().equals("O")) {
            System.out.println("Please enter either X or O");
            input = br.readLine();
        }
        return "[" + input.toUpperCase() + "]";
    }

    private static boolean isOpponentStarter() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N")) {
            System.out.println("Please enter either Y or N");
            input = br.readLine();
        }
        if (input.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    private static int getDepth() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (!input.toUpperCase().equals("E")  && !input.toUpperCase().equals("I")) {
            System.out.println("Please enter either E (easy) or I (impossible to win)");
            input = br.readLine();
        }
        if (input.toUpperCase().equals("E")) {
            return 1;
        }
        return 10;
    }

    private static void playGame(boolean opponentStarts, Board board, Game game) throws IOException {
        if (opponentStarts) {
            do {
                getMove(board, game);
                game.setIsWon(WinChecker.isAWinForOpponent(board, game.getOpponentPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                makeMove(board, game);
                game.setIsWon(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
            } while (!game.isATie() && !game.isWon());
        } else {
            do {
                makeMove(board, game);
                game.setIsWon(WinChecker.isAWinForAIPlayer(board, game.getAiPlayerPiece()));
                game.setIsATie(TieChecker.isATie(board));
                if (game.isATie() || game.isWon()) {
                    break;
                }
                getMove(board, game);
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

    private static void getMove(Board board, Game game) throws IOException {
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

    private static void makeMove(Board board, Game game) {
        System.out.println("AI's move:");
        AIPlayer aiPlayer = new AIPlayer();
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