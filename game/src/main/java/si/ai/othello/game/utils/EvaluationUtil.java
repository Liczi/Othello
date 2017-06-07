package si.ai.othello.game.utils;

import si.ai.othello.game.Board;
import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 30.05.2017.
 */
public class EvaluationUtil {

    private static final int[][] staticMap = {
            {4, -3, 2, 2, 2, 2, -3, 4},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            {2, -1, 1, 0, 0, 1, -1, 2},
            {2, -1, 0, 1, 1, 0, -1, 2},
            {2, -1, 0, 1, 1, 0, -1, 2},
            {2, -1, 1, 0, 0, 1, -1, 2},
            {-3, -4, -1, -1, -1, -1, -4, -3},
            {4, -3, 2, 2, 2, 2, -3, 4},
    };

    //hiding constructor for util class
    private EvaluationUtil() {

    }

    public static int countCones(Boolean[][] board, boolean color) {
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

    //might get negative values
    public static double evaluateStaticPositionMap(Pointer pointer) {
        return staticMap[pointer.getColIndex()][pointer.getRowIndex()];
    }


}
