package si.ai.othello.game.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Jakub Licznerski
 *         Created on 30.05.2017.
 *         Auxilary class for Board (provides static versions of some Board methods)
 */
public class BoardUtils {

    private BoardUtils() {

    }


    //this method changes given board
    public static Boolean[][] moveAt(Boolean[][] board, Pointer at, boolean color) {
        setValueAt(board, at, color);

        getNeighbours(board, at, false)
                .filter(ptr -> getValueAt(board, ptr) != color)
                .forEach(ptr -> {
                    int colR = ptr.getColIndex() - at.getColIndex();
                    int rowR = ptr.getRowIndex() - at.getRowIndex();

                    Pointer current = new Pointer(at);
                    Boolean currentValue;
                    boolean end = false;
                    do {
                        //move
                        current = current.move(colR, rowR);
                        if (current == null)
                            end = true;
                        else {
                            currentValue = getValueAt(board, current);
                            if (currentValue == null)
                                end = true;
                            else if (currentValue == Boolean.valueOf(color)) {
                                colR *= -1;
                                rowR *= -1;

                                do {
                                    setValueAt(board, current, color);
                                    current = current.move(colR, rowR);
                                } while (!current.equals(at));
                                end = true;
                            }
                        }
                    } while (!end);
                });

        return board;
    }

    public static Stream<Pointer> getNeighbours(Boolean[][] board, Pointer of, boolean isNull) {
        Stream.Builder<Pointer> builder = Stream.builder();

        for (int i = -1; i < 2; i++) {
            builder.add(Pointer.of(of.getColIndex() + i, of.getRowIndex() - 1));
            builder.add(Pointer.of(of.getColIndex() + i, of.getRowIndex() + 1));
        }

        builder.add(Pointer.of(of.getColIndex() - 1, of.getRowIndex()));
        builder.add(Pointer.of(of.getColIndex() + 1, of.getRowIndex()));


        return builder.build()
                .filter(Objects::nonNull)
                .filter(ptr -> (getValueAt(board, ptr) == null) == isNull);
    }

    public static void setValueAt(Boolean[][] board, Pointer at, boolean color) {
        board[at.getColIndex()][at.getRowIndex()] = color;
    }

    public static Boolean getValueAt(Boolean[][] board, Pointer at) {
        return board[at.getColIndex()][at.getRowIndex()];
    }

    public static Stream<Pointer> getAvailableMoves(Boolean[][] board, boolean color) {
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
                .map(ptr -> getNeighbours(board, ptr, true))
                .flatMap(Stream::distinct)
                .distinct()
                .filter(pointer -> hasCaptureMove(board, pointer, color));
    }

    //todo test without this filter
    public static boolean hasCaptureMove(Boolean[][] board, Pointer at, boolean color) {
        //todo performance improvement (in class Board)
        return getNeighbours(board, at, false)
                .filter(ptr -> getValueAt(board, ptr) != color)
                .anyMatch(ptr -> {
                    int colR = ptr.getColIndex() - at.getColIndex();
                    int rowR = ptr.getRowIndex() - at.getRowIndex();

                    Pointer current = new Pointer(at);
                    Boolean currentValue;
                    boolean end = false;
                    do {
                        //move
                        current = current.move(colR, rowR);
                        if (current == null)
                            end = true;
                        else {
                            currentValue = getValueAt(board, current);
                            if (currentValue == null)
                                end = true;
                            else if (currentValue == Boolean.valueOf(color))
                                return true;
                        }
                    } while (!end);

                    return false;
                });
    }

    public static boolean isNoEmptyConeLeft(Boolean[][] board) {
        for (Boolean[] booleans : board) {
            for (Boolean aBoolean : booleans) {
                if (aBoolean == null)
                    return false;
            }
        }
        return true;
    }

    public static Boolean[][] copyBoard(Boolean[][] board) {
        Boolean[][] newBoard = new Boolean[board.length][board.length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                newBoard[i][j] = board[i][j];

            }
        }
        return newBoard;
    }
}
