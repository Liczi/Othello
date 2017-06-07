package si.ai.othello.game.heuristics;

import static si.ai.othello.game.utils.BoardUtils.isNoEmptyConeLeft;

/**
 * @author Jakub Licznerski
 *         Created on 07.06.2017.
 */
public class MixedHeuristic implements EvaluationHeuristic {
    @Override
    public double evaluate(Boolean[][] board, boolean color) {
        StaticBoardHeuristic heuristic = new StaticBoardHeuristic();
        return heuristic.evaluate(board, color);
    }

    @Override
    public double noAvailableMovesEvaluation(Boolean[][] currentBoard, boolean playerColor, boolean currentColor) {
        //todo ???
        if (isNoEmptyConeLeft(currentBoard)) //-> game ended
            return evaluate(currentBoard, playerColor);
        //no more moves for the player (not full board)
        //so if its our turn, we lost, if the opponent, we won
        return playerColor == currentColor ? -5 : 5;
    }

}
