package si.ai.othello.game;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
interface Player {

    String getName();
    boolean getColor();
    void nextMove();
}
