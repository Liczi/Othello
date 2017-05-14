package si.ai.othello.game.utils.io;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Jakub Licznerski
 *         Created on 11.05.2017.
 */
public class WebDisplay implements Display {
    @Override
    public void updateBoard(Boolean[][] board) {
        //todo signal web module to refresh(display current board)
        throw new NotImplementedException();
    }
}
