package si.ai.othello.game.utils;

import si.ai.othello.game.Game;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public class Pointer {
    private int colIndex;
    private int rowIndex;

    public Pointer(int colIndex, int rowIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    //copy constructor
    public Pointer(Pointer pointer) {
        this.rowIndex = pointer.getRowIndex();
        this.colIndex = pointer.getColIndex();
    }

    public static Pointer of(int colIndex, int rowIndex) {
        if (colIndex > Game.BOARD_SIZE - 1 ||
                colIndex < 0 ||
                rowIndex > Game.BOARD_SIZE - 1 ||
                rowIndex < 0) {
            return null;
        }
        return new Pointer(colIndex, rowIndex);
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pointer))
            return false;

        Pointer pointer = (Pointer) o;
        return rowIndex == pointer.rowIndex && colIndex == pointer.colIndex;
    }

    @Override
    public int hashCode() {
        int result = rowIndex;
        result = 31 * result + colIndex;
        return result;
    }
}
