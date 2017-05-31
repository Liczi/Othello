package si.ai.othello.game;


import si.ai.othello.game.utils.Pointer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static si.ai.othello.game.Game.BLACK;
import static si.ai.othello.game.Game.WHITE;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
//todo this class has  members, which help populating the board

public class Board {
    public static final int BOARD_SIZE = 8;

    private Boolean[][] board;
    private int currentWhite;
    private int currentBlack;

    public Board() {
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
        Stream.Builder<Pointer> streamBuilder = Stream.builder();

        //not-null stream init
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            Boolean[] columns = board[i];
            for (int j = 0, columnsLength = columns.length; j < columnsLength; j++) {
                //we are interested only in opposite color cones' neighbours
                if (columns[j] == Boolean.valueOf(!color))
                    streamBuilder.add(new Pointer(i, j));
            }
        }

        //todo test distinct
        return streamBuilder.build()
                .map(ptr -> getNeighbours(ptr, true))
                .flatMap(Stream::distinct)
                .distinct()
                .filter(pointer -> hasCaptureMove(pointer, color))
                .toArray(Pointer[]::new);
    }


    //todo test without this filter
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
                            if (currentValue == null)
                                end = true;
                            else if (currentValue == Boolean.valueOf(color))
                                return true;
                        }
                    } while (!end);

                    return false;
                });
    }

    /**
     * Places the cone in given position and performs all possible captures
     *
     * @param pointer pointer to given cone position
     * @param color   color of placed cone
     */
    public void moveAt(Pointer pointer, boolean color) {
        setConeAt(pointer, color);

        getNeighbours(pointer, false)
                .filter(ptr -> getValueAt(ptr) != color)
                .forEach(ptr -> {
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
                            if (currentValue == null)
                                end = true;
                            else if (currentValue == Boolean.valueOf(color)) {
                                colR *= -1;
                                rowR *= -1;

                                do {
                                    setConeAt(current, color);
                                    current = current.move(colR, rowR);
                                } while (!current.equals(pointer));
                                end = true;
                            }
                        }
                    } while (!end);
                });
    }

    private Stream<Pointer> getNeighbours(Pointer pointer, boolean isNull) {
        Stream.Builder<Pointer> builder = Stream.builder();

        for (int i = -1; i < 2; i++) {
            builder.add(Pointer.of(pointer.getColIndex() + i, pointer.getRowIndex() - 1));
            builder.add(Pointer.of(pointer.getColIndex() + i, pointer.getRowIndex() + 1));
        }

        builder.add(Pointer.of(pointer.getColIndex() - 1, pointer.getRowIndex()));
        builder.add(Pointer.of(pointer.getColIndex() + 1, pointer.getRowIndex()));


        return builder.build()
                .filter(Objects::nonNull)
                .filter(ptr -> (getValueAt(ptr) == null) == isNull);
    }

    void setConeAt(Pointer position, boolean color) {
        board[position.getColIndex()][position.getRowIndex()] = color;
    }

    //todo test this method
    public Boolean[][] cloneBoard() {
        Boolean[][] newBoard = new Boolean[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                newBoard[i][j] = board[i][j];

            }
        }
        return newBoard;
    }

    public Boolean[][] getBoard() {
        return board;
    }

    //todo is this necessary
    public void updateCones() {
        currentBlack = currentWhite = 0;

        for (Boolean[] booleans : board) {
            for (Boolean aBoolean : booleans) {
                if (aBoolean != null) {
                    if (aBoolean)
                        currentWhite++;
                    else
                        currentBlack++;
                }
            }
        }
    }

    //todo test
    public boolean isEndOfGame(boolean currentColor) {
        return currentWhite + currentBlack >= (Math.pow(BOARD_SIZE, 2)) || (getAvailableMoves(currentColor).length <= 0);
    }

    private void setInitialCones() {
        currentWhite = 2;
        currentBlack = 2;
        Pointer pointer = new Pointer(3, 3);
        setConeAt(pointer, WHITE);
        setConeAt(pointer.move(0, 1), BLACK);
        setConeAt(pointer.move(1, 0), WHITE);
        setConeAt(pointer.move(0, -1), BLACK);
    }

    public int getCurrentWhite() {
        return currentWhite;
    }

    public int getCurrentBlack() {
        return currentBlack;
    }
}
