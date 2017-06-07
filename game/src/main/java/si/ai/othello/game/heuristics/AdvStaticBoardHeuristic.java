package si.ai.othello.game.heuristics;

import si.ai.othello.game.utils.Pointer;

import static si.ai.othello.game.utils.BoardUtils.isNoEmptyConeLeft;
import static si.ai.othello.game.utils.EvaluationUtil.evaluateStaticPositionMap;

/**
 * @author Jakub Licznerski
 *         Created on 07.06.2017.
 */
public class AdvStaticBoardHeuristic implements EvaluationHeuristic {
    @Override
    public double evaluate(Boolean[][] board, boolean color) {
        double result = 0;
        Pointer pointer = new Pointer(0, 0);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    pointer.setColIndex(i);
                    pointer.setRowIndex(j);
                    double temp = evaluateStaticPositionMap(pointer);
                    result += (board[i][j] == color ? 1 : -1) * temp;
                }
            }
        }
        return result;
    }

    @Override
    public double noAvailableMovesEvaluation(Boolean[][] currentBoard, boolean playerColor, boolean currentColor) {
        if (isNoEmptyConeLeft(currentBoard)) //-> game ended
            return evaluate(currentBoard, playerColor);
        //no more moves for the player (not full board)
        //so if its our turn, we lost, if the opponent, we won
        return playerColor == currentColor ? -5 : 5;


//        if (isNoEmptyConeLeft(currentBoard)) //-> game ended
//                return heuristic.evaluate(currentBoard, this.color);
//            //no more moves for the player (not full board)
//            //so if its our turn, we lost, if the opponent, we won
//            //return currentColor == this.color ? Integer.MIN_VALUE + 1 : Integer.MAX_VALUE - 1;
//            return this.color == currentColor ? NO_MOVES_PENALTY : -NO_MOVES_PENALTY;
    }
}
