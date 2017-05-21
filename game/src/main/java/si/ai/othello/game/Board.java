package si.ai.othello.game;


import si.ai.othello.game.utils.Pointer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
//todo this class has  members, which help populating the board

public class Board {
    public static final int BOARD_SIZE = 8;

    private Game game;
    private Boolean[][] board;
    private int currentWhite;
    private int currentBlack;

    public Board(Game game) {
        this.game = game;
        board = new Boolean[BOARD_SIZE][BOARD_SIZE];
        setInitialCones();
    }

    public Boolean getValueAt(Pointer pointer) {
        return board[pointer.getColIndex()][pointer.getRowIndex()];
    }

    /**
     * @return array of possible moves for given player color
     */
    public Pointer[] getAvailableMoves(boolean color) {
        //todo use color !!!
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
                .filter(this::hasAdjacentOpponent)
                .filter(this::isValidMoveNullUnchecked)
                .toArray(Pointer[]::new);
    }

    //we dont check if there is null on current position
    private boolean isValidMoveNullUnchecked(Pointer pointer) {

        // todo check if there will be a capture when placed at this position

        throw new NotImplementedException();
    }

    private boolean hasAdjacentOpponent(Pointer pointer) {
        boolean color = game.getCurrentColor();

        //todo implement body
        throw new NotImplementedException();
    }

    //todo is check needed here ? for already taken position
    public void setConeAt(boolean color, Pointer position) {
        board[position.getColIndex()][position.getRowIndex()] = color;
    }

    //todo test this method
    public Boolean[][] cloneBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public Boolean[][] getBoard() {
        return board;
    }

    public void updateCones() {
        //todo update number of current cones of each player
        throw new NotImplementedException();
    }

    public boolean isEndOfGame() {
        return currentWhite + currentBlack >= (BOARD_SIZE ^ 2) || (getAvailableMoves(game.getCurrentColor()).length <= 0);
    }

    private void setInitialCones() {
        currentWhite = 2;
        currentBlack = 2;

        throw new NotImplementedException();
        //todo implement initial cones positions in the center (2 cones of each color)
    }
}
