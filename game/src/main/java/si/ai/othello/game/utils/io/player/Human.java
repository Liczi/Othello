package si.ai.othello.game.utils.io.player;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public void nextMove() {
        throw new NotImplementedException();
    }
}
