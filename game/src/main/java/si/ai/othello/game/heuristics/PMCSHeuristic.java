package si.ai.othello.game.heuristics;

import com.sun.org.apache.xpath.internal.operations.Bool;
import si.ai.othello.game.utils.Pointer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static si.ai.othello.game.utils.BoardUtils.getAvailableMoves;
import static si.ai.othello.game.utils.BoardUtils.getNeighbours;
import static si.ai.othello.game.utils.EvaluationUtil.countCones;

/**
 * @author Jakub Licznerski
 *         Created on 07.06.2017.
 */
public class PMCSHeuristic implements EvaluationHeuristic {

    private final int NO_MOVES_PENALTY = 20;
    private final double CORNERS_WEIGHT = 801.724;
    private final double NEAR_CORNERS_WEIGHT = 382.026;
    private final double STABILITY_WEIGHT = 10;
    private final double MOBILITY_WEIGHT = 78.922;
    private final double PARITY_WEIGHT = 10;

    private final int[][] staticStabilityMap = {
            {20, -3, 11, 8, 8, 11, -3, 20},
            {-3, -7, -4, 1, 1, -4, -7, -3},
            {11, -4, 2, 2, 2, 2, -4, 11},
            {8, 1, 2, -3, -3, 2, 1, 8},
            {8, 1, 2, -3, -3, 2, 1, 8},
            {11, -4, 2, 2, 2, 2, -4, 11},
            {-3, -7, -4, 1, 1, -4, -7, -3},
            {20, -3, 11, 8, 8, 11, -3, 20}
    };

    @Override
    public double evaluate(Boolean[][] board, boolean color) {
        return parity(board, color) + mobility(board, color) + corners(board, color) + stability(board, color);
    }

    @Override
    public double noAvailableMovesEvaluation(Boolean[][] currentBoard, boolean playerColor, boolean currentColor) {
//        if (isNoEmptyConeLeft(currentBoard)) //-> game ended
//            return evaluate(currentBoard, playerColor);
//        //no more moves for the player (not full board)
//        //so if its our turn, we lost, if the opponent, we won
//        return playerColor == currentColor ? -NO_MOVES_PENALTY : NO_MOVES_PENALTY;
        return evaluate(currentBoard, playerColor);
    }

    private double parity(Boolean[][] board, boolean color) {
        int cCurr = countCones(board, color);
        int cOpp = countCones(board, !color);

        return (100 * (cCurr - cOpp) / (cCurr + cOpp)) * PARITY_WEIGHT;
    }

    private double mobility(Boolean[][] board, boolean color) {
        double mCurr = getAvailableMoves(board, color).count();
        double mOpp = getAvailableMoves(board, !color).count();

        return mCurr + mOpp != 0 ?
                (100 * (mCurr - mOpp) / (mCurr + mOpp)) * MOBILITY_WEIGHT : 0;
    }

    private double corners(Boolean[][] board, boolean color) {
        //todo implement dynamic corner evaluation (when corner is not taken, punish only diagonal if it will be stable)

        int corCurr = 0;
        int corOpp = 0;

        List<Pointer> nearCorners = new ArrayList<>();
        float nearCornersCount = 0;

        //count corners and
        for (int i = 0; i < 8; i += 7) {
            if (board[i][7 - i] != null) {
                if (board[i][7 - i] == color)
                    corCurr++;
                else
                    corOpp++;

                nearCorners.addAll(getNeighbours(board, Pointer.of(i, 7 - i), false)
                        .collect(Collectors.toList()));
            }

            if (board[i][i] != null) {
                if (board[i][i] == color)
                    corCurr++;
                else
                    corOpp++;

                nearCorners.addAll(getNeighbours(board, Pointer.of(i, i), false)
                        .collect(Collectors.toList()));
            }
        }

        for (Pointer nearCorner : nearCorners) {
            int offset = board[nearCorner.getColIndex()][nearCorner.getRowIndex()] == color? 1 : -1;
            nearCornersCount += offset;
        }


        //todo test for decimal values!
        return (25 * (corCurr - corOpp)) * CORNERS_WEIGHT + (12.5f * nearCornersCount) * NEAR_CORNERS_WEIGHT;
    }

    private double stability(Boolean[][] board, boolean color) {
        double result = 0;
        Pointer pointer = new Pointer(0, 0);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    pointer.setColIndex(i);
                    pointer.setRowIndex(j);
                    double temp = staticStabilityMap[pointer.getColIndex()][pointer.getRowIndex()];
                    result += (board[i][j] == color ? 1 : -1) * temp;
                }
            }
        }
        return result * STABILITY_WEIGHT;
    }
}
