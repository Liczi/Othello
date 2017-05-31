package si.ai.othello.game.utils;

import si.ai.othello.game.Board;
import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 30.05.2017.
 */
public class EvaluationUtil {

    public static final double CORNERS_WEIGHT = 3.0;
    public static final double NO_MOVES_PENALTY = -100;

    //hiding constructor for util class
    private EvaluationUtil() {

    }

    public static double evaluateCones(Boolean[][] board, boolean color) {
        int current = 0;
        for (Boolean[] booleans : board) {
            for (Boolean aBoolean : booleans) {
                if (aBoolean != null) {
                    if (aBoolean == color)
                        current++;
                }
            }
        }
        return current;
    }


//    public static double evaluateOpponentCones(Boolean[][] board, boolean color) {
//        int current = 0;
//        for (Boolean[] booleans : board) {
//            for (Boolean aBoolean : booleans) {
//                if (aBoolean != null) {
//                    if (aBoolean == !color)
//                        current++;
//                }
//            }
//        }
//        return current;
//    }


    public static double evaluateCorners(Boolean[][] board, boolean color) {
        int corners = 0;

        for (int i = 0; i < 8; i += 7) {
            if (Boolean.valueOf(color).equals(board[i][i]))
                corners++;
            if (Boolean.valueOf(color).equals(board[i][7 - i]))
                corners++;
        }

        return corners;
    }


    public static double evaluateAvailableMoves(Board board, boolean color) {
        return board.getAvailableMoves(color).length;
    }


    //todo test this (position dependant)
    //might get negative values
    public static double evaluateStaticPositionMap(Pointer pointer) {
        //distance to center of board
//        double colPos = Math.abs(3.5-pointer.getColIndex());
//        double rowPos = Math.abs(3.5-pointer.getRowIndex());

//        if (colPos >= 3.5 && rowPos >= 3.5)
//            return 99;
//        else if (colPos == 2.5) {
//            if (rowPos == 3.5)
//        }
//        else if (colPos == 1.5) {
//
//        }
//        //(colPos == 0.5)
//        else {
//
//        }

        //todo extract to static class field
        int[][] staticMap = {
                {99, -8, 8, 6, 6, 8, -8, 99},
                {-8, -24, -4, -3, -3, -4, -24, -8},
                {8, -4, 7, 4, 4, 7, -4, 8},
                {6, -3, 4, 0, 0, 4, -3, 6},
                {6, -3, 4, 0, 0, 4, -3, 6},
                {8, -4, 7, 4, 4, 7, -4, 8},
                {-8, -24, -4, -3, -3, -4, -24, -8},
                {99, -8, 8, 6, 6, 8, -8, 99}
        };

        return staticMap[pointer.getColIndex()][pointer.getRowIndex()];
    }


}
