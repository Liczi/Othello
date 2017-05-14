package si.ai.othello.game.player;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public interface Player {

    String getName();
    boolean getColor();
    void nextMove();
}
