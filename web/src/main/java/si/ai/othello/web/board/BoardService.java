package si.ai.othello.web.board;

import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public interface BoardService {
    Boolean[][] getBoard();
    Pointer[] getAvailableMoves();
}
