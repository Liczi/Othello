package si.ai.othello.game.player;

import si.ai.othello.game.Board;
import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 11.05.2017.
 */
public class Human implements IPlayer {

    private String name;
    private boolean color;

    public Human(String name, boolean color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getColor() {
        return color;
    }

    @Override
    public Pointer nextMove(Board board) {
        throw new UnsupportedOperationException();
    }
}
