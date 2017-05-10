package si.ai.othello.game.utils;


import si.ai.othello.game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Stream;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
//todo this class has static members, which help populating the board

public class BoardUtil {

    private static Game game;

    public BoardUtil(Game game) {
        BoardUtil.game = game;
    }

    public static Boolean getValueAt(Pointer pointer) {
        return game.getBoard()[pointer.getColIndex()][pointer.getRowIndex()];
    }

    /**
     * @return array of possible moves for given player color
     */
    public static Pointer[] getAvailableMoves() {
        Boolean[][] board = game.getBoard();
        Stream.Builder<Pointer> streamBuilder = Stream.builder();

        //null checking before filters applied
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            Boolean[] columns = board[i];
            for (int j = 0, columnsLength = columns.length; j < columnsLength; j++) {
                if (columns[j] == null)
                    streamBuilder.add(new Pointer(i, j));
            }
        }

        return streamBuilder.build()
                .filter(BoardUtil::hasAdjacentOpponent)
                .filter(BoardUtil::isValidMoveNullUnchecked)
                .toArray(Pointer[]::new);
    }

    private static boolean isValidMoveNullUnchecked(Pointer pointer) {

        // todo check if there will be a capture when placed at this position

        throw new NotImplementedException();
    }

    private static boolean hasAdjacentOpponent(Pointer pointer) {
        boolean color = game.getCurrentColor();

        //todo implement body
        throw new NotImplementedException();
    }

    //todo is check needed here ? for already taken position
    public static void setConeAt(boolean color, Pointer position) {
        game.getBoard()[position.getColIndex()][position.getRowIndex()] = color;
    }


}
