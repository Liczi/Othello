package si.ai.othello.web.board;

import si.ai.othello.game.utils.Pointer;

import static si.ai.othello.game.Game.BLACK;
import static si.ai.othello.game.Game.WHITE;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class MockBoardService implements BoardService {
    @Override
    public Boolean[][] getBoard() {
        Boolean [][] board = new Boolean[8][8];

        board[3][3] = BLACK;
        board[3][4] = WHITE;
        board[4][3] = WHITE;
        board[4][4] = BLACK;

        return board;
    }

    @Override
    public Pointer[] getAvailableMoves() {
        Pointer[] moves = new Pointer[4];

        moves[0] = new Pointer(0,0);
        moves[1] = new Pointer(7,7);
        moves[2] = new Pointer(0,7);
        moves[3] = new Pointer(7,0);

        return moves;
    }
}
