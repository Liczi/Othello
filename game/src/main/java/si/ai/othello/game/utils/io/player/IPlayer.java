package si.ai.othello.game.utils.io.player;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public interface IPlayer {

    String getName();
    boolean getColor();
    void nextMove();
}
