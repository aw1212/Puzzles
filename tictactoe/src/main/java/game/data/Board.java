package game.data;

public class Board {

    private static final int COLUMN_SIZE = 3;
    private static final int ROW_SIZE = 3;
    private Cell[][] board;

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public static int getRowSize() {
        return ROW_SIZE;
    }

    public static int getColumnSize() {
        return COLUMN_SIZE;
    }

    public String toString() {
        String aString = "";
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                aString += " " + board[row][col].getCell();
            }
            aString += "\r\n";
        }
        return aString;
    }

}