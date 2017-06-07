package si.ai.othello.game.heuristics;

/**
 * @author Jakub Licznerski
 *         Created on 31.05.2017.
 */
public interface EvaluationHeuristic {

    double evaluate(Boolean[][] board, boolean color);

    double noAvailableMovesEvaluation(Boolean[][] currentBoard, boolean playerColor, boolean currentColor);
}
