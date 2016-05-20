package tictactoe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tictactoe.data.Board;
import tictactoe.data.Game;

@SpringBootApplication
public class TicTacToe implements CommandLineRunner {

    @Autowired
    private GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(TicTacToe.class, args);
    }

    public void run(String[] args) throws IOException {
        Game game = new Game();
        Board board = BoardService.getNewBoard();
        System.out.println("Welcome to Tic-Tac-Toe. Would you like to be X or O?");
        game.setOpponentPiece(getOpponentPieceChoice());
        game.setAiPlayerPiece(game.getOpponentPiece().equals("[O]") ? "[X]" : "[O]");
        System.out.println("Would you like to go first? Y/N");
        boolean opponentStarts = isOpponentStarter();
        System.out.println("Lastly, what difficulty level? Easy (E) or Impossible to Win (I)?");
        game.setDifficultyLevel(getDepth());
        System.out.println(board);
        gameService.playGame(opponentStarts, board, game);
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
        return input.toUpperCase().equals("Y");
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

}