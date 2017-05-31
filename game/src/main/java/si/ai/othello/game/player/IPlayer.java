package si.ai.othello.game.player;

import si.ai.othello.game.Board;
import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public interface IPlayer {

    String getName();
    boolean getColor();
    Pointer nextMove(Board board);
}
