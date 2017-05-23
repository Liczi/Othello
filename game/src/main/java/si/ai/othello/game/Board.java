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

        //not-null stream init
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            Boolean[] columns = board[i];
            for (int j = 0, columnsLength = columns.length; j < columnsLength; j++) {
                //we are interested only in opposite color cones' neighbours
                if (columns[j] == !color)
                    streamBuilder.add(new Pointer(i, j));
            }
        }

        //todo test distinct
        return streamBuilder.build()
                .map(ptr -> getNeighbours(ptr, true))
                .flatMap(Stream::distinct)
                .filter(pointer -> hasCaptureMove(pointer, color))
                .toArray(Pointer[]::new);
    }

    //we dont check if there is null on current position nor if has neighbouring opposite color
    private boolean hasCaptureMove(Pointer pointer, boolean color) {

        /* todo performance improvement
        we start from the "most likely position" e.g.:
            - we are at the top left quarter of the board
            - so we want to start checking from down, then right-down, then right, right-up, up,

            - so there are 4 quarters, --, -+, +-, ++
            - when first is - we want to start from down otherwise we start from up
            - when second is - we want to continue clockwise, otherwise counter-clockwise
         */

        return getNeighbours(pointer, false)
                .filter(ptr -> getValueAt(ptr) != color)
                .anyMatch(ptr -> {
                    int colR = ptr.getColIndex() - pointer.getColIndex();
                    int rowR = ptr.getRowIndex() - pointer.getRowIndex();

                    Pointer current = new Pointer(pointer);
                    Boolean currentValue;
                    boolean end = false;
                    do {
                        //move
                        current = current.move(colR, rowR);
                        if (current == null)
                            end = true;
                        else {
                            currentValue = getValueAt(current);
                            if (currentValue == color)
                                return true;
                        }
                    } while (!end);

                    return false;
                });
    }

    private Stream<Pointer> getNeighbours(Pointer pointer, boolean isNull) {
        Stream.Builder<Pointer> builder = Stream.builder();

        for (int i = -1; i < 2; i += 2) {
            builder.add(Pointer.of(pointer.getColIndex() + i, pointer.getRowIndex()));
        }
        for (int i = -1; i < 2; i += 2) {
            builder.add(Pointer.of(pointer.getColIndex(), pointer.getRowIndex() + i));
        }

        return builder.build()
                .filter(ptr -> getValueAt(ptr).equals(null) == isNull);
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
        //todo is this necessary
        throw new NotImplementedException();
    }

    public boolean isEndOfGame() {
        return currentWhite + currentBlack >= (BOARD_SIZE ^ 2) || (getAvailableMoves(game.getCurrentColor()).length <= 0);
    }

    //todo test that method
    private void setInitialCones() {
        currentWhite = 2;
        currentBlack = 2;
        Pointer pointer = new Pointer(3, 3);
        setConeAt(true, pointer);
        setConeAt(false, pointer.move(0, 1));
        setConeAt(true, pointer.move(1, 0));
        setConeAt(false, pointer.move(0, -1));
    }
}
