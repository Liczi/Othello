package si.ai.othello.game.utils.io;

/**
 * @author Jakub Licznerski
 *         Created on 11.05.2017.
 */
public class ConsoleDisplay implements Display {
    @Override
    public void updateBoard(Boolean[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("[" + getChar(board[i][j]) + ']' );
            }
            System.out.println();
        }
    }


    private char getChar(Boolean cone) {
        if (cone == null)
            return '_';
        else if (cone) {
            return 'W';
        } else
            return 'B';
    }
}
