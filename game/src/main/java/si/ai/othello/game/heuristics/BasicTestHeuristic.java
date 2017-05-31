package si.ai.othello.game.heuristics;

import com.sun.org.apache.xpath.internal.operations.Bool;
import si.ai.othello.game.utils.Pointer;

import static si.ai.othello.game.utils.EvaluationUtil.*;

import java.util.Arrays;

/**
 * @author Jakub Licznerski
 *         Created on 31.05.2017.
 */

//todo refactor or delete
public class BasicTestHeuristic implements EvaluationHeuristic {

    @Override
    public double evaluate(Boolean[][] board, boolean color) {
        int result = 0;
        Pointer pointer = new Pointer(0, 0);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (Boolean.valueOf(color).equals(board[i][j])) {
                    pointer.setColIndex(i);
                    pointer.setRowIndex(j);
                    result += evaluateStaticPositionMap(pointer);
                }
            }
        }
        return result;
    }
}
